package kc.domain.repository;

import kc.domain.entity.Asset;
import kc.domain.entity.Product;
import kc.domain.settings.JacksonConfiguration;
import lombok.SneakyThrows;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepository {


    private final JacksonConfiguration jacksonConfiguration;
    private final RestHighLevelClient restHighLevelClient;

    @Autowired
    private AssetRepository assetRepository;

    public ProductRepository(JacksonConfiguration jacksonConfiguration, RestHighLevelClient restHighLevelClient) {
        this.jacksonConfiguration = jacksonConfiguration;
        this.restHighLevelClient = restHighLevelClient;
    }

    @SneakyThrows
    public Product save(Product product){

        IndexRequest indexRequest = new IndexRequest("products");
        indexRequest.id(product.getId());
        indexRequest.source(jacksonConfiguration.objectMapper().writeValueAsString(product) ,XContentType.JSON);
        IndexResponse index = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);

        List<Asset> assetList = product.getAssets();



        for(Asset x:assetList){

            assetRepository.save(x);

        }


        return product;




    }




}
