package com.game.rania.model.element;

import java.util.Vector;

public class Group
{

  private Vector<Object> elements = new Vector<Object>();

  public Vector<Object> getElements()
  {
    return elements;
  }

  // add elements
  public void addElements(Vector<Object> objects)
  {
    elements.addAll(objects);
  }

  public void addElement(Object object)
  {
    elements.add(object);
  }

  public void addElement(Group group)
  {
    elements.addAll(group.getElements());
  }

  // remove elements
  public void removeElements(Vector<Object> objects)
  {
    elements.removeAll(objects);
  }

  public void removeElement(Object object)
  {
    elements.remove(object);
  }

  public void removeElement(Group group)
  {
    elements.removeAll(group.getElements());
  }

  public void removeElement(int num)
  {
    elements.remove(num);
  }

  public void setVisible(boolean visible)
  {
    for (Object element : elements)
    {
      element.visible = visible;
    }
  }

  public void setVisible(Object object)
  {
    for (Object element : elements)
    {
      if (element == object)
      {
        element.visible = true;
      }
      else
      {
        element.visible = false;
      }
    }
  }

  public void setVisible(Group group)
  {
    Vector<Object> groupElements = group.getElements();
    for (Object element : elements)
    {
      if (groupElements.contains(element))
      {
        element.visible = true;
      }
      else
      {
        element.visible = false;
      }
    }
  }
}
