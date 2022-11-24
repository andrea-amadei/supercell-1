package fi.aalto.amadei.utils;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import fi.aalto.amadei.beans.FriendRequest;
import fi.aalto.amadei.beans.Request;
import fi.aalto.amadei.beans.UnfriendRequest;
import fi.aalto.amadei.beans.UpdateRequest;

import java.lang.reflect.Type;

/**
 * Allows for deserialization of different types of requests
 */
public class RequestAdapter implements JsonDeserializer<Request> {

    @Override
    public Request deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        // Get request type and remove field
        String serializedType = jsonElement.getAsJsonObject().get("type").getAsString();
        jsonElement.getAsJsonObject().remove("type");

        // Redirect type to correct request
        return switch (serializedType) {
            case "make_friends" ->  jsonDeserializationContext.deserialize(jsonElement, FriendRequest.class);
            case "del_friends" ->   jsonDeserializationContext.deserialize(jsonElement, UnfriendRequest.class);
            case "update" ->        jsonDeserializationContext.deserialize(jsonElement, UpdateRequest.class);

            default -> throw new JsonParseException("Unknown type \"" + serializedType + "\"");
        };
    }
}
