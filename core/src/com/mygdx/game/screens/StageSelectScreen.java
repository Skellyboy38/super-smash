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
import com.mygdx.game.characters.Fighter;
import com.mygdx.game.stages.FinalDestination;
import com.mygdx.game.stages.Map;

public class StageSelectScreen extends Screen{
	public static final float ZOOMED_WIDTH = MyGdxGame.GAME_WIDTH/3.25424F;
	public static final float ZOOMED_HEIGHT = MyGdxGame.GAME_HEIGHT/2.76923F;
	
	public static final int NUMBER_STAGES = 10;
	private ArrayList<Fighter> characters;
	public FightScreen fight;
	
	private Texture backgroundTexture;
	private Texture final_destination_zoomed;
	private Image final_destination_zoomed_image;
	private Texture empty_zoomed;
	private Image empty_zoomed_image;
	private Image background;
	private Stage stage;
	private ArrayList<Actor> addRemoveActors;
	private TextButton[] buttons;
	private TextButton backButton;
	
	private BitmapFont font;
	private TextButtonStyle style;
	private TextureAtlas atlas;
	private Skin skin;
	
	private TextButtonStyle final_destination_style;
	private TextureAtlas final_destination_atlas;
	private Skin final_destination_skin;
	
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

	public StageSelectScreen(MyGdxGame game, SpriteBatch batch) {
		super(game);
		this.game = game;
		this.batch = batch;
		font = new BitmapFont();
		stage = new Stage();
		addRemoveActors = new ArrayList<Actor>();
		buttons = new TextButton[NUMBER_STAGES];
		buttonHeight = MyGdxGame.GAME_HEIGHT/5;
		buttonWidth = MyGdxGame.GAME_WIDTH/8;
		
		drawZoomed = false;				//Whether or not to draw the zoomed image
		
		positionX = -500;
		positionY = MyGdxGame.GAME_HEIGHT/3;
		
		create();
		stage.addActor(background);
		stage.addActor(backButton);
		for(int i = 0; i < buttons.length; i++) {
			stage.addActor(buttons[i]);
		}
	}
	
	public void create() {
		empty_zoomed = new Texture("stageSelect/stage_image/blank/Blank1.9.png");
		empty_zoomed_image = new Image(empty_zoomed);
		empty_zoomed_image.setWidth(ZOOMED_WIDTH);
		empty_zoomed_image.setHeight(ZOOMED_HEIGHT);
		
		addRemoveActors.add(empty_zoomed_image);
		
		final_destination_zoomed = new Texture("stageSelect/stage_image/final_destination/Final_destination.9.png");
		final_destination_zoomed_image = new Image(final_destination_zoomed);
		final_destination_zoomed_image.setWidth(ZOOMED_WIDTH);
		final_destination_zoomed_image.setHeight(ZOOMED_HEIGHT);
		
		backgroundTexture = new Texture("stageSelect/background.png");
		background = new Image(backgroundTexture);
		background.setPosition(0, 0);
		background.setHeight(MyGdxGame.GAME_HEIGHT);
		background.setWidth(MyGdxGame.GAME_WIDTH);
		
		backStyle = new TextButtonStyle();
		backAtlas = new TextureAtlas("back/goBack.pack");
		backSkin = new Skin();
		backSkin.addRegions(backAtlas);
		backStyle.font = font;
		backStyle.up = backSkin.getDrawable("backButton");
		
		style = new TextButtonStyle();
		atlas = new TextureAtlas("stageSelect/stage_image/blank/blank.pack");
		skin = new Skin();
		skin.addRegions(atlas);
		style.font = font;
		style.up = skin.getDrawable("Blank1");
		style.over = skin.getDrawable("Blank2");
		style.checkedOver = skin.getDrawable("Blank2");
		
		final_destination_style = new TextButtonStyle();
		final_destination_atlas = new TextureAtlas("stageSelect/stage_image/final_destination/final_destination.pack");
		final_destination_skin = new Skin();
		final_destination_skin.addRegions(final_destination_atlas);
		final_destination_style.font = font;
		final_destination_style.up = final_destination_skin.getDrawable("Final_destination");
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
		float posX = MyGdxGame.GAME_WIDTH/divisor;
		float posY = MyGdxGame.GAME_HEIGHT/1.5F;
		//=======================================================================================
		buttons[0] = new TextButton("", final_destination_style);		//Creating the button for final destination
		buttons[0].setHeight(buttonHeight);
		buttons[0].setWidth(buttonWidth);
		buttons[0].setPosition(posX, posY);
		posX += buttons[0].getWidth()*1.05F;
		
		buttons[0].addListener(new ClickListener() {
			public void enter (InputEvent event, float x, float y, int pointer, Actor fromButton) {
				addRemoveActors.get(0).remove();
				addRemoveActors.remove(0);
				stage.addActor(final_destination_zoomed_image);
				addRemoveActors.add(final_destination_zoomed_image);
				drawZoomed = true;
				positionX = -500;
			}
		});
		
		if(posX + buttons[0].getWidth() > MyGdxGame.GAME_WIDTH) {
			divisor += 0.2F;
			posX = MyGdxGame.GAME_WIDTH/divisor;
			posY -= buttons[0].getHeight()*1.05F;
		}
		//=======================================================================================
		for(int i = 1; i < buttons.length; i++) {
			buttons[i] = new TextButton("", style);
			buttons[i].setHeight(buttonHeight);
			buttons[i].setWidth(buttonWidth);
			buttons[i].setPosition(posX, posY);
			posX += buttons[i].getWidth()*1.05F;
			if(posX + buttons[i].getWidth() > MyGdxGame.GAME_WIDTH) {
				divisor += 0.2F;
				posX = MyGdxGame.GAME_WIDTH/divisor;
				posY -= buttons[i].getHeight()*1.05F;
			}
			buttons[i].addListener(new ClickListener() {
				public void enter (InputEvent event, float x, float y, int pointer, Actor fromButton) {
					addRemoveActors.get(0).remove();
					addRemoveActors.remove(0);
					stage.addActor(empty_zoomed_image);
					addRemoveActors.add(empty_zoomed_image);
					drawZoomed = true;
					positionX = -500;
				}
			});
		}
		
		buttons[0].addListener(new ClickListener() {
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				FinalDestination finalDestination = new FinalDestination(batch);
				Map map = finalDestination;
				fight = new FightScreen(game, batch, map, characters, map.getStage());
				fight.addScreens(back);
				game.changeScreen(fight);
            	return true;
            }
		});
	}
	
	public void addScreens(Screen back) {
		this.back = back;
	}
	
	public void passCharacters(ArrayList<Fighter> characters) {
		this.characters = characters;
	}
	
	public void update() {
		if(drawZoomed) {
			positionX = positionX + 20;
		}
		if(positionX >= MyGdxGame.GAME_WIDTH/20) {
			drawZoomed = false;
		}
		empty_zoomed_image.setPosition(positionX, positionY);
		final_destination_zoomed_image.setPosition(positionX, positionY);
	}
	
	public void show() {
		Gdx.input.setInputProcessor(stage);
	}
	
	public void render() {
		super.render();
		update();
		stage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		stage.act();
		stage.draw();
	}
	
	public void clear() {
		positionX = -500;
		characters = null;
	}

	
	
	
	
	
	
	
	
	
	
	
}
