package RainManager;

import RainDrop.RainDrop;
import TestRunners.GdxTestRunner;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by Amaterasu on 2015-03-20.
 */
@RunWith(GdxTestRunner.class)
public class RainManagerTests {
    RainManager validNanoTimeDefaultManager;
    RainManager notValidNanoTimeDefaultManager;
    @Before
    public void Initialize()
    {
        RainDrop.SetTexture(new Texture(1,1, Pixmap.Format.Alpha));

        validNanoTimeDefaultManager = new RainManager(1);
        validNanoTimeDefaultManager.Produce(10);

        notValidNanoTimeDefaultManager = new RainManager(10);
        notValidNanoTimeDefaultManager.Produce(1);
    }

    @Test
    public void CreateRainManager_ValidFrequency_ExpectGivingFrequency()
    {
        long frequency = 100000;
        long expected = frequency;

        RainManager rm = new RainManager(frequency);

        long actual = rm.frequency;
        Assert.assertEquals(expected,actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void CreateRainManager_NotValidFrequency_ExpectIllegalArgumentException()
    {
        RainManager rm = new RainManager(0);
    }

    @Test
    public void Produce_WhenNanoTimeIsMoreThanFrequency_ExpectNotNullWave()
    {
        RainManager rm = new RainManager(1);

        rm.Produce(10);

        Assert.assertNotNull(rm.wave);
    }

    @Test
    public void CreateRainManager_ValidFrequanecy_ExpectNotNullGenerator()
    {
        RainManager rm = new RainManager(10000);

        Assert.assertNotNull(rm.generator);
    }

    @Test
    public void Produce_WhenNanoTimeIsLessThanFrequency_ExpectNotNullWave()
    {
        RainManager rm = new RainManager(10);

        rm.Produce(1);

        Assert.assertNull(rm.wave);
    }

    @Test
    public void IterateProduce_AllValid_ExpectNotNullCurrentRainDrop()
    {
        validNanoTimeDefaultManager.Iterate();

        Assert.assertNotNull(validNanoTimeDefaultManager.currentRaindrop);
    }

    @Test
    public void IterateProduce_NanoTimeNotValid_ExpectNullCurrentRainDrop()
    {
        Assert.assertNull(validNanoTimeDefaultManager.currentRaindrop);
    }

    @Test
    public void Iterate2XProduce_AllValid_ExpectDiffrentRainDrops()
    {
        RainDrop first;
        RainDrop second;

        validNanoTimeDefaultManager.Iterate();
        first = validNanoTimeDefaultManager.GetCurrent();
        validNanoTimeDefaultManager.Iterate();
        second = validNanoTimeDefaultManager.GetCurrent();

        Assert.assertNotEquals(first,second);
    }

    @Test(expected = NullPointerException.class)
    public void Iterate_NotProduce_ExpectNullPointerException()
    {
        RainManager rm = new RainManager(10);

        rm.Iterate();
    }

    @Test
    public void IterateWithDeleting_RainDropReachedTheLimit_ExpectLessWaveSize() {
        int actual = validNanoTimeDefaultManager.wave.size();
        validNanoTimeDefaultManager.Iterate();

        validNanoTimeDefaultManager.GetCurrent().SetPositon(new Vector2(0, -100));
        for (int i = 0; i < 4; i++)
            validNanoTimeDefaultManager.Iterate();

        int expected = validNanoTimeDefaultManager.wave.size();
        Assert.assertNotEquals(expected, actual);
    }

    @Test
    public void IterateWithDeleting_AllRainDropReachTheLimit_ExpectTrueValueEndFlag() {
        boolean end = false;
        int size= validNanoTimeDefaultManager.wave.size();

        for(int i=0;i<size*2+1;i++) {
            validNanoTimeDefaultManager.Iterate();
            validNanoTimeDefaultManager.GetCurrent().SetPositon(new Vector2(0, -100));
        }

        end = validNanoTimeDefaultManager.Empty();
        Assert.assertTrue(end);
    }

    @Test(expected = NullPointerException.class)
    public void CheckIterator_Null_ExpectException()
    {
        validNanoTimeDefaultManager.CheckIterator(null);
    }
}
