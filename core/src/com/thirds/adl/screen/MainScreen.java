package com.thirds.adl.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.thirds.adl.AppDevLanguage;

/**
 * Startup screen, shows [New Project] button.
 * @see com.badlogic.gdx.Screen
 * @see com.thirds.adl.screen.NewProjectScreen
 */
public class MainScreen implements Screen {

    private AppDevLanguage game;

    private OrthographicCamera camera;
    private ScreenViewport viewport;
    private Stage stage;

    private SpriteBatch batch;
    private ShapeRenderer shapeRenderer;

    private Texture texLogo;
    private Texture texNewProject;
    private Button btnNewProject;

    private float time = 0.0f;
    private float fillWhiteTime = -1.0f;

    public MainScreen(AppDevLanguage game) {
        this.game = game;
    }

    @Override
    public void show() {

        camera = new OrthographicCamera(640, 480);
        viewport = new ScreenViewport(camera);
        viewport.apply();
        stage = new Stage(viewport);
        Gdx.input.setInputProcessor(stage);

        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();

        texLogo = new Texture("Logo.png");
        texNewProject = new Texture("buttons/NewProj.png");
        btnNewProject = new Button(new TextureRegionDrawable(new TextureRegion(texNewProject)));
        btnNewProject.setX(-1000f);
        btnNewProject.setY(-1000f);
        stage.addActor(btnNewProject);
        btnNewProject.addListener(new InputListener() {
                                      public boolean touchDown(InputEvent event,
                                                               float x, float y,
                                                               int pointer, int button) {
                                          Gdx.app.log("ADL New Project Button", "Pressed");
                                          newProject();
                                          return true;
                                      }
                                  }
        );
    }

    private void newProject() {

        fillWhiteTime = 0.0f;
    }

    @Override
    public void render(float delta) {

        time += delta;
        if (fillWhiteTime != -1.0f) {
            fillWhiteTime += delta*2;
            if (fillWhiteTime > 1.0f) game.setScreen(new NewProjectScreen(game));
        }

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
        } else if (time < 2) {
            batch.draw(texLogo,
                    (Gdx.graphics.getWidth() - texLogo.getWidth()) / 2,
                    (Gdx.graphics.getHeight() - texLogo.getHeight()) / 2);
        } else if (time < 3) {
            float time_modified = time - 2;
            float val = time_modified*time_modified*time_modified;
            batch.draw(texLogo,
                    (Gdx.graphics.getWidth() - texLogo.getWidth()) / 2,
                    (Gdx.graphics.getHeight() - texLogo.getHeight()) / 2 + val*50);
        } else if (time < 4) {
            float time_modified = 4 - time;
            float val = 2 - (time_modified*time_modified*time_modified);
            batch.draw(texLogo,
                    (Gdx.graphics.getWidth() - texLogo.getWidth()) / 2,
                    (Gdx.graphics.getHeight() - texLogo.getHeight()) / 2 + val*50);
        } else if (time < 5) {
            float time_modified = 5 - time;
            float val = time_modified*time_modified*time_modified;
            batch.draw(texLogo,
                    (Gdx.graphics.getWidth() - texLogo.getWidth()) / 2,
                    (Gdx.graphics.getHeight() - texLogo.getHeight()) / 2 + 100);
            btnNewProject.setX((Gdx.graphics.getWidth() - texNewProject.getWidth()) / 2);
            btnNewProject.setY((Gdx.graphics.getHeight() - texNewProject.getHeight()) / 2
                    - val*Gdx.graphics.getHeight()/3 - 150);
        } else {
            /* Finished; go to next screen. */
            batch.draw(texLogo,
                    (Gdx.graphics.getWidth() - texLogo.getWidth()) / 2,
                    (Gdx.graphics.getHeight() - texLogo.getHeight()) / 2 + 100);
            btnNewProject.setX((Gdx.graphics.getWidth() - texNewProject.getWidth()) / 2);
            btnNewProject.setY((Gdx.graphics.getHeight() - texNewProject.getHeight()) / 2
                    - 150);
        }
        batch.end();

        stage.draw();

        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(0.9f, 0.9f, 0.9f, (fillWhiteTime == -1.0f) ? 0 : fillWhiteTime);
        shapeRenderer.rect(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        shapeRenderer.end();
    }

    @Override
    public void resize(int width, int height) {

        camera.update();
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
