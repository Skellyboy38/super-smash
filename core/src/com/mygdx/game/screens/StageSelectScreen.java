package com.mygdx.game.screens;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.stages.FinalDestination;
import com.mygdx.game.stages.Map;

public class StageSelectScreen extends Screen{
	public static final int NUMBER_STAGES = 10;
	private ArrayList<Character> characters;
	public FightScreen fight;
	
	private Texture backgroundTexture;
	private Texture zoomed;
	private Image background;
	private Stage stage;
	private TextButton[] buttons;
	private TextButton backButton;
	
	private BitmapFont font;
	private TextButtonStyle style;
	private TextureAtlas atlas;
	private Skin skin;
	
	private TextButtonStyle backStyle;
	private TextureAtlas backAtlas;
	private Skin backSkin;
	
	private MyGdxGame game;
	private SpriteBatch batch;
	private Screen back;
	
	private float buttonHeight;
	private float buttonWidth;
	private boolean drawZoomed;
	private float positionX;
	private float positionY;
	private boolean canChangeScreen = true;

	public StageSelectScreen(MyGdxGame game, SpriteBatch batch) {
		super(game);
		this.game = game;
		this.batch = batch;
		font = new BitmapFont();
		stage = new Stage();
		buttons = new TextButton[NUMBER_STAGES];
		buttonHeight = game.GAME_HEIGHT/5;
		buttonWidth = game.GAME_WIDTH/8;
		drawZoomed = false;
		positionX = -500;
		positionY = game.GAME_HEIGHT/3;
		create();
		stage.addActor(background);
		stage.addActor(backButton);
		for(int i = 0; i < buttons.length; i++) {
			stage.addActor(buttons[i]);
		}
	}
	
	public void create() {
		zoomed = new Texture("stageSelect/stage_image/Blank1.9.png");
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
		
		style = new TextButtonStyle();
		atlas = new TextureAtlas("stageSelect/stage_image/blank.pack");
		skin = new Skin();
		skin.addRegions(atlas);
		style.font = font;
		style.up = skin.getDrawable("Blank1");
		style.over = skin.getDrawable("Blank2");
		style.checkedOver = skin.getDrawable("Blank2");
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
            	clear();
            	return true;
            }
        });
		
		float divisor = 1.8F;
		float posX = game.GAME_WIDTH/divisor;
		float posY = game.GAME_HEIGHT/1.5F;
		
		for(int i = 0; i < buttons.length; i++) {
			buttons[i] = new TextButton("", style);
			buttons[i].setHeight(buttonHeight);
			buttons[i].setWidth(buttonWidth);
			buttons[i].setPosition(posX, posY);
			posX += buttons[i].getWidth()*1.05F;
			if(posX + buttons[i].getWidth() > game.GAME_WIDTH) {
				divisor += 0.2F;
				posX = game.GAME_WIDTH/divisor;
				posY -= buttons[i].getHeight()*1.05F;
			}
			buttons[i].addListener(new ClickListener() {
				public void enter (InputEvent event, float x, float y, int pointer, Actor fromButton) {
					drawZoomed = true;
					positionX = -500;
				}
			});
		}
		
		buttons[0].addListener(new ClickListener() {
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				FinalDestination finalDestination = new FinalDestination(batch);
				Map map = finalDestination;
				fight = new FightScreen(game, batch, map, characters);
				fight.addScreens(back);
				game.changeScreen(fight);
            	return true;
            }
		});
	}
	
	public void addScreens(Screen back) {
		this.back = back;
	}
	
	public void passCharacters(ArrayList<Character> characters) {
		this.characters = characters;
	}
	
	public void update() {
		if(drawZoomed) {
			positionX = positionX + 20;
		}
		if(positionX >= game.GAME_WIDTH/20) {
			drawZoomed = false;
		}
	}
	
	public void show() {
		Gdx.input.setInputProcessor(stage);
	}
	
	public void render() {
		super.render();
		stage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		update();
		stage.act();
		stage.draw();
		batch.begin();
		batch.draw(zoomed, positionX, positionY, 500, 400);
		batch.end();
	}
	
	public void clear() {
		positionX = -500;
		
	}

	
	
	
	
	
	
	
	
	
	
	
}
