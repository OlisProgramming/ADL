package com.thirds.adl.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.thirds.adl.AppDevLanguage;
import com.thirds.adl.interpreter.tokens.exception.TokenizerException;
import com.thirds.adl.util.Naming;

public class TokenizerErrorScreen implements Screen {

    private AppDevLanguage game;

    private OrthographicCamera camera;
    private ScreenViewport viewport;

    private SpriteBatch batch;
    private GlyphLayout glyphLayout;
    private BitmapFont font;

    private String headerText = "";
    private String subtitleText = "";

    public TokenizerErrorScreen(AppDevLanguage game, TokenizerException exception) {

        this.game = game;
        subtitleText = Naming.camelCaseToNewlines(exception.getExceptionName());
    }

    @Override
    public void show() {

        camera = new OrthographicCamera(640, 480);
        viewport = new ScreenViewport(camera);
        viewport.apply();

        batch = new SpriteBatch();
        font = new BitmapFont(Gdx.files.internal("fonts/sinkin.fnt"));
        font.setColor(0.2f, 0.2f, 0.2f, 1.0f);
        glyphLayout = new GlyphLayout();

        Gdx.input.setInputProcessor(new InputProcessor() {

            @Override
            public boolean keyDown(int keycode) { return false; }
            @Override
            public boolean keyUp(int keycode) { return false; }

            @Override
            public boolean keyTyped(char character) {

                if (character == '\n' || character == '\r') {
                    game.setScreen(new MainScreen(game));
                    return true;
                }
                return false;
            }

            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) { return false; }
            @Override
            public boolean touchUp(int screenX, int screenY, int pointer, int button) { return false; }
            @Override
            public boolean touchDragged(int screenX, int screenY, int pointer) { return false; }
            @Override
            public boolean mouseMoved(int screenX, int screenY) { return false; }
            @Override
            public boolean scrolled(int amount) { return false; }
        });
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0.9f, 0.9f, 0.9f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        font.setColor(0.2f, 0.2f, 0.2f, 1.0f);
        glyphLayout.setText(font, "Error while\ntokenizing\nADL code!");
        font.draw(batch, glyphLayout,
                (- glyphLayout.width) / 2,
                (- glyphLayout.height) / 2 + 250);

        font.setColor(0.5f, 0.5f, 0.5f, 1.0f);
        glyphLayout.setText(font, subtitleText);
        font.draw(batch, glyphLayout,
                    (-glyphLayout.width) / 2,
                    (-glyphLayout.height) / 2);

        batch.end();
    }

    @Override
    public void resize(int width, int height) {

        camera.update();
        viewport.update(width, height);
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

    }
}
