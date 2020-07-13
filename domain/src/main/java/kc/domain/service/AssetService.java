package kc.domain.service;



import kc.domain.entity.Asset;

import java.io.IOException;
import java.util.List;


public interface AssetService {


    List<Asset> findAllAssets();

    Asset findAssetById(String id);

    String addAsset(Asset asset) throws IOException;

    Asset deleteAsset(String id);

    Asset updateAssetById(Asset assetDto, String id);

}

