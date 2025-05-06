package daos;

import java.util.Set;

public interface UserDAO {
    Car getCar();
    Car getUserByMakeAndModel();
    boolean insertCar();
    boolean updateCar();
    boolean deleteCar();
}
