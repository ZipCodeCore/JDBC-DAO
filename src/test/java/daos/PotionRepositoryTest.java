package daos;

import models.Potion;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;


public class PotionRepositoryTest {


    @Test
    public void testCreate() {
        //given
        Potion potion = new Potion(1L, "Restore Health", "blue mountain flower", "daedra heart", "health regen");
        String expected = potion.toString();
        //when
        Connection connection = main.SQLConnect.getConnection("mysql");
        PotionRepository potionRepository = new PotionRepository(connection);
        potionRepository.create(potion);
        Potion potion1 = potionRepository.findById(1L);
        String actual = potion1.toString();
        //then
        Assert.assertEquals(expected, actual);
        potionRepository.delete(1L);
    }

    @Test
    public void testFindAll() {
        //given
        Potion potion1 = new Potion(1L, "Restore Health", "blue mountain flower", "daedra heart", "health regen");
        Potion potion2 = new Potion(2L, "Restore Magicka", "briar heart", "red mountain flower", "magicka regen");
        List<Potion> list = new ArrayList<>();
        list.add(potion1);
        list.add(potion2);
        String expected = list.toString();
        //when
        Connection connection = main.SQLConnect.getConnection("mysql");
        PotionRepository potionRepository = new PotionRepository(connection);
        potionRepository.create(potion1);
        potionRepository.create(potion2);
        String actual = potionRepository.findAll().toString();
        //then
        Assert.assertEquals(expected, actual);
        potionRepository.delete(1L);
        potionRepository.delete(2L);
    }

    @Test
    public void testFindById() {
        //given
        Potion potion = new Potion(1L, "Restore Health", "blue mountain flower", "daedra heart", "health regen");
        String expected = potion.toString();
        //when
        Connection connection = main.SQLConnect.getConnection("mysql");
        PotionRepository potionRepository = new PotionRepository(connection);
        potionRepository.create(potion);
        Potion potion1 = potionRepository.findById(1L);
        String actual = potion1.toString();
        //then
        Assert.assertEquals(expected, actual);
        potionRepository.delete(1L);

    }

    @Test
    public void testUpdate() {
        //given
        Potion potion = new Potion(1L, "Restore Health", "blue mountain flower", "daedra heart", "health regen");
        Potion newPotion = new Potion(1L, "Restore Magicka", "briar heart", "red mountain flower", "magicka regen");
        String expected = newPotion.toString();
        //when
        Connection connection = main.SQLConnect.getConnection("mysql");
        PotionRepository potionRepository = new PotionRepository(connection);
        potionRepository.create(potion);
        potionRepository.update(1L, newPotion);
        Potion potion1 = potionRepository.findById(1L);
        String actual = potion1.toString();
        //then
        Assert.assertEquals(expected, actual);
        potionRepository.delete(1L);
    }

    @Test
    public void testDeleteByID() {
        //given
        Potion potion1 = new Potion(1L, "Restore Health", "blue mountain flower", "daedra heart", "health regen");
        Potion potion2 = new Potion(2L, "Restore Magicka", "briar heart", "red mountain flower", "magicka regen");
        int expected = 1;
        //when
        Connection connection = main.SQLConnect.getConnection("mysql");
        PotionRepository potionRepository = new PotionRepository(connection);
        potionRepository.create(potion1);
        potionRepository.create(potion2);
        potionRepository.delete(2L);
        int actual = potionRepository.findAll().size();
        //then
        Assert.assertEquals(expected, actual);
        potionRepository.delete(1L);
    }

    @Test
    public void testDeleteByObject() {
        //given
        Potion potion1 = new Potion(1L, "Restore Health", "blue mountain flower", "daedra heart", "health regen");
        Potion potion2 = new Potion(2L, "Restore Magicka", "briar heart", "red mountain flower", "magicka regen");
        int expected = 1;
        //when
        Connection connection = main.SQLConnect.getConnection("mysql");
        PotionRepository potionRepository = new PotionRepository(connection);
        potionRepository.create(potion1);
        potionRepository.create(potion2);
        potionRepository.delete(potion2);
        int actual = potionRepository.findAll().size();
        //then
        Assert.assertEquals(expected, actual);
        potionRepository.delete(1L);
    }
}
