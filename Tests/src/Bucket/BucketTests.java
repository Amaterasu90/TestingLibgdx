package Bucket;

import TestRunners.GdxTestRunner;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(GdxTestRunner.class)
public class BucketTests {
    private Bucket bucket;
    private Texture defaultTexture;

    @Before
    public void Initialize(){

        bucket = new Bucket();
        defaultTexture = new Texture(1,1, Pixmap.Format.Alpha);
    }
    @Test
    public void CreateBucket_ValidPosition11_ExpectPositon11(){
        Vector2 v = new Vector2(1,1);
        Vector2 expected = v;

        Bucket b = Bucket.CreatePosition(v);

        Vector2 actual = b.GetPosition();
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void CreateBucket_ValidRectangle_ExpectRectangle(){
        Rectangle r = new Rectangle(0,0,2,2);
        Rectangle expected = r;

        Bucket b = Bucket.CreateBoudns(r);

        Rectangle actual = b.GetBouds();
        Assert.assertEquals(expected,actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void CreateBucket_NotValidRectangleWidth_ExpectIllegalArgumentException() throws IllegalArgumentException
    {
        Bucket b = new Bucket(new Texture(-1,0, Pixmap.Format.Alpha));
    }

    @Test(expected = IllegalArgumentException.class)
    public void CreateBucket_NotValidRectangleHeight_ExpectedIllegalArgumentException() throws IllegalArgumentException
    {
        Bucket b = Bucket.CreateBoudns(new Rectangle(0,0,1,0));
    }

    @Test(expected = NullPointerException.class)
    public void CreateBucket_NotValidPosition_ExpectNullPointerException()
    {
        Vector2 v = null;
        Bucket b = Bucket.CreatePosition(v);
    }

    @Test(expected = NullPointerException.class)
    public void CreateBucket_NotValidRectangle_ExpectNullPointerException()
    {
        Rectangle r = null;
        Bucket b = Bucket.CreateBoudns(r);
    }

    @Test(expected = NullPointerException.class)
    public void CreateBucket_ValidRectangleNotValidTexture_ExpectNullPointerException()
    {
        Texture t = null;
        Bucket b = new Bucket(t);
    }

    @Test(expected = NullPointerException.class)
    public void CreateManyArgumentsBucket_ValidRectangleNotValidTexture_ExpectNullPointerException()
    {
        Texture t = null;
        Vector2 v = new Vector2(0,0);
        Bucket b = new Bucket(v,t);
    }

    @Test
    public void CreateManyArgumentsBucket_ValidTexture_ExpectCorrectBounds()
    {
        Rectangle defaultBounds = new Rectangle(0,0,1,1);
        Rectangle expected = defaultBounds;
        Vector2 defaultVector = Vector2.Zero;
        Texture defaultTexture = new Texture(1,1, Pixmap.Format.Alpha);

        Bucket b = new Bucket(defaultVector,defaultTexture);

        Rectangle actual = b.GetBouds();
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void CreateManyArgumentBucket_ValidPosition_ExpectCorrectPosition()
    {
        Vector2 defaultVector = Vector2.Zero;
        Vector2 expected = defaultVector;
        Texture texture = defaultTexture;

        Bucket b = new Bucket(defaultVector,texture);

        Vector2 actual = b.GetPosition();
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void GetHorizontalyCenter_ValidBounds_Expect32()
    {
        int expected = 32;
        Texture texture = new Texture(64,1, Pixmap.Format.Alpha);

        Bucket b = new Bucket(texture);

        int actual = b.GetHorizontalCenter();
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void DefaultConstructor_GetPosition_Expect00()
    {
        Vector2 expect = new Vector2(0,0);

        Bucket b = new Bucket();

        Vector2 actual = b.GetPosition();
        Assert.assertEquals(expect,actual);
    }

    @Test
    public void DefaultConstructor_GetBounds_Expect0011()
    {
        Rectangle expect = new Rectangle(0,0,1,1);

        Bucket b = new Bucket();

        Rectangle actual = b.GetBouds();
        Assert.assertEquals(expect,actual);
    }


    @Test
    public void SetPositon_ValidPosition_ExpectGivingPositon()
    {
        Vector2 newPosition = new Vector2(10,10);
        Vector2 expected = newPosition;

        bucket.SetPositon(newPosition);

        Vector2 actual = bucket.GetPosition();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void MoveRelative_ValidVector_ExpectChangePosition()
    {
        Vector2 relativeMove = new Vector2(1,1);
        Vector2 expect = bucket.GetPosition().add(relativeMove);

        bucket.MoveRelative(relativeMove);

        Vector2 actual = bucket.GetPosition();
        Assert.assertEquals(expect, actual);
    }

    @Test
    public void DefalutConstructor_GetTexture_ExpectNotNull()
    {
        Bucket b = new Bucket();

        Texture actual = b.view;

        Assert.assertNotNull(actual);
    }

    @Test
    public void CreateMultipleArgumetsBucket_ValidTexture_ExpectGivingTexture()
    {
        Bucket b = new Bucket(Vector2.Zero,defaultTexture);

        Texture expect = defaultTexture;
        Texture actual = b.view;
        Assert.assertEquals(expect,actual);
    }

    @Test(expected = NullPointerException.class)
    public void CreateBucket_NotValidTexture_ExpectNullPointerException()
    {
        Bucket b= new Bucket(null);
    }

    @Test
    public void CreateBucket_ValidTexture_ExpectGivingTexture()
    {
        Texture expect = defaultTexture;

        Bucket b =new Bucket(defaultTexture);

        Texture actual = b.view;
        Assert.assertEquals(expect,actual);
    }

    @Test
    public void DrawOnSprite_ValidSprite_ExpectChangeSpriteBatch(){
        Bucket b = new Bucket();
        SpriteBatch sprite = new SpriteBatch();
        SpriteBatch expected = new SpriteBatch();

        sprite.begin();
        b.Draw(sprite);
        sprite.end();
        int renderCallsActual = sprite.totalRenderCalls;
        int renderCallsExpected = expected.totalRenderCalls;

        boolean result = false;

        if(renderCallsActual!=renderCallsExpected)
            result = true;

        Assert.assertTrue(result);
    }
}