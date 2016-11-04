package inz.satellites.screens;

/**
 * Created by VIT on 2016-10-17.
 */

import com.badlogic.gdx.Gdx;

import inz.satellites.engine.Satellites;


public class ScoreScreen extends AbstractScreen{

    public ScoreScreen(Satellites game){
        super(game);
    }

    public void show() {
        Gdx.app.log("density", "" + Gdx.graphics.getDensity());
        Gdx.app.log("stage width", "" + stage.getWidth());
        Gdx.app.log("stage height", "" + stage.getHeight());
        Gdx.app.log("screen width", "" + Gdx.graphics.getWidth());
        Gdx.app.log("screen height", "" + Gdx.graphics.getHeight());

        defaultBackground(); // inicjalizacja tla
        stage.addActor(background);
        Gdx.input.setInputProcessor(stage);

    }
}
