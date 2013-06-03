package com.game.rania.userdata;

public class Command
{
	public int command;
	public int length;
	public byte[] data;
	public Command(int com, int len, byte[] data)
	{
		this.command = com;
		this.data = data;
		this.length = len;
		
	}
}
