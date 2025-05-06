package daos;

import models.Movie;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class MovieDAO implements DAO<Movie> {

    public Movie findById(Integer id) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Movies WHERE id=" + id);

            if (rs.next()) {
                return extractMovieFromResultSet(rs);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public Set<Movie> findAll() {
        Connection conn = ConnectionFactory.getConnection();

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Movies");
            Set movies = new HashSet();

            while(rs.next())
            {
                Movie movie = extractMovieFromResultSet(rs);

                movies.add(movie);
            }
            return movies;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public Boolean update(Integer id, Movie movie) {
        Connection conn = ConnectionFactory.getConnection();

        try{
            PreparedStatement ps = conn.prepareStatement("UPDATE Movies SET title=?, runtime=?, imdbscore=?, releaseyear=? WHERE id=?;");
            ps.setString(1, movie.getTitle());
            ps.setInt(2, movie.getRuntime());
            ps.setInt(3, movie.getImdbscore());
            ps.setInt(4, movie.getReleaseyear());
            ps.setInt(5, id);
            int i = ps.executeUpdate();

            if(i == 1) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public Boolean create(Movie movie) {
        Connection conn = ConnectionFactory.getConnection();

        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO Movies VALUES (NULL, ?, ?, ?, ?);");
            ps.setString(1, movie.getTitle());
            ps.setInt(2, movie.getRuntime());
            ps.setInt(3, movie.getImdbscore());
            ps.setInt(4, movie.getReleaseyear());
            int i = ps.executeUpdate();

            if(i == 1) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public Boolean delete(Integer id) {
        Connection conn = ConnectionFactory.getConnection();

        try {

            Statement stmt = conn.createStatement();
            int i = stmt.executeUpdate("DELETE FROM Movies WHERE id=" + id);

            if(i == 1) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    private Movie extractMovieFromResultSet(ResultSet rs) throws SQLException {
        Movie movie = new Movie();
        movie.setId( rs.getInt("id") );
        movie.setTitle( rs.getString("title") );
        movie.setRuntime( rs.getInt("runtime") );
        movie.setImdbscore( rs.getInt("imdbscore") );
        movie.setReleaseyear( rs.getInt("releaseyear") );
        return movie;
    }
}
