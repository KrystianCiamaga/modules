
package kc.domain.repository;


import kc.domain.entity.Asset;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface AssetRepository extends ElasticsearchRepository<Asset,String> {


}

