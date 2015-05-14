package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.mygdx.game.MyGdxGame;

public class OnePlayerCharacterSelect extends Screen{
	private Texture picture;
	private Image background;
	private MyGdxGame game;
	private BitmapFont font;
	private Screen back;
	private TextButton[] buttons;
	public static final int NUM_CHARACTERS = 20;

	private Stage stage;
	private TextButton backButton;
	private TextButtonStyle textButtonStyle;
	private TextButtonStyle textButtonStyleBack;
	private Skin skin;
	private Skin skinBack;
	private TextureAtlas buttonAtlas;
	private TextureAtlas buttonAtlasBack;

	private float buttonHeight;
	private float buttonWidth;

	public OnePlayerCharacterSelect(MyGdxGame game) {
		stage = new Stage();
		this.game = game;
		
		font = new BitmapFont();
		skin = new Skin();
		buttonAtlas = new TextureAtlas("CharacterSelectScreen/pictures/locked.pack");
		skin.addRegions(buttonAtlas);
		textButtonStyle = new TextButtonStyle();
		textButtonStyle.font = font;
		textButtonStyle.up = skin.getDrawable("Locked");
		textButtonStyle.over = skin.getDrawable("Locked_hover");
		textButtonStyle.checkedOver = skin.getDrawable("Locked_hover");
		
		create();
		stage.addActor(background);
		stage.addActor(backButton);
		for(int i = 0; i < buttons.length; i++) {
			stage.addActor(buttons[i]);
		}
	}

	public void addScreens(Screen back) {
		this.back = back;
	}

	public void create() {
		picture = new Texture("CharacterSelectScreen/background.png");
		background = new Image(picture);
		background.setHeight((float)game.height);
		background.setWidth((float)game.width);
		buttonHeight = background.getHeight()/7;
		buttonWidth = background.getWidth()/10;
		createBackButton();
		createButtons();
	}

	public void createButtons() {
		float initialPositionX = 0;
		float initialPositionY = background.getHeight()/2 + 100;
		buttons = new TextButton[NUM_CHARACTERS];
		for(int i = 0; i < buttons.length; i++) {
			if(i == 10) {
				initialPositionY -= buttonHeight;
				initialPositionX = 0;
			}
			buttons[i] = new TextButton("", textButtonStyle);
			buttons[i].setHeight(buttonHeight);
			buttons[i].setWidth(buttonWidth);
			buttons[i].setPosition(initialPositionX, initialPositionY);
			initialPositionX += buttonWidth;
		}
	}

	public void createBackButton() {
		skinBack = new Skin();
		buttonAtlasBack = new TextureAtlas("back/goBack.pack");

		skinBack.addRegions(buttonAtlasBack);
		textButtonStyleBack = new TextButtonStyle();
		textButtonStyleBack.font = font;
		textButtonStyleBack.up = skinBack.getDrawable("backButton");
		backButton = new TextButton("", textButtonStyleBack);

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
	
	public void show() {
		Gdx.input.setInputProcessor(stage);
	}

	public void render() {
		update();
		stage.act();
		stage.draw();
	}

	public void update() {

	}
}
