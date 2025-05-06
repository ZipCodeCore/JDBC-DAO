package daos;

import models.Movie;
import org.junit.Assert;
import org.junit.Test;

public class TestMovieDao {
    private MovieDAO mdao = new MovieDAO();
    private Movie movie1 = new Movie("Jumanji", 104, 7, 1995);
    private Movie movie2 = new Movie("Bad Boys II", 147, 6, 2003);

    @Test
    public void testFindById() {
        String expected = "Corpse Bride | 120 | 7 | 2005";
        String actual = mdao.findById(1).toString();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testFindAll() {
        Integer expected = 6;
        Integer actual = mdao.findAll().size();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testUpdate() {
        Assert.assertTrue(mdao.create(movie1));
        Integer id = MovieDTO.getIdOfLast();
        mdao.update(id, movie2);
        String expected = movie2.toString();
        String actual = mdao.findById(id).toString();
        Assert.assertEquals(expected, actual);
        mdao.delete(id);
    }

    @Test
    public void testCreate(){
        Assert.assertTrue(mdao.create(movie1));
        Integer id = MovieDTO.getIdOfLast();
        String expected = "Jumanji | 104 | 7 | 1995";
        String actual = mdao.findById(id).toString();
        Assert.assertEquals(expected, actual);
        mdao.delete(id);
    }

    @Test
    public void testDelete() {
        Assert.assertTrue(mdao.create(movie1));
        Integer id = MovieDTO.getIdOfLast();
        Assert.assertTrue(mdao.delete(id));
        Assert.assertEquals(6, mdao.findAll().size());
    }
}
