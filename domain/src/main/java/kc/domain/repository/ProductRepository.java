package kc.domain.repository;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.module.SimpleModule;
import kc.domain.entity.Asset;
import kc.domain.entity.Product;
import kc.domain.serializer.ProductSerializer;
import kc.domain.service.AssetServiceImlp;
import kc.domain.settings.JacksonConfiguration;
import lombok.SneakyThrows;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.TermQuery;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class ProductRepository {


    private final JacksonConfiguration jacksonConfiguration;
    private final RestHighLevelClient restHighLevelClient;


    @Autowired
    private AssetServiceImlp assetServiceImlp;

    public ProductRepository(JacksonConfiguration jacksonConfiguration, RestHighLevelClient restHighLevelClient) {
        this.jacksonConfiguration = jacksonConfiguration;
        this.restHighLevelClient = restHighLevelClient;
    }

    @SneakyThrows
    public Product save(Product product) {

        SimpleModule module = new SimpleModule();
        module.addSerializer(Product.class, new ProductSerializer());


        IndexRequest indexRequest = new IndexRequest("products");
        indexRequest.id(product.getId());
        indexRequest.source(jacksonConfiguration.objectMapper().registerModule(module).writeValueAsString(product), XContentType.JSON);
        IndexResponse index = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);

        List<Asset> assetList = product.getAssets();


        return product;

    }

    public Product findById(String id) throws IOException {

        SearchRequest searchRequest = new SearchRequest("products");

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchQuery("id", id));
        searchRequest.source(searchSourceBuilder);

        SearchResponse response = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

        SearchHit[] hits = response.getHits().getHits();

        List<Product> products = new ArrayList<>();


        for (SearchHit hit : hits) {

            String jsonString = hit.getSourceAsString();

            JsonNode node = jacksonConfiguration.objectMapper().readValue(jsonString, JsonNode.class);

            ///////////

            List<String> assetsIDs = node.get("assets").findValuesAsText("assetId");

            List<Asset> assetList = new ArrayList<>();


            SearchRequest searchRequest2 = new SearchRequest("assets");

            SearchSourceBuilder sourceBuilder2 = new SearchSourceBuilder();


            System.out.println(sourceBuilder2.query(QueryBuilders.termsQuery("id",assetsIDs)).toString());




            searchRequest2.source(sourceBuilder2);

            SearchResponse searchResponse = restHighLevelClient.search(searchRequest2,RequestOptions.DEFAULT);

            SearchHit[] hits2 = searchResponse.getHits().getHits();

            for (SearchHit hit2 : hits2) {
                String jsonString2=hit2.getSourceAsString();
                Asset tmpCl = jacksonConfiguration.objectMapper().reader().readValue(jsonString2,Asset.class);
                assetList.add(tmpCl);
                System.out.println(tmpCl.getCategory());
            }



     /*       for (String x : assetsIDs) {
                Asset asset = assetServiceImlp.findAssetById(x);

                assetList.add(asset);

            }*/





            Product product = jacksonConfiguration.objectMapper().readValue(jsonString, Product.class);
            product.setAssets(assetList);
            products.add(product);

        }


        return products.get(0);
    }


}
