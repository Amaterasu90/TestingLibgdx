package com.mygdx.game;

import Bucket.Bucket;
import WithoutTestRunner.GameSettings.GameSettings;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class ExampleGame extends ApplicationAdapter {
	private Texture dropImage;
    private Texture bucketImage;
    private Sound dropSound;
    private Music rainMusic;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Bucket bucket;
    public static GameSettings customSettings = new GameSettings(800,480);

	@Override
	public void create () {
        dropImage = new Texture(Gdx.files.internal("droplet.png"));

        bucketImage = new Texture(Gdx.files.internal("bucket.png"));
        bucket = new Bucket(bucketImage);
        int horizontalCenter = bucket.GetHorizontalCenter();
        bucket.SetPositon(new Vector2(horizontalCenter,20));

        dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.mp3"));
        rainMusic = Gdx.audio.newMusic(Gdx.files.internal("rain.wav"));

        rainMusic.setLooping(true);
        rainMusic.play();

        camera = new OrthographicCamera();
        int cameraWidth = customSettings.GetWindowWidth();
        int cameraHeight = customSettings.GetWindowHeight();
        camera.setToOrtho(false,cameraWidth,cameraHeight);

        batch = new SpriteBatch();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        bucket.Draw(batch);
        batch.end();
	}
}
