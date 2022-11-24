package fi.aalto.amadei.beans;

import fi.aalto.amadei.model.FriendshipMap;
import fi.aalto.amadei.model.StateStorage;

/**
 * Makes two users friends with each other (symmetric)
 */
public class FriendRequest implements Request {

    private String user1;
    private String user2;

    public String getUser1() {
        return user1;
    }

    public String getUser2() {
        return user2;
    }

    @Override
    public void execute(FriendshipMap friendshipMap, StateStorage stateStorage) {
        friendshipMap.addFriendship(user1, user2);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FriendRequest that)) return false;

        if (getUser1() != null ? !getUser1().equals(that.getUser1()) : that.getUser1() != null) return false;
        return getUser2() != null ? getUser2().equals(that.getUser2()) : that.getUser2() == null;
    }

    @Override
    public int hashCode() {
        int result = getUser1() != null ? getUser1().hashCode() : 0;
        result = 31 * result + (getUser2() != null ? getUser2().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "FriendRequest{" +
                "user1='" + user1 + '\'' +
                ", user2='" + user2 + '\'' +
                '}';
    }
}
