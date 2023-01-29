package com.tvolkov.pbs.service;

import com.tvolkov.pbs.component.MessageBuilder;
import com.tvolkov.pbs.configuration.properties.EmailProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.mail.Address;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Properties;

import static java.util.stream.Collectors.joining;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {

    private final MessageBuilder messageBuilder;
    private final EmailProperties properties;

    public void sendEmailNotification(String recipient, String title, String body) {
        Session session = createSession();
        try {
            MimeMessage message = messageBuilder.createMessage(recipient, title, body, session);
            sendMessage(session, message);
        } catch (MessagingException | UnsupportedEncodingException e) {
            log.error("Email was not sent, {}.", e.getMessage());
            throw new RuntimeException("Email was not sent", e);
        }
    }

    private boolean sendMessage(Session session, MimeMessage message) throws MessagingException {
        try (Transport transport = session.getTransport()) {
            transport.connect(properties.getSmtp().getHost(), properties.getSmtp().getUsername(), properties.getSmtp().getPassword());
            transport.sendMessage(message, message.getAllRecipients());
            log.info("success send email to recipients {}", Arrays.stream(message.getAllRecipients())
                    .map(Address::toString).collect(joining(",")));

            log.debug("is connected {}, stmp host {}", transport.isConnected(), properties.getSmtp().getHost());
            return true;
        } catch (Exception e) {
            log.error("Сannot send email, transport exception, {}.", e.getMessage());
            throw new RuntimeException("Сannot send email, transport exception", e);
        }
    }

    private Session createSession() {
        Properties props = new Properties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.port", properties.getSmtp().getPort());
        props.put("mail.smtp.starttls.enabled", properties.getSmtp().isStarttlsEnabled());
        props.put("mail.smtp.auth", properties.getSmtp().isAuth());

        return Session.getDefaultInstance(props);
    }
}
