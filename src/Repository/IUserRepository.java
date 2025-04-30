package Repository;

import Model.User;

import java.util.List;

public interface IUserRepository {
    List<User> getUsers();
}
