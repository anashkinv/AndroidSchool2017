package anashkinv.androidschool2017.api.Deserializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.Map;

import anashkinv.androidschool2017.model.Price;

public class PriceDeserializer implements JsonDeserializer<Price> {

    @Override
    public Price deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();

        Price price = new Price();
        for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
            price.setSymbol(entry.getKey());

            for (Map.Entry<String, JsonElement> param : entry.getValue().getAsJsonObject().entrySet()) {
                switch (param.getKey()) {
                    case "BTC":
                        price.setBtc(param.getValue().getAsDouble());
                        break;
                    case "USD":
                        price.setUsd(param.getValue().getAsDouble());
                        break;
                    case "EUR":
                        price.setEur(param.getValue().getAsDouble());
                        break;
                }
            }

            // only one element
            break;
        }

        return price;
    }
}