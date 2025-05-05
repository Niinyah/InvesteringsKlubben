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
            String line = reader.nextLine();
            while (reader.hasNextLine()) {
                line = reader.nextLine();
                String[] col = line.split(";");

                LocalDate birthDate = LocalDate.parse(col[3]);
                double initialCashDKK = Double.parseDouble(col[4]);
                LocalDate createdAt = LocalDate.parse(col[5]);
                LocalDate lastUpdated = LocalDate.parse(col[6], formatter);


                User user = new User(col[0], col[1], col[2], birthDate, initialCashDKK, createdAt, lastUpdated);
                users.add(user);

            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();

        }
        return users;
    }


}

