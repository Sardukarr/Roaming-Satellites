package inz.satellites.screens;

/**
 * Created by VIT on 2016-10-17.
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;

import inz.satellites.engine.Satellites;
import inz.satellites.utils.Config;


public class GameScreen extends AbstractScreen{

    public GameScreen(Satellites game){
        super(game);
    }

    public void show() {
//



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
