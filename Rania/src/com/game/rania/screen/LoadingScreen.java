package com.game.rania.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.game.rania.RaniaGame;
import com.game.rania.model.MultilineText;
import com.game.rania.model.Text;
import com.game.rania.model.element.Font;
import com.game.rania.model.element.RegionID;
import com.game.rania.model.ui.TextList;

public class LoadingScreen extends RaniaScreen
{

  protected LoadableScreen loadScreen  = null;
  protected TextList       textLoading = null;

  public LoadingScreen(LoadableScreen screen)
  {
    super();
    loadScreen = screen;
  }

  @Override
  public void show()
  {
    mView.loadTexture("data/backgrounds/loading.jpg", RegionID.LOADING, false);

    textLoading = new TextList(RegionID.LOADING, 0.0f, 0.0f,
        new MultilineText("Подождите...", Font.getFont("data/fonts/Arial.ttf", 30),
            new Color(1, 1, 1, 1),
            mView.getTextureRegion(RegionID.LOADING).getRegionWidth() * 0.5f,
            mView.getTextureRegion(RegionID.LOADING).getRegionHeight() * 0.5f,
            Text.Align.RIGHT, Text.Align.TOP),
        mView.getTextureRegion(RegionID.LOADING).getRegionWidth() * 0.95f,
        mView.getTextureRegion(RegionID.LOADING).getRegionHeight() * 0.95f);
    mController.addHUDObject(textLoading);
  }

  @Override
  public void render(float deltaTime)
  {
    if (loadScreen.isLoaded())
    {
      loadScreen.set();
      update(deltaTime);
      return;
    }

    String currentObject = loadScreen.getCurrentResource();
    if (currentObject != null)
    {
      textLoading.addLine(currentObject);
    }
    else
    {
      Gdx.app.log("Error", "Screen not loaded : " + loadScreen.getClass());
      RaniaGame.mGame.dispose();
      return;
    }

    super.render(deltaTime);

    Gdx.app.postRunnable(new Runnable()
    {
      @Override
      public void run()
      {
        loadScreen.loadNextResource();
      }
    });
  }

  @Override
  public void dispose()
  {
    mController.removeHUDObject(textLoading);
  }
}
