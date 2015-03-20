package WithoutTestRunner.RainGenerator;

import RainDrop.RainDrop;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Amaterasu on 2015-03-18.
 */

public class RainGeneratorTests {
    RainGenerator defaultGenerator;
    final int defaultSeed = 1;
    final int defaultAmount = 1;
    @Before
    public void Initialize()
    {
        defaultGenerator = RainGenerator.CreateWithDefaultSeed(defaultAmount);
    }
    @Test
    public void CreateRainGenerator_ValidSimultaneousAmount_ExpectProperAmount()
    {
        int count = 3;
        int expected = count;

        RainGenerator rg = RainGenerator.CreateWithDefaultSeed(count);

        int actual = rg.GetSimultaneousAmount();
        Assert.assertEquals(expected,actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void CreateRainGenerator_NotValidSimultaneousAmount_ExpectIllegalArgumentException()
    {
        RainGenerator rg = RainGenerator.CreateWithDefaultSeed(0);
    }

    @Test
    public void CreateRainGenerator_ValidSeed_ExpectGivenSeed()
    {
        int seed = 10;
        int expected = seed;

        RainGenerator rg = new RainGenerator(1,seed);

        int actual = rg.seed;
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void CreateRainGenerator_ValidSeed_ExpectNotNullRandomObject()
    {
        int seed = 10;

        RainGenerator rg = new RainGenerator(1,seed);

        Random actual = rg.random;
        Assert.assertNotNull(actual);
    }

    @Test
    public void Generate_DefaultRainGenerator_ExpectNotNullPositionsList()
    {
        List<RainDrop> result;

        result = defaultGenerator.Geterate();

        Assert.assertNotNull(result);
    }

    @Test
    public void Generate_DefaultRainGenerator_ExpectProperLength()
    {
        int expect = defaultGenerator.GetSimultaneousAmount();
        List<RainDrop> result;

        result = defaultGenerator.Geterate();

        int actual = result.size();
        Assert.assertEquals(expect,actual);
    }

    @Test
    public void SetRangeGenerateHorizontal_DefaultRainGenerator_ExpectProperVectors()
    {
        boolean pass = true;
        List<RainDrop> result;

        defaultGenerator.SetRange(0,1);
        result = defaultGenerator.Geterate();

        for(RainDrop rd : result) {
            if (rd.GetPosition().x < defaultGenerator.minRange || rd.GetPosition().x > defaultGenerator.maxRange)
                pass = false;
        }

        Assert.assertTrue(pass);
    }

    @Test
    public void DefaultConstructor_GetSeed_ExpectDefaultSeed()
    {
        int expect = defaultSeed;

        RainGenerator def = new RainGenerator();

        int actual = def.seed;
        Assert.assertEquals(expect,actual);
    }

    @Test
    public void DefaultConstructor_GetSimultaneousAmount_ExpectDefaultAmount()
    {
        int expect = defaultAmount;

        RainGenerator def = new RainGenerator();

        int actual = def.GetSimultaneousAmount();
        Assert.assertEquals(expect,actual);
    }

    @Test
    public void SetHeightOfFalling_ValidHeight480_Expect480()
    {
        int height = 480;
        int expect = height;

        RainGenerator.SetHeightOfFalling(height);

        int actual = RainGenerator.height;
        Assert.assertEquals(expect,actual);
    }

    @Test
    public void Generate_DefaultRainGenerator_ExpectAllElementHaveTheSameY() {
        int height = 480;
        RainGenerator.SetHeightOfFalling(height);
        List<Float> expect = new ArrayList<Float>();
        for(int i=0;i<defaultAmount;i++)
            expect.add((float)height);

        List<RainDrop> result = defaultGenerator.Geterate();

        List<Float> actual = new ArrayList<Float>();
        for(int i=0;i<defaultAmount;i++)
            actual.add(result.get(i).GetPosition().y);

        Assert.assertArrayEquals(expect.toArray(),actual.toArray());
    }
}
