package Service;

public interface IUserService {

    String getUserID (String fullName);
    String getFullName(String userID);
    double getInitialCash(String userID);
}
