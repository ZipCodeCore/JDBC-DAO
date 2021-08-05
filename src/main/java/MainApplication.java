import com.mysql.cj.jdbc.Driver;
import daos.Engine;
import daos.PhoneRepository;
import models.Phone;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.StringJoiner;

/**
 * @author git-leon
 * @version 1.0.0
 * @date 8/2/21 9:49 AM
 */
public class MainApplication {

    public static void main(String[] args) {
        Engine engine = new Engine();
        engine.registerJDBCDriver();
        Connection mysqlDbConnection = engine.getConnection("mysql");
        PhoneRepository phoneRepository = new PhoneRepository(mysqlDbConnection);
        engine.executeStatement(mysqlDbConnection, "DROP DATABASE IF EXISTS walmart;");
        engine.executeStatement(mysqlDbConnection, "CREATE DATABASE IF NOT EXISTS walmart;");
        engine.executeStatement(mysqlDbConnection, "USE walmart;");
        engine.executeStatement(mysqlDbConnection, new StringBuilder()
                .append("CREATE TABLE IF NOT EXISTS walmart.phone(")
                .append("id int auto_increment primary key,")
                .append("name text not null,")
                .append("color varchar(30) not null,")
                .append("carrier varchar(30),")
                .append("cameras int,")
                .append("price int not null);")
                .toString());

        phoneRepository.create(new Phone(20, "ePhone", "'blue'", "'sprint'",3, 425.0));
        phoneRepository.create(new Phone(21, "strawberry", "'red'", "'tmobile'", 3, 599.0));
        System.out.println(phoneRepository.readAll());

    }
}