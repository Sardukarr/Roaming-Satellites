package inz.satellites.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.Array;

import java.util.Iterator;

import inz.satellites.objects.Satellite;
import inz.satellites.objects.GameObject;
import inz.satellites.utils.Config;

/**
 * Created by VIT on 2016-10-21.
 */
public class World {
    /**
     * Statek.
     */
    private Satellite satellite;


    /**
     * Lista istniejących pocisków.
     */
    private Array<GameObject> bullets;


    private Array<GameObject> powerUps;

    /**
     * Liczba żyć.
     */
    private int lives = 3;

    /**
     * Wynik gry.
     */
    private int score = 0;

    /**
     * Flaga do sprawdzania czy gra jest spauzowana.
     */
    private boolean paused = false;
    /**
     * Dźwięk kurczaka.
     */
    private Sound chickenSound;
    /**
     * Dźwięk laseru.
     */
    private Sound blasterSound;
    /**
     * Dźwięk końca rozgrywki.
     */
    private Sound noSound;
    /**
     * poziom gry
     */
    private int level=0;

    /**
     * Konstruktor.
     */
    public World() {
        float halfWidth = (Config.SCREEN_WIDTH - Config.SHIP_WIDTH) / 2;
        satellite = new Satellite(halfWidth,Config.EDGE_DISTANCE);

        bullets = new Array<GameObject>();

        powerUps = new Array<GameObject>();


      //  chickenSound =  Gdx.audio.newSound(Gdx.files.internal("sounds/dead.mp3"));
      //  blasterSound =  Gdx.audio.newSound(Gdx.files.internal("sounds/laser.mp3"));
     //   noSound = Gdx.audio.newSound(Gdx.files.internal("sounds/noo.mp3"));
        prepareGame();
    }


    public Satellite getSatellite() {
        return satellite;
    }


    /**
     *
     * @return  {@link World#bullets} lista pocisków
     */
    public Array<GameObject> getBullets(){return bullets;}

    public Array<GameObject> getPowerUps(){
        return powerUps;
    }

    /**
     *
     * @return  liczba żyć
     */
    public int getLives(){return lives;}

    /**
     *
     * @return  wynik gry
     */
    public int getScore() {return score;}

    /**
     *
     * @return  level gry
     */
    public int getLevel() {return level;}

    /**
     *
     * @return  dźwięk lasera
     */
    public Sound getBlasterSound() {
        return blasterSound;
    }

    /**
     *
     * @return  dźwięk końca gry
     */
    public Sound getNoSound() {
        return noSound;
    }

    public void levelUp() {level++;}
    /**
     * Przygotowanie planszy z grą.
     */
    protected void prepareGame(){

        //   spawnBoss();
       // spawnChickens();
    }



    public void killedLeg()
    {
        score+=10;
    }
    public void killedPower()
    {
        score+=100;
        satellite.upgrade=true;
    }
    /**
     * Funkcja wywoływana, kiedy gracz zostanie zabity.
     */
    public void killedPlayer(){
        //score -= 100;
        lives -= 1;
    }

    /**
     *
     * @return  flaga czy gra jest spauzowana
     */
    public boolean isPaused() {
        return paused;
    }

    /**
     * Pauzowanie gry.
     * @param paused  ustawia flagę pauzy
     */
    public void setPaused(boolean paused) {
        this.paused = paused;
    }


    /**
     * Wywoływane na koniec gry
     */
    public void endGame(){

        paused = true;
        noSound.play();
    }

    /**
     * Rusza statek do pozycji (x,y), jeśli gra nie jest spauzowana.
     * @param screenX  pozycja X
     * @param screenY  pozycja Y
     */
    public void moveShip(int screenX, int screenY){
        if(paused){
            return;
        }
        satellite.smoothMove(screenX,screenY);
    }

    /**
     * Wywołanie ataku.
     */
    /**
    public void attack(){
        if(paused){
            return;
        }
        if(!satellite.upgrade)
        {
            GameObject bullet = new SimpleAttack(0,Config.EDGE_DISTANCE + Config.SHIP_HEIGHT,0);
            blasterSound.play(0.2f);
            try {
                float x = ship.getXMiddle();
                float y = ship.getYMiddle();
                bullet.setMiddle(x, y);
                bullets.add(bullet);
            }   catch (Exception e){
                e.printStackTrace();
            }
        }
        else
        {
            GameObject bullet1 = new SimpleAttack(0,Config.EDGE_DISTANCE + Config.SHIP_HEIGHT,-1);
            GameObject bullet2 = new SimpleAttack(0,Config.EDGE_DISTANCE + Config.SHIP_HEIGHT,1);
            blasterSound.play(0.2f);
            try {
                float x = ship.getXMiddle();
                float y = ship.getYMiddle();
                bullet1.setMiddle(x, y);
                bullets.add(bullet1);

                bullet2.setMiddle(x, y);
                bullets.add(bullet2);
            }   catch (Exception e){
                e.printStackTrace();
            }
        }
    }
     */
    /**
     * Funkcja przygotowująca obiekt do zniszczenia.
     */
    public void dispose() {
        satellite.dispose();

        for (GameObject gameObject : bullets) {
            gameObject.dispose();
        }

        bullets.clear();
    }
}
