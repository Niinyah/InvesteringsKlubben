package Service;

import Model.User;
import Repository.IUserRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

    public void addUser(String fullName, double initialCash, String email, String birthDateInString){
        List<User> users = userRepository.getUsers();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String userIDFromLastUser = users.getLast().getUserID();
        int userID = Integer.parseInt(userIDFromLastUser) + 1;
        String lastUserID = userID + "";

        LocalDate birthDate = LocalDate.parse(birthDateInString, formatter);
        LocalDate createdAt = LocalDate.now();
        LocalDate lastUpdated = LocalDate.now();

        User user = new User(lastUserID, fullName, email, birthDate, initialCash, createdAt, lastUpdated);
        userRepository.writeUserToCsv(user);
    }
}
