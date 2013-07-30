package com.game.rania.model.element;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class Font {

	static private String charaters = FreeTypeFontGenerator.DEFAULT_CHARS + "ÉÖÓÊÅÍÃØÙÇÕÚÔÛÂÀÏĞÎËÄÆİß×ÑÌÈÒÜÁŞ¨éöóêåíãøùçõúôûâàïğîëäæıÿ÷ñìèòüáş¸";
	static private HashMap<String, BitmapFont> fonts = new HashMap<String, BitmapFont>();
	
	static public void loadFont(String fontFile, int ... fontSizes){
		try
		{
			FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(fontFile));
			for (int i = 0; i < fontSizes.length; i++){
				int fontSize = fontSizes[i];
				BitmapFont font = generator.generateFont(fontSize, charaters, false);
				font.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
				fonts.put(fontFile + String.valueOf(fontSize), font);
			}
			generator.dispose();
		} 
		catch(Exception ex)
		{
			Gdx.app.log("Error", "Font not found: " + fontFile);
		}
	}
	
	static public BitmapFont loadFont(String fontFile, int size){
		try
		{
			FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(fontFile));
			BitmapFont font = generator.generateFont(size, charaters, false);
			font.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
			fonts.put(fontFile + String.valueOf(size), font);
			generator.dispose();
			return font;
		} 
		catch(Exception ex)
		{
			Gdx.app.log("Error", "Font not found: " + fontFile);
			return null;
		}
	}
	
	static public BitmapFont getFont(String fontFile, int size){
		BitmapFont font = fonts.get(fontFile + String.valueOf(size));
		if (font != null)
			return font;
		return loadFont(fontFile, size);
	}
}
