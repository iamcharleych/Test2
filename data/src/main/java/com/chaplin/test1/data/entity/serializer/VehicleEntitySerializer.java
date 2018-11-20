package com.chaplin.test1.data.entity.serializer;

import com.chaplin.test1.data.entity.VehicleEntity;
import com.google.gson.*;

import java.lang.reflect.Type;

public class VehicleEntitySerializer implements JsonDeserializer<VehicleEntity> {

    @Override
    public VehicleEntity deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();

        VehicleEntity vehicle = gson.fromJson(json, VehicleEntity.class);

        if (vehicle != null) {
            if (json.getAsJsonObject().has("coordinates")) {
                JsonArray jsonArray = json.getAsJsonObject().getAsJsonArray("coordinates");
                if (jsonArray != null && jsonArray.size() > 1) {
                    vehicle.setLongitude(jsonArray.get(0).getAsFloat());
                    vehicle.setLatitude(jsonArray.get(1).getAsFloat());
                }
            }
        }

        return vehicle;
    }
}
