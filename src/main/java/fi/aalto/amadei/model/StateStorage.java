package fi.aalto.amadei.model;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Stores the current state of every user
 */
public class StateStorage {

    private final Map<String, Map<String, StorageValue>> map;
    //                User        Key     Value

    public StateStorage() {
        map = new HashMap<>();
    }

    /**
     * Updates the current state of a user and returns all changes
     * @param user the user whose state is updated
     * @param timestamp the timestamp of the update
     * @param values the new state
     * @return a map containing all key and values changed during the update
     */
    public Map<String, String> updateState(String user, long timestamp, Map<String, String> values) {
        Map<String, String> changes = new HashMap<>();

        // If user does not exist, create a new map for him
        if(!map.containsKey(user)) {
            map.put(user, new HashMap<>());
        }

        // For every key-value in the update
        for(String k : values.keySet()) {

            // if map already contained an older key        or
            // if map didn't contain that key
            if((map.get(user).containsKey(k) && map.get(user).get(k).getTimestamp() < timestamp) || !map.get(user).containsKey(k)) {
                // Update the state
                map.get(user).put(k, new StorageValue(timestamp, values.get(k)));

                // Add it to changes
                changes.put(k, values.get(k));
            }
        }

        return changes;
    }

    /**
     * Get the state of a user
     * @param user the user to get the state of
     * @return the state of the user
     */
    public Map<String, String> getState(String user) {
        return map.getOrDefault(user, Collections.emptyMap()).entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, v -> v.getValue().getValue()));
    }
}
