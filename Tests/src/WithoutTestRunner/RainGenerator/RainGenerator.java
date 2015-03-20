package WithoutTestRunner.RainGenerator;

import RainDrop.RainDrop;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Amaterasu on 2015-03-18.
 */
public class RainGenerator {
    protected static int height;
    private int simultaneousAmount;
    protected int seed;
    protected  List<RainDrop> rainDrops;
    protected Random random;
    protected int minRange = 0;
    protected int maxRange = 800;

    public RainGenerator(int amount, int seed) {
        rainDrops = new ArrayList<RainDrop>();
        CheckAmount(amount);
        simultaneousAmount = amount;
        this.seed = seed;
        random = new Random(seed);
    }

    public RainGenerator() {
        this(1,1);
    }

    private void CheckAmount(int simultaneousAmount)
    {
        if(simultaneousAmount<1)
            throw new IllegalArgumentException("Simultaneous Amount can't be less than 1");
    }

    public int GetSimultaneousAmount() {
        return simultaneousAmount;
    }

    protected static RainGenerator CreateWithDefaultSeed(int amount) {
        return new RainGenerator(amount,0);
    }

    public List<RainDrop> Geterate() {

        List<RainDrop> result = new ArrayList<RainDrop>();
        RainDrop rainDrop;
        int random;
        for(int i = 0;i<simultaneousAmount;i++) {
            random = RandomRangeHorizontal();
            rainDrop = new RainDrop(new Vector2(random,400),10);
            result.add(rainDrop);
        }
        return result;
    }

    private int RandomRangeHorizontal() {
        int random = Math.abs(this.random.nextInt()) % maxRange;
        return random;
    }

    public void SetRange(int min, int max) {
        minRange = min;
        maxRange = max;
    }

    public static void SetHeightOfFalling(int height) {
        RainGenerator.height = height;
    }
}
