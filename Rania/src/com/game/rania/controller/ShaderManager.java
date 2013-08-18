package com.game.rania.controller;

import java.util.HashMap;

import com.game.rania.model.Shader;

public class ShaderManager
{

  public HashMap<String, Shader> shaders = new HashMap<String, Shader>();

  public ShaderManager()
  {
  }

  public void clear()
  {
    for (Shader shader : shaders.values())
    {
      shader.dispose();
    }
    shaders.clear();
  }

  public Shader getShader(String vertShader, String fragShader)
  {
    String key = vertShader + fragShader;
    if (shaders.containsKey(key))
      return shaders.get(key);
    Shader shader = new Shader(vertShader, fragShader);
    if (!shader.isCompiled())
      return null;
    shaders.put(key, shader);
    return shader;
  }
}
