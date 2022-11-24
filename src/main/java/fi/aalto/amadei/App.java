package fi.aalto.amadei;


import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Entry point of the application
 * Also does argument checking, error checking and file checking
 */
public class App {

    public static void main(String[] args) {
        // Gets list of arguments
        List<String> arguments = List.of(args);

        // Check if they are valid
        if(arguments.size() != 2) {
            System.out.println("Wrong number of arguments. Usage: -i <path_to_file>");
            return;
        }

        if(!arguments.get(0).equals("-i")) {
            System.out.println("Wrong argument. Usage: -i <path_to_file>");
            return;
        }

        // Check it's a path
        Path path;
        try {
            path = Paths.get(arguments.get(1));
        } catch (InvalidPathException e) {
            System.out.println("Invalid path. Usage: -i <path_to_file>");
            return;
        }

        // Check if the file exists (and it's not a directory)
        File file = path.toFile();
        if(!file.exists() || file.isDirectory()) {
            System.out.println("Invalid file. Usage: -i <path_to_file>");
            return;
        }

        // Check if the file can be opened
        try {
            // Execute all requests
            FileReader.executeAllRequests(file);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to open file. Usage: -i <path_to_file>");
            return;
        }
    }
}
