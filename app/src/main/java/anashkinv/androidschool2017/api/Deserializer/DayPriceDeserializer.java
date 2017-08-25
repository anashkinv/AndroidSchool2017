package anashkinv.androidschool2017.api.Deserializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

import anashkinv.androidschool2017.model.DayPrice;

public class DayPriceDeserializer implements JsonDeserializer<DayPrice> {

    @Override
    public DayPrice deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();

        DayPrice dayPrice = new DayPrice();
        for (JsonElement item : jsonObject.get("Data").getAsJsonArray()) {
            JsonObject object = item.getAsJsonObject();
            dayPrice.addHistory(object.get("time").getAsString(), object.get("close").getAsFloat());
        }

        return dayPrice;
    }
}