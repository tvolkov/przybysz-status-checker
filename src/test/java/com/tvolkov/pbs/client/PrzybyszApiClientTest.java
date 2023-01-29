package com.tvolkov.pbs.client;

import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@AutoConfigureWireMock(port = 0)
@TestPropertySource(properties = {"przybysz.baseUrl=http://localhost:${wiremock.server.port}"})
class PrzybyszApiClientTest {

    @Autowired
    private PrzybyszApiClient przybyszApiClient;

    //todo add test for applications endpoint
    @Test
    void test_get_stages() {
        WireMock.stubFor(WireMock.get(urlPathEqualTo("/dict/stages")).willReturn(aResponse()
                .withStatus(200)
                .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .withBody("""
                        [{"synonym":"Przyj\\u0119to w Urz\\u0119dzie","id":2,"name":"Przyj\\u0119to w Urz\\u0119dzie"},{"synonym":"Wyznaczono inspektora","id":3,"name":"Dekretacja"},{"synonym":"Weryfikacja wniosku","id":4,"name":"Weryfikacja wniosku"},{"synonym":"Pismo w sprawie \\u2013 braki formalne","id":5,"name":"Pismo w sprawie \\u2013 braki formalne"},{"synonym":"Pismo w sprawie \\u2013 wszcz\\u0119cie post\\u0119powania","id":6,"name":"Pismo w sprawie \\u2013 wszcz\\u0119cie post\\u0119powania"},{"synonym":"Ponowne wezwanie","id":7,"name":"Ponowne wezwanie"},{"synonym":"Post\\u0119powanie w toku","id":9,"name":"Post\\u0119powanie w toku"},{"synonym":"Post\\u0119powanie w toku","id":10,"name":"Projekt decyzji"},{"synonym":"Zako\\u0144czenie post\\u0119powania","id":11,"name":"Zako\\u0144czenie post\\u0119powania"},{"synonym":"Karta pobytu do odbioru","id":13,"name":"Karta pobytu do odbioru"},{"synonym":"Zlecenie personalizacji karty pobytu","id":12,"name":"Zlecenie personalizacji karty pobytu"},{"synonym":"Rejestracja wniosku","id":1,"name":"Rejestracja wniosku"},{"synonym":"Zawieszenie post\\u0119powania","id":17,"name":"Zawieszenie post\\u0119powania na wniosek Strony"},{"synonym":"Zawieszenie post\\u0119powania z urz\\u0119du","id":16,"name":"Zawieszenie post\\u0119powania z urz\\u0119du"},{"synonym":"Karta odebrana","id":18,"name":"Karta odebrana"},{"synonym":"Pismo w sprawie","id":19,"name":"Pismo w sprawie"}]%
                        """)));

        var stages = przybyszApiClient.getStages("");
        assertEquals(16, stages.getStages().size());
    }
}