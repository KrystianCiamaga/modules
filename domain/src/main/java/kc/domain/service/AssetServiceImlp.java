
package kc.domain.service;

import kc.domain.entity.Asset;
import kc.domain.repository.AssetRepository;
import kc.domain.settings.JacksonConfiguration;
import lombok.NoArgsConstructor;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
@NoArgsConstructor
public class AssetServiceImlp implements AssetService {



    private  AssetRepository assetRepository;
   private  ElasticsearchOperations elasticsearchOperations;

    public AssetServiceImlp(AssetRepository assetRepository, ElasticsearchOperations elasticsearchOperations) {
        this.assetRepository = assetRepository;
        this.elasticsearchOperations = elasticsearchOperations;
    }



    @Override
    public String addAsset(Asset asset) throws IOException {

        IndexQuery indexQuery = new IndexQueryBuilder()
                .withId(asset.getId())
                .withObject(asset)
                .build();



        return indexQuery.getId();

    }


    public AssetServiceImlp(AssetRepository assetRepository) {
        this.assetRepository = assetRepository;
    }


  @Override
    public List<Asset> findAllAssets(int pageNumber, int pageSize) {


        return assetRepository.findAll(PageRequest.of(pageNumber,pageSize)).stream()
                .collect(Collectors.toList());
    }

    @Override
    public Asset findAssetById(String id) {

        Optional<Asset> asset =assetRepository.findById(id);

        return asset.orElse(null);


    }



    @Override
    public Asset deleteAsset(String id) {

        Optional<Asset> asset =assetRepository.findById(id);

        assetRepository.deleteById(id);

        return asset.orElse(null);

    }

    @Override
    public Asset updateAssetById(Asset asset,String id) {
        Optional<Asset> newAasset = Optional.ofNullable(assetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Asset not found")));


        assetRepository.save(newAasset.get());

        return newAasset.get();

    }
}
