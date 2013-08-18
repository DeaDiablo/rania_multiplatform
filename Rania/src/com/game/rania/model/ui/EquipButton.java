package com.game.rania.model.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.game.rania.RaniaGame;
import com.game.rania.controller.Controllers;
import com.game.rania.controller.TimeController;
import com.game.rania.model.element.Player;
import com.game.rania.model.items.Equip;

public class EquipButton extends PressedButton
{
  protected Equip<?> skillEquip = null;
  protected float    v          = 0.0f, v2 = 0.0f, alpha = 1.0f;

  public EquipButton(Equip<?> equip, float x, float y, int keyCode)
  {
    super(null, null, x, y);
    setKey(keyCode);
    vAlign = Align.TOP;
    action = new TouchAction()
    {
      @Override
      public void execute(boolean touch)
      {
        if (TimeController.globalCooldown.check() || TimeController.getCooldown(skillEquip.item).check())
          return;

        Player player = Controllers.locController.getPlayer();
        if (player.body.wear <= 0 || skillEquip.wear <= 0)
          return;

        skillEquip.startUse();
      }
    };

    replaceEquip(equip);
  }

  public void replaceEquip(Equip<?> newEquip)
  {
    skillEquip = newEquip;
    region = RaniaGame.mView.getTextureRegion(skillEquip.item.getIconID());

    if (region != null)
    {
      v = region.getV();
      v2 = region.getV2();
    }
  }

  @Override
  public boolean draw(SpriteBatch sprite, ShapeRenderer shape)
  {
    color.a = 0.35f;
    if (!super.draw(sprite, shape))
      return false;

    color.a = 1.0f;
    region.setV(v2 - (v2 - v) * Math.min(TimeController.getCooldown(skillEquip.item).progress(), TimeController.globalCooldown.progress()));
    super.draw(sprite, shape);
    region.setV(v);

    return true;
  }
}
