package com.game.rania.screen.part;

import com.game.rania.RaniaGame;
import com.game.rania.controller.Controllers;
import com.game.rania.controller.LocationController;
import com.game.rania.model.Player;
import com.game.rania.model.ammunition.Laser;
import com.game.rania.model.element.Group;
import com.game.rania.model.element.RegionID;
import com.game.rania.model.items.Equip;
import com.game.rania.model.items.Weapon;
import com.game.rania.model.ui.PressedButton;
import com.game.rania.model.ui.TouchAction;
import com.game.rania.net.NetController;
import com.game.rania.view.MainView;

public class SkillsPanel extends Group implements Part{

	private MainView mView = RaniaGame.mView;
	private LocationController lController = Controllers.locController;
	private NetController nController = Controllers.netController;
	
	private PressedButton skill1 = null;
	private PressedButton skill2 = null;
	private PressedButton skill3 = null;
	private PressedButton skill4 = null;
	private PressedButton skill5 = null;
	
	public SkillsPanel(){
	}
	
	@Override
	public void loadPart() {
		float halfWidth = mView.getHUDCamera().getWidth() * 0.5f;
		float halfHeight  = mView.getHUDCamera().getHeight() * 0.5f;

		skill1 = new PressedButton(RegionID.BTN_UI_CHAT_OFF,
								   RegionID.BTN_UI_CHAT_ON,
								   -halfWidth * 0.9f, 0.0f,
								   new TouchAction() {
								   		@Override
								   		public void execute(boolean touch) {
								   			Player player = lController.getPlayer();
											if (player.weapon.size() > 0) {
									   			Equip<Weapon> weapon = player.weapon.values().iterator().next();
									   			RaniaGame.mController.addObject(
									   					new Laser(player,
									   							  player.target,
									   							  player.domain.color));
								   				nController.sendUseEquip(weapon.id);
											}
								   		}
								   });
		
		skill2 = new PressedButton(RegionID.BTN_UI_CHAT_OFF,
				   				   RegionID.BTN_UI_CHAT_ON,
								   -halfWidth * 0.9f, -halfHeight * 0.2f,
				   				   new TouchAction() {
				   						@Override
				   						public void execute(boolean touch) {
											if (lController.getPlayer().weapon.size() > 1) {
									   			Equip<Weapon> weapon = lController.getPlayer().weapon.get(1);
								   				nController.sendUseEquip(weapon.id);
											}
				   						}
				   				   });
		
		skill3 = new PressedButton(RegionID.BTN_UI_CHAT_OFF,
								   RegionID.BTN_UI_CHAT_ON,
								   -halfWidth * 0.9f, -halfHeight * 0.4f,
								   new TouchAction() {
										@Override
										public void execute(boolean touch) {
											if (lController.getPlayer().weapon.size() > 2) {
									   			Equip<Weapon> weapon = lController.getPlayer().weapon.get(2);
								   				nController.sendUseEquip(weapon.id);
											}
										}
								   });
		
		skill4 = new PressedButton(RegionID.BTN_UI_CHAT_OFF,
								   RegionID.BTN_UI_CHAT_ON,
								   -halfWidth * 0.9f, -halfHeight * 0.6f,
								   new TouchAction() {
										@Override
										public void execute(boolean touch) {
											if (lController.getPlayer().weapon.size() > 3) {
									   			Equip<Weapon> weapon = lController.getPlayer().weapon.get(3);
								   				nController.sendUseEquip(weapon.id);
											}
										}
								   });
		
		skill5 = new PressedButton(RegionID.BTN_UI_CHAT_OFF,
								   RegionID.BTN_UI_CHAT_ON,
								   -halfWidth * 0.9f, -halfHeight * 0.8f,
								   new TouchAction() {
										@Override
										public void execute(boolean touch) {
											if (lController.getPlayer().weapon.size() > 4) {
									   			Equip<Weapon> weapon = lController.getPlayer().weapon.get(4);
								   				nController.sendUseEquip(weapon.id);
											}
										}
								   });
		
		if (lController.getPlayer().weapon.size() > 0)
			addElement(skill1);
		if (lController.getPlayer().weapon.size() > 1)
			addElement(skill2);
		if (lController.getPlayer().weapon.size() > 2)
			addElement(skill3);
		if (lController.getPlayer().weapon.size() > 3)
			addElement(skill4);
		if (lController.getPlayer().weapon.size() > 4)
			addElement(skill5);
	}

	@Override
	public void unloadPart() {
	}

	@Override
	public void addPart() {
		RaniaGame.mController.addObject(this);
	}

	@Override
	public void removePart() {
		RaniaGame.mController.removeObject(this);
	}

}
