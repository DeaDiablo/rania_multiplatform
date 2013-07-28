package com.game.rania.screen.part;

import java.util.ArrayList;
import java.util.List;

import com.game.rania.RaniaGame;
import com.game.rania.controller.Controllers;
import com.game.rania.controller.LocationController;
import com.game.rania.model.Player;
import com.game.rania.model.Target;
import com.game.rania.model.ammunition.Laser;
import com.game.rania.model.ammunition.Rocket;
import com.game.rania.model.ammunition.Repair;
import com.game.rania.model.ammunition.Bfg;
import com.game.rania.model.element.Group;
import com.game.rania.model.element.RegionID;
import com.game.rania.model.items.Device;
import com.game.rania.model.items.Droid;
import com.game.rania.model.items.Equip;
import com.game.rania.model.items.Weapon;
import com.game.rania.model.ui.PressedButton;
import com.game.rania.model.ui.TouchAction;
import com.game.rania.net.NetController;
import com.game.rania.view.MainView;

public class SkillsPanel extends Group implements Part{
	private class SkillSetting {
		public RegionID region_On;
		public RegionID region_Off;
		public float x;
		public float y;
	}
	
	private MainView mView = RaniaGame.mView;
	private LocationController lController = Controllers.locController;
	private NetController nController = Controllers.netController;
	
	public List<PressedButton> skills = new ArrayList<PressedButton>();

	public SkillsPanel(){
	}
	
	@Override
	public void loadPart() {
		SkillSetting SS = new SkillSetting();
		SS.region_On = RegionID.NONE;
		SS.region_Off = RegionID.NONE;
		Player player = lController.getPlayer();
		int i = 0;
		for (Equip<Weapon> wpn :player.weapon.values()) {
			if (wpn!=null)	{
				i++;
				getSettingsSkill(SS, (Device)wpn.item, i);
				PressedButton skill = new PressedButton(SS.region_Off, SS.region_On, SS.x, SS.y, new TouchAction() {
					@Override
		   		public void execute(boolean touch) {
///////////////////////////////////////////////
						Player player = lController.getPlayer();
						//if (player.target.type != Target.user)
		   			//	return;
		   			List<PressedButton> skills = Parts.getSkillsPanel().skills; 
		   			int skillCount = skills.size();
		   			int i=0;
		   			for (Equip<Weapon> wpn :player.weapon.values()) {
		   				if (i==0) {  // тут надо чета делать что бы выбрать нужный итем. 
		   					switch (wpn.item.weaponType) {
		   						case Weapon.Type.Laser: {
		   							RaniaGame.mController.addObject(new Laser(player, player.target, player.domain.color));
				   					nController.sendUseEquip(wpn.id);
				   					break;
		   						}
		   						case Weapon.Type.Rocket: {
		   							RaniaGame.mController.addObject(new Rocket(player, player.target, player.domain.color));
				   					nController.sendUseEquip(wpn.id);
				   					break;
		   						}
		   						case Weapon.Type.BFG: {
		   							RaniaGame.mController.addObject(new Bfg(player, player.target, player.domain.color));
				   					nController.sendUseEquip(wpn.id);
				   					break;
		   						}
		   					}
		   					break;
		   				}
		   				i++;
		   			}
///////////////////////////////////////////////
					}});
				skills.add(skill);
			}			
		}
		for (Equip<Droid> drd :player.droid.values()) {
			if (drd!=null)	{
				i++;
				getSettingsSkill(SS, (Device)drd.item, i);
				PressedButton skill = new PressedButton(SS.region_Off, SS.region_On, SS.x, SS.y, new TouchAction() {
					@Override
		   		public void execute(boolean touch) {
///////////////////////////////////////////////
						Player player = lController.getPlayer();
						List<PressedButton> skills = Parts.getSkillsPanel().skills; 
		   			int skillCount = skills.size();
		   			int i=0;
		   			for (Equip<Droid> drd :player.droid.values()) {
		   				if (i==0) {  // тут надо чета делать что бы выбрать нужный итем. 
		   					RaniaGame.mController.addObject(new Repair(player, player.target, player.domain.color));
		   					nController.sendUseEquip(drd.id);
		   					break;
		   				}
		   			}
///////////////////////////////////////////////		   			
					}});
				skills.add(skill);
			}			
		}
		for (int j=0;j<skills.size();j++) {
			addElement(skills.get(j));
		}
	}

	private void getSettingsSkill(SkillSetting ss, Device dev, int countSkill) {
		Weapon wpn = null;
		Droid drd = null;
		if (dev.deviceType == Device.Type.weapon) {
			wpn = (Weapon)dev;
		}
		if (dev.deviceType == Device.Type.droid) {
			drd = (Droid)dev;
		}
		if (wpn!=null) {
			switch (wpn.weaponType) {
				case Weapon.Type.Laser: {
					ss.region_On = RegionID.BTN_UI_SKILL_LASER_ON;
					ss.region_Off = RegionID.BTN_UI_SKILL_LASER_OFF;
					break;
				}
				case Weapon.Type.Rocket: {
					ss.region_On = RegionID.BTN_UI_SKILL_ROCKET_ON;
					ss.region_Off = RegionID.BTN_UI_SKILL_ROCKET_OFF;
					break;
				}
				case Weapon.Type.BFG: {
					ss.region_On = RegionID.BTN_UI_SKILL_BFG_ON;
					ss.region_Off = RegionID.BTN_UI_SKILL_BFG_OFF;
					break;
				}
			}
		}
		if (drd!=null) {
			ss.region_On = RegionID.BTN_UI_SKILL_DROID_ON;
			ss.region_Off = RegionID.BTN_UI_SKILL_DROID_OFF;
		}
		float halfWidth = mView.getHUDCamera().getWidth() * 0.5f;
		float halfHeight  = mView.getHUDCamera().getHeight() * 0.5f;
		ss.x = -halfWidth * 0.9f;
		ss.y = -halfHeight * (0.2f*countSkill-0.4f); 
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
