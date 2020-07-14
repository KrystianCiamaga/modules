import kc.domain.controller.AssetController;
import kc.domain.entity.Asset;
import kc.mocks.LocalDatabases;
import kc.mocks.LocalPlatform;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.configuration.IMockitoConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.SQLOutput;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith({LocalPlatform.class})
public class Tests {

    static Logger logger=LoggerFactory.getLogger(Tests.class);

@Test
public void getAllAssetsTest() throws IOException, InterruptedException {


    HttpClient httpClient = HttpClient.newHttpClient();

    URI uri = URI.create("http://localhost:7775/assets");

    HttpRequest httpRequest = HttpRequest.newBuilder(uri).build();

    HttpResponse<String> response = httpClient.send(httpRequest,
            HttpResponse.BodyHandlers.ofString());


    System.out.println(response.body());

}

}
