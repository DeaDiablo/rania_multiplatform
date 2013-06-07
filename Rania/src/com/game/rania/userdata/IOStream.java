package com.game.rania.userdata;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

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
		int len = iStream.readInt();
		byte[] data = new byte[len];
		iStream.read(data);			
		return new Command(idCommand, len, data);
	}
	
	public int readInt() throws IOException{
		byte[] b = new byte[4];
		iStream.read(b);
		return NetController.byteArrayToInt(b);
	}

	//send
	public void sendCommand(Command command) throws IOException{
		oStream.write(NetController.intToByteArray(command.idCommand));
		oStream.write(NetController.intToByteArray(command.length));
		oStream.write(command.data);
		oStream.flush();
	}
	
	public void sendCommand(int commandID) throws IOException{
		oStream.write(NetController.intToByteArray(commandID));
		oStream.write(NetController.intToByteArray(0));
		oStream.flush();
	}
	
	public void sendCommand(int commandID, byte[] data) throws IOException{
		oStream.write(NetController.intToByteArray(commandID));
		oStream.write(NetController.intToByteArray(data.length));
		oStream.write(data);
		oStream.flush();
	}
}