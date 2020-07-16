import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import kc.domain.entity.Asset;
import kc.domain.settings.JacksonConfiguration;
import kc.mocks.LocalPlatform;

import org.json.JSONArray;
import org.json.JSONException;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.client.RestTemplate;


import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

@ExtendWith({LocalPlatform.class})
public class Tests {


    static ObjectMapper objectMapper=new ObjectMapper();

    @BeforeAll
    static void init(){
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(MapperFeature.DEFAULT_VIEW_INCLUSION, true);
        objectMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
    }


    @Test
    public void shouldReturnAssetWithCorrectId() throws IOException, InterruptedException {

        URI uri = URI.create("http://localhost:7775/assets/ala");
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);

        String body = result.getBody();

        Asset asset =objectMapper.reader().readValue(body,Asset.class);

        //Verify request succeed
        Assert.assertEquals(200, result.getStatusCodeValue());
        Assert.assertTrue(asset.getId().equals("ala"));
        Assert.assertTrue(asset.getName().equals("AAAnbAA"));
        Assert.assertTrue(asset.getCategory().equals("BBB"));

    }

    @Test
public void getAllAssetsTest() throws IOException, InterruptedException, JSONException {

        List<String> listOfIDs = new ArrayList<>();
        listOfIDs.add("ala");
        listOfIDs.add("132r");
        listOfIDs.add("alGEa");
        listOfIDs.add("alGhbbkEa");


    HttpClient httpClient = HttpClient.newHttpClient();

    URI urii = URI.create("http://localhost:7775/assets");

    HttpRequest httpRequest = HttpRequest.newBuilder(urii).build();

    HttpResponse<String> response = httpClient.send(httpRequest,
            HttpResponse.BodyHandlers.ofString());


       List<Asset> list = Arrays.asList(objectMapper.readValue(response.body(),Asset[].class));


       for(Asset x:list){
           Assert.assertTrue(listOfIDs.contains(x.getId()));
       }

}

}
