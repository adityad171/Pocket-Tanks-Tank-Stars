package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Database;
import com.mygdx.game.Project1;
import com.mygdx.game.Tanks;

import java.util.ArrayList;

public class pause_Menu implements Screen {
    Project1 game;

    private TextureRegion backgroundTexture;
    private OrthographicCamera camera;
    private Database db;
    private Stage stage;
    private Texture bg;
    private Texture title;
    private BitmapFont font=new BitmapFont(Gdx.files.internal("font-title-export.fnt"));

    public void save(){
        
    }

    public pause_Menu(final Project1 game, final Database db) {
        this.game = game;
        this.db=db;
        bg = new Texture(Gdx.files.internal("pause_bg.jpg"));
        title = new Texture(Gdx.files.internal("pause_title.png"));
        camera = new OrthographicCamera();
        camera.setToOrtho(false, this.game.P1_WIDTH, this.game.P1_HEIGHT);
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        Skin n_Skin = new Skin(Gdx.files.internal("golden-spiral/skin/golden-ui-skin.json"));
        ImageButton resumeButton = new ImageButton(n_Skin);
        resumeButton.setSize(200, 250);
        resumeButton.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture("golden-spiral/raw/progress-bar.png")));
        resumeButton.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(new Texture("golden-spiral/raw/progress-bar.png")));

        resumeButton.setPosition(game.P1_WIDTH/5+250,game.P1_HEIGHT/2-220);
        resumeButton.addListener(new ClickListener(){
            @Override
            public void touchUp (InputEvent event,float x, float y,int pointer ,int button){
            }
            @Override
            public boolean touchDown(InputEvent event,float x, float y , int pointer , int button){
                return true;
            }
        });

        stage.addActor(resumeButton);

        Skin skin2 = new Skin(Gdx.files.internal("golden-spiral/skin/golden-ui-skin.json"));
        ImageButton restart = new ImageButton(skin2);
        restart.setSize(220, 300);
        restart.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture("golden-spiral/raw/progress-bar-life.png")));
        restart.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(new Texture("golden-spiral/raw/progress-bar-life.png")));

        restart.setPosition(game.P1_WIDTH/5-100,game.P1_HEIGHT/2-250);
        restart.addListener(new ClickListener(){
            @Override
            public void touchUp (InputEvent event,float x, float y,int pointer ,int button){
                Main_Page mp=new Main_Page(game,db);
                game.setScreen(mp);
            }
            @Override
            public boolean touchDown(InputEvent event,float x, float y , int pointer , int button){
                return true;
            }
        });

        stage.addActor(restart);

        Skin skin3 = new Skin(Gdx.files.internal("golden-spiral/skin/golden-ui-skin.json"));
        ImageButton exit = new ImageButton(skin3);
        exit.setSize(220, 300);
        exit.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture("golden-spiral/raw/progress-bar-mana.png")));
        exit.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(new Texture("golden-spiral/raw/progress-bar-mana.png")));

        exit.setPosition(game.P1_WIDTH/5+600,game.P1_HEIGHT/2-250);
        exit.addListener(new ClickListener(){
            @Override
            public void touchUp (InputEvent event,float x, float y,int pointer ,int button){
                Start st=new Start(game);
                game.setScreen(st);
            }
            @Override
            public boolean touchDown(InputEvent event,float x, float y , int pointer , int button){
                return true;
            }
        });

        stage.addActor(exit);
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
        game.getBatch().draw(bg, 0,0, game.P1_WIDTH, game.P1_HEIGHT);
        game.getBatch().draw(title, game.P1_WIDTH/2-250,game.P1_HEIGHT/2, 500, 400);
        font.draw(game.getBatch(),"PAUSED",game.P1_WIDTH/2-100,game.P1_HEIGHT/2+170);
        font.draw(game.getBatch(),"RESUME",game.P1_WIDTH/5+250,game.P1_HEIGHT/2);
        font.draw(game.getBatch(),"EXIT",game.P1_WIDTH/5+650,game.P1_HEIGHT/2);
        font.draw(game.getBatch(),"RESTART",game.P1_WIDTH/5-100,game.P1_HEIGHT/2);

        game.getBatch().end();
        stage.act();
        stage.draw();
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
        stage.dispose();

    }
}
