package com.game.rania.userdata;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.badlogic.gdx.Gdx;
import com.game.rania.net.NetController;

public class IOStream
{

  private DataInputStream  iStream = null;
  private DataOutputStream oStream = null;

  public IOStream(InputStream is, OutputStream os)
  {
    iStream = new DataInputStream(is);
    oStream = new DataOutputStream(os);
  }

  // read
  public Command readCommand() throws IOException
  {
    int idCommand = iStream.readInt();
    int controlCRC = iStream.readInt();
    int len = iStream.readInt();
    int buffCount = (len / 1024) + 1;
    if (buffCount > 1024)
    {
      Gdx.app.log("BufferCount", "Big size buffer");
      return null;
    }

    byte[] data = new byte[len];
    int offset = 0;
    for (int i = 0; i < buffCount; i++)
    {
      if ((buffCount - 1) == i)
      {
        iStream.read(data, offset, len - (buffCount - 1) * 1024);
        offset += (len - (buffCount - 1) * 1024);
      }
      else
      {
        iStream.read(data, offset, 1024);
        offset += 1024;
      }
    }
    if (offset != len)
    {
      Gdx.app.log("Offset error", "Offset=" + String.valueOf(offset) + ", Len=" + String.valueOf(len));
    }
    return new Command(idCommand, len, data, controlCRC);
  }

  public int readInt() throws IOException
  {
    byte[] b = new byte[4];
    iStream.read(b);
    return NetController.byteArrayToInt(b);
  }

  // send
  public void sendCommand(Command command) throws IOException
  {
    oStream.write(NetController.intToByteArray(command.idCommand));
    oStream.write(NetController.intToByteArray(0));
    oStream.write(NetController.intToByteArray(command.length));
    oStream.write(command.data);
    oStream.flush();
  }

  public void sendCommand(int commandID) throws IOException
  {
    oStream.write(NetController.intToByteArray(commandID));
    oStream.write(NetController.intToByteArray(0));
    oStream.write(NetController.intToByteArray(0));
    oStream.flush();
  }

  public void sendCommand(int commandID, byte[] data) throws IOException
  {
    oStream.write(NetController.intToByteArray(commandID));
    oStream.write(NetController.intToByteArray(CRC(data)));
    oStream.write(NetController.intToByteArray(data.length));
    oStream.write(data);
    oStream.flush();
  }

  private int CRC(byte[] data)
  {
    int res = 0;
    for (int i = 0; i < data.length; i++)
    {
      res = res + (data[i] * (i));
    }
    return res;
  }
}
