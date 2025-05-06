package daos;

import models.Movie;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MovieDTO implements DTO<Movie> {
    public int getId(Movie movie) {
        return movie.getId();
    }

    public static Integer getIdOfLast(){
        Connection conn = ConnectionFactory.getConnection();

        try{
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT id FROM Movies ORDER BY id DESC LIMIT 1;");
            if(rs.next()) return rs.getInt("id");
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return null;
    }
}
