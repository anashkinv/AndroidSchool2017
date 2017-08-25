package anashkinv.androidschool2017.api.Deserializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.Map;

import anashkinv.androidschool2017.model.Coin;
import anashkinv.androidschool2017.model.CoinList;

public class CoinListDeserializer implements JsonDeserializer<CoinList> {

    @Override
    public CoinList deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();

        CoinList coinList = new CoinList();
        if (jsonObject.has("Response")) {
            coinList.setResponse(jsonObject.get("Response").getAsString());
        }
        if (jsonObject.has("BaseImageUrl")) {
            coinList.setBaseImageUrl(jsonObject.get("BaseImageUrl").getAsString());
        }
        if (jsonObject.has("BaseLinkUrl")) {
            coinList.setBaseLinkUrl(jsonObject.get("BaseLinkUrl").getAsString());
        }

        if (jsonObject.has("Data")) {
            System.out.println(jsonObject.getAsJsonObject("Data"));
            for (Map.Entry<String, JsonElement> coinParams : jsonObject.getAsJsonObject("Data").entrySet()) {
                Coin coin = (Coin) context.deserialize(coinParams.getValue().getAsJsonObject(), Coin.class);
                coin.setBaseImageUrl(coinList.getBaseImageUrl());
                coinList.addCoin(coin);
            }
        }

        return coinList;
    }
}