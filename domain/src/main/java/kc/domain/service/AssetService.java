package kc.domain.service;



import kc.domain.entity.Asset;

import java.io.IOException;
import java.util.List;


public interface AssetService {


    List<Asset> findAllAssets();

    Asset findAssetById(String id);

    Asset addAsset(Asset asset) throws IOException;

    void deleteAsset(String id) throws IOException;

    Asset updateAssetById(Asset assetDto, String id);

}

