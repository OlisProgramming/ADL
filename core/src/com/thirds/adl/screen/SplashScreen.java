package com.thirds.adl.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class SplashScreen implements Screen {

    private OrthographicCamera camera;
    private ScreenViewport viewport;

    private SpriteBatch batch;
    private Texture texLogo;

    private float time;

    @Override
    public void show() {

        camera = new OrthographicCamera(640, 480);
        viewport = new ScreenViewport(camera);
        viewport.apply();

        batch = new SpriteBatch();
        texLogo = new Texture("Logo.png");

        time = 0.0f;
    }

    @Override
    public void render(float delta) {

        time += delta;

        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        if (time < 1) {
            float time_scaled = 1 - (1 - time)*(1 - time);
            batch.draw(texLogo,
                    (Gdx.graphics.getWidth() - time_scaled*texLogo.getWidth()) / 2,
                    (Gdx.graphics.getHeight() - time_scaled*texLogo.getHeight()) / 2,
                    time_scaled*256, time_scaled*256);
        } else {
            batch.draw(texLogo,
                    (Gdx.graphics.getWidth() - texLogo.getWidth()) / 2,
                    (Gdx.graphics.getHeight() - texLogo.getHeight()) / 2);
        }
        batch.end();
    }

    @Override
    public void resize(int width, int height) {

        viewport.update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

        batch.dispose();
        texLogo.dispose();
    }
}
