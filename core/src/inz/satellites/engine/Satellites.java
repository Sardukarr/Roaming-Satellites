package inz.satellites.engine;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import inz.satellites.screens.MainMenuScreen;
import inz.satellites.screens.GameScreen;
import inz.satellites.screens.ScoreScreen;

public class Satellites extends Game{
	public Satellites(){

	}
	@Override
	public void resize(int width, int height) {
		super.resize(width, height);

		if( getScreen() == null ) {
			setScreen( new MainMenuScreen( this) );

		}
	}

	@Override
	public void create() {


	}

	@Override
	public void render() {
		super.render();
	}

	/**
	 * ustawienie ekranu gry
	 */
	public void startGame() {setScreen(new GameScreen(this));}
	/**
	 * ustawienie ekranu punktacji
	 */
	public void showScore() { setScreen(new ScoreScreen(this));  }
	/**
	 * ustawienie ekranu menu
	 */
	public void showMenu() {setScreen(new MainMenuScreen(this));}

}
