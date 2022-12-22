package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class bullet {
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

     int x;
    int y;
    Texture img = new Texture(Gdx.files.internal("gobline.png"));
    public bullet(int x,int y){
        this.x=x;
        this.y=y;
    }
}
