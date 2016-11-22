package inz.satellites.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import inz.satellites.utils.Config;

/**
 * Created by VIT on 2016-10-21.
 */
public class Satellite extends  GameObject{

    protected int halfWidth;

    protected int halfHeight;

    private float invincibleTime = 0f;
    private Texture shield;
    public boolean upgrade = false;

    public Satellite(float x, float y){
        texture = setTexture("satellite.png");
        width = Config.SHIP_WIDTH;
        height = Config.SHIP_HEIGHT;
        halfHeight = height/2;
        halfWidth = width/2;
        boundingBox = new Rectangle(x,y,width,height);
        shield = setTexture("shield.png");
    }

    /**
     * Porusza statek z prędkością proporcjonalną do odległości miejsca docelowego.
     * @param screenX  docelowe miejsce X
     * @param screenY  <s>docelowe miejsce Y</s> nie używane
     */
    public void smoothMove(int screenX, int screenY){
        float xx = getX();
        float yy = getY();
        float delta = Gdx.graphics.getDeltaTime();
        float middleX = xx + 5 *  delta * (screenX/ Config.screenRatioX - (xx+ halfWidth)) + halfWidth;
        float middleY = yy + 5 * delta * (((Config.SCREEN_HEIGHT-screenY)/ Config.screenRatioY)+Config.SCREEN_HEIGHT/2 - (yy+ halfHeight))+halfHeight;
        setMiddle(middleX,middleY);  //((Config.SCREEN_HEIGHT-screenY)/Config.screenRatioY)+Config.SCREEN_HEIGHT/2)
    }

    public boolean godmode = false;
    public boolean detectColission(GameObject object){
        if(invincibleTime > 0 || godmode) {
            return false;
        }
        boolean colission = boundingBox.overlaps(object.getBoundingBox());
        if(colission)
            invincibleTime = 5f;
        return colission;
    }

    public void updateTimeSinceCollision(float deltaTime){
        invincibleTime -= deltaTime;
        if(invincibleTime<0){
            invincibleTime = 0;
        }
    }

    @Override
    public void draw(SpriteBatch batch) {
        if(!batch.isDrawing()){
            batch.begin();
        }
        batch.draw(texture, boundingBox.getX(), boundingBox.getY(), width, height);
        if(invincibleTime !=0 || godmode){
            batch.draw(shield,boundingBox.getX()-32,boundingBox.getY()-32,2*width,2*height);
        }
        batch.end();
    }

}
