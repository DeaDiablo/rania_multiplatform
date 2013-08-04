package com.game.rania.controller;

import java.util.Collections;
import java.util.Vector;

import com.badlogic.gdx.InputMultiplexer;
import com.game.rania.model.element.Group;
import com.game.rania.model.element.Object;

public class MainController extends InputMultiplexer
{

  private Vector<UpdateController> updateControllers  = new Vector<UpdateController>();
  // objects
  private Vector<Object>           sceneObjects       = new Vector<Object>();
  private Vector<Object>           HUDObjects         = new Vector<Object>();
  // remove objects
  private Vector<Object>           removeSceneObjects = new Vector<Object>();
  private Vector<Object>           removeHUDObjects   = new Vector<Object>();

  public MainController()
  {
    super();
  }

  public void init()
  {
    if (Controllers.inputController != null)
      super.addProcessor(0, Controllers.inputController);
  }

  // update controllers
  public void addProcessor(UpdateController controller)
  {
    if (updateControllers.contains(controller))
      return;
    updateControllers.add(controller);
    super.addProcessor(controller);
  }

  public void removeProcessor(UpdateController controller)
  {
    if (!updateControllers.contains(controller))
      return;
    controller.stopContoller();
    updateControllers.remove(controller);
    super.removeProcessor(controller);
  }

  // scene objects
  public Vector<Object> getObjects()
  {
    return sceneObjects;
  }

  public Vector<Object> getReverseObjects()
  {
    Vector<Object> reverseVec = new Vector<Object>(sceneObjects);
    Collections.reverse(reverseVec);
    return reverseVec;
  }

  public void addObject(Object object)
  {
    if (sceneObjects.contains(object))
      return;

    for (int i = 0; i < sceneObjects.size(); i++)
    {
      if (object.zIndex < sceneObjects.get(i).zIndex)
      {
        sceneObjects.insertElementAt(object, i);
        return;
      }
    }
    sceneObjects.add(object);
  }

  public void removeObject(Object object)
  {
    removeSceneObjects.add(object);
  }

  // HUD objects
  public Vector<Object> getHUDObjects()
  {
    return HUDObjects;
  }

  public Vector<Object> getReverseHUDObjects()
  {
    Vector<Object> reverseVec = new Vector<Object>(HUDObjects);
    Collections.reverse(reverseVec);
    return reverseVec;
  }

  public void addHUDObject(Object object)
  {
    if (HUDObjects.contains(object))
      return;

    for (int i = 0; i < HUDObjects.size(); i++)
    {
      if (object.zIndex < HUDObjects.get(i).zIndex)
      {
        HUDObjects.insertElementAt(object, i);
        return;
      }
    }
    HUDObjects.add(object);
  }

  public void removeHUDObject(Object object)
  {
    removeHUDObjects.add(object);
  }

  // group object
  public void addObject(Group group)
  {
    for (Object object : group.getElements())
    {
      addObject(object);
    }
  }

  public void removeObject(Group group)
  {
    for (Object object : group.getElements())
    {
      removeSceneObjects.add(object);
    }
  }

  public void addHUDObject(Group group)
  {
    for (Object object : group.getElements())
    {
      addHUDObject(object);
    }
  }

  public void removeHUDObject(Group group)
  {
    for (Object object : group.getElements())
    {
      removeHUDObjects.add(object);
    }
  }

  public void update(float deltaTime)
  {
    if (!removeSceneObjects.isEmpty())
    {
      sceneObjects.removeAll(removeSceneObjects);
      removeSceneObjects.clear();
    }
    if (!removeHUDObjects.isEmpty())
    {
      HUDObjects.removeAll(removeHUDObjects);
      removeHUDObjects.clear();
    }

    for (Object object : sceneObjects)
    {
      object.update(deltaTime);
    }

    for (Object object : HUDObjects)
    {
      object.update(deltaTime);
    }

    Controllers.commandController.updateCommands(deltaTime);

    for (UpdateController controller : updateControllers)
    {
      controller.update(deltaTime);
    }
  }

  public void clear()
  {
    super.clear();
    updateControllers.clear();
    sceneObjects.clear();
    HUDObjects.clear();
  }
}
