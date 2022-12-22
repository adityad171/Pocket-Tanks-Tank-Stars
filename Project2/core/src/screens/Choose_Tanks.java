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

public class Choose_Tanks implements Screen {
    private Project1 game;
    private Texture img;

    private Texture Next;
    private Texture back;
    private boolean next1=false;
    private int i=0;
    private int choice=0;
    private Texture purp;
    private Texture name;
    private TextureRegion backgroundTexture;
    OrthographicCamera camera;
    private ArrayList<Tanks> TankList ;
    private Database db;
    private Stage stage;
    private BitmapFont font=new BitmapFont(Gdx.files.internal("font-title-export.fnt"));
    public void MakeTanks(){
        Tanks A=new Tanks(new Texture(Gdx.files.internal("Tank1.png")),new Texture(Gdx.files.internal("leftCanon.png")),"Arkansas",800,new Texture(Gdx.files.internal("h1.jpg")),new Texture(Gdx.files.internal("t1.jpg")));
        Tanks B=new Tanks(new Texture(Gdx.files.internal("Tank2.png")),new Texture(Gdx.files.internal("rightCanon.png")),"Bh",800,new Texture(Gdx.files.internal("h2.jpg")),new Texture(Gdx.files.internal("t2.jpg")));
        Tanks C=new Tanks(new Texture(Gdx.files.internal("Tank3.png")),new Texture(Gdx.files.internal("leftCanon.png")),"Ctiya",700,new Texture(Gdx.files.internal("h1.jpg")),new Texture(Gdx.files.internal("t3.jpg")));
        this.TankList.add(A);
        this.TankList.add(B);
        this.TankList.add(C);
    }

    public Choose_Tanks(final Project1 game) {
        this.game = game;
        final Database db=new Database();
        img = new Texture(Gdx.files.internal("choosebg.jpeg"));
        purp = new Texture(Gdx.files.internal("purple.jpg"));
//        leftArr = new Texture(Gdx.files.internal("left.png"));
//        rightArr = new Texture(Gdx.files.internal("Right.png"));
        Next = new Texture(Gdx.files.internal("go.jpg"));
        back = new Texture(Gdx.files.internal("back.jpeg"));
        TankList =new ArrayList<Tanks>();
        MakeTanks();
        backgroundTexture = new TextureRegion(img, 0, 0, img.getWidth(), img.getHeight());
        camera = new OrthographicCamera();
        camera.setToOrtho(false, this.game.P1_WIDTH, this.game.P1_HEIGHT);
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        Skin select_skin = new Skin(Gdx.files.internal("golden-spiral/skin/golden-ui-skin.json"));
        Skin n_Skin = new Skin(Gdx.files.internal("golden-spiral/skin/golden-ui-skin.json"));
        Skin Arrowl_Skin = new Skin(Gdx.files.internal("star-soldier/skin/star-soldier-ui.json"));
        Skin Arrowr_Skin = new Skin(Gdx.files.internal("star-soldier/skin/star-soldier-ui.json"));

        ImageButton nextButton = new ImageButton(n_Skin);
        nextButton.setSize(120, 180);
        nextButton.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture("golden-spiral/raw/progress-bar.png")));
        nextButton.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(new Texture("golden-spiral/raw/progress-bar.png")));

        nextButton.setPosition(game.P1_WIDTH-img.getWidth(),game.P1_HEIGHT/2-220);
        nextButton.addListener(new ClickListener(){
            @Override
            public void touchUp (InputEvent event,float x, float y,int pointer ,int button){
                if (next1) {
                    Main_Page mp=new Main_Page(game,db);
                    game.setScreen(mp);
                }
            }
            @Override
            public boolean touchDown(InputEvent event,float x, float y , int pointer , int button){
                return true;
            }
        });

        stage.addActor(nextButton);

        ImageButton selectButton = new ImageButton(select_skin);
        selectButton.setSize(120, 180);
        selectButton.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture("golden-spiral/raw/mana-orb.png")));
        selectButton.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(new Texture("golden-spiral/raw/button-over.9.png")));
        selectButton.setPosition(game.P1_WIDTH-img.getWidth(),game.P1_HEIGHT/2+10);
        selectButton.addListener(new ClickListener(){
            @Override
            public void touchUp (InputEvent event,float x, float y,int pointer ,int button){
                if (choice==1) {
                    db.getTankList().add(TankList.get(i));
                    next1=true;
                }
                if (choice==0) {
                    db.getTankList().add(TankList.get(i));
                    choice++;
                }
            }
            @Override
            public boolean touchDown(InputEvent event,float x, float y , int pointer , int button){
                return true;
            }
        });

        stage.addActor(selectButton);
        ImageButton backButton = new ImageButton(new Skin(Gdx.files.internal("star-soldier/skin/star-soldier-ui.json")));
        backButton.setSize(80,70);
        backButton.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture("star-soldier/raw/left-arrow.png")));
        backButton.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(new Texture("star-soldier/raw/left-arrow-down.png")));
        backButton.setPosition(10,game.P1_HEIGHT-80);
        backButton.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event,float x, float y,int pointer ,int button){
                Start sp=new Start(game);
                game.setScreen(sp);
            }
            @Override
            public boolean touchDown(InputEvent event,float x, float y , int pointer , int button){
                return true;
            }
        });

        stage.addActor(backButton);

        ImageButton Arrow_Button1 = new ImageButton(Arrowl_Skin);
        Arrow_Button1.setSize(100,120);
        Arrow_Button1.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture("star-soldier/raw/left-arrow.png")));
        Arrow_Button1.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(new Texture("star-soldier/raw/left-arrow-down.png")));
        Arrow_Button1.setPosition(game.P1_WIDTH/2-500, game.P1_HEIGHT/2-80);
        Arrow_Button1.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event,float x, float y,int pointer ,int button){
                if(i!=0 && !next1)
                    i--;
            }
            @Override
            public boolean touchDown(InputEvent event,float x, float y , int pointer , int button){
                return true;
            }
        });

        stage.addActor(Arrow_Button1);

        ImageButton Arrow_Button2 = new ImageButton(Arrowr_Skin);
        Arrow_Button2.setSize(100, 120);
        Arrow_Button2.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture("star-soldier/raw/right-arrow.png")));
        Arrow_Button2.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(new Texture("star-soldier/raw/right-arrow-down.png")));
        Arrow_Button2.setPosition(game.P1_WIDTH/2+20,game.P1_HEIGHT/2-80);
        Arrow_Button2.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event,float x, float y,int pointer ,int button){
                if(i!=TankList.size()-1 && !next1)
                    i++;
            }
            @Override
            public boolean touchDown(InputEvent event,float x, float y , int pointer , int button){
                return true;
            }
        });

        stage.addActor(Arrow_Button2);
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
        game.getBatch().draw(backgroundTexture, 0,0, game.P1_WIDTH-img.getWidth()-100, game.P1_HEIGHT );
        game.getBatch().draw(TankList.get(i).getTankImg(), game.P1_WIDTH/2-380,game.P1_HEIGHT/2-150, 350, 200);
        game.getBatch().draw(purp, game.P1_WIDTH-img.getWidth()-100,0, img.getWidth()+100, game.P1_HEIGHT );
        font.draw(game.getBatch(),"Select",game.P1_WIDTH-img.getWidth()-30,game.P1_HEIGHT/2+230);
        font.draw(game.getBatch(),"Tank "+(choice+1),game.P1_WIDTH-img.getWidth()-10,game.P1_HEIGHT/2+200);
        font.draw(game.getBatch(),"Start Game",game.P1_WIDTH-img.getWidth()-90,game.P1_HEIGHT/2-70);
        game.getBatch().draw(TankList.get(i).getName1(), 320,530, 200, 70);
        game.getBatch().draw(TankList.get(i).getHealthImg(), 500,390, 100, 50);
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
