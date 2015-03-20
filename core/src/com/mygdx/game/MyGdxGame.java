package com.mygdx.game;

import Bucket.Bucket;
import RainDrop.RainDrop;
import RainManager.RainManager;
import WithoutTestRunner.GameSettings.GameSettings;
import WithoutTestRunner.RainMotor.RainMotor;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.TimeUtils;

public class MyGdxGame extends ApplicationAdapter {
    private Texture dropImage;
    private Texture bucketImage;
    private Sound dropSound;
    private Music rainMusic;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Bucket bucket;
    private RainMotor motor;
    private RainManager manager;
    public static GameSettings customSettings = new GameSettings(800,480);

    @Override
    public void create () {
        dropImage = new Texture(Gdx.files.internal("droplet.png"));
        RainDrop.SetTexture(dropImage);

        bucketImage = new Texture(Gdx.files.internal("bucket.png"));
        bucket = new Bucket(bucketImage);

        motor = new RainMotor(5);

        manager = new RainManager(TimeUtils.nanoTime());

        int horizontalCenterWidnow = customSettings.GetHorizontalCenter();
        int horizontalCenter = bucket.GetHorizontalCenter();
        bucket.SetPositon(new Vector2(horizontalCenterWidnow-horizontalCenter,0));

        dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));
        rainMusic = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));

        rainMusic.setLooping(true);
        rainMusic.play();

        camera = new OrthographicCamera();
        int cameraWidth = customSettings.GetWindowWidth();
        int cameraHeight = customSettings.GetWindowHeight();
        camera.setToOrtho(false,cameraWidth,cameraHeight);

        batch = new SpriteBatch();
    }

    private RainDrop rd = new RainDrop(new Vector2(100,400));

    @Override
    public void render () {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        manager.Produce(TimeUtils.nanoTime());
        manager.Iterate();
        motor.Update(manager.GetCurrent(), Gdx.graphics.getDeltaTime());
        manager.Draw(batch);
        bucket.Draw(batch);
        batch.end();

        if(Gdx.input.isTouched())
        {
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(),Gdx.input.getY(),0);
            camera.unproject(touchPos);
            bucket.SetPositon(new Vector2((int) touchPos.x - bucket.GetHorizontalCenter(), 0));
        }
        else
        {
            Vector2 limitation;
            if(bucket.GetPosition().x < 0) {
                limitation = new Vector2(0, 0);
                bucket.SetPositon(limitation);
            }
            if(bucket.GetPosition().x > customSettings.GetWindowWidth() - bucket.GetBouds().width) {
                limitation = new Vector2(customSettings.GetWindowWidth() - bucket.GetBouds().width, 0);
                bucket.SetPositon(limitation);
            }
            Vector2 relativeMove = new Vector2(200 * Gdx.graphics.getDeltaTime(),0);
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                Vector2 tmp = relativeMove;
                tmp.scl(-1);
                bucket.MoveRelative(tmp);
            }
            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                bucket.MoveRelative(relativeMove);
            }
        }
    }
}
