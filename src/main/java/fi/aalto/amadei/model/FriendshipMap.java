package fi.aalto.amadei.model;

import java.util.*;

/**
 * Maps friendship between users
 */
public class FriendshipMap {

    // Key data structure is a HashMap (fast to read, fast to write)
    // Key is user, value is a list of friends
    // Since friendship is symmetric, both variation of friendship are recorded: user1 -> [user2], user2 -> [user1]
    // This way it uses 2x more space but is also up to N times faster on searches
    private final Map<String, List<String>> map;

    public FriendshipMap() {
        map = new HashMap<>();
    }

    private void addFriend(String user1, String user2) {
        if(!map.containsKey(user1))
            map.put(user1, new ArrayList<>(Collections.singletonList(user2)));
        else
            map.get(user1).add(user2);
    }

    private void removeFriend(String user1, String user2) {
        if(map.containsKey(user1))
            map.get(user1).remove(user2);
    }

    /**
     * Returns the list of friends of the specified user
     * @param user the specified user
     * @return the list of friends
     */
    public List<String> getFriends(String user) {
        return map.getOrDefault(user, Collections.emptyList());
    }

    /**
     * Creates a friendship between two users (symmetric)
     * @param user1 the first user
     * @param user2 the second user
     */
    public void addFriendship(String user1, String user2) {
        addFriend(user1, user2);
        addFriend(user2, user1);
    }

    /**
     * Removes a friendship between two users (if it exists)
     * @param user1 the first user
     * @param user2 the second user
     */
    public void removeFriendship(String user1, String user2) {
        removeFriend(user1, user2);
        removeFriend(user2, user1);
    }
}
