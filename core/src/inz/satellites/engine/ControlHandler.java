package inz.satellites.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Timer;

/**
 * Created by VIT on 2016-10-21.
 */
// todo: implements GestureDetector.GestureListener, InputProcessor
public class ControlHandler implements GestureDetector.GestureListener, InputProcessor{
    /**
     * Świat.
     */
    private World world;

    /**
     * Przekazuje zdarzenia do kolejnych {@link InputProcessor}
     */
    private InputMultiplexer inputMultiplexer;

    /**
     * Detektor gestów.
     */
    private GestureDetector gestureDetector;

    /**
     * Timer do wywołwania zdarzeń.
     */
    private Timer timer;

    /**
     * Zadanie auto ataku wywoływane co 0.5 sekundy.
     */
    private Timer.Task autoAttackTask;

    /**
     * Wskaźnik do auto ataku.
     */
    private int autoAttackPointer = -1;

    private Dialog dialog;

    /**
     * Konstruktor do klasy obsługującej gesty.
     * @param world
     */
    public ControlHandler(final World world, Dialog dialog ) {
        inputMultiplexer = new InputMultiplexer();
        gestureDetector = new GestureDetector(this);
        inputMultiplexer.addProcessor(gestureDetector);
        inputMultiplexer.addProcessor(this);
        this.world = world;
        this.dialog = dialog;


        timer = Timer.instance();
        autoAttackTask = new Timer.Task() {
            @Override
            public void run() {
             //   world.attack();
            }
        };
        timer.scheduleTask(autoAttackTask,1,0.5f);
        timer.stop();


        godmodeButton = new TextButton("GODMODE", dialog.getSkin());
    }

    /**
     *
     * @return zwraca multiplexer do przekazywania zdarzeń
     */
    public InputMultiplexer getInputMultiplexer(){

        return inputMultiplexer;
    }

    /**
     * Funkcja do sprawdzania nazw wywoływanych gestów.
     * @param gesture  gest
     */
    private void gestureName(String gesture) {
        if(world.isPaused())
            System.out.println(gesture);
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        gestureName("panStop");
        return false;
    }
    /**
     * Przypisanie autoataku do 1. wciśniętego wskaźnika.
     */
    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        if(autoAttackPointer == -1) {
            timer.start();
            autoAttackPointer = pointer;
        }
        gestureName("public boolean touchDown(float x, float y, int pointer, int button)");
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        gestureName("public boolean touchDown(int screenX, int screenY, int pointer, int button)");
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        gestureName("public boolean tap");
        return false;
    }

    @Override
    public boolean longPress(float x, float y) {
        gestureName("public boolean longPress");
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        gestureName("public boolean fling");
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        gestureName("public boolean pan");
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        gestureName("public boolean zoom initialDistance:" + initialDistance + " distance:" + distance);
        if(world.isPaused() &&  distance > Gdx.graphics.getHeight()/2){
            addButton();
        }
        return true;
    }
    public static boolean godemodeButtonAdded = false;
    public static Button godmodeButton;
    private void addButton(){
        final boolean currentState = world.getSatellite().godmode;
        godmodeButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                gestureName("stan godmode " + !currentState);
                world.getSatellite().godmode = !currentState;
                dialog.removeActor(godmodeButton);
                godemodeButtonAdded = false;
            }
        });
        if(!godemodeButtonAdded) {
            dialog.add(godmodeButton);
            godemodeButtonAdded =true;
        }

    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        gestureName("public boolean pinch");
        return true;
    }
// todo: test czy dziala

    @Override
    public void pinchStop() {
        gestureName("panStop");
    }
    /**
     * Włączenie i wyłączenie pauzy na klawiszu powrotu.
     */
    @Override
    public boolean keyDown(int keycode) {
        gestureName("public boolean keyDown");
        if(keycode == Input.Keys.BACK || keycode == Input.Keys.ESCAPE){
            if(!(world.getLives()<0))
                world.setPaused(!world.isPaused());
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        gestureName("public boolean keyUp");
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        gestureName("public boolean keyTyped");
        return false;
    }

    /**
     * Wyłączenie autoataku po zaprzestaniu naciskania zapamiętanego wskaźnika.
     */
    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        gestureName("public boolean touchUp");
        if(pointer == autoAttackPointer) {
            timer.stop();
            autoAttackPointer = -1;
        }
        else{
      //      world.attack();
        }
        return false;
    }

    /**
     * Poruszanie statkiem przez przytrzymanie wskaźnika.
     */
    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        gestureName("public boolean touchDragged");
        world.moveShip(screenX, screenY);
        return true;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        gestureName("public boolean mouseMoved");
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        gestureName("public boolean scrolled");
        return true;
    }
}
