package com.game.rania.model;

import com.badlogic.gdx.graphics.Color;

public class Domain{
	
	static public final int unknow 	= 0;
	static public final int sectan 	= 1;
	static public final int mort 	= 2;
	static public final int erbo 	= 3;
	static public final int gurdin 	= 4;
	static public final int arah 	= 5;
	
	public int id;
	public String DomainName;
	
	static public Color getColor(int id){
		switch(id) {
		case sectan:
			return new Color(0.0f, 0.0f, 1.0f, 1.0f);

		case mort:
			return new Color(1.0f, 0.0f, 0.0f, 1.0f);

		case erbo:
			return new Color(1.0f, 1.0f, 0.0f, 1.0f);

		case gurdin:
			return new Color(0.0f, 1.0f, 0.0f, 1.0f);

		case arah:
			return new Color(0.6f, 0.2f, 0.8f, 1.0f);
			
		}
		return new Color(1.0f, 1.0f, 1.0f, 1.0f);
	}
}
