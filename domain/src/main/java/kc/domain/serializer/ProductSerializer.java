package kc.domain.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import kc.domain.entity.Asset;
import kc.domain.entity.Product;

import java.io.IOException;

public class ProductSerializer  extends StdSerializer<Product> {


    public ProductSerializer() {
        this(null);
    }

    public ProductSerializer(Class<Product> t) {
        super(t);
    }

    @Override
    public void serialize(Product product, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("price",product.getPrice());
        jsonGenerator.writeStringField("id",product.getId());
        jsonGenerator.writeStringField("type",product.getType());
        jsonGenerator.writeStringField("isin",product.getIsin());
        jsonGenerator.writeStringField("contractType",product.getContractType());

        jsonGenerator.writeFieldName("assets");
        jsonGenerator.writeStartArray();
        for(Asset asset : product.getAssets()){
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField("assetId",asset.getId());
            jsonGenerator.writeEndObject();
        }
        jsonGenerator.writeEndArray();

        jsonGenerator.writeEndObject();


    }
}
