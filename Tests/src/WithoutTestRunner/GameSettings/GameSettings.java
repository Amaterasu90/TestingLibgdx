package WithoutTestRunner.GameSettings;

/**
 * Created by Amaterasu on 2015-03-18.
 */
public class GameSettings {
    private int windowHeight;
    private int windowWidth;

    public GameSettings(int width, int height) {
        CheckHeight(height);
        CheckWidth(width);
        windowHeight = height;
        windowWidth = width;
    }

    public GameSettings() {
        this(1,1);
    }

    private void CheckHeight(int height)
    {
        if(height<1)
            throw new IllegalArgumentException("Height can't be less than 1.");
    }

    private void CheckWidth(int width)
    {
        if(width<1)
            throw new IllegalArgumentException("Width can't be less than 1.");
    }

    public void SetWindowWidth(int width) {
        CheckWidth(width);
        windowWidth = width;
    }

    public int GetWindowWidth() {
        return windowWidth;
    }

    public void SetWindowHeight(int height) {
        CheckHeight(height);
        windowHeight = height;
    }

    public int GetWindowHeight() {
        return windowHeight;
    }

    public int GetHorizontalCenter() {
        return windowWidth/2;
    }
}
