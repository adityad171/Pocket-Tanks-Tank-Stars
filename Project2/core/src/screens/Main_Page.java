package screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Database;
import com.mygdx.game.Project1;
import com.mygdx.game.Tanks;
import com.mygdx.game.block;

import java.io.*;
import java.util.ArrayList;

public class Main_Page extends block implements Screen, Serializable {
    private Database db;
    private Project1 game;
    private transient boolean showBulletLeft, showBulletRight;

    private int pt1=2;
    private int pt2=45;
    private int leftAngle;
    private int rightAngle;

    private transient Texture texture;
    private transient int leftVelocity, rightVelocity;


    private Tanks tank1;
    private Tanks tank2;
    private Texture pause;
    private int c=0;
    private bullet b1;
    private bullet b2;
    private Stage stage;
    private TextureRegion backgroundTexture;
    OrthographicCamera camera;
    private BitmapFont font=new BitmapFont(Gdx.files.internal("default.fnt"));

    @Override
    public void move(Object o) {
        bullet b3=(bullet)o;
        b3.setX(b3.getX()+1);
        b3.setY(b3.getY()+1);
    }


    class myT1 extends Thread{
        bullet b1;
        public myT1(bullet b1){
            this.b1=b1;
        }
        @Override
        public void run(){
            while (true) {
                 b1.y= projectileLeft(b1.getX(), tank1.getX_cord() + 40, 75, -40)-150;
                if (b1.getX() > game.P1_WIDTH || b1.y > game.P1_HEIGHT) {
                    showBulletLeft = false;
                    break;
                }
                if (tank2.getX_cord()<=b1.getX() && tank2.getY_cord()>=b1.y && tank2.getX_cord()+30>=b1.getX() && tank2.getY_cord()<=b1.y+10) {
                    tank2.setHealth(tank1.getHealth()-50);
                    showBulletLeft = false;
                    break;
                }
                if (tank2.getX_cord()-30<=b1.getX() && tank2.getY_cord()>=b1.y && tank2.getX_cord()+60>=b1.getX() && tank2.getY_cord()<=b1.y+10) {
                    tank2.setHealth(tank1.getHealth()-50);
                    showBulletLeft = false;
                    break;
                }
                b1.setX(b1.getX()+1);

                try {
                    Thread.sleep(20);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }

        }
    }
    public class Data {
        Project1 g;
        Database db;
        public void saveload(Project1 game) {
            this.g = game;
        }

        public void save() {
            try {
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("sava.dat")));
                Main_Page d = new Main_Page(g,db);
                d.db=db;
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    class myT2 extends Thread{
        bullet b2;
        public myT2(bullet b2){
            this.b2=b2;
        }
        @Override
        public void run(){
                while (true) {
                    b2.y = projectileRight(b2.getX(), tank2.getX_cord() + 45, 75, 180 - 40)-150;

                    if (b2.getX() < 0 || b2.y > game.P1_HEIGHT) {
                        showBulletRight = false;
                        break;
                    }
                    if (tank1.getX_cord()<=b2.getX() && tank1.getY_cord()>=b2.y && tank1.getX_cord()+30>=b2.getX() && tank1.getY_cord()<=b2.y+10) {
                        tank1.setHealth(tank1.getHealth()-50);
                        showBulletRight = false;
                        break;
                    }
                    if (tank1.getX_cord()-30<=b2.getX() && tank1.getY_cord()>=b2.y && tank1.getX_cord()+60>=b2.getX() && tank1.getY_cord()<=b2.y+10) {
                        tank1.setHealth(tank1.getHealth()-50);
                        showBulletRight = false;
                        break;
                    }
                    b2.setX(b2.getX()-1);

                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                }
            }
    }

    public int projectileLeft(double x, int x1, double u, int angle) {
        return (int) (-(x - x1) * Math.tan(Math.toRadians(angle)) - (20 * ((x - x1) * (x - x1))
                / (2 * u * u * Math.cos(Math.toRadians(angle))) * Math.cos(Math.toRadians(angle))) + 430);
    }

    public int projectileRight(int x, int x1, double u, int angle) {
        return (int) ((x - x1) * Math.tan(Math.toRadians(angle)) - (20 * ((x - x1) * (x - x1))
                / (2 * u * u * Math.cos(Math.toRadians(angle))) * Math.cos(Math.toRadians(angle))) + 430);
    }
    public void leftFireClick1(bullet b1) {
        showBulletLeft = true;
        c=1;
        b1.setX(tank1.getX_cord() + 30);
        tank1.setFuel(150);

        myT1 thread1=new myT1(b1);
        thread1.start();
    }

    public void rightFireClick1(bullet b2) {
        showBulletRight = true;
        b2.setX(tank2.getX_cord() + 45);
        c=0;
        tank2.setFuel(150);
        myT2 thread2=new myT2(b2);
        thread2.start();
    }
    public Main_Page(final Project1 game, final Database db){
        this.db=db;
        this.game=game;
        texture=new Texture(Gdx.files.internal("terrain.png"));
        pause=new Texture(Gdx.files.internal("pause.jpg"));

        tank1=(Tanks) db.getTankList().get(0).clone();
        tank2=(Tanks) db.getTankList().get(1).clone();
        tank1.setX_cord(20*pt1);
        tank1.setY_cord(db.getArrY()[pt1]);
        tank2.setX_cord(20*pt2);
        tank2.setY_cord(db.getArrY()[pt2]);
        backgroundTexture = new TextureRegion(texture, 0, 0, this.game.P1_WIDTH, this.game.P1_HEIGHT);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, this.game.P1_WIDTH, this.game.P1_HEIGHT);
        leftAngle = 40;
        rightAngle = 40;

        showBulletLeft = false;
        showBulletRight = false;

        b1=new bullet(tank1.getX_cord(),tank1.getY_cord());
        b2=new bullet(tank2.getX_cord(),tank2.getY_cord());

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        Skin fire1 = new Skin(Gdx.files.internal("golden-spiral/skin/golden-ui-skin.json"));
        ImageButton fireButton1 = new ImageButton(fire1);
        fireButton1.setSize(100, 150);
        fireButton1.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture("golden-spiral/raw/progress-bar-life.png")));
        fireButton1.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(new Texture("golden-spiral/raw/progress-bar-mana.png")));

        fireButton1.setPosition(this.game.P1_WIDTH/4-100,this.game.P1_HEIGHT/8-100);
        fireButton1.addListener(new ClickListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer , int button){
                if (c==0){
                System.out.println("fire1");
                leftFireClick1(b1);}
            }
            @Override
            public boolean touchDown(InputEvent event,float x, float y , int pointer , int button){
                return true;
            }
        });

        stage.addActor(fireButton1);

        Skin fire2 = new Skin(Gdx.files.internal("golden-spiral/skin/golden-ui-skin.json"));
        ImageButton fireButton2 = new ImageButton(fire2);
        fireButton2.setSize(100, 150);
        fireButton2.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture("golden-spiral/raw/progress-bar-life.png")));
        fireButton2.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(new Texture("golden-spiral/raw/progress-bar-mana.png")));

        fireButton2.setPosition(3*this.game.P1_WIDTH/4+100,this.game.P1_HEIGHT/8-100);
        fireButton2.addListener(new ClickListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer , int button){
                if (c==1){
                    System.out.println("fire2");
                    rightFireClick1(b2);}
            }
            @Override
            public boolean touchDown(InputEvent event,float x, float y , int pointer , int button){
                return true;
            }
        });

        stage.addActor(fireButton2);

        Skin pauseSkin = new Skin(Gdx.files.internal("star-soldier/skin/star-soldier-ui.json"));
        ImageButton pauseButton = new ImageButton(pauseSkin);
        pauseButton.setSize(80,80);
        pauseButton.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture("star-soldier/raw/programs.9.png")));
        pauseButton.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(new Texture("star-soldier/raw/programs-pressed.9.png")));

        pauseButton.setPosition(30,500);
        pauseButton.addListener(new ClickListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer , int button){
                pause_Menu pm=new pause_Menu(game,db);
                game.setScreen(pm);
            }
            @Override
            public boolean touchDown(InputEvent event,float x, float y , int pointer , int button){
                return true;
            }
        });

        stage.addActor(pauseButton);



        Skin powerup_skin1 = new Skin(Gdx.files.internal("star-soldier/skin/star-soldier-ui.json"));
        ImageButton Power1 = new ImageButton(powerup_skin1);
        Power1.setSize(80,80);
        Power1.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture("star-soldier/raw/button-scroll-up.png")));
        Power1.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(new Texture("star-soldier/raw/button-scroll-up-pressed.png")));

        Power1.setPosition(70,250);
        Power1.addListener(new ClickListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer , int button){
                leftVelocity++;
            }
            @Override
            public boolean touchDown(InputEvent event,float x, float y , int pointer , int button){
                return true;
            }
        });

        stage.addActor(Power1);

        Skin powerup_skin2 = new Skin(Gdx.files.internal("star-soldier/skin/star-soldier-ui.json"));
        ImageButton Power2 = new ImageButton(powerup_skin2);
        Power2.setSize(80,80);
        Power2.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture("star-soldier/raw/button-scroll-up.png")));
        Power2.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(new Texture("star-soldier/raw/button-scroll-up-pressed.png")));

        Power2.setPosition(1000,250);
        Power2.addListener(new ClickListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer , int button){
                rightVelocity++;
            }
            @Override
            public boolean touchDown(InputEvent event,float x, float y , int pointer , int button){
                return true;
            }
        });

        stage.addActor(Power2);
        Skin powerdown_skin1 = new Skin(Gdx.files.internal("star-soldier/skin/star-soldier-ui.json"));
        ImageButton Power3 = new ImageButton(powerdown_skin1);
        Power3.setSize(80,80);
        Power3.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture("star-soldier/raw/button-scroll-down.png")));
        Power3.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(new Texture("star-soldier/raw/button-scroll-down-pressed.png")));

        Power3.setPosition(70,150);
        Power3.addListener(new ClickListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer , int button){
                leftVelocity--;
            }
            @Override
            public boolean touchDown(InputEvent event,float x, float y , int pointer , int button){
                return true;
            }
        });
        stage.addActor(Power3);
        Skin powerdown_skin2 = new Skin(Gdx.files.internal("star-soldier/skin/star-soldier-ui.json"));
        ImageButton Power4 = new ImageButton(powerdown_skin2);
        Power4.setSize(80,80);
        Power4.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture("star-soldier/raw/button-scroll-down.png")));
        Power4.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(new Texture("star-soldier/raw/button-scroll-down-pressed.png")));

        Power4.setPosition(1000,150);
        Power4.addListener(new ClickListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer , int button){
                rightVelocity--;
            }
            @Override
            public boolean touchDown(InputEvent event,float x, float y , int pointer , int button){
                return true;
            }
        });
        stage.addActor(Power4);
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
        game.getBatch().draw(texture, 0,0, this.game.P1_WIDTH, this.game.P1_HEIGHT);
        game.getBatch().draw(tank1.getTankImg(), tank1.getX_cord(), tank1.getY_cord(), 70, 50 );
        game.getBatch().draw(tank2.getTankImg(), tank2.getX_cord(), tank2.getY_cord(), 70, 50 );
        game.getBatch().draw(b1.img, tank1.getX_cord(),tank1.getY_cord(), 30, 30 );
        font.draw(game.getBatch(),"Tank1 Health: "+tank1.getHealth(),game.P1_WIDTH/4,game.P1_HEIGHT/2+200);
        font.draw(game.getBatch(),"Tank2 Health: "+tank2.getHealth(),3*game.P1_WIDTH/4-50,game.P1_HEIGHT/2+200);
        font.draw(game.getBatch(),"Tank1 Fuel: "+tank1.getFuel(),game.P1_WIDTH/4,game.P1_HEIGHT/2+250);
        font.draw(game.getBatch(),"Tank2 Fuel: "+tank2.getFuel(),3*game.P1_WIDTH/4-50,game.P1_HEIGHT/2+250);
        font.draw(game.getBatch(),leftAngle+" deg",tank1.getX_cord(),tank1.getY_cord()-5);
        font.draw(game.getBatch(),rightAngle+" deg",tank2.getX_cord(),tank2.getY_cord()-5);
        font.draw(game.getBatch(),"Power: "+leftVelocity,tank1.getX_cord(),tank1.getY_cord()-20);
        font.draw(game.getBatch(),"Power:"+rightVelocity,tank2.getX_cord(),tank2.getY_cord()-20);

        System.out.println(tank1.getHealth());
        if(showBulletLeft)
            game.getBatch().draw(b1.img, b1.getX(),b1.y, 30, 30 );

        if(showBulletRight)
            game.getBatch().draw(b2.img, b2.getX(),b2.y, 30, 30 );
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)&& pt1>=1 && c==0 && tank1.getFuel()!=0){
            System.out.println(tank1.getX_cord());
            pt1--;
            tank1.setFuel(tank1.getFuel()-10);
            tank1.setX_cord(pt1*20);
            tank1.setY_cord(db.getArrY()[pt1]);
            System.out.println("x:"+tank1.getX_cord());
            System.out.println("y:"+tank1.getY_cord());
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)&& pt2>=1 && c==1 && tank2.getFuel()!=0){
            System.out.println(tank2.getX_cord());
            pt2--;
            tank2.setX_cord(pt2*20);
            tank2.setFuel(tank2.getFuel()-10);
            tank2.setY_cord(db.getArrY()[pt2]);
            System.out.println("x:"+tank2.getX_cord());
            System.out.println("y:"+tank2.getY_cord());
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)&& pt1<=50 && c==0 && tank1.getFuel()!=0){
            System.out.println(tank1.getX_cord());
            pt1++;
            tank1.setX_cord(pt1*20);
            tank1.setFuel(tank1.getFuel()-10);
            tank1.setY_cord(db.getArrY()[pt1]);
            System.out.println("x:"+tank1.getX_cord());
            System.out.println("y:"+tank1.getY_cord());
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)&& pt2<=50 && c==1 && tank2.getFuel()!=0){
            System.out.println(tank2.getX_cord());
            pt2++;
            tank2.setFuel(tank2.getFuel()-10);
            tank2.setX_cord(pt2*20);
            tank2.setY_cord(db.getArrY()[pt2]);
            System.out.println("x:"+tank2.getX_cord());
            System.out.println("y:"+tank2.getY_cord());
        }

        if (Gdx.input.isKeyPressed(Input.Keys.UP) && c==0){
            leftAngle++;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP) && c==1){
            rightAngle++;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN) && c==0){
            leftAngle--;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN) && c==1){
            rightAngle--;
        }
        if (c==-1)
            move(b1);
        if(tank1.getHealth()<=0){
            pause_Menu sp=new pause_Menu(game,db);
            game.setScreen(sp);}
        if(tank2.getHealth()<=0){
            pause_Menu sp=new pause_Menu(game,db);
            game.setScreen(sp);}
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
