package com.game.rania.userdata;

public class Command
{
	//commands list
	public static final int none          = 0;
	public static final int login         = 1;
	public static final int faillogin     = 2;
	public static final int disconnect    = 5;
	public static final int password      = 6;
	public static final int player        = 7;
	public static final int locations     = 8;
	public static final int planets       = 9;
	public static final int touchPlayer   = 10;
	public static final int users         = 11;
	public static final int addUser       = 12;
	public static final int touchUser     = 13;
	public static final int removeUser    = 14;
	
	public int idCommand  = none;
	public int length     = 0;
	public byte[] data 	  = new byte[0];

	public Command(int com)
	{
		this.idCommand = com;
	}
	
	public Command(int com, int len, byte[] data)
	{
		this.idCommand = com;
		this.length = len;
		this.data = data;
	}
	
	public Command(Command command){
		this.idCommand = command.idCommand;
		this.length = command.length;
		this.data = command.data;
	}
}
