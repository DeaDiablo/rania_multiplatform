package com.game.rania.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.game.rania.RaniaGame;
import com.game.rania.controller.Controllers;
import com.game.rania.model.element.Object;
import com.game.rania.model.element.RegionID;
import com.game.rania.utils.Shader;

public class Planet extends Object{

	public int id				= -1;
	public int type      		= -1;
	public int speed			=  0;
	public int orbit			=  0;
	public int radius			=  0;
	public int atmosphere		= -1;
	public String name    	    = "";
	public int  idLocation   	= -1;
	public Star star 			= null;
	public Shader shader	    = new Shader("data/shaders/main.vert", "data/shaders/planet.frag") ;
	private Texture cloudTexture = null;
	
	private static final float radianAndTime = MathUtils.PI / 180.0f / 3600.0f / 100.0f; 
	private float time = 0.0f;
	private float dt = 0.0f;
	private final float cloudSpeedX = 0.005f;
	private final float cloudSpeedY = 0.002f;
	private Vector2 v_speed = new Vector2();

	public Planet(int id, String name, int type, int radius, int atmosphere, int speed, int orbit, int idLocation) {
		super(RegionID.fromInt(RegionID.PLANET_0.ordinal() + type), 0, 0);
		cloudTexture = RaniaGame.mView.getTexture(RegionID.CLOUDS);
		this.id = id;
		this.name = name;
		this.type = type;
		this.radius = radius;
		this.atmosphere = atmosphere;
		this.speed = speed;
		this.orbit = orbit;
		this.idLocation = idLocation;
		this.star = Controllers.locController.getLocation(idLocation).star;
		zIndex = Indexes.planets;
		updatePosition();
	}
	
	public void updatePosition(){
		time = Controllers.netController.getServerTime();
		calcPosition(time);
		if (region != null)
			scale.set(2.0f * radius / region.getRegionWidth(), 2.0f * radius / region.getRegionHeight());
	}
	@Override
	public void update(float deltaTime){
		dt += deltaTime;
		v_speed.set(cloudSpeedX * dt, cloudSpeedY * dt);
		calcPosition(time + dt);
	}
	
	@Override
	public boolean draw(SpriteBatch sprite){
		if (star == null)
			return false;
		if (shader.isCompiled())
			return shaderDraw(sprite);
		return super.draw(sprite);
	}
	
	protected boolean shaderDraw(SpriteBatch sprite) {
		if (!visible)
			return false;

		sprite.setShader(shader);

		cloudTexture.bind(1);
		Gdx.gl.glActiveTexture(GL10.GL_TEXTURE0);
		shader.setUniformi("u_texture2", 1);
		shader.setUniformf("v_speed", v_speed);
		
		super.draw(sprite);

		sprite.setShader(null);

		return true;
	}

	private void calcPosition(float currentTime) {
		if (star == null)
			return;
		position.set(MathUtils.cos(speed * currentTime * radianAndTime), 
					 MathUtils.sin(speed * currentTime * radianAndTime));
		position.mul(orbit);
		position.add(star.position);
		angle = speed * currentTime + 45.0f;
	}
}
