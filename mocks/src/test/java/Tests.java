import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import kc.domain.entity.Asset;
import kc.domain.repository.AssetRepository;
import kc.domain.settings.JacksonConfiguration;
import kc.mocks.LocalPlatform;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.json.JSONArray;
import org.json.JSONException;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.http.HttpMethod;
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


    static ObjectMapper objectMapper = new ObjectMapper();
    static RestHighLevelClient restClient;

    @BeforeAll
    static void init() {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(MapperFeature.DEFAULT_VIEW_INCLUSION, true);
        objectMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);


        ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo("localhost:9200").build();
        restClient = RestClients.create(clientConfiguration).rest();

    }

    @Test
    public void whenRequestForAllAssetsThenTheAllAssetsShouldBeReturned() throws IOException, InterruptedException{

        //given

        Asset asset1 = new Asset();
        asset1.setId("asset1");
        asset1.setName("name1");
        asset1.setCategory("category1");

        Asset asset2 = new Asset();
        asset2.setId("asset2");
        asset2.setName("name2");
        asset2.setCategory("category2");

        Asset asset3 = new Asset();
        asset3.setId("asset3");
        asset3.setName("name4");
        asset3.setCategory("category3");

        List<Asset> assetList = new ArrayList<>();
        assetList.add(asset1);
        assetList.add(asset2);
        assetList.add(asset3);

        IndexRequest indexRequest = new IndexRequest("assets");

        for (Asset asset : assetList) {
            indexRequest.id(asset.getId());
            indexRequest.source(objectMapper.writeValueAsString(asset), XContentType.JSON);
            restClient.index(indexRequest, RequestOptions.DEFAULT);
        }

        List<String> listOfIDs = new ArrayList<>();
        listOfIDs.add("asset1");
        listOfIDs.add("asset2");
        listOfIDs.add("asset3");


        //when

        HttpClient httpClient = HttpClient.newHttpClient();

        URI urii = URI.create("http://localhost:7775/assets");

        HttpRequest httpRequest = HttpRequest.newBuilder(urii).build();

        HttpResponse<String> response = httpClient.send(httpRequest,
                HttpResponse.BodyHandlers.ofString());


        Asset[] list = objectMapper.readValue(response.body(), Asset[].class);


        //then

        for (Asset x : list) {
            Assert.assertTrue(listOfIDs.contains(x.getId()));
        }


        //deleting data
        for (String id : listOfIDs) {
            DeleteRequest deleteRequest = new DeleteRequest("assets", id);
            restClient.delete(deleteRequest, RequestOptions.DEFAULT);
        }


    }


    @Test
    public void whenRequestForAGivenAssetThenTheAssetShouldBeReturned() throws IOException, InterruptedException {

        //given

        Asset asset1 = new Asset();
        asset1.setId("test2");
        asset1.setName("name");
        asset1.setCategory("category");

        IndexRequest indexRequest = new IndexRequest("asset");
        indexRequest.id(asset1.getId());
        indexRequest.source(objectMapper.writeValueAsString(asset1), XContentType.JSON);
        restClient.index(indexRequest, RequestOptions.DEFAULT);

        //when

        RestTemplate restTemplate = new RestTemplate();
        URI uri = URI.create("http://localhost:7775/assets/test2");
        ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);

        String body = result.getBody();

        Asset asset = objectMapper.reader().readValue(body, Asset.class);

        //then

        Assert.assertEquals(200, result.getStatusCodeValue());
        Assert.assertEquals("test2", asset.getId());
        Assert.assertEquals("name", asset.getName());
        Assert.assertEquals("category", asset.getCategory());


        //deleting data
        DeleteRequest deleteRequest = new DeleteRequest("asset", "test2");
        restClient.delete(deleteRequest, RequestOptions.DEFAULT);
    }


    @Test
    public void whenRequestForDeleteAssetThenTheAssetShouldBeDeleted() throws IOException, InterruptedException {

        //given

        Asset asset1 = new Asset();
        asset1.setId("test2");
        asset1.setName("name2");
        asset1.setCategory("category1111");

        IndexRequest indexRequest = new IndexRequest("assets");
        indexRequest.id(asset1.getId());
        indexRequest.source(objectMapper.writeValueAsString(asset1), XContentType.JSON);
        restClient.index(indexRequest, RequestOptions.DEFAULT);

        //when
/*
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpDelete httpDelete = new HttpDelete("http://localhost:7775/assets/test2");

        ResponseHandler<String> responseHandler = response -> {
            int status = response.getStatusLine().getStatusCode();
            if (status >= 200 && status < 300) {
                HttpEntity entity = response.getEntity();
                return entity != null ? EntityUtils.toString(entity) : null;
            } else {
                throw new ClientProtocolException("Unexpected response status: " + status);
            }
        };
        String responseBody = httpclient.execute(httpDelete, responseHandler);
        System.out.println("----------------------------------------");

*/


        RestTemplate restTemplate = new RestTemplate();


        URI urii = URI.create("http://localhost:7775/assets/test2");

         restTemplate.delete(urii);


        //then

        URI uri2 = URI.create("http://localhost:7775/assets/test2");

        ResponseEntity<String> result = restTemplate.getForEntity(uri2, String.class);

        String body = result.getBody();

        Asset asset = objectMapper.reader().readValue(body, Asset.class);


        System.out.println(asset.getName());
        System.out.println(asset.getCategory());

        Assert.assertNull(asset);





    }

}
