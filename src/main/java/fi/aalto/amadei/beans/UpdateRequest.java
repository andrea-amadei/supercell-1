package fi.aalto.amadei.beans;

import fi.aalto.amadei.model.FriendshipMap;
import fi.aalto.amadei.model.StateStorage;
import fi.aalto.amadei.utils.Instances;

import java.util.Map;

/**
 * Update a user state and notify all friends
 */
public class UpdateRequest implements Request {

    private String user;
    private long timestamp;
    private Map<String, String> values;

    public String getUser() {
        return user;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public Map<String, String> getValues() {
        return values;
    }

    @Override
    public void execute(FriendshipMap friendshipMap, StateStorage stateStorage) {
        // If a storage update changed the user's state in any way
        if(stateStorage.updateState(user, timestamp, values)) {

            // If the executing user has no friends, don't do anything
            if(friendshipMap.getFriends(user).size() == 0)
                return;

            // Create a new response
            Response response = new Response(friendshipMap.getFriends(user), user, timestamp, stateStorage.getState(user));

            // Stringify it and print it
            System.out.println(Instances.gson().toJson(response));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UpdateRequest that)) return false;

        if (getTimestamp() != that.getTimestamp()) return false;
        if (getUser() != null ? !getUser().equals(that.getUser()) : that.getUser() != null) return false;
        return getValues() != null ? getValues().equals(that.getValues()) : that.getValues() == null;
    }

    @Override
    public int hashCode() {
        int result = getUser() != null ? getUser().hashCode() : 0;
        result = 31 * result + (int) (getTimestamp() ^ (getTimestamp() >>> 32));
        result = 31 * result + (getValues() != null ? getValues().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UpdateRequest{" +
                "user='" + user + '\'' +
                ", timestamp=" + timestamp +
                ", values=" + values +
                '}';
    }
}
