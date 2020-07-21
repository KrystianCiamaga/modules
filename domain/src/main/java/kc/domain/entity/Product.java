package kc.domain.entity;//package krystianciamaga.com.demo.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "products")
public class Product {

    @Id
    private String id;

    private String type;
    private String isin;
    private Double price;
    List<Asset> assets;
    private String contractType;



}
