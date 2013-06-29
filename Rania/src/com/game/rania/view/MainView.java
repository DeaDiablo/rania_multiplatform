package com.game.rania.view;

import java.util.EnumMap;
import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.game.rania.RaniaGame;
import com.game.rania.model.Text;
import com.game.rania.model.element.Font;
import com.game.rania.model.element.Object;
import com.game.rania.model.element.RegionID;

public class MainView {
	
	//camera
	private Camera camera = null;
	private Camera cameraHUD = null;
	
	//sprites
	private SpriteBatch spriteBatch = null;
	private ShapeRenderer shapeRenderer = null;
	
	//fps
	private Text  fps = null;
	
	//textures
	private HashMap<String, Texture> textures = new HashMap<String, Texture>();
	private EnumMap<RegionID, TextureRegion> textureRegions = new EnumMap<RegionID, TextureRegion>(RegionID.class);
	
	public MainView(float widthCamera, float heightCamera){
		//create camera
		camera = new Camera(widthCamera, heightCamera);
		cameraHUD = new Camera(widthCamera, heightCamera);
		
		spriteBatch = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();
		fps = new Text("", Font.getFont("data/fonts/Postmodern One.ttf", 30), new Color(1, 1, 1, 1), 0, 0);
	}
	
	public TextureRegion loadTexture(String fileTexture, RegionID id) {
		Texture texture = textures.get(fileTexture);
		if (texture == null)
		{
			texture = new Texture(Gdx.files.internal(fileTexture));
			textures.put(fileTexture, texture);
		}
		TextureRegion region = new TextureRegion(texture);
		texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		textureRegions.put(id, region);
		return region;
	}
	
	public TextureRegion loadTexture(String fileTexture, RegionID id, int x, int y, int width, int height) {
		Texture texture = textures.get(fileTexture);
		if (texture == null)
		{
			texture = new Texture(Gdx.files.internal(fileTexture));
			texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
			textures.put(fileTexture, texture);
		}
		TextureRegion region = new TextureRegion(texture, x, y, width, height);
		textureRegions.put(id, region);
		return region;
	}
	
	public void unloadTexture(String fileTexture){
		textures.remove(fileTexture);
	}
	
	public Texture getTexture(String fileTexture){
		return textures.get(fileTexture);
	}
	
	public TextureRegion getTextureRegion(RegionID id){
		return textureRegions.get(id);
	}
	
	public Texture getTexture(RegionID id){
		TextureRegion regTexture = textureRegions.get(id);
		if (regTexture == null)
			return null;
		return regTexture.getTexture();
	}
	
	public Camera getCamera(){
		return camera;
	}
	
	public void setCamera(Camera cam){
		camera = cam;
	}

	public Camera getHUDCamera(){
		return cameraHUD;
	}
	
	public void setHUDCamera(Camera cam){
		cameraHUD = cam;
	}
	
	public SpriteBatch getSpriteBatch(){
		return spriteBatch;
	}
	
	public ShapeRenderer getShapeRenderer(){
		return shapeRenderer;
	}
	
	public void clear(){
		textureRegions.clear();
		for(Texture texture : textures.values()){
			texture.dispose();
		}
		textures.clear();
	}
	
	public void draw(){
		if (camera == null)
			return;
		
		//for fps show
		fps.content = String.valueOf(Gdx.graphics.getFramesPerSecond());

		//update camera
		camera.update();
		cameraHUD.update();
		
		//start render
		spriteBatch.setProjectionMatrix(camera.combined);
		spriteBatch.begin();
		//render sprite objects
		for (Object object : RaniaGame.mController.getObjects()) {
			object.draw(spriteBatch);
		}
		spriteBatch.end();

		shapeRenderer.setProjectionMatrix(camera.combined);
		//render shape objects
		for (Object object : RaniaGame.mController.getObjects()) {
			object.draw(shapeRenderer);
		}

		//HUD sprite render
		spriteBatch.setProjectionMatrix(cameraHUD.combined);
		spriteBatch.begin();
		//render HUD objects
		for (Object object : RaniaGame.mController.getHUDObjects()) {
			object.draw(spriteBatch);
		}
		fps.draw(spriteBatch, cameraHUD.getLeft() + fps.getTextBound().width * 0.5f, cameraHUD.getTop() - fps.getTextBound().height * 0.5f);
		spriteBatch.end();
		
		//HUD shape render
		shapeRenderer.setProjectionMatrix(cameraHUD.combined);
		for (Object object : RaniaGame.mController.getHUDObjects()) {
			object.draw(shapeRenderer);
		}
	}
}
