package fi.aalto.amadei;

import fi.aalto.amadei.model.FriendshipMap;
import fi.aalto.amadei.beans.Request;
import fi.aalto.amadei.model.StateStorage;
import fi.aalto.amadei.utils.Instances;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReader {

    /**
     * Reads file line by line, parse requests and executes them
     * @param file The file containing the requests
     * @throws FileNotFoundException if the file does not exist or cannot be opened
     */
    public static void executeAllRequests(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        FriendshipMap friendshipMap = new FriendshipMap();
        StateStorage stateStorage = new StateStorage();

        String line;
        while(scanner.hasNextLine()) {
            line = scanner.nextLine();

            // Get request from file
            Request request;
            try {
                request = Instances.gson().fromJson(line, Request.class);
            } catch (Exception e) {
                System.out.println("File contains invalid syntax");
                scanner.close();
                return;
            }

            // Executes it
            request.execute(friendshipMap, stateStorage);
        }
        scanner.close();
    }
}
