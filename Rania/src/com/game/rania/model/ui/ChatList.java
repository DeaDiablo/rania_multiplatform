package com.game.rania.model.ui;

import java.util.Vector;

import com.badlogic.gdx.graphics.Color;
import com.game.rania.model.RegionID;
import com.game.rania.model.element.MultilineText;

public class ChatList extends TextList
{

  public static final int            mainChannel     = 1;
  public static final int            locationChannel = 2;
  public static final int            planetChannel   = 3;
  public static final int            domainChannel   = 4;
  public static final int            privateChannel  = 5;

  public static final int            maxCoundLines   = 500;

  protected Vector<Vector<TextLine>> listChannel     = new Vector<Vector<TextLine>>();

  public ChatList(RegionID idTexture, float x, float y, MultilineText text, float widthList, float heightList)
  {
    super(idTexture, x, y, text, widthList, heightList);
    listChannel.add(lines); // main
    listChannel.add(new Vector<TextLine>()); // location
    listChannel.add(new Vector<TextLine>()); // planet
    listChannel.add(new Vector<TextLine>()); // domain
    listChannel.add(new Vector<TextLine>()); // private
  }

  public void addText(String text, int channel)
  {
    addText(text, this.text.color, channel);
  }

  public void addText(String text, Color color, int channel)
  {

    boolean needUpdate = false;

    if (channel == currentChannel && lines.size() == endLine)
      needUpdate = true;

    Vector<TextLine> textLines = listChannel.get(channel - 1);
    parseText(textLines, text, this.text.color);
    while (textLines.size() > maxCoundLines)
    {
      textLines.remove(0);
    }

    if (needUpdate)
      goToEnd();
  }

  protected int currentChannel = 1;

  public void setCurrentChannel(int channel)
  {
    if (channel > listChannel.size())
    {
      currentChannel = 0;
      return;
    }

    currentChannel = channel;
    lines = listChannel.get(channel - 1);
    endLine = lines.size();
    beginLine = Math.max(0, endLine - countLine);
  }

  public int getCurrentChannel()
  {
    return currentChannel;
  }
}
