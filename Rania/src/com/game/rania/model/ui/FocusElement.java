package com.game.rania.model.ui;

import com.game.rania.model.Object;

public class FocusElement
{
  private static Object focusElement = null;

  public static Object getFocus()
  {
    return focusElement;
  }

  public static void setFocus(Object element)
  {
    focusElement = element;
  }

  public static void clearFocus()
  {
    focusElement = null;
  }
}
