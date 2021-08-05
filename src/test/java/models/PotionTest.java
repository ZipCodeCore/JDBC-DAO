package models;

import org.junit.Assert;
import org.junit.Test;


public class PotionTest {

    @Test
    public void testConstructor() {
        //given
        Long expectedId = 1L;
        String expectedName = "Restore Magicka";
        String expectedIngredient1 = "briar heart";
        String expectedIngredient2 = "red mountain flower";
        String expectedEffect = "magicka regen";
        //when
        Potion potion = new Potion(expectedId, expectedName, expectedIngredient1, expectedIngredient2, expectedEffect);
        Long actualID = potion.getId();
        String actualName = potion.getName();
        String actualIngredient1 = potion.getIngredient1();
        String actualIngredient2 = potion.getIngredient2();
        String actualEffect = potion.getEffect();
        //then
        Assert.assertEquals(expectedId, actualID);
        Assert.assertEquals(expectedName, actualName);
        Assert.assertEquals(expectedIngredient1, actualIngredient1);
        Assert.assertEquals(expectedIngredient2, actualIngredient2);
        Assert.assertEquals(expectedEffect, actualEffect);

    }

    @Test
    public void testGetId() {
        //given
        Long expected = 3L;
        //when
        Potion potion = new Potion(expected, "Restore Magicka", "briar heart", "red mountain flower", "magicka regen");
        Long actual = potion.getId();
        //then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testSetId() {
        //given
        Long expected = 2L;
        //when
        Potion potion = new Potion();
        potion.setId(expected);
        Long actual = potion.getId();
        //then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetName() {
        //given
        String expected = "Restore Magicka";
        //when
        Potion potion = new Potion(1L, expected, "briar heart", "red mountain flower", "magicka regen");
        String actual = potion.getName();
        //then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testSetName() {
        //given
        String expected = "Restore Magicka";
        //when
        Potion potion = new Potion();
        potion.setName(expected);
        String actual = potion.getName();
        //then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetIngredient1() {
        //given
        String expected = "briar heart";
        //when
        Potion potion = new Potion(1L, "Restore Magicka", expected, "red mountain flower", "magicka regen");
        String actual = potion.getIngredient1();
        //then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testSetIngredient1() {
        //given
        String expected = "briar heart";
        //when
        Potion potion = new Potion();
        potion.setIngredient1(expected);
        String actual = potion.getIngredient1();
        //then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetIngredient2() {
        //given
        String expected = "red mountain flower";
        //when
        Potion potion = new Potion(1L, "Restore Magicka", "briar heart", expected, "magicka regen");
        String actual = potion.getIngredient2();
        //then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testSetIngredient2() {
        //given
        String expected = "red mountain flower";
        //when
        Potion potion = new Potion();
        potion.setIngredient2(expected);
        String actual = potion.getIngredient2();
        //then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetEffect() {
        //given
        String expected = "magicka regen";
        //when
        Potion potion = new Potion(1L, "Restore Magicka", "briar heart", "red mountain flower", expected);
        String actual = potion.getEffect();
        //then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testSetEffect() {
        //given
        String expected = "magicka regen";
        //when
        Potion potion = new Potion();
        potion.setEffect(expected);
        String actual = potion.getEffect();
        //then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testToString() {
        //given
        String expected = "Potion {ID = 1, Name = Restore Magicka, First Ingredient = briar heart, Second Ingredient = red mountain flower, Effects = magicka regen}";
        //when
        Potion potion = new Potion(1L, "Restore Magicka", "briar heart", "red mountain flower", "magicka regen");
        String actual = potion.toString();
        //then
        Assert.assertEquals(expected, actual);
    }
}