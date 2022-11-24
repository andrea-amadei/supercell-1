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
     * Updates the current state of a user
     * @param user the user whose state is updated
     * @param timestamp the timestamp of the update
     * @param values the new state
     * @return true if the state changed in any way, false otherwise
     */
    public boolean updateState(String user, long timestamp, Map<String, String> values) {
        Set<String> oldKeys = map.getOrDefault(user, Collections.emptyMap()).keySet();
        Map<String, StorageValue> newMap = new HashMap<>();

        // Checks if the state changed in any way
        boolean didStateChange = false;

        for(String k : values.keySet()) {
            if(oldKeys.contains(k)) {
                // Check if the old key-value is older than the new one
                // If it is, replace it with the new one
                if(map.get(user).get(k).getTimestamp() < timestamp) {
                    newMap.put(k, new StorageValue(timestamp, values.get(k)));
                    didStateChange = true;
                }
            }

            // If one of the new key-value didn't exist before, add it
            else {
                newMap.put(k, new StorageValue(timestamp, values.get(k)));
                didStateChange = true;
            }
        }

        // If state changed
        if(didStateChange)
            map.put(user, newMap);

        return didStateChange;
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
