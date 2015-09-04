package com.mygdx.game.screens;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.characters.Fighter;
import com.mygdx.game.stages.Map;

public class FightScreen extends Screen{
	private Map map;
	private Stage stage;
	private OrthographicCamera camera;
	private ArrayList<Fighter> characters;
	private SpriteBatch batch;
	private Screen back;
	private MyGdxGame game;
	
	private float cameraWidth;
	private float cameraHeight;
	private float cameraPositionX;
	private float cameraPositionY;

	public FightScreen(MyGdxGame game, SpriteBatch batch, Map map, ArrayList<Fighter> characters, Stage stage) {
		super(game);
		this.map = map;
		this.batch = batch;
		this.characters = characters;
		this.game = game;
		this.stage = stage;
		camera = (OrthographicCamera)stage.getCamera();
		
		cameraWidth = MyGdxGame.GAME_WIDTH;
		cameraHeight = MyGdxGame.GAME_HEIGHT;
		cameraPositionX = 0;
		cameraPositionY = 0;
	}
	
	public void addCharacters(ArrayList<Fighter> characters) {
		this.characters = characters;
	}
	
	public void render() {
		super.render();
		stage.act();
		stage.draw();
		map.render();
		for(int i = 0; i < characters.size(); i++) {
			characters.get(i).render();
		}
		update();
	}
	
	public void addScreens(Screen back) {
		this.back = back;
	}
	
	public void clear() {
		for(int i = 0; i < characters.size(); i++) {
			characters.remove(i);
		}
		map = null;
	}
	
	public void updateCamera() {
		camera.viewportHeight = cameraHeight;
		camera.viewportWidth = cameraWidth;
		camera.translate(1,1);
	}
	
	public void update() {
		if(cameraHeight > 50 || cameraWidth > 50) {
			cameraHeight -= 0.05f;
			cameraWidth -= 0.05f;
		}
		//updateCamera();
		if(Intersector.overlaps(map.getCollisionBoxes()[0], characters.get(0).getCollisionBox())) {
			characters.get(0).capVerticalPosition();
		}
		else {
			characters.get(0).uncapVerticalPosition();
		}
		if(Gdx.input.isKeyPressed(31)) {
			clear();
			game.changeScreen(back);
		}
	}
	
	public void show() {
		Gdx.input.setInputProcessor(stage);
	}
	
	
	
}
