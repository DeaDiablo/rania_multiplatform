package com.game.rania.userdata;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.badlogic.gdx.Gdx;
import com.game.rania.net.NetController;

public class IOStream{

	private DataInputStream iStream = null;
	private DataOutputStream oStream = null;

	public IOStream(InputStream is, OutputStream os) {
		iStream = new DataInputStream(is);
		oStream = new DataOutputStream(os);
	}

	//read
	public Command readCommand() throws IOException{
		int idCommand = iStream.readInt();
		int controlCRC = iStream.readInt();
		int len = iStream.readInt();
		Gdx.app.log("len",String.valueOf(idCommand) + " " + String.valueOf(len) + " " + String.valueOf(controlCRC));
		byte[] data = new byte[len];
		iStream.read(data);
		return new Command(idCommand, len, data, controlCRC);
	}
	
	public int readInt() throws IOException{
		byte[] b = new byte[4];
		iStream.read(b);
		return NetController.byteArrayToInt(b);
	}

	//send
	public void sendCommand(Command command) throws IOException{
		oStream.write(NetController.intToByteArray(command.idCommand));
		oStream.write(NetController.intToByteArray(0));
		oStream.write(NetController.intToByteArray(command.length));
		oStream.write(command.data);
		oStream.flush();
	}
	
	public void sendCommand(int commandID) throws IOException{
		Gdx.app.log("send",String.valueOf(commandID));
		oStream.write(NetController.intToByteArray(commandID));
		oStream.write(NetController.intToByteArray(0));
		oStream.write(NetController.intToByteArray(0));
		oStream.flush();
	}
	
	public void sendCommand(int commandID, byte[] data) throws IOException{
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
