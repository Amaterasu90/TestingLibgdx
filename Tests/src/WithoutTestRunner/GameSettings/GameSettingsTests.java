package WithoutTestRunner.GameSettings;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GameSettingsTests {
    private GameSettings myGameSettings;

    @Before
    public void Initialize()
    {
        myGameSettings = new GameSettings();

    }
    @Test
    public void GetSetWindowHeight_ValidHeight480_Expects480()
    {
        int height = 480;

        myGameSettings.SetWindowHeight(height);
        int actual = myGameSettings.GetWindowHeight();

        int expects = height;
        Assert.assertEquals(expects,actual);
    }

    @Test
    public void GetSetWindowWidth_ValidWidth800_Expects800()
    {
        int width = 800;

        myGameSettings.SetWindowWidth(width);
        int actual = myGameSettings.GetWindowWidth();

        int expects = width;
        Assert.assertEquals(expects, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void SetWindowHeight_NotValidHeight_ExpectsIllegalArgumentException() throws IllegalArgumentException
    {
        myGameSettings.SetWindowHeight(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void CreateGameSettings_NotValidHeight_ExpectsIllegalArgumentException() throws  IllegalArgumentException
    {
        GameSettings gs = new GameSettings(1,0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void CreateGameSettings_NotValidWidth_ExpectsIllegalArgumentException() throws  IllegalArgumentException
    {
        GameSettings gs = new GameSettings(0,1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void SetWindowWidth_NotValidWidth_ExpectsIllegalArgumentException() throws IllegalArgumentException
    {
        myGameSettings.SetWindowWidth(0);
    }

    @Test
    public void CreateGameSettings_ValidHeight480_ExpectsHeight480()
    {
        int height = 480;
        int width = 800;
        int expects = height;

        GameSettings gs =new GameSettings(width,height);

        int actual = gs.GetWindowHeight();
        Assert.assertEquals(expects,actual);
    }

    @Test
    public void CreateGameSettings_ValidWidth800_ExpectsWidth800()
    {
        int height = 480;
        int width = 800;
        int expects = width;

        GameSettings gs =new GameSettings(width,height);

        int actual = gs.GetWindowWidth();
        Assert.assertEquals(expects,actual);
    }

    @Test
    public void CreateGameSettings_DefaultConstructor_ExpectsHeight1()
    {
        GameSettings gs = new GameSettings();

        int expects = 1;
        int actual = gs.GetWindowHeight();

        Assert.assertEquals(expects,actual);
    }

    @Test
    public void CreateGameSettings_DefaultConstructor_ExpectsWidth1()
    {
        GameSettings gs = new GameSettings();

        int expects = 1;
        int actual = gs.GetWindowWidth();

        Assert.assertEquals(expects,actual);
    }

    @Test
    public void GetHorizontalyCenter_ValidWindowSize_Expect32()
    {
        int width = 800;
        int expected = 800/2;
        GameSettings gs = new GameSettings(width,480);

        int actual = gs.GetHorizontalCenter();

        Assert.assertEquals(expected,actual);
    }
}
