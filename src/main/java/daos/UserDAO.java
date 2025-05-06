package daos;

import models.User;

import java.util.List;

public interface UserDAO {
    User findById (Integer id);
    List<User> findAll();
    Boolean updateUser (User dto);
    Boolean createUser (User dto);
    Boolean deleteUser (Integer id);
}
