package Bucket;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Amaterasu on 2015-03-18.
 */
public class Bucket {
    private Rectangle bounds;
    protected Texture view;

    protected static Bucket CreateBoudns(Rectangle bounds)
    {
        Vector2 position = new Vector2(bounds.x,bounds.y);
        Texture view = new Texture((int)bounds.width,(int)bounds.height, Pixmap.Format.Alpha);
        return new Bucket(position,view);
    }

    protected static Bucket CreatePosition(Vector2 position)
    {
        Texture view = new Texture(1,1, Pixmap.Format.Alpha);
        return new Bucket(position,view);
    }

    public Bucket(Texture texture) {
        view = texture;
        Rectangle tmp = new Rectangle(0,0,texture.getWidth(),texture.getHeight());
        CheckBounds(tmp);
        bounds = tmp;
    }

    public Bucket(Vector2 position,Texture texture)
    {
        CheckTexture(texture);
        view = texture;
        Rectangle tmp = new Rectangle(position.x,position.y,texture.getWidth(),texture.getHeight());
        CheckBounds(tmp);
        bounds = tmp;
    }

    private void CheckTexture(Texture view)
    {
        if(view == null)
            throw new NullPointerException("Texture can't be null.");
        if(view.getWidth() < 0)
            throw new IllegalArgumentException("Texture width can't be less than 0.");
        if(view.getHeight() < 0)
            throw new IllegalArgumentException("Texture height can't be less than 0.");
    }

    private void CheckBounds(Rectangle bounds)
    {
        if(bounds.width < 1)
            throw new IllegalArgumentException("Width of bounds can't be less than 1.");
        if(bounds.height < 1)
            throw new IllegalArgumentException("Height of bounds can't be less than 1.");
    }

    public Bucket() {
        bounds = new Rectangle(0,0,1,1);
        view = new Texture(1,1, Pixmap.Format.Alpha);
    }

    public Vector2 GetPosition() {
        return new Vector2(bounds.x,bounds.y);
    }

    public Rectangle GetBouds() {
        return bounds;
    }

    public int GetHorizontalCenter() {
        return (int)(bounds.width/2);
    }

    public void SetPositon(Vector2 newPosition) {
        bounds.x = (int)newPosition.x;
        bounds.y = (int)newPosition.y;
    }

    public void MoveRelative(Vector2 relativeMove) {
        Vector2 actualPosition = GetPosition();
        Vector2 newPosition = actualPosition.add(relativeMove);
        SetPositon(newPosition);
    }

    public void Draw(SpriteBatch sprite) {
        Vector2 pos = GetPosition();
        sprite.draw(view, pos.x, pos.y);
    }
}
