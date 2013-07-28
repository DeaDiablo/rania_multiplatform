package com.game.rania.model.items;

import com.game.rania.model.element.Object;
import com.game.rania.model.element.RegionID;

public class Hyper extends Device{

	public int radius;
	public int time_start;
	public int time_reload;

	public Hyper() {
		super();
	}
	
	public Hyper(Device device) {
		super(device);
	}
	
	@Override
	public RegionID getIconID(){
		return RegionID.NONE;
	}
	
	@Override
	public boolean use(Object user, Object target){
		return false;
	}
}
