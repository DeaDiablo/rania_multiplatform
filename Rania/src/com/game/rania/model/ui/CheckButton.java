package com.game.rania.model.ui;

import com.game.rania.model.RegionID;
import com.game.rania.model.element.Text;

public class CheckButton extends Button
{

  private boolean check = false;

  public CheckButton(RegionID regionOff, RegionID regionOn, float x, float y)
  {
    super(regionOff, regionOn, x, y, null, null);
  }

  public CheckButton(RegionID regionOff, RegionID regionOn, float x, float y, TouchAction action)
  {
    super(regionOff, regionOn, x, y, null, action);
  }

  public CheckButton(RegionID regionOff, RegionID regionOn, float x, float y, Text text, TouchAction action)
  {
    super(regionOff, regionOn, x, y, text, action);
  }

  public CheckButton(RegionID regionOff, RegionID regionOn, float x, float y, Text text)
  {
    super(regionOff, regionOn, x, y, text, null);
  }

  @Override
  public boolean use()
  {
    check = !check;

    if (action != null)
      action.execute(check);

    return true;
  }

  public boolean getCheck()
  {
    return check;
  }

  public void setCheck(boolean check)
  {
    this.check = check;
  }

  @Override
  public boolean checkButton()
  {
    return check;
  }
}
