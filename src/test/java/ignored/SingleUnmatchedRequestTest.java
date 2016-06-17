package ignored;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.github.tomakehurst.wiremock.testsupport.WireMockTestClient;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

public class SingleUnmatchedRequestTest {

    @Rule
    public WireMockRule wm = new WireMockRule(options()
        .dynamicPort()
        .withRootDirectory("src/main/resources/empty"));

    WireMockTestClient client;

    @Before
    public void init() {
        client = new WireMockTestClient(wm.port());
    }

    @Test
    public void unmatched() {
        wm.stubFor(get(urlEqualTo("/hit")).willReturn(aResponse().withStatus(200)));
        client.get("/near-misssss");
    }
}