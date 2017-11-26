package models;

import com.google.gson.*;

import java.lang.reflect.Type;

public class AbstractModelAdapter<T> implements JsonSerializer<T>, JsonDeserializer<T> {
    @Override
    public T deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        // Deserialize with concrete implementation
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        JsonElement fields = jsonObject.get("fields");
        String impl = jsonObject.get("impl").getAsString();

        try {
            return jsonDeserializationContext.deserialize(fields, Class.forName("models." + impl));
        } catch (ClassNotFoundException e) {
            throw new JsonParseException("Unknown class" + type);
        }
    }

    @Override
    public JsonElement serialize(T t, Type type, JsonSerializationContext jsonSerializationContext) {
        // Store the implementing class of the problem set so it can be deserialized properly
        JsonObject result = new JsonObject();
        result.add("impl", new JsonPrimitive(t.getClass().getSimpleName()));
        result.add("fields", jsonSerializationContext.serialize(t, t.getClass()));
        return result;
    }
}
