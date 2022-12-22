package screens;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Project1;

import java.awt.*;

public class load_game implements Screen {
    private Project1 game;
    private Texture bg;
    private Texture purp;
    private Texture g1;
    private TextureRegion backgroundTexture;

    OrthographicCamera camera;
    public load_game(Project1 game){
        this.game=game;
        bg=new Texture(Gdx.files.internal("load_bg.jpg"));
        purp=new Texture(Gdx.files.internal("purp.png"));
        g1=new Texture(Gdx.files.internal("game1.jpg"));
        backgroundTexture = new TextureRegion(bg, 0, 0, this.game.P1_WIDTH, this.game.P1_HEIGHT);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, this.game.P1_WIDTH, this.game.P1_HEIGHT);
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 0);
        camera.update();
        game.getBatch().setProjectionMatrix(camera.combined);
        game.getBatch().begin();
        game.getBatch().draw(backgroundTexture, 0,0, this.game.P1_WIDTH, this.game.P1_HEIGHT);
        game.getBatch().draw(g1, this.game.P1_WIDTH/3-180,this.game.P1_HEIGHT*2/3-50, this.game.P1_WIDTH/6+100, this.game.P1_HEIGHT/6+60);
        game.getBatch().draw(purp, this.game.P1_WIDTH/3-180,this.game.P1_HEIGHT/3-150, this.game.P1_WIDTH/6+100, this.game.P1_HEIGHT/6+60);
        game.getBatch().draw(purp, this.game.P1_WIDTH*2/3-120,this.game.P1_HEIGHT*2/3-50, this.game.P1_WIDTH/6+100, this.game.P1_HEIGHT/6+60);
        game.getBatch().draw(purp, this.game.P1_WIDTH*2/3-120,this.game.P1_HEIGHT/3-150, this.game.P1_WIDTH/6+100, this.game.P1_HEIGHT/6+60);
        game.font.draw(game.getBatch(), "Save Game :1",this.game.P1_WIDTH/3-100,this.game.P1_HEIGHT*2/3-80 );
        game.font.draw(game.getBatch(), "Save Game :3",this.game.P1_WIDTH/3-100,this.game.P1_HEIGHT/3-180 );
        game.font.draw(game.getBatch(), "Save Game :2",this.game.P1_WIDTH*2/3-40,this.game.P1_HEIGHT*2/3-80 );
        game.font.draw(game.getBatch(), "Save Game :4",this.game.P1_WIDTH*2/3-40,this.game.P1_HEIGHT/3-180 );


        game.getBatch().end();
    }

    public void load(){

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}

