package Repository;

import Model.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserRepository implements IUserRepository {
    @Override
    public List<User> getUsers() {
        File file = new File("src/Data/users.csv");
        List<User> users = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        try {
            Scanner reader = new Scanner(file);
            String txtLine = reader.nextLine();
            while (reader.hasNextLine()) {
                txtLine = reader.nextLine();
                String[] txtLineSplit = txtLine.split(";");

                LocalDate birthDate = LocalDate.parse(txtLineSplit[3], formatter);
                double initialCashDKK = Double.parseDouble(txtLineSplit[4]);
                LocalDate createdAt = LocalDate.parse(txtLineSplit[5], formatter);
                LocalDate lastUpdated = LocalDate.parse(txtLineSplit[6], formatter);


                User user = new User(txtLineSplit[0], txtLineSplit[1], txtLineSplit[2], birthDate, initialCashDKK, createdAt, lastUpdated);
                users.add(user);

            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();

        }
        return users;
    }


}

