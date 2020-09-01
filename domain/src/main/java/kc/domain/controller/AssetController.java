package kc.domain.controller;


import kc.domain.entity.Asset;
import kc.domain.service.AssetServiceImlp;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("assets")
public class AssetController {

    private final AssetServiceImlp assetServiceImlp;


    public AssetController(AssetServiceImlp assetServiceImlp) {
        this.assetServiceImlp = assetServiceImlp;
    }


    @GetMapping()
    @Secured("ROLE_CLIENT")
    public List<Asset> getAllAssets() {
        System.out.println("WSZEDLEM TUTAJ");
        return assetServiceImlp.findAllAssets();
    }

    @GetMapping("/test")
    @Secured("ROLE_CLIENT")
    public String testowy(){
        return "testowy endpoint asset";
    }

    @GetMapping("{id}")
    public Asset getAssetById(@PathVariable String id){

        return assetServiceImlp.findAssetById(id);
    }

    @DeleteMapping("{id}")
    public void deleteAssetById(@PathVariable String id) throws IOException {
        assetServiceImlp.deleteAsset(id);
    }


    @PostMapping
    public Asset addAsset(@RequestBody Asset assetDto) throws IOException {
       return assetServiceImlp.addAsset(assetDto);
    }

    @PutMapping("{id}")
    public void updateAssetById(@RequestBody Asset assetDto, @PathVariable String id){

        assetServiceImlp.updateAssetById(assetDto,id);

    }



}
