package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import screens.Start;

import java.awt.*;

public class Project1 extends Game {
	public static final int P1_WIDTH = 1200;
	public static final int P1_HEIGHT = 600;

	private SpriteBatch batch;
	private Texture img;
	Sprite spr;
	public BitmapFont font;

	@Override
	public void create () {
		batch = new SpriteBatch();
		font=new BitmapFont();
		this.setScreen(new Start(this));

	}

	public SpriteBatch getBatch() {
		return batch;
	}

	@Override
	public void render () {
		super.render();
	}

	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
