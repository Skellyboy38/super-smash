package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
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
	private Texture picture;
	private Image background;
	private MyGdxGame game;
	private BitmapFont font;
	private Screen back;			//The play screen.
	private TextButton[] buttons;	//All the character choices are represented with their own button.
	private TextButton add1;
	private TextButton add2;
	private TextButton add3;
	private TextButton add4;
	private TextButton remove1;
	private TextButton remove2;
	private TextButton remove3;
	private TextButton remove4;
	private boolean[] characterExists;	//Keeps track of which players are playing (1 to 4).
	private boolean[] tokenMovable;
	public static final int NUM_CHARACTERS = 68;		//The number of characters to choose from.
	public final String START_MESSAGE = "Press Enter to start"; //When all characters are chosen, this message appears.
	private boolean canStart;	//Determines whether or not the game can begin.
	private Texture token;
	private TextButton token1;
	private TextButton token2;
	private TextButton token3;
	private TextButton token4;
	private TextButtonStyle tokenStyle;
	private Skin tokenSkin;
	private TextureAtlas tokenAtlas;
	
	private Array<Actor> addRemoveActors;

	private Stage stage;
	private TextButton backButton;
	private TextButtonStyle textButtonStyle;
	private TextButtonStyle textButtonStyleBack;
	private TextButtonStyle addStyle;
	private TextButtonStyle removeStyle;
	private Skin skin;
	private Skin addSkin;
	private Skin removeSkin;
	private Skin skinBack;
	private TextureAtlas buttonAtlas;
	private TextureAtlas buttonAtlasBack;		//Anything with "back" at the end pertains to the back button.
	private TextureAtlas addAtlas;
	private TextureAtlas removeAtlas;

	private float buttonHeight;
	private float buttonWidth;

	public OnePlayerCharacterSelect(MyGdxGame game) {
		super(game);
		canStart = false;
		stage = new Stage();
		this.game = game;
		
		tokenMovable = new boolean[] {false, false, false, false};
		addRemoveActors = new Array<Actor>();
		font = new BitmapFont();
		skin = new Skin();
		buttonAtlas = new TextureAtlas("CharacterSelectScreen/pictures/locked.pack");
		skin.addRegions(buttonAtlas);
		textButtonStyle = new TextButtonStyle();
		textButtonStyle.font = font;
		textButtonStyle.up = skin.getDrawable("Locked");
		textButtonStyle.over = skin.getDrawable("Locked_hover");
		textButtonStyle.checkedOver = skin.getDrawable("Locked_hover");
		
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
		
		create();
		addRemoveActors.add(add1);
		addRemoveActors.add(add2);
		addRemoveActors.add(add3);
		addRemoveActors.add(add4);
		addRemoveActors.add(remove1);
		addRemoveActors.add(remove2);
		addRemoveActors.add(remove3);
		addRemoveActors.add(remove4);
		
		stage.addActor(background);
		stage.addActor(backButton);
		stage.addActor(add1);
		stage.addActor(add2);
		stage.addActor(add3);
		stage.addActor(add4);
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
		background.setHeight(game.GAME_HEIGHT);
		background.setWidth(game.GAME_WIDTH);
		background.setPosition(0, 0);
		buttonHeight = 75;
		buttonWidth = 75;
		createBackButton();
		createButtons();
	}

	public void createButtons() {
		float initialPositionX = 30;
		float initialPositionY = 5*(background.getHeight()/6);
		int numberButtonsPerRow = (int)((background.getWidth()-40)/buttonWidth);
		buttons = new TextButton[NUM_CHARACTERS];
		for(int i = 0; i < buttons.length; i++) {
			if(i%numberButtonsPerRow == 0) {
				initialPositionY -= buttonHeight;
				initialPositionX = 30;
			}
			buttons[i] = new TextButton("", textButtonStyle);
			buttons[i].setHeight(buttonHeight);
			buttons[i].setWidth(buttonWidth);
			buttons[i].setPosition(initialPositionX, initialPositionY);
			initialPositionX += buttonWidth;
		}
		
		add1 = new TextButton("", addStyle);
		add1.setPosition(game.GAME_WIDTH/8.287F, game.GAME_HEIGHT/12.25F);
		add1.setHeight(game.GAME_HEIGHT/20F);
		add1.setWidth(game.GAME_WIDTH/9F);
		add1.addListener(new InputListener() {
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				addRemoveActors.get(0).remove();
				stage.addActor(remove1);
				stage.addActor(token1);
				return true;
			}
		});
		
		add2 = new TextButton("", addStyle);
		add2.setPosition(game.GAME_WIDTH/3.125F, game.GAME_HEIGHT/12.25F);
		add2.setHeight(game.GAME_HEIGHT/20F);
		add2.setWidth(game.GAME_WIDTH/9F);
		add2.addListener(new InputListener() {
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				addRemoveActors.get(1).remove();
				stage.addActor(remove2);
				stage.addActor(token2);
				return true;
			}
		});
		
		add3 = new TextButton("", addStyle);
		add3.setPosition(game.GAME_WIDTH/1.948F, game.GAME_HEIGHT/12.25F);
		add3.setHeight(game.GAME_HEIGHT/20F);
		add3.setWidth(game.GAME_WIDTH/9F);
		add3.addListener(new InputListener() {
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				addRemoveActors.get(2).remove();
				stage.addActor(remove3);
				stage.addActor(token3);
				return true;
			}
		});
		
		add4 = new TextButton("", addStyle);
		add4.setPosition(game.GAME_WIDTH/1.411F, game.GAME_HEIGHT/12.25F);
		add4.setHeight(game.GAME_HEIGHT/20F);
		add4.setWidth(game.GAME_WIDTH/9F);
		add4.addListener(new InputListener() {
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				addRemoveActors.get(3).remove();
				stage.addActor(remove4);
				stage.addActor(token4);
				return true;
			}
		});
		
		remove1 = new TextButton("", removeStyle);
		remove1.setPosition(game.GAME_WIDTH/8.287F, game.GAME_HEIGHT/12.25F);
		remove1.setHeight(game.GAME_HEIGHT/20F);
		remove1.setWidth(game.GAME_WIDTH/9F);
		remove1.addListener(new InputListener() {
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				addRemoveActors.get(4).remove();
				stage.addActor(add1);
				token1.remove();
				return true;
			}
		});
		
		remove2 = new TextButton("", removeStyle);
		remove2.setPosition(game.GAME_WIDTH/3.125F, game.GAME_HEIGHT/12.25F);
		remove2.setHeight(game.GAME_HEIGHT/20F);
		remove2.setWidth(game.GAME_WIDTH/9F);
		remove2.addListener(new InputListener() {
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				addRemoveActors.get(5).remove();
				stage.addActor(add2);
				token2.remove();
				return true;
			}
		});
		
		remove3 = new TextButton("", removeStyle);
		remove3.setPosition(game.GAME_WIDTH/1.948F, game.GAME_HEIGHT/12.25F);
		remove3.setHeight(game.GAME_HEIGHT/20F);
		remove3.setWidth(game.GAME_WIDTH/9F);
		remove3.addListener(new InputListener() {
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				addRemoveActors.get(6).remove();
				stage.addActor(add3);
				token3.remove();
				return true;
			}
		});
		
		remove4 = new TextButton("", removeStyle);
		remove4.setPosition(game.GAME_WIDTH/1.411F, game.GAME_HEIGHT/12.25F);
		remove4.setHeight(game.GAME_HEIGHT/20F);
		remove4.setWidth(game.GAME_WIDTH/9F);
		remove4.addListener(new InputListener() {
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				addRemoveActors.get(7).remove();
				stage.addActor(add4);
				token4.remove();
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
		Array<Actor> actors = stage.getActors();
		for(int i = 0; i < actors.size; i++) {
			actors.get(i).remove();
		}
		stage.addActor(background);
		stage.addActor(backButton);
		stage.addActor(add1);
		stage.addActor(add2);
		stage.addActor(add3);
		stage.addActor(add4);
		for(int i = 0; i < buttons.length; i++) {
			stage.addActor(buttons[i]);
		}
		token1.setPosition(game.GAME_WIDTH/3.926F, game.GAME_HEIGHT/3.091F);
		token2.setPosition(game.GAME_WIDTH/2.228F, game.GAME_HEIGHT/3.091F);
		token3.setPosition(game.GAME_WIDTH/1.554F, game.GAME_HEIGHT/3.091F);
		token4.setPosition(game.GAME_WIDTH/1.191F, game.GAME_HEIGHT/3.091F);
		
	}

	public void render() {
		super.render();
		stage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		update();
		stage.act();
		stage.draw();
	}

	public void update() {
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
	}
}
