package RainDrop;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;


/**
 * Created by Amaterasu on 2015-03-19.
 */
public class RainDrop {
    private final float mass;
    private Vector2 position;
    private static Texture view;

    public RainDrop(Vector2 position) {
        this(position,0);
    }

    public RainDrop() {
        this(new Vector2(1,1),0);
    }

    public RainDrop(Vector2 position, float mass) {
        CheckPosition(position);
        this.position = position;
        CheckMass(mass);
        this.mass = mass;
    }

    private void CheckMass(float mass) {
        if(mass<0)
            throw new IllegalArgumentException("Mass can't be less than 0");
    }

    private static void CheckTexture(Texture view)
    {
        if(view.getWidth() < 1)
            throw new IllegalArgumentException("Width of texture can't be less than 1.");
        if(view.getHeight() < 1)
            throw new IllegalArgumentException("Height of texture can't be less than 1.");
    }

    public Vector2 GetPosition() {
        return position;
    }

    public void SetPositon(Vector2 positon) {
        CheckPosition(positon);
        this.position = positon;
    }

    private void CheckPosition(Vector2 positon) {
        if(positon == null)
            throw new NullPointerException("Position can't be null");
    }

    public Rectangle GetBoudns() {
        return new Rectangle(position.x,position.y,view.getWidth(),view.getHeight());
    }

    protected static RainDrop CreatePositioning(Vector2 position)
    {
        return new RainDrop(position);
    }

    public Texture GetTexture() {
        return view;
    }

    public float GetMass() {
        return mass;
    }

    public static void SetTexture(Texture view) {
        CheckTexture(view);
        RainDrop.view = view;
    }
}
