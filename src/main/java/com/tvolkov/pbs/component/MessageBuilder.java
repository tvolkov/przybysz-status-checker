package com.tvolkov.pbs.component;

import com.tvolkov.pbs.configuration.properties.EmailProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

@Component
@RequiredArgsConstructor
@Slf4j
public class MessageBuilder {

    private final EmailProperties properties;

    private static final String TEXT_HTML_UTF8 = "text/html; charset=utf-8";
    public MimeMessage createMessage(String recipient, String title, String body, Session session) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(properties.getSmtp().getFrom(), properties.getSmtp().getFromName()));
        InternetAddress[] recipientAddresses = new InternetAddress[1];
        InternetAddress internetAddress = new InternetAddress(recipient);
        recipientAddresses[0] = internetAddress;
        message.setRecipients(Message.RecipientType.TO, recipientAddresses);
        message.setSubject(title, StandardCharsets.UTF_8.toString());
        message.setContent(body, TEXT_HTML_UTF8);
        return message;
    }
}
