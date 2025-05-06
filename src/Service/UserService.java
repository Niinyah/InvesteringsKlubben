package Service;

import Model.User;
import Repository.IUserRepository;

import java.util.ArrayList;
import java.util.List;

public class UserService implements Service.IUserService {
    private final IUserRepository userRepository;

    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;

    }
    // metode skal kigge på navn og give userid der hører til.
    @Override
    public String getUserID(String fullName) {
        List<User> users = userRepository.getUsers();
        String userID = "";
        for (User user : users) {
            if (fullName.equals(user.getFullName())) {
                userID = user.getUserID();
            }
        }
        return userID;
    }
    // metode der returner user.getFullName
    @Override
    public String getFullName(String userID) {
        List<User> users = userRepository.getUsers();
        String fullName = "";
        for (User user : users) {
            if (userID.equals(user.getUserID())) {
                fullName = user.getFullName();
            }
        }
        return fullName;
    }
    // metode der returner user.getInitialCash
    @Override
    public double getInitialCash(String userID) {
        List<User> users = userRepository.getUsers();
        double initialCash = 0;
        for (User user : users) {
            if (userID.equals(user.getUserID())) {
                initialCash = user.getInitialCashDKK();
            }
        }
        return initialCash;
    }

    public List<String> getAllUserIDs(){
        List<User> users = userRepository.getUsers();
        List<String> userIDs = new ArrayList<>();

        for (User user : users){
            userIDs.add(user.getUserID());
        }
        return userIDs;
    }
}
