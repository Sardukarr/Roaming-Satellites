package inz.satellites.objects;

import com.badlogic.gdx.math.Rectangle;

import inz.satellites.utils.Config;

/**
 * Created by VIT on 2016-10-21.
 */
public class Enamy extends GameObject {

    public Enamy(float x, float y){
        texture = setTexture("satellite.png");
        width = Config.SHIP_WIDTH;
        height = Config.SHIP_HEIGHT;
       // halfHeight = height/2;
      //  halfWidth = width/2;
        boundingBox = new Rectangle(x,y,width,height);

    }
}
