
package kc.domain.repository;


import com.fasterxml.jackson.databind.MappingIterator;
import kc.domain.entity.Asset;
import kc.domain.settings.JacksonConfiguration;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@Slf4j
public class AssetRepository {



    @Autowired
    private RestHighLevelClient restclient;
    @Autowired
    private JacksonConfiguration jackson;




    @SneakyThrows
    public <S extends Asset> S save(S s) {

        IndexRequest indexRequest = new IndexRequest("asset");
        indexRequest.id(s.getId());
        indexRequest.source(jackson.objectMapper().writeValueAsString(s), XContentType.JSON);
        IndexResponse index = restclient.index(indexRequest, RequestOptions.DEFAULT);

        // TODO: 16/07/2020 change return

        return s;
    }


    @SneakyThrows
    public List<Asset> findAll() {
        SearchRequest searchRequest = new SearchRequest("asset");

        SearchResponse searchResponse =  restclient.search(searchRequest, RequestOptions.DEFAULT);

        SearchHit[] hits = searchResponse.getHits().getHits();

        List<Asset> list=new ArrayList<>();

        for (SearchHit hit : hits) {
            String jsonString=hit.getSourceAsString();
            Asset tmpCl=(Asset )jackson.objectMapper().reader().readValue(jsonString,Asset.class);
            list.add(tmpCl);
        }
        return list;
    }


    @SneakyThrows
    public Optional<Asset> findById(String s) {

        SearchRequest searchRequest = new SearchRequest("asset");

        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

        sourceBuilder.query(QueryBuilders.termQuery("id", s));
                searchRequest.source(sourceBuilder);
        SearchResponse searchResponse = restclient.search(searchRequest,RequestOptions.DEFAULT);

        SearchHit[] hits = searchResponse.getHits().getHits();

        List<Asset> list=new ArrayList<>();

        for (SearchHit hit : hits) {
            String jsonString=hit.getSourceAsString();
            Asset tmpCl=(Asset )jackson.objectMapper().reader().readValue(jsonString,Asset.class);
            list.add(tmpCl);
            System.out.println(tmpCl.getCategory());
        }

        return Optional.ofNullable(list.get(0));
    }


}

