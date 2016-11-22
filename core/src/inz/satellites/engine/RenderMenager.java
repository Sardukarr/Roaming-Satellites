package inz.satellites.engine;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import inz.satellites.objects.Satellite;
import inz.satellites.utils.Config;

/**
 * Created by VIT on 2016-10-21.
 */
public class RenderMenager {


    private World world;

    private Satellite satellite;

    private OrthographicCamera camera;

    private SpriteBatch batch;

    private Skin skin;

    private Label score;

    private Label lives;

    private Image hearth;

    private Table infoHeader;

    public RenderMenager(final World world, final SpriteBatch batch, final Skin skin) {
        this.world = world;
        this.satellite = world.getSatellite();
        this.skin = skin;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
        this.batch = batch;
        batch.setProjectionMatrix(camera.combined);

        lives = new Label(" 3 x",skin);
        score = new Label("0",skin);
        hearth = new Image( new Texture(Gdx.files.internal("hearth.png")));
        hearth.setWidth(15);
        hearth.setHeight(15);

        infoHeader = prepareInfoHeader();

//        isGodModeOn = new Label("sample text",this.skin);
    }

    private Table prepareInfoHeader(){
        Label l = new Label("sample",skin);
       // isGodModeOn = l;
        Table table = new Table(skin){
        };
        table.setWidth(Config.SCREEN_WIDTH);
        table.setPosition(0, Config.SCREEN_HEIGHT - 20);
        table.add("Score: ").left();
        table.add(score).expand().left();
        table.add(l).center();
//        table.add(isGodModeOn).center();
        table.add(hearth).expand().right();
        table.add(lives).right();
        return table;
    }
    public void lastScoreUpdate(){
        if(!batch.isDrawing()) {
            batch.begin();
        }
        score.setText(world.getScore() + "");
        infoHeader.draw(batch, 1f);
        batch.end();
    }
    /**
     * Renderowanie świata gry.
     * @param delta  czas od poprzedniej klatki sceny
     */
    public void render(float delta) {
        if (world.isPaused()) {
            return;
        }
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        batch.setProjectionMatrix(camera.combined);

        //aktualizacja belki
        score.setText(world.getScore() + "");
        lives.setText(" " + world.getLives() + " x");

       // isGodModeOn.setText(world.getShip().godmode ? "GODMODE ON" : "");

        satellite.updateTimeSinceCollision(delta);

        //rysowanie statku
        satellite.draw(batch);

        batch.begin();

    }
}
