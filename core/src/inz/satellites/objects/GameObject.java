package inz.satellites.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

import inz.satellites.utils.Config;
/**
 * Created by VIT on 2016-10-21.
 */
public class GameObject {

    protected int width     = 0;

    protected int height    = 0;

    protected Rectangle borderBox;

    protected Texture texture;

    protected Vector2 speed = new Vector2(0,0);

    public float getWidth() {
        return width;
    }

    public float getHeight() {return height;}

    public Rectangle getBorderBox() {
        return borderBox;
    }
}




