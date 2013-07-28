package com.game.rania.model.ui;

import com.game.rania.RaniaGame;
import com.game.rania.controller.Controllers;
import com.game.rania.model.Player;
import com.game.rania.model.Target;
import com.game.rania.model.items.Equip;

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
				if (player == null || player.target.type != Target.user)
					return;

				skillEquip.item.use(player, player.target.object);
				Controllers.netController.sendUseEquip(skillEquip.id);
			}
		};
	}
	
	public void replaceEquip(Equip<?> newEquip){
		skillEquip = newEquip;
		region = RaniaGame.mView.getTextureRegion(skillEquip.item.getIconID());
	}
}
