package RainDrop;

import TestRunners.GdxTestRunner;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by Amaterasu on 2015-03-19.
 */
@RunWith(GdxTestRunner.class)
public class RainDropTests {
    RainDrop defaultDrop;
    Texture defaultTexture;
    RainDrop customTwoArgumentDrop;
    final Vector2 customPositon = new Vector2(1,1);
    final float customMass = 10.0f;
    @Before
    public void Initialize()
    {
        defaultDrop = new RainDrop();
        defaultTexture = new Texture(1,1, Pixmap.Format.Alpha);
        customTwoArgumentDrop = new RainDrop(customPositon,customMass);
    }
    @Test
    public void CreateRainDrop_ValidPosition_ExpectPosition()
    {
        Vector2 position = Vector2.Zero;
        Vector2 expect = position;

        RainDrop rd = new RainDrop(position);

        Vector2 actual = rd.GetPosition();
        Assert.assertEquals(expect, actual);
    }

    @Test
    public void GetPosition_DefaultRainDrop_ExpectProperPositon()
    {
        RainDrop rd = new RainDrop();

        Vector2 actual = rd.GetPosition();

        Assert.assertNotNull(actual);
    }

    @Test
    public void SetPositionDefaultRainDrop_ValidPosition_ExpectPosition()
    {
        Vector2 positon = new Vector2(1,1);
        Vector2 expect = positon;
        RainDrop rd = new RainDrop();

        rd.SetPositon(positon);

        Vector2 actual = rd.GetPosition();
        Assert.assertEquals(expect, actual);
    }

    @Test(expected = NullPointerException.class)
    public void SetPositionDefaultRainDrop_NullPosition_ExpectNullPointerException()
    {
        RainDrop rd = new RainDrop();
        rd.SetPositon(null);
    }

    @Test(expected = NullPointerException.class)
    public void CreateRainDrop_NullPosition_ExpectNullPointerException()
    {
        RainDrop rd = RainDrop.CreatePositioning(null);
    }

    @Test
    public void GetPositon_DefaultRainDrop_ExpectedProperPosition()
    {
        Vector2 expected = new Vector2(1,1);

        Vector2 actual = defaultDrop.GetPosition();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void GetTexture_DefaultRainDrop_ExpectedNotNullTexture()
    {
        Texture actual = defaultDrop.GetTexture();

        Assert.assertNotNull(actual);
    }

    @Test
    public void SetStaticTextureGet_ValidTexture_ExepectEquealsTextures()
    {
        RainDrop.SetTexture(defaultTexture);

        RainDrop rd1 = new RainDrop();
        RainDrop rd2 = new RainDrop();

        Assert.assertEquals(rd1.GetTexture(),rd2.GetTexture());
    }

    @Test(expected = NullPointerException.class)
    public void SetStaticTexture_NullTexture_ExpectException()
    {
        RainDrop.SetTexture(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void SetStaticTexture_NotValidTexture_ExpectException()
    {
        RainDrop.SetTexture(new Texture(-1,0, Pixmap.Format.Alpha));
    }

    @Test
    public void CustomTwoArgumentRainDrop_ValidAll_ExpectProperPosition()
    {
        Vector2 expected = customPositon;

        RainDrop rd = customTwoArgumentDrop;

        Vector2 actual  = rd.GetPosition();
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void CustomTwoArgumentRainDrop_ValidAll_ExpectProperMass()
    {
        float expected = customMass;

        RainDrop rd = customTwoArgumentDrop;

        float actual  = rd.GetMass();
        Assert.assertEquals(expected,actual,0.01);
    }

    @Test(expected = IllegalArgumentException.class)
    public void CreateTwoArgumentRainDrop_NotValidMass_ExpectIllegalArgumentException()
    {
        RainDrop rd = new RainDrop(customPositon,-1);
    }

    @Test
    public void SetStaticTextureGetBounds_ValidTexture_ExpectProperBounds()
    {
        Rectangle expected = new Rectangle(defaultDrop.GetPosition().x,defaultDrop.GetPosition().y,
                defaultTexture.getWidth(),defaultTexture.getHeight());

        RainDrop.SetTexture(defaultTexture);

        Rectangle actual = defaultDrop.GetBoudns();
        Assert.assertEquals(expected,actual);
    }
}
