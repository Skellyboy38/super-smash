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

	public FightScreen(MyGdxGame game, SpriteBatch batch, Map map, ArrayList<Fighter> characters, Stage stage) {
		super(game);
		this.map = map;
		this.batch = batch;
		this.characters = characters;
		this.game = game;
		this.stage = stage;
		camera = (OrthographicCamera)stage.getCamera();
	}
	
	public void create() {
		
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
		camera.setToOrtho(true, characters.get(0).getWidth()*2, characters.get(0).getHeight()*2);
		camera.position.set(characters.get(0).getPosition(), 0);
		camera.update();
		stage.getViewport().update((int)characters.get(0).getWidth()*2, (int)characters.get(0).getHeight()*2);
	}
	
	public void update() {
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
