package com.game.rania.screen.part;

import com.badlogic.gdx.graphics.Color;
import com.game.rania.RaniaGame;
import com.game.rania.controller.Controllers;
import com.game.rania.model.Font;
import com.game.rania.model.Group;
import com.game.rania.model.RegionID;
import com.game.rania.model.element.MultilineText;
import com.game.rania.model.element.Planet;
import com.game.rania.model.element.Star;
import com.game.rania.model.element.Target;
import com.game.rania.model.element.Text;
import com.game.rania.model.element.User;
import com.game.rania.model.items.Item;
import com.game.rania.model.ui.TextList;
import com.game.rania.view.MainView;

public class InfoPanel extends Group implements Part
{

  private TextList info  = null;
  private MainView mView = RaniaGame.mView;

  public InfoPanel()
  {
  }

  public void setTargetInfo(Target target)
  {
    switch (target.type)
    {
      case Target.user:
        setUserInfo((User) target.object);
        break;

      case Target.planet:
        setPlanetInfo((Planet) target.object);
        break;

      case Target.star:
        setStarInfo((Star) target.object);
        break;

      case Target.item:
        setItemInfo((Item) target.object);
        break;

      case Target.none:
        info.clear();
        break;

      default:
        info.clear();
        info.addLine("Неизвестно");
        break;
    }
  }

  public void setUserInfo(User user)
  {
    info.clear();
    info.addLine("Пилот: " + user.pilotName);
    info.addLine("Корабль: " + user.shipName);
    info.addLine("Раса: " + user.domain.domainName);
  }

  public void setStarInfo(Star star)
  {
    info.clear();
    info.addLine("Звезда: " + star.name);
    info.addLine("Радиус: " + star.radius);
    info.addLine("Позиция: [" + Controllers.locController.getOutputX(star.position.x) + ", " + Controllers.locController.getOutputY(star.position.y) + "]");
  }

  public void setPlanetInfo(Planet planet)
  {
    info.clear();
    info.addLine("Планета: " + planet.name);
    info.addLine("Звезда: " + planet.star.name);
    info.addLine("Радиус: " + planet.radius);
    info.addLine("Орбита: " + planet.orbit);
  }

  public void setItemInfo(Item item)
  {
    info.clear();
    info.addLine("Неизвестно");
  }

  @Override
  public void unloadPart()
  {
    removePart();
    clear();
  }

  @Override
  public void loadPart()
  {
    mView.loadTexture("data/location/info.png", RegionID.INFO);

    float halfWidth = mView.getHUDCamera().getWidth() * 0.5f;
    float halfHeight = mView.getHUDCamera().getHeight() * 0.5f;

    info = new TextList(RegionID.INFO,
                        -halfWidth + mView.getTexture(RegionID.INFO).getWidth() * 0.5f,
                        halfHeight - mView.getTexture(RegionID.INFO).getHeight() * 0.5f,
                        new MultilineText("", Font.getFont("data/fonts/Arial.ttf", 20),
                                          new Color(1, 1, 1, 1),
                                          -mView.getTextureRegion(RegionID.INFO).getRegionWidth() * 0.5f + 10.0f,
                                          mView.getTextureRegion(RegionID.INFO).getRegionHeight() * 0.5f - 10.0f,
                                          Text.Align.LEFT, Text.Align.TOP),
                        mView.getTextureRegion(RegionID.INFO).getRegionWidth() * 0.95f,
                        mView.getTextureRegion(RegionID.INFO).getRegionHeight() * 0.95f);
    addElement(info);
  }

  @Override
  public void addPart()
  {
    RaniaGame.mController.addHUDObject(this);
  }

  @Override
  public void removePart()
  {
    RaniaGame.mController.removeHUDObject(this);
  }
}
