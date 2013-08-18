package com.game.rania.model.items;

import com.game.rania.model.element.Player;

public abstract class Device extends Item
{

  public class Type
  {
    public static final int none    = 0;
    public static final int body    = 1;
    public static final int engine  = 2;
    public static final int fuelbag = 3;
    public static final int droid   = 4;
    public static final int shield  = 5;
    public static final int hyper   = 6;
    public static final int radar   = 7;
    public static final int weapon  = 8;
  }

  public int    deviceType;
  public String vendorStr;
  public int    durability;

  public Device()
  {
    super();
  }

  public Device(Device device)
  {
    super(device);
    deviceType = device.deviceType;
    vendorStr = device.vendorStr;
    durability = device.durability;
  }

  @Override
  public boolean use(Player player)
  {
    if (player == null || player.target == null)
      return false;
    return true;
  }
}
