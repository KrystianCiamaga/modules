
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
import org.springframework.data.domain.Sort;
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


    @Autowired
    private  AssetRepository assetRepository;



    @Override
    public Asset addAsset(Asset asset) throws IOException {

        return  assetRepository.save(asset);

    }


  @Override
    public List<Asset> findAllAssets() {

        return assetRepository.findAll();
    }

    @Override
    public Asset findAssetById(String id) {

        Optional<Asset> asset =assetRepository.findById(id);

        return asset.orElse(null);


    }

    @Override
    public void deleteAsset(String id) throws IOException {

      assetRepository.delete(id);


    }

    @Override
    public Asset updateAssetById(Asset asset,String id) {
        Optional<Asset> newAasset = Optional.ofNullable(assetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Asset not found")));


        assetRepository.save(newAasset.get());

        return newAasset.get();

    }
}
