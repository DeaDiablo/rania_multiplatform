package com.game.rania.model.ui;

import com.game.rania.RaniaGame;
import com.game.rania.controller.Controllers;
import com.game.rania.model.Player;
import com.game.rania.model.Target;
import com.game.rania.model.items.Device;
import com.game.rania.model.items.Equip;
import com.game.rania.model.items.Item;
import com.game.rania.model.element.Object;

public class SkillButton extends PressedButton{
	
	protected Equip<?> skillEquip = null;
	
	public SkillButton(Equip<?> equip, float x, float y, int keyCode){
		super(equip.item.getIconID(), null, x, y);
		skillEquip = equip;
		setKey(keyCode);
		action = new TouchAction() {
			@Override
			public void execute(boolean touch) {
				Player player = Controllers.locController.getPlayer();
				if (player.body.wear<=0||skillEquip.wear<=0)
					return;
				int itemType =  skillEquip.item.itemType;
				switch (itemType) {
					case Item.Type.device: {
						Device dev = (Device)skillEquip.item;
						int deviceType = dev.deviceType;
						switch (deviceType) {
							case Device.Type.weapon: {
								if ((player == null || player.target.type != Target.user || player.target.user == player.id))
									return;
								
								skillEquip.item.use(player, player.target.object);
								Controllers.netController.sendUseEquip(skillEquip.id);
								break;
							}
							case Device.Type.droid: {
								Object targetObj = player.target.object;
								if (player.target.type!= Target.user) 
									targetObj = player;
								skillEquip.item.use(player, targetObj);
								break;
							}
							case Device.Type.hyper: {
								break;
							}
							case Device.Type.shield: {
								break;
							}
						}
						break;
					}
					case Item.Type.consumable: {
						break;
					}
				}
			}
		};
	}
	
	public void replaceEquip(Equip<?> newEquip){
		skillEquip = newEquip;
		region = RaniaGame.mView.getTextureRegion(skillEquip.item.getIconID());
	}
}
