package com.thirds.adl.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.thirds.adl.exception.ADLException;
import com.thirds.adl.interpreter.Interpreter;
import com.thirds.adl.interpreter.parse.Parser;
import com.thirds.adl.test.UnexpectedVariableValueTestException;

public class TestScreen implements Screen {

    private int testCount = 0;

    private OrthographicCamera camera;
    private ScreenViewport viewport;

    private SpriteBatch batch;
    private GlyphLayout glyphLayout;
    private BitmapFont font;

    private String testText = "No test\ninitialised yet";

    private Parser testFile(String path) throws ADLException {

        testCount++;
        testText = path;
        return Interpreter.test("tests/" + path + ".adl");
    }

    private void testFileForVariables(String path, String[] variableNames, Object[] expectedValues) throws ADLException {

        Parser parser = testFile(path);
        for (int i = 0; i < variableNames.length; i++) { /* If a variable doesn't match the expected value, return false. */

            Object variable = parser.variableHandler.getVariableValue(variableNames[i]);
            String varString = variable == null ?
                    null : variable.toString();

            Object expectedValue = expectedValues[i];
            String expectedString = expectedValue == null ?
                    null : expectedValue.toString();

            if ((expectedString == null && varString != null)
                    || (varString == null && expectedString != null)
                    || (varString != null && (!(varString.equals(expectedString))))) {
                throw new UnexpectedVariableValueTestException(
                        variableNames[i], variable, expectedValue);
            }
        }
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

        Runnable runnable = () -> {
            try {

                testText = "Running tests in\n3 seconds.";
                Thread.sleep(1000L);
                testText = "Running tests in\n2 seconds.";
                Thread.sleep(1000L);
                testText = "Running tests in\n1 second.";
                Thread.sleep(1000L);

                testFileForVariables("variableAssignment",
                        new String[] {"message", "number"},
                        new Object[] {"Testing.", 1234});

                testFileForVariables("variableDeletion",
                        new String[] {"message", "number"},
                        new Object[] {null, null});

                testFileForVariables("mathExpressions",
                        new String[] {"addition", "longAddition", "constantAdded", "longVariableAddition"},
                        new Object[] {180, 1000, 250, 1530});

                testFileForVariables("setVarToVar",
                        new String[] {"secondText", "secondInt"},
                        new Object[] {"Hello", 100});

                testText = "Passed " + testCount + " tests!";

            } catch (ADLException e) {
                testText = "Test " + testCount + "\n(" + testText + ")\nfailed!";
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        new Thread(runnable).start();
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0.9f, 0.9f, 0.9f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        font.setColor(0.2f, 0.2f, 0.2f, 1.0f);
        glyphLayout.setText(font, "Running Tests...");
        font.draw(batch, glyphLayout,
                (- glyphLayout.width) / 2,
                (- glyphLayout.height) / 2 + 180);

        font.setColor(0.5f, 0.5f, 0.5f, 1.0f);
        glyphLayout.setText(font, Integer.toString(testCount));
        font.draw(batch, glyphLayout,
                    (-glyphLayout.width) / 2,
                    (-glyphLayout.height) / 2 + 30);
        glyphLayout.setText(font, testText);
        font.draw(batch, glyphLayout,
                (-glyphLayout.width) / 2,
                (-glyphLayout.height) / 2 - 40);

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
