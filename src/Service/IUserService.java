package Service;

import java.util.List;

public interface IUserService {

    String getUserID (String fullName);
    String getFullName(String userID);
    double getInitialCash(String userID);
    List<String> getAllUserIDs();
    void addUser(String fullName, double initialCash, String email, String birthDayInString);
}
