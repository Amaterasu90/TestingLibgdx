package WithoutTestRunner.RainMotor;

import RainDrop.RainDrop;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Amaterasu on 2015-03-20.
 */
public class RainMotor {
    public final float acceleration;

    public RainMotor(float acceleration) {
        CheckAcceleration(acceleration);
        this.acceleration = acceleration;
    }

    private void CheckAcceleration(float acceleration) {
        if(acceleration == 0)
            throw new IllegalArgumentException("Accleration can't be 0 because this will be constant velocity");
    }


    private float DeltaDisplacement(RainDrop drop, float deltaTime)
    {
        float height = drop.GetPosition().y;

        float displacement = (float)Math.sqrt(2*acceleration*height)*deltaTime/2;

        return displacement;
    }

    public void Update(RainDrop defaultRainDrop, float deltaTime) {
        Vector2 oldPosition = defaultRainDrop.GetPosition();

        float deltaDisplacement = DeltaDisplacement(defaultRainDrop, deltaTime);

        Vector2 newPosition = oldPosition.add(new Vector2(0,-deltaDisplacement));
        defaultRainDrop.SetPositon(newPosition);

        defaultRainDrop.SetPositon(newPosition);
    }
}
