package fi.aalto.amadei.beans;

import java.util.List;
import java.util.Map;

/**
 * The response to an update request
 */
public class Response {
    private List<String> broadcast;
    private String user;
    private long timestamp;
    private Map<String, String> values;

    public Response(List<String> broadcast, String user, long timestamp, Map<String, String> values) {
        this.broadcast = broadcast;
        this.user = user;
        this.timestamp = timestamp;
        this.values = values;
    }

    public List<String> getBroadcast() {
        return broadcast;
    }

    public void setBroadcast(List<String> broadcast) {
        this.broadcast = broadcast;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public Map<String, String> getValues() {
        return values;
    }

    public void setValues(Map<String, String> values) {
        this.values = values;
    }
}
