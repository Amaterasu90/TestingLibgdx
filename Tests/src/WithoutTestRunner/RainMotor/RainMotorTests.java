package WithoutTestRunner.RainMotor;

import RainDrop.RainDrop;
import com.badlogic.gdx.math.Vector2;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Amaterasu on 2015-03-20.
 */
public class RainMotorTests {
    final int defaultHeight = 100;
    final int defaultGravity = 1;
    final float defaultMass = 1.0f;
    final float defaultDeltaTime = 2.0f;
    RainDrop defaultRainDrop;
    RainMotor defaultRainMotor;
    @Before
    public void Initialize()
    {
        defaultRainDrop = new RainDrop(new Vector2(0,defaultHeight),defaultMass);
        defaultRainMotor = new RainMotor(defaultGravity);
    }
    @Test
    public void CreateRainMotor_ValidAcceleration_ExpectGivenAcceleration()
    {
        float acceleration = 1;
        float expected = acceleration;

        RainMotor rm = new RainMotor(acceleration);

        float actual = rm.acceleration;
        Assert.assertEquals(expected,actual,0.01);
    }

    @Test(expected = IllegalArgumentException.class)
    public void CreateRainMotor_NotValidAcceleration_ExpectIllegalArgumentException()
    {
        RainMotor rm = new RainMotor(0);
    }

    @Test
    public void Update_DefaultRainDrop_ExpectDiffrentPostionY()
    {
        float actual = defaultRainDrop.GetPosition().y;

        defaultRainMotor.Update(defaultRainDrop,1.0f);

        float expect = defaultRainDrop.GetPosition().y;
        Assert.assertNotEquals(expect,actual,0.1);
    }

    @Test
    public void Update_DefaultRainDropSink_ExpectLessPositionY()
    {
        boolean result = true;
        float oldHeight = defaultRainDrop.GetPosition().y;

        defaultRainMotor.Update(defaultRainDrop,1.0f);

        float newHeight = defaultRainDrop.GetPosition().y;
        if(newHeight>=oldHeight)
            result = false;
        Assert.assertTrue(result);
    }

    @Test
    public void Update2X_DefaultRainDropAccelerateSink_ExpectProperPositionY85()
    {
        int expect = 72;

        defaultRainMotor.Update(defaultRainDrop,defaultDeltaTime);
        defaultRainMotor.Update(defaultRainDrop,defaultDeltaTime);

        int actual = (int)defaultRainDrop.GetPosition().y;
        Assert.assertEquals(expect,actual);
    }
}
