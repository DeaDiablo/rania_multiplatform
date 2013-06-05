package com.game.rania.userdata;

import java.net.Socket;
import java.util.List;

import com.game.rania.net.Receiver;

public class User {
	public String login;
	public Socket socket;
	public boolean isConnected;
	public boolean isLogin;
	public Receiver receiver;
	public List<Command> commands;
	public int idLocation;
	public int x;
	public int y;
	public String PilotName;
	public String ShipName;
	public int serverTime;
	
	public void PauseReceiver()
	{
		boolean isRecieverWorked=true;
		this.receiver.finish();
		while (isRecieverWorked)
		{
			if (this.receiver.getState()!=Thread.State.RUNNABLE)
			{
				isRecieverWorked=false;
			}
		}
		
	}
	public void ResumeReceiver()
	{
		this.receiver.start();
	}
}
