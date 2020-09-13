package kc.domain.controller;



import kc.domain.entity.Asset;
import kc.domain.entity.Product;
import kc.domain.service.AssetServiceImlp;
import kc.domain.settings.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("assets")
@ComponentScan(basePackages = "Jwt2")
public class AssetController {

    private final AssetServiceImlp assetServiceImlp;

    @Autowired
    Producer producer;

/*
    @Autowired
    Consumer consumer;
*/


    public AssetController(AssetServiceImlp assetServiceImlp) {
        this.assetServiceImlp = assetServiceImlp;
    }


    @GetMapping()
    public List<Asset> getAllAssets() {
        System.out.println("WSZEDLEM TUTAJ");
        return assetServiceImlp.findAllAssets();
    }

    @GetMapping("/test")
    @PreAuthorize("hasAuthority('write:read')")
        public String testowy(){

        producer.sendMessage("TESTOWA WIADOMOSC");



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
