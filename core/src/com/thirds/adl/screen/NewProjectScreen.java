package com.thirds.adl.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.CharArray;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.thirds.adl.AppDevLanguage;

import java.io.IOException;

class NewProjectScreen implements Screen {

    private AppDevLanguage game;

    private OrthographicCamera camera;
    private ScreenViewport viewport;

    private SpriteBatch batch;
    private GlyphLayout glyphLayout;
    private BitmapFont font;

    private String projectName = "";
    private String debugText = "";

    /**
     * 0: Default
     * 1: The user has pressed Enter to selecting the project name
     * 2: The user has confirmed the project name
     */
    private int state = 0;

    NewProjectScreen(AppDevLanguage game) {
        this.game = game;
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

                if (Character.isLetter(character)) {
                    if (state == 0) {
                        if (projectName.equals("")) {
                            projectName += Character.toUpperCase(character);
                        } else {
                            projectName += character;
                        }
                    }
                } else if (character == '\b') {
                    if (state == 0) {
                        if (projectName != null && projectName.length() > 0) {
                            projectName = projectName.substring(0, projectName.length() - 1);
                        }
                    } else if (state == 1) {
                            state = 0;
                    }
                } else if (character == '\n' || character == '\r') {
                    if (state == 0) {
                        state = 1;
                    } else if (state == 1) {
                        state = 2;
                        /* Block that requires synchronisation */
                        Thread thread = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                createFile("Main.adl", "Testing... Testing...\n1, 2, 3...");
                            }
                        });
                        thread.start();
                    }
                }
                return true;
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

    private void createFile(String path, String input) {

        debugText = "Create File: " + path;
        Gdx.app.log("Create File", path);
        FileHandle fileHandle = Gdx.files.external("Documents/ADL/" + projectName + "/" + path);
        try {
            if (!fileHandle.exists()) {
                boolean success = fileHandle.parent().file().mkdirs();
                success |= fileHandle.file().createNewFile();
                if (!success) {
                    debugText = "Error while trying to create new file:\nDocuments/ADL" + projectName + "/" + path;
                    Gdx.app.log("Error while trying to create new file", "Documents/ADL" + projectName + "/" + path);
                }
            }
            fileHandle.writeString(input, false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0.9f, 0.9f, 0.9f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        font.setColor(0.2f, 0.2f, 0.2f, 1.0f);
        switch (state) {
            case 0:
                glyphLayout.setText(font, "Name your ADL project.");
                break;
            case 1:
                glyphLayout.setText(font, "Press Enter to confirm,\nand Backspace to cancel.");
                break;
            case 2:
                glyphLayout.setText(font, "Setting up ADL Project!");
                break;
        }
        font.draw(batch, glyphLayout,
                (- glyphLayout.width) / 2,
                (- glyphLayout.height) / 2 + 200);

        if (state == 0 || state == 1) {

            if (projectName.equals("")) {
                font.setColor(0.5f, 0.5f, 0.5f, 1.0f);
                glyphLayout.setText(font, "ADLProject");
            } else {
                glyphLayout.setText(font, projectName);
            }

            font.draw(batch, glyphLayout,
                    (-glyphLayout.width) / 2,
                    (-glyphLayout.height) / 2 - 100);

        } else if (state == 2) {

            font.setColor(0.5f, 0.5f, 0.5f, 1.0f);
            glyphLayout.setText(font, debugText);
            font.draw(batch, glyphLayout,
                    (-glyphLayout.width) / 2,
                    (-glyphLayout.height) / 2 - 100);
        }
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
