package com.tvolkov.pbs.client;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.tvolkov.pbs.dto.StagesResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@AutoConfigureWireMock(port = 0)
@TestPropertySource(properties = {"przybysz.baseUrl=http://localhost:${wiremock.server.port}"})
class PrzybyszApiClientTest {

    @Autowired
    private PrzybyszApiClient przybyszApiClient;

    @Test
    void test_get_stages() {
        WireMock.stubFor(WireMock.get(urlPathEqualTo("/dict/stages")).willReturn(aResponse()
                .withStatus(200)
                .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .withBody("""
                        {
                           "@context":"\\/api\\/v1\\/contexts\\/Stage",
                           "@id":"\\/api\\/v1\\/dict\\/stages",
                           "@type":"hydra:Collection",
                           "hydra:member":[
                              {
                                 "@id":"\\/api\\/v1\\/dict\\/stages\\/2",
                                 "@type":"Stage",
                                 "synonym":"Przyjęto w Urzędzie",
                                 "id":2,
                                 "name":"Przyjęto w Urzędzie"
                              },
                              {
                                 "@id":"\\/api\\/v1\\/dict\\/stages\\/3",
                                 "@type":"Stage",
                                 "synonym":"Wyznaczono inspektora",
                                 "id":3,
                                 "name":"Dekretacja"
                              },
                              {
                                 "@id":"\\/api\\/v1\\/dict\\/stages\\/4",
                                 "@type":"Stage",
                                 "synonym":"Weryfikacja wniosku",
                                 "id":4,
                                 "name":"Weryfikacja wniosku"
                              },
                              {
                                 "@id":"\\/api\\/v1\\/dict\\/stages\\/5",
                                 "@type":"Stage",
                                 "synonym":"Pismo w sprawie – braki formalne",
                                 "id":5,
                                 "name":"Pismo w sprawie – braki formalne"
                              },
                              {
                                 "@id":"\\/api\\/v1\\/dict\\/stages\\/6",
                                 "@type":"Stage",
                                 "synonym":"Pismo w sprawie – wszczęcie postępowania",
                                 "id":6,
                                 "name":"Pismo w sprawie – wszczęcie postępowania"
                              },
                              {
                                 "@id":"\\/api\\/v1\\/dict\\/stages\\/7",
                                 "@type":"Stage",
                                 "synonym":"Ponowne wezwanie",
                                 "id":7,
                                 "name":"Ponowne wezwanie"
                              },
                              {
                                 "@id":"\\/api\\/v1\\/dict\\/stages\\/9",
                                 "@type":"Stage",
                                 "synonym":"Postępowanie w toku",
                                 "id":9,
                                 "name":"Postępowanie w toku"
                              },
                              {
                                 "@id":"\\/api\\/v1\\/dict\\/stages\\/10",
                                 "@type":"Stage",
                                 "synonym":"Postępowanie w toku",
                                 "id":10,
                                 "name":"Projekt decyzji"
                              },
                              {
                                 "@id":"\\/api\\/v1\\/dict\\/stages\\/11",
                                 "@type":"Stage",
                                 "synonym":"Zakończenie postępowania",
                                 "id":11,
                                 "name":"Zakończenie postępowania"
                              },
                              {
                                 "@id":"\\/api\\/v1\\/dict\\/stages\\/13",
                                 "@type":"Stage",
                                 "synonym":"Karta pobytu do odbioru",
                                 "id":13,
                                 "name":"Karta pobytu do odbioru"
                              },
                              {
                                 "@id":"\\/api\\/v1\\/dict\\/stages\\/12",
                                 "@type":"Stage",
                                 "synonym":"Zlecenie personalizacji karty pobytu",
                                 "id":12,
                                 "name":"Zlecenie personalizacji karty pobytu"
                              },
                              {
                                 "@id":"\\/api\\/v1\\/dict\\/stages\\/1",
                                 "@type":"Stage",
                                 "synonym":"Rejestracja wniosku",
                                 "id":1,
                                 "name":"Rejestracja wniosku"
                              },
                              {
                                 "@id":"\\/api\\/v1\\/dict\\/stages\\/17",
                                 "@type":"Stage",
                                 "synonym":"Zawieszenie postępowania",
                                 "id":17,
                                 "name":"Zawieszenie postępowania na wniosek Strony"
                              },
                              {
                                 "@id":"\\/api\\/v1\\/dict\\/stages\\/16",
                                 "@type":"Stage",
                                 "synonym":"Zawieszenie postępowania z urzędu",
                                 "id":16,
                                 "name":"Zawieszenie postępowania z urzędu"
                              },
                              {
                                 "@id":"\\/api\\/v1\\/dict\\/stages\\/18",
                                 "@type":"Stage",
                                 "synonym":"Karta odebrana",
                                 "id":18,
                                 "name":"Karta odebrana"
                              },
                              {
                                 "@id":"\\/api\\/v1\\/dict\\/stages\\/19",
                                 "@type":"Stage",
                                 "synonym":"Pismo w sprawie",
                                 "id":19,
                                 "name":"Pismo w sprawie"
                              }
                           ]
                        }
                        """)));

        StagesResponse stages = przybyszApiClient.getStages("");
        assertTrue(stages.hydraMemberList().size() == 16);
    }
}