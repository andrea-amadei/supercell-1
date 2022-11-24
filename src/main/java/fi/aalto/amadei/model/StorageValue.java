package fi.aalto.amadei.model;

public class StorageValue {
    private long timestamp;
    private String value;

    public StorageValue(long timestamp, String value) {
        this.timestamp = timestamp;
        this.value = value;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StorageValue that)) return false;

        if (getTimestamp() != that.getTimestamp()) return false;
        return getValue() != null ? getValue().equals(that.getValue()) : that.getValue() == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (getTimestamp() ^ (getTimestamp() >>> 32));
        result = 31 * result + (getValue() != null ? getValue().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "StorageValue{" +
                "timestamp=" + timestamp +
                ", value='" + value + '\'' +
                '}';
    }
}
