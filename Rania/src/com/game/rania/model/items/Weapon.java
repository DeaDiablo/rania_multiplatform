package com.game.rania.model.items;

import com.game.rania.RaniaGame;
import com.game.rania.model.ammunition.BFG;
import com.game.rania.model.ammunition.Laser;
import com.game.rania.model.ammunition.Rocket;
import com.game.rania.model.element.Object;
import com.game.rania.model.element.RegionID;

public class Weapon extends Device{

	public class Type {
		public static final int Laser	= 0;
		public static final int Rocket	= 1;
		public static final int BFG		= 2;
	}

	public int weaponType;
	public int radius;
	public int power;
	public int time_start;
	public int time_reload;
	
	public Weapon() {
		super();
	}
	
	public Weapon(Device device) {
		super(device);
	}
	
	@Override
	public RegionID getIconID(){
		switch(weaponType){
			case Type.Laser:
				return RegionID.LASER_SKILL;

			case Type.Rocket:
				return RegionID.ROCKET_SKILL;

			case Type.BFG:
				return RegionID.BFG_SKILL;
		}
		return RegionID.NONE;
	}
	
	@Override
	public boolean use(Object user, Object target){
		Object bullet = null;

		switch(weaponType){
			case Type.Laser:
				bullet = new Laser(user, target, color);
				break;

			case Type.Rocket:
				bullet = new Rocket(user, target, color);
				break;

			case Type.BFG:
				bullet = new BFG(user, target, color);
				break;
		}
		
		if (bullet == null)
			return false;
		
		RaniaGame.mController.addObject(bullet);
		return true;
	}
}
