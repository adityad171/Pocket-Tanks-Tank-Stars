package screens;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
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

public class Start implements Screen {
    private final Project1 game;
    private Texture texture;
    private Texture start;
    private Music BackgroundMusic;
    public Music getBackgroundMusic() {
        return BackgroundMusic;
    }
    public void setBackgroundMusic(Music backgroundMusic) {
        BackgroundMusic = backgroundMusic;
    }
    private Texture resume;
    private Texture exit;
    private TextureRegion backgroundTexture;

    private OrthographicCamera camera;
    public Start(Project1 game){
        this.game=game;
        texture=new Texture(Gdx.files.internal("bg.jpg"));
        start=new Texture(Gdx.files.internal("start.png"));
        resume=new Texture(Gdx.files.internal("resume.png"));
        exit=new Texture(Gdx.files.internal("quit.png"));
        setBackgroundMusic(Gdx.audio.newMusic(Gdx.files.internal("StartGame.mp3")));
        BackgroundMusic().setLooping(true);
        getBackgroundMusic().play();
        backgroundTexture = new TextureRegion(texture, 0, 0, this.game.P1_WIDTH, this.game.P1_HEIGHT);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, this.game.P1_WIDTH, this.game.P1_HEIGHT);
    }

    private Music BackgroundMusic() {
        return BackgroundMusic;
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
        game.getBatch().draw(resume,this.game.P1_WIDTH/2-170,this.game.P1_HEIGHT/2-220, 100, 70);
        game.getBatch().draw(start,this.game.P1_WIDTH/2-50,this.game.P1_HEIGHT/2-220, 100, 70);
        game.getBatch().draw(exit, this.game.P1_WIDTH/2+70,this.game.P1_HEIGHT/2-220, 100, 70);

        game.getBatch().end();

        if (Gdx.input.justTouched() && Gdx.input.getX() < (this.game.P1_WIDTH/2)+50 && Gdx.input.getX() > (this.game.P1_WIDTH/2)-50 && Gdx.input.getY() < (this.game.P1_HEIGHT/2+220) && Gdx.input.getY() > (this.game.P1_HEIGHT/2)+150) {
            game.setScreen(new Choose_Tanks(game));
            dispose();
        }

        if (Gdx.input.justTouched() && Gdx.input.getX() < (this.game.P1_WIDTH/2)-70 && Gdx.input.getX() > (this.game.P1_WIDTH/2)-170 && Gdx.input.getY() < (this.game.P1_HEIGHT/2+220) && Gdx.input.getY() > (this.game.P1_HEIGHT/2)+150) {
            game.setScreen(new load_game(game));
            dispose();
        }
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

