package RainManager;

import RainDrop.RainDrop;
import WithoutTestRunner.RainGenerator.RainGenerator;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Amaterasu on 2015-03-20.
 */
public class RainManager {
    protected long frequency;
    protected List<RainDrop> wave = new ArrayList<RainDrop>();
    protected final RainGenerator generator;
    protected RainDrop currentRaindrop;
    private boolean empty;

    public RainManager(long frequency) {
        CheckFrequency(frequency);
        this.generator = new RainGenerator(3,10);
        this.frequency = frequency;
    }

    private void CheckFrequency(long frequency) {
        if(frequency<1)
            throw new IllegalArgumentException("Frequency can't be less than 1.");
    }

    private Iterator<RainDrop> iterator;
    public void Produce(long NanoTime) {
        if(NanoTime>frequency && wave.size() == 0) {
            wave = generator.Geterate();
            iterator = wave.iterator();
        }
        frequency = TimeUtils.nanoTime();
    }

    protected void CheckIterator(Iterator<RainDrop> iterator)
    {
        if(iterator == null)
            throw new NullPointerException("Not made a wave.");
    }

    int counter=0;
    public void Iterate() {


        if (wave.size()!=0) {
            empty = false;
            currentRaindrop = wave.get(counter);
        }
        else
        {
            empty = true;
            counter = 0;
            return;
        }


        if (currentRaindrop.GetPosition().y + currentRaindrop.GetBoudns().height < 0) {
            wave.remove(currentRaindrop);
        }
        else
        {
            counter = (counter + 1) % wave.size();
        }

    }

    public RainDrop GetCurrent() {

        return currentRaindrop;
    }

    public boolean Empty() {
        return empty;
    }

    public void Draw(SpriteBatch batch) {
        for(RainDrop r : wave)
        {
            Vector2 pos = r.GetPosition();
            batch.draw(r.GetTexture(),pos.x,pos.y);
        }
    }
}
