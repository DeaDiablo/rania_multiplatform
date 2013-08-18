package com.game.rania.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;

public class Shader extends ShaderProgram
{

  public Shader(String vertShader, String fragShader)
  {
    super(Gdx.files.internal(vertShader).readString(),
          Gdx.files.internal(fragShader).readString());

    if (!isCompiled())
    {
      Gdx.app.log("Problem loading shader:", getLog());
    }
  }

}
