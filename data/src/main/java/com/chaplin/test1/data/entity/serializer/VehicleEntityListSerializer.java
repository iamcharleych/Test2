package com.chaplin.test1.data.entity.serializer;

import com.chaplin.test1.data.entity.VehicleEntity;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class VehicleEntityListSerializer implements JsonDeserializer<List<VehicleEntity>> {

    @Override
    public List<VehicleEntity> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        ArrayList<VehicleEntity> result = new ArrayList<>();

        if (json.isJsonObject() && json.getAsJsonObject().has("placemarks")) {
            JsonArray jsonArray = json.getAsJsonObject().getAsJsonArray("placemarks");

            for (int i = 0, size = jsonArray.size(); i < size; i++) {
                result.add(context.deserialize(jsonArray.get(i), VehicleEntity.class));
            }
        }

        return result;
    }
}
