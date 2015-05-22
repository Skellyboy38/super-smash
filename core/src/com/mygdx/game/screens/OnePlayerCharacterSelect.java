package com.mygdx.game.screens;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.MyGdxGame;

public class OnePlayerCharacterSelect extends Screen{
	public static final int NUM_CHARACTERS = 68;		//The number of characters to choose from.
	
	private Texture picture;
	private Image background;
	private Texture chalkManIcon;
	private Image chalkMan;
	private Image[] icons;
	private MyGdxGame game;
	private BitmapFont font;
	private Screen back;			//The play screen.
	private Screen next;
	private TextButton[] buttons;	//All the character choices are represented with their own button.
	private SpriteBatch batch;
	private Array<Actor> addRemoveActors;
	private ArrayList<Character> characters;
	private Stage stage;
	
	private Group first;
	private Group second;
	private Group third;
	
	private TextButton add1;
	private TextButton add2;
	private TextButton add3;
	private TextButton add4;
	
	private TextButton remove1;
	private TextButton remove2;
	private TextButton remove3;
	private TextButton remove4;
	
	private TextButton token1;
	private TextButton token2;
	private TextButton token3;
	private TextButton token4;
	
	private TextButton backButton;
	
	private boolean[] characterExists;	//Keeps track of which players are playing (1 to 4).
	private boolean[] tokenMovable;
	public final String START_MESSAGE = "Press Enter to start"; //When all characters are chosen, this message appears.
	private boolean canStart;	//Determines whether or not the game can begin.
	
	private TextButtonStyle tokenStyle;
	private TextButtonStyle textButtonStyle;
	private TextButtonStyle textButtonStyleBack;
	private TextButtonStyle addStyle;
	private TextButtonStyle removeStyle;
	private TextButtonStyle chalkManStyle;
	
	private Skin tokenSkin;
	private Skin skin;
	private Skin addSkin;
	private Skin removeSkin;
	private Skin skinBack;
	
	private TextureAtlas tokenAtlas;
	private TextureAtlas buttonAtlas;
	private TextureAtlas buttonAtlasBack;		//Anything with "back" at the end pertains to the back button.
	private TextureAtlas addAtlas;
	private TextureAtlas removeAtlas;
	private TextureAtlas chalkManAtlas;
	
	private float buttonHeight;
	private float buttonWidth;

	public OnePlayerCharacterSelect(MyGdxGame game, SpriteBatch batch) {
		super(game);
		this.game = game;
		this.batch = batch;
		canStart = false;
		stage = new Stage();
		first = new Group();
		second = new Group();
		third = new Group();
		characters = new ArrayList<Character>();
		
		tokenMovable = new boolean[] {false, false, false, false};
		characterExists = new boolean[] {false, false, false, false};
		addRemoveActors = new Array<Actor>();
		font = new BitmapFont();
		font.setScale(2);
		font.setColor(255,255,255,1);
		chalkManIcon = new Texture("CharacterSelectScreen/character_images/chalk_man.png");
		chalkMan = new Image(chalkManIcon);
		chalkMan.setPosition(game.GAME_WIDTH/9.80392F, game.GAME_HEIGHT/15.07692F);
		chalkMan.setHeight(game.GAME_HEIGHT/2.5789F);
		chalkMan.setWidth(game.GAME_WIDTH/4.46428F);
		chalkMan.setZIndex(1);
		icons = new Image[NUM_CHARACTERS];
		icons[0] = chalkMan;
		
		create();
		addRemoveActors.add(add1);
		addRemoveActors.add(add2);
		addRemoveActors.add(add3);
		addRemoveActors.add(add4);
		addRemoveActors.add(remove1);
		addRemoveActors.add(remove2);
		addRemoveActors.add(remove3);
		addRemoveActors.add(remove4);
		first.addActor(background);
		for(int i = 0; i < buttons.length; i++) {
			second.addActor(buttons[i]);
		}
		third.addActor(backButton);
		third.addActor(add1);
		third.addActor(add2);
		third.addActor(add3);
		third.addActor(add4);
		
		stage.addActor(first);
		stage.addActor(second);
		stage.addActor(third);
		
		
	}

	public void addScreens(Screen back, Screen next) {
		this.back = back;
		this.next = next;
	}

	public void create() {
		skin = new Skin();
		buttonAtlas = new TextureAtlas("CharacterSelectScreen/pictures/locked.pack");
		chalkManAtlas = new TextureAtlas("CharacterSelectScreen/chalk_man_icon/chalk_man.pack");
		skin.addRegions(buttonAtlas);
		skin.addRegions(chalkManAtlas);
		textButtonStyle = new TextButtonStyle();
		chalkManStyle = new TextButtonStyle();
		chalkManStyle.font = font;
		textButtonStyle.font = font;
		textButtonStyle.up = skin.getDrawable("Locked");
		textButtonStyle.over = skin.getDrawable("Locked_hover");
		textButtonStyle.checkedOver = skin.getDrawable("Locked_hover");
		chalkManStyle.up = skin.getDrawable("Chalk_man");
		
		addStyle = new TextButtonStyle();
		addSkin = new Skin();
		addAtlas = new TextureAtlas("CharacterSelectScreen/add_button/add.pack");
		addSkin.addRegions(addAtlas);
		addStyle.font = font;
		addStyle.up = addSkin.getDrawable("Add");
		removeStyle = new TextButtonStyle();
		removeSkin = new Skin();
		removeAtlas = new TextureAtlas("CharacterSelectScreen/remove_button/remove.pack");
		removeSkin.addRegions(removeAtlas);
		removeStyle.font = font;
		removeStyle.up = removeSkin.getDrawable("Remove");
		characterExists = new boolean[4];
		
		for(int i = 0; i < characterExists.length; i++) {
			characterExists[i] = false;
		}
		picture = new Texture("CharacterSelectScreen/background.png");
		background = new Image(picture);
		background.setHeight(game.GAME_HEIGHT);
		background.setWidth(game.GAME_WIDTH);
		background.setPosition(0, 0);
		background.setZIndex(0);
		buttonHeight = 75;
		buttonWidth = 75;
		createBackButton();
		createButtons();
	}

	public void createButtons() {
		float initialPositionX = game.GAME_WIDTH/51.724F;
		float initialPositionY = game.GAME_HEIGHT/1.291F;
		buttons = new TextButton[NUM_CHARACTERS];
		buttons[0] = new TextButton("", chalkManStyle);
		buttons[0].setHeight(buttonHeight);
		buttons[0].setWidth(buttonWidth);
		buttons[0].setPosition(initialPositionX, initialPositionY);
		initialPositionX += buttonWidth + buttonWidth*0.05;
		for(int i = 1; i < buttons.length; i++) {
			if(initialPositionX + buttonWidth >= game.GAME_WIDTH) {
				initialPositionY -= buttonHeight + buttonHeight*0.05;
				initialPositionX = game.GAME_WIDTH/51.724F;
			}
			buttons[i] = new TextButton("", textButtonStyle);
			buttons[i].setHeight(buttonHeight);
			buttons[i].setWidth(buttonWidth);
			buttons[i].setPosition(initialPositionX, initialPositionY);
			initialPositionX += buttonWidth + buttonWidth*0.05;
		}
		
		add1 = new TextButton("", addStyle);
		add1.setPosition(game.GAME_WIDTH/8.287F, game.GAME_HEIGHT/12.25F);
		add1.setHeight(game.GAME_HEIGHT/20F);
		add1.setWidth(game.GAME_WIDTH/9F);
		add1.setZIndex(100);
		add1.addListener(new InputListener() {
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				addRemoveActors.get(0).remove();
				third.addActor(remove1);
				third.addActor(token1);
				characterExists[0] = true;
				return true;
			}
		});
		
		add2 = new TextButton("", addStyle);
		add2.setPosition(game.GAME_WIDTH/3.125F, game.GAME_HEIGHT/12.25F);
		add2.setHeight(game.GAME_HEIGHT/20F);
		add2.setWidth(game.GAME_WIDTH/9F);
		add2.setZIndex(100);
		add2.addListener(new InputListener() {
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				addRemoveActors.get(1).remove();
				third.addActor(remove2);
				third.addActor(token2);
				characterExists[1] = true;
				return true;
			}
		});
		
		add3 = new TextButton("", addStyle);
		add3.setPosition(game.GAME_WIDTH/1.948F, game.GAME_HEIGHT/12.25F);
		add3.setHeight(game.GAME_HEIGHT/20F);
		add3.setWidth(game.GAME_WIDTH/9F);
		add3.setZIndex(100);
		add3.addListener(new InputListener() {
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				addRemoveActors.get(2).remove();
				third.addActor(remove3);
				third.addActor(token3);
				characterExists[2] = true;
				return true;
			}
		});
		
		add4 = new TextButton("", addStyle);
		add4.setPosition(game.GAME_WIDTH/1.411F, game.GAME_HEIGHT/12.25F);
		add4.setHeight(game.GAME_HEIGHT/20F);
		add4.setWidth(game.GAME_WIDTH/9F);
		add4.setZIndex(100);
		add4.addListener(new InputListener() {
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				addRemoveActors.get(3).remove();
				third.addActor(remove4);
				third.addActor(token4);
				characterExists[3] = true;
				return true;
			}
		});
		
		remove1 = new TextButton("", removeStyle);
		remove1.setPosition(game.GAME_WIDTH/8.287F, game.GAME_HEIGHT/12.25F);
		remove1.setHeight(game.GAME_HEIGHT/20F);
		remove1.setWidth(game.GAME_WIDTH/9F);
		remove1.setZIndex(100);
		remove1.addListener(new InputListener() {
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				addRemoveActors.get(4).remove();
				third.addActor(add1);
				token1.remove();
				characterExists[0] = false;
				token1.setPosition(game.GAME_WIDTH/3.926F, game.GAME_HEIGHT/3.091F);
				return true;
			}
		});
		
		remove2 = new TextButton("", removeStyle);
		remove2.setPosition(game.GAME_WIDTH/3.125F, game.GAME_HEIGHT/12.25F);
		remove2.setHeight(game.GAME_HEIGHT/20F);
		remove2.setWidth(game.GAME_WIDTH/9F);
		remove2.setZIndex(100);
		remove2.addListener(new InputListener() {
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				addRemoveActors.get(5).remove();
				third.addActor(add2);
				token2.remove();
				characterExists[1] = false;
				token2.setPosition(game.GAME_WIDTH/2.228F, game.GAME_HEIGHT/3.091F);
				return true;
			}
		});
		
		remove3 = new TextButton("", removeStyle);
		remove3.setPosition(game.GAME_WIDTH/1.948F, game.GAME_HEIGHT/12.25F);
		remove3.setHeight(game.GAME_HEIGHT/20F);
		remove3.setWidth(game.GAME_WIDTH/9F);
		remove3.setZIndex(100);
		remove3.addListener(new InputListener() {
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				addRemoveActors.get(6).remove();
				third.addActor(add3);
				token3.remove();
				characterExists[2] = false;
				token3.setPosition(game.GAME_WIDTH/1.554F, game.GAME_HEIGHT/3.091F);
				return true;
			}
		});
		
		remove4 = new TextButton("", removeStyle);
		remove4.setPosition(game.GAME_WIDTH/1.411F, game.GAME_HEIGHT/12.25F);
		remove4.setHeight(game.GAME_HEIGHT/20F);
		remove4.setWidth(game.GAME_WIDTH/9F);
		remove4.setZIndex(100);
		remove4.addListener(new InputListener() {
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				addRemoveActors.get(7).remove();
				third.addActor(add4);
				token4.remove();
				characterExists[3] = false;
				token4.setPosition(game.GAME_WIDTH/1.191F, game.GAME_HEIGHT/3.091F);
				return true;
			}
		});
		
		tokenSkin = new Skin();
		tokenAtlas = new TextureAtlas("CharacterSelectScreen/token/token.pack");
		tokenSkin.addRegions(tokenAtlas);
		tokenStyle = new TextButtonStyle();
		tokenStyle.font = font;
		tokenStyle.up = tokenSkin.getDrawable("Token");
		
		token1 = new TextButton("", tokenStyle);
		token1.setPosition(game.GAME_WIDTH/3.926F, game.GAME_HEIGHT/3.091F);
		token1.setHeight(50);
		token1.setWidth(50);
		token1.addListener(new InputListener() {
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				tokenMovable[0] = !tokenMovable[0];
				return true;
			}
		});
		token2 = new TextButton("", tokenStyle);
		token2.setPosition(game.GAME_WIDTH/2.228F, game.GAME_HEIGHT/3.091F);
		token2.setHeight(50);
		token2.setWidth(50);
		token2.addListener(new InputListener() {
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				tokenMovable[1] = !tokenMovable[1];
				return true;
			}
		});
		token3 = new TextButton("", tokenStyle);
		token3.setPosition(game.GAME_WIDTH/1.554F, game.GAME_HEIGHT/3.091F);
		token3.setHeight(50);
		token3.setWidth(50);
		token3.addListener(new InputListener() {
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				tokenMovable[2] = !tokenMovable[2];
				return true;
			}
		});
		token4 = new TextButton("", tokenStyle);
		token4.setPosition(game.GAME_WIDTH/1.191F, game.GAME_HEIGHT/3.091F);
		token4.setHeight(50);
		token4.setWidth(50);
		token4.addListener(new InputListener() {
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				tokenMovable[3] = !tokenMovable[3];
				return true;
			}
		});
		
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
				clear();
				return true;
			}
		});
	}
	
	public void show() {
		Gdx.input.setInputProcessor(stage);
	}
	
	public void clear() {
		remove1.remove();
		remove2.remove();
		remove3.remove();
		remove4.remove();
		
		second.addActor(add1);
		second.addActor(add2);
		second.addActor(add3);
		second.addActor(add4);
		
		token1.setPosition(game.GAME_WIDTH/3.926F, game.GAME_HEIGHT/3.091F);
		token2.setPosition(game.GAME_WIDTH/2.228F, game.GAME_HEIGHT/3.091F);
		token3.setPosition(game.GAME_WIDTH/1.554F, game.GAME_HEIGHT/3.091F);
		token4.setPosition(game.GAME_WIDTH/1.191F, game.GAME_HEIGHT/3.091F);
		canStart = false;
		characterExists[0] = false;
		characterExists[1] = false;
		characterExists[2] = false;
		characterExists[3] = false;
		
	}

	public void render() {
		super.render();
		stage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		stage.act();
		stage.draw();
		update();	//Call update last so that the background does not cover the play message.
	}

	public void update() {
		batch.begin();
		if(tokenMovable[0]) {
			token1.setPosition(Gdx.input.getX() - token1.getWidth()/2, game.GAME_HEIGHT - Gdx.input.getY() - token1.getHeight()/2);
		}
		if(tokenMovable[1]) {
			token2.setPosition(Gdx.input.getX() - token1.getWidth()/2, game.GAME_HEIGHT - Gdx.input.getY() - token1.getHeight()/2);
		}
		if(tokenMovable[2]) {
			token3.setPosition(Gdx.input.getX() - token1.getWidth()/2, game.GAME_HEIGHT - Gdx.input.getY() - token1.getHeight()/2);
		}
		if(tokenMovable[3]) {
			token4.setPosition(Gdx.input.getX() - token1.getWidth()/2, game.GAME_HEIGHT - Gdx.input.getY() - token1.getHeight()/2);
		}
		
		if(((token1.getX() + token1.getWidth()/2) >= buttons[0].getX() && (token1.getX() + token1.getWidth()/2) <= buttons[0].getX()+buttons[0].getWidth()) && 
				((token1.getY() + token1.getHeight()/2) >= buttons[0].getY() && (token1.getY() + token1.getHeight()/2) <= buttons[0].getY()+buttons[0].getHeight())) {
			second.addActor(icons[0]);
		}
		else
			icons[0].remove();
		
		if(characterExists[0] || characterExists[1] || characterExists[2] || characterExists[3]) {
			canStart = true;
		}
		else
			canStart = false;
		if(canStart) {
			font.draw(batch, START_MESSAGE , game.GAME_WIDTH/5.747F, game.GAME_HEIGHT/1.05F);
			if(Gdx.input.isKeyPressed(66)) {
				game.changeScreen(next);
			}
		}
		batch.end();
	}
}
