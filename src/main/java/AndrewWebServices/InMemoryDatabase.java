package AndrewWebServices;

import java.util.HashMap;
import java.util.Map;

/*
 * InMemoryDatabase is a fake for the AndrewWS database which is used to improve test efficiency.
 * Remember, fakes are fully functional classes with simplified implementation.
 * What is the simplest core functionality that we need for a functional database?
 * 
 * Hint: there are two methods you need to implement
 */
public class InMemoryDatabase extends Database /* should there be something here? */ {
    // Implement your fake database here
    private Map<String, Integer> userMap = new HashMap<>();

    // Override getPassword to return the stored password without delay
    @Override
    public int getPassword(String accountName) {
        // Return 0 (or -1) if the user is not in the map
        return userMap.getOrDefault(accountName, 0);
    }

    // A helper method to add a user and password to this fake database
    public void addUser(String accountName, int password) {
        userMap.put(accountName, password);
    }
}