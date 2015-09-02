package data;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public final class CharacterSelectImages {
	
	public static BitmapFont font = new BitmapFont();
	
	public static final Texture[] textures = new Texture[] {
		new Texture("CharacterSelectScreen/character_images/lonrk.png")		//Lonrk
	};
	
	public static final Image[] images = new Image[] {
		new Image(textures[0])		//Lonrk
	};
	
	public static final TextureAtlas[] textureAtlases = new TextureAtlas[] {
		new TextureAtlas("CharacterSelectScreen/lonrk_icon/lonrk.pack")		//Lonrk
	};
	
	private CharacterSelectImages() {
		
	}
	
	
}
