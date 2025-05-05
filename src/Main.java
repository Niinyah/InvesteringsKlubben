import Model.Stock;
import Model.User;
import Repository.IStockMarketRepository;
import Repository.StockMarketRepository;
import Repository.UserRepository;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserRepository userRepository = new UserRepository();
        List<User> users = userRepository.getUsers();

        for(User user : users ) {
        System.out.println(user);

        }
    }
}
