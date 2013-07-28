package com.game.rania.screen.part;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Input;
import com.game.rania.RaniaGame;
import com.game.rania.controller.Controllers;
import com.game.rania.controller.LocationController;
import com.game.rania.model.element.Group;
import com.game.rania.model.element.RegionID;
import com.game.rania.model.items.Equip;
import com.game.rania.model.items.Weapon;
import com.game.rania.model.ui.SkillButton;
import com.game.rania.view.MainView;

public class SkillsPanel extends Group implements Part{
	
	private MainView 	       mView 	   = RaniaGame.mView;
	private LocationController lController = Controllers.locController;
	
	public List<SkillButton> skills = new ArrayList<SkillButton>();

	public SkillsPanel(){
	}
	
	@Override
	public void loadPart() {
		mView.loadTexture("data/gui/icon_collect.png", RegionID.LASER_SKILL, 96, 192, 96,  96, false);
		//mView.loadTexture("data/gui/icon_collect.png", RegionID.LASER_SKILL, 96, 288, 96,  96, false);
		mView.loadTexture("data/gui/icon_collect.png", RegionID.ROCKET_SKILL, 384, 0, 96,  96, false);
		//mView.loadTexture("data/gui/icon_collect.png", RegionID.ROCKET_SKILL, 384, 96, 96,  96, false);
		mView.loadTexture("data/gui/icon_collect.png", RegionID.BFG_SKILL, 384, 192, 96,  96, false);
		//mView.loadTexture("data/gui/icon_collect.png", RegionID.BFG_SKILL, 384, 288, 96,  96, false);
		mView.loadTexture("data/gui/icon_collect.png", RegionID.REPAIR_KIT_SKILL, 0, 384, 96,  96, false);
		//mView.loadTexture("data/gui/icon_collect.png", RegionID.REPAIR_KIT_SKILL, 0, 480, 96,  96, false);
		
		float halfWidth = mView.getHUDCamera().getWidth() * 0.5f;
		float halfHeight  = mView.getHUDCamera().getHeight() * 0.5f;

		int i = 0;
		for(Equip<Weapon> equip : lController.getPlayer().weapon.values()) {
			skills.add(new SkillButton(equip, -halfWidth * 0.9f, -halfHeight * 0.2f * i, Input.Keys.NUM_1 + i));
			i++;
		}
		
		for (SkillButton skill : skills) {
			addElement(skill);
		}
	}

	@Override
	public void unloadPart() {
	}

	@Override
	public void addPart() {
		RaniaGame.mController.addHUDObject(this);
	}

	@Override
	public void removePart() {
		RaniaGame.mController.removeHUDObject(this);
	}

}
