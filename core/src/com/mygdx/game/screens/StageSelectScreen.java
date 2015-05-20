package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.mygdx.game.MyGdxGame;

public class StageSelectScreen extends Screen{
	
	private Texture backgroundTexture;
	private Image background;
	private Stage stage;
	private TextButton[] buttons;
	private TextButton backButton;
	
	private BitmapFont font;
	private TextButtonStyle style;
	private TextureAtlas atlas;
	private Skin skin;
	
	private BitmapFont backFont;
	private TextButtonStyle backStyle;
	private TextureAtlas backAtlas;
	private Skin backSkin;
	
	private MyGdxGame game;
	private SpriteBatch batch;
	private Screen next;
	private Screen back;

	public StageSelectScreen(MyGdxGame game, SpriteBatch batch) {
		super(game);
		this.game = game;
		this.batch = batch;
		font = new BitmapFont();
		stage = new Stage();
		create();
		stage.addActor(background);
		stage.addActor(backButton);
	}
	
	public void create() {
		backgroundTexture = new Texture("stageSelect/background.png");
		background = new Image(backgroundTexture);
		background.setPosition(0, 0);
		background.setHeight(game.GAME_HEIGHT);
		background.setWidth(game.GAME_WIDTH);
		
		backStyle = new TextButtonStyle();
		backAtlas = new TextureAtlas("back/goBack.pack");
		backSkin = new Skin();
		backSkin.addRegions(backAtlas);
		backStyle.font = font;
		backStyle.up = backSkin.getDrawable("backButton");
		createButtons();
	}
	
	public void createButtons() {
		backButton = new TextButton("", backStyle);
		backButton.setHeight(100);
		backButton.setWidth(100);
		backButton.setPosition(50,50);
		
		backButton.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
            	game.changeScreen(back);
            	return true;
            }
        });
		
	}
	
	public void addScreens(Screen next, Screen back) {
		this.next = next;
		this.back = back;
	}
	
	public void update() {
		
	}
	
	public void show() {
		Gdx.input.setInputProcessor(stage);
	}
	
	public void render() {
		super.render();
		update();
		stage.act();
		stage.draw();
	}
	
	public void clear() {
		
	}

	
	
	
	
	
	
	
	
	
	
	
}
