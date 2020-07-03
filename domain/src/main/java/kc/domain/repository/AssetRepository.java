
package kc.domain.repository;


import kc.domain.entity.Asset;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class AssetRepository implements PagingAndSortingRepository<Asset, String> {


    private RestHighLevelClient restclient;

    public AssetRepository(RestHighLevelClient restclient) {
        this.restclient = restclient;
    }

    @Override
    public Iterable<Asset> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Asset> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Asset> S save(S s) {
        return null;
    }

    @Override
    public <S extends Asset> Iterable<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<Asset> findById(String s) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(String s) {
        return false;
    }

    @Override
    public Iterable<Asset> findAll() {
        return null;
    }

    @Override
    public Iterable<Asset> findAllById(Iterable<String> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(String s) {

    }

    @Override
    public void delete(Asset asset) {

    }

    @Override
    public void deleteAll(Iterable<? extends Asset> iterable) {

    }

    @Override
    public void deleteAll() {

    }
}

