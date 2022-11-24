package fi.aalto.amadei.beans;

import fi.aalto.amadei.model.FriendshipMap;
import fi.aalto.amadei.model.StateStorage;

/**
 * Generic request
 */
public interface Request {

    /**
     * Executes the request. To be implemented by requests!
     * @param friendshipMap the Friendship Map instance (where friendships are stored)
     * @param stateStorage the State Storage instance (where every user's state is stored)
     */
    void execute(FriendshipMap friendshipMap, StateStorage stateStorage);
}
