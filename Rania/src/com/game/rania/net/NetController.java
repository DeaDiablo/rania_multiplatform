package com.game.rania.net;

import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.game.rania.Config;
import com.game.rania.controller.CommandController;
import com.game.rania.controller.command.AddUserCommand;
import com.game.rania.controller.command.RemoveUserCommand;
import com.game.rania.controller.command.SetTargetCommand;
import com.game.rania.model.Player;
import com.game.rania.model.User;
import com.game.rania.model.Location;
import com.game.rania.model.Planet;
import com.game.rania.userdata.Command;
import com.game.rania.userdata.Client;
import com.game.rania.userdata.IOStream;
import com.game.rania.utils.Condition;

public class NetController {

	private Receiver receiver = null;
	private CommandController cController = null;
	
	public NetController(CommandController controller){
		cController = controller;
	}
	
	public void dispose(){
		if (receiver != null)
			receiver.stopThread();
	}

	public void SendTouchPoint(int x, int y, int currentX, int currentY, Client client)
	{
		byte[] data = new byte[16];
		byte[] xArr = intToByteArray(x);
		byte[] yArr = intToByteArray(y);
		byte[] userxArr = intToByteArray(currentX);
		byte[] useryArr = intToByteArray(currentY);
		System.arraycopy(xArr, 0, data, 0, 4);
		System.arraycopy(yArr, 0, data, 4, 4);
		System.arraycopy(userxArr, 0, data, 8, 4);
		System.arraycopy(useryArr, 0, data, 12, 4);
		try
		{
			client.stream.sendCommand(Command.touchPlayer, data);
		}
		catch (Exception ex)
		{

		}
	}

	public Client ClientLogin(String Login, String Password)
	{
		Client client = new Client();
		client.login = Login;
		client.socket = null;
		client.isLogin = false;
		try
		{
			client.socket = new Socket(InetAddress.getByName(Config.serverAddress), Config.serverPort);
			if (client.socket.isConnected())
			{
				client.stream = new IOStream(client.socket.getInputStream(), client.socket.getOutputStream());
				client.stream.sendCommand(Command.login, Login.getBytes("UTF-16LE"));
				client.stream.sendCommand(Command.password, Password.getBytes("UTF-16LE"));

				Command answer = client.stream.readCommand();
				if (answer.idCommand == Command.login)
				{
					client.isLogin = true;
					byte[] b = new byte[4];
					for (int i=0; i<4; i++)
					{
						b[i] = answer.data[i];
					}
					client.serverTime = byteArrayToInt(b);
					receiver = new Receiver(client, this);
					receiver.start();
				}
			}
		}
		catch (Exception ex)
		{
			return null;
		}
		return client;
	}
	
	public void ClientDisconnect(Client client)
	{
		try
		{
			client.stream.sendCommand(Command.disconnect);
			client.socket.shutdownInput();
			client.socket.shutdownOutput();
			client.socket.close();
		}
		catch (Exception ex)
		{
			
		}
	}
	
	public void ClientRelogin(Client client)
	{
		
	}
	
	public HashMap<String, User> GetUsersInLocation(Client client)
	{
		HashMap<String, User> UsersMap = new HashMap<String, User>();
		try
		{
			client.stream.sendCommand(Command.users);
			Command command = waitCommand(Command.users);
			int ArrPtr = 0;
			byte[] UsersCountArr = new byte[4];
			for (int i=0;i<4;i++)
			{
				UsersCountArr[i] = command.data[ArrPtr];
				ArrPtr++;
			}
			for (int i=0;i<byteArrayToInt(UsersCountArr);i++)
			{
				byte[] idArr = new byte[4];
				for (int j=0;j<4;j++)
				{
					idArr[j] = command.data[ArrPtr];
					ArrPtr++;
				}
				byte[] ShipNameLenArr = new byte[4];
				for (int j=0;j<4;j++)
				{
					ShipNameLenArr[j] = command.data[ArrPtr];
					ArrPtr++;
				}
				byte[] ShipNameArr = new byte[byteArrayToInt(ShipNameLenArr)];
				for (int j=0;j<byteArrayToInt(ShipNameLenArr);j++)
				{
					ShipNameArr[j] = command.data[ArrPtr];
					ArrPtr++;
				}
				byte[] xArr = new byte[4];
				for (int j=0;j<4;j++)
				{
					xArr[j] = command.data[ArrPtr];
					ArrPtr++;
				}
				byte[] yArr = new byte[4];
				for (int j=0;j<4;j++)
				{
					yArr[j] = command.data[ArrPtr];
					ArrPtr++;
				}
				byte[] TargetxArr = new byte[4];
				for (int j=0;j<4;j++)
				{
					TargetxArr[j] = command.data[ArrPtr];
					ArrPtr++;
				}
				byte[] TargetyArr = new byte[4];
				for (int j=0;j<4;j++)
				{
					TargetyArr[j] = command.data[ArrPtr];
					ArrPtr++;
				}
				User userShip = new User(byteArrayToInt(idArr),
						                 byteArrayToInt(xArr),
						                 byteArrayToInt(yArr),
						                 new String(ShipNameArr, "UTF-16LE"),
						                 "");
				UsersMap.put(String.valueOf(userShip.id), userShip);
			}
		}
		catch (Exception ex)
		{

		}
		return UsersMap;
	}

	public HashMap<String, Planet> GetCurrentPlanets(Client client)
	{
		HashMap<String, Planet> planets = new HashMap<String, Planet>();
		try
		{
			client.stream.sendCommand(Command.planets);
			Command command = waitCommand(Command.planets);
			int ArrPtr = 0;
			byte[] PlanetsCountArr = new byte[4];
			for (int i=0;i<4;i++)
			{
				PlanetsCountArr[i] = command.data[ArrPtr];
				ArrPtr++;
			}
			for (int i=0;i<byteArrayToInt(PlanetsCountArr);i++)
			{
				byte[] idArr = new byte[4];
				for (int j=0;j<4;j++)
				{
					idArr[j] = command.data[ArrPtr];
					ArrPtr++;
				}
				byte[] PlanetNameLenArr = new byte[4];
				for (int j=0;j<4;j++)
				{
					PlanetNameLenArr[j] = command.data[ArrPtr];
					ArrPtr++;
				}
				byte[] PlanetNameArr = new byte[byteArrayToInt(PlanetNameLenArr)];
				for (int j=0;j<byteArrayToInt(PlanetNameLenArr);j++)
				{
					PlanetNameArr[j] = command.data[ArrPtr];
					ArrPtr++;
				}
				byte[] PlanetTypeArr = new byte[4];
				for (int j=0;j<4;j++)
				{
					PlanetTypeArr[j] = command.data[ArrPtr];
					ArrPtr++;
				}
				byte[] R_speedArr = new byte[4];
				for (int j=0;j<4;j++)
				{
					R_speedArr[j] = command.data[ArrPtr];
					ArrPtr++;
				}
				byte[] OrbitArr = new byte[4];
				for (int j=0;j<4;j++)
				{
					OrbitArr[j] = command.data[ArrPtr];
					ArrPtr++;
				}
				byte[] RadiusArr = new byte[4];
				for (int j=0;j<4;j++)
				{
					RadiusArr[j] = command.data[ArrPtr];
					ArrPtr++;
				}
				char[] ColorArr = new char[4];
				for (int j=0;j<4;j++)
				{
					ColorArr[j]=(char)command.data[ArrPtr];
					ArrPtr++;
				}
				byte[] AtmosphereArr = new byte[4];
				for (int j=0;j<4;j++)
				{
					AtmosphereArr[j] = command.data[ArrPtr];
					ArrPtr++;
				}
				Planet planet     = new Planet();
				planet.id         = byteArrayToInt(idArr);
				planet.planetType = byteArrayToInt(PlanetTypeArr);
				planet.orbit      = byteArrayToInt(OrbitArr);
				planet.planetName = new String(PlanetNameArr, "UTF-16LE");
				planet.speed      = byteArrayToInt(R_speedArr);
				planet.radius     = byteArrayToInt(RadiusArr);
				planet.color      = new Color(ColorArr[0] / 255.0f, ColorArr[1] / 255.0f, ColorArr[2] / 255.0f, ColorArr[3] / 255.0f);
				planet.atmosphere = byteArrayToInt(AtmosphereArr);
				planets.put(String.valueOf(planet.id), planet);
			}
		}
		catch (Exception ex)
		{
			ClientRelogin(client);
		}
		return planets;
	}
	
	public HashMap<String, Location> GetAllLocations(Client client)
	{
		HashMap<String, Location> locations = new HashMap<String, Location>();
		try
		{
			client.stream.sendCommand(Command.locations);
			Command command = waitCommand(Command.locations);
			int ArrPtr = 0;
			byte[] LocationsCountArr = new byte[4];
			for (int i=0;i<4;i++)
			{
				LocationsCountArr[i] = command.data[ArrPtr];
				ArrPtr++;
			}
			for (int i=0;i<byteArrayToInt(LocationsCountArr);i++)
			{
				byte[] idArr = new byte[4];
				for (int j=0;j<4;j++)
				{
					idArr[j] = command.data[ArrPtr];
					ArrPtr++;
				}
				byte[] StarNameLenArr = new byte[4];
				for (int j=0;j<4;j++)
				{
					StarNameLenArr[j] = command.data[ArrPtr];
					ArrPtr++;
				}
				byte[] StarNameArr = new byte[byteArrayToInt(StarNameLenArr)];
				for (int j=0;j<byteArrayToInt(StarNameLenArr);j++)
				{
					StarNameArr[j] = command.data[ArrPtr];
					ArrPtr++;
				}
				byte[] StarTypeArr = new byte[4];
				for (int j=0;j<4;j++)
				{
					StarTypeArr[j] = command.data[ArrPtr];
					ArrPtr++;
				}
				byte[] xArr = new byte[4];
				for (int j=0;j<4;j++)
				{
					xArr[j] = command.data[ArrPtr];
					ArrPtr++;
				}
				byte[] yArr = new byte[4];
				for (int j=0;j<4;j++)
				{
					yArr[j] = command.data[ArrPtr];
					ArrPtr++;
				}
				byte[] radiusArr = new byte[4];
				for (int j=0;j<4;j++)
				{
					radiusArr[j] = command.data[ArrPtr];
					ArrPtr++;
				}
				Location Loc   = new Location();
				Loc.id         = byteArrayToInt(idArr);
				Loc.x          = byteArrayToInt(xArr);
				Loc.y          = byteArrayToInt(yArr);
				Loc.starRadius = byteArrayToInt(radiusArr);
				Loc.starType   = byteArrayToInt(StarTypeArr);
				Loc.starName   = new String(StarNameArr, "UTF-16LE");
				locations.put(String.valueOf(Loc.id), Loc);
			}
		}
		catch (Exception ex)
		{
			ClientRelogin(client);
		}
		return locations;
	}
	
	public Player getPlayerData(Client client)
	{
		try
		{
			client.stream.sendCommand(Command.player);

			Command command = waitCommand(Command.player);
			
			int ArrPtr = 0;
			byte[] idArr = new byte[4];
			System.arraycopy(command.data, ArrPtr, idArr, 0, 4);
			ArrPtr=ArrPtr+4;
			
			byte[] LocArr = new byte[4];
			System.arraycopy(command.data, ArrPtr, LocArr, 0, 4);
			ArrPtr=ArrPtr+4;
			
			byte[] xArr = new byte[4];
			System.arraycopy(command.data, ArrPtr, xArr, 0, 4);
			ArrPtr=ArrPtr+4;

			byte[] yArr = new byte[4];
			System.arraycopy(command.data, ArrPtr, yArr, 0, 4);
			ArrPtr=ArrPtr+4;
			
			byte[] PnameLenArr = new byte[4];
			System.arraycopy(command.data, ArrPtr, PnameLenArr, 0, 4);
			ArrPtr=ArrPtr+4;
			
			byte[] PnameArr = new byte[byteArrayToInt(PnameLenArr)];
			System.arraycopy(command.data, ArrPtr, PnameArr, 0, byteArrayToInt(PnameLenArr));
			ArrPtr=ArrPtr+byteArrayToInt(PnameLenArr);
			
			byte[] SnameLenArr = new byte[4];
			System.arraycopy(command.data, ArrPtr, SnameLenArr, 0, 4);
			ArrPtr=ArrPtr+4;
			
			byte[] SnameArr = new byte[byteArrayToInt(SnameLenArr)];
			System.arraycopy(command.data, ArrPtr, SnameArr, 0, byteArrayToInt(SnameLenArr));
			ArrPtr=ArrPtr+byteArrayToInt(SnameLenArr);
			
			Player player = new Player(byteArrayToInt(idArr), 
                                       byteArrayToInt(LocArr),
                                       byteArrayToInt(xArr),
                                       byteArrayToInt(yArr), 
                                       new String(PnameArr, "UTF-16LE"),
                                       new String(SnameArr, "UTF-16LE"));
			return player;
		}
		catch (Exception ex)
		{
			ClientRelogin(client);
		}
		return null;
	}

	public static int byteArrayToInt(byte[] b)
	{
		return b[3] & 0xFF | (b[2] & 0xFF) << 8 | (b[1] & 0xFF) << 16 | (b[0] & 0xFF) << 24;
	}

	public static byte[] intToByteArray(int a)
	{
		return new byte[] { (byte)((a >> 24) & 0xFF), (byte)((a >> 16) & 0xFF), (byte)((a >> 8) & 0xFF), (byte)(a & 0xFF) };
	}
	
	//commands 
	private Condition cWaitCommand = new Condition(), cCopyCommand = new Condition();
	private volatile int idWaitCommand = Command.none;
	private volatile Command currentCommand = null;
	
	public Command waitCommand(int idCommand) throws InterruptedException{
		idWaitCommand = idCommand;
		
		cWaitCommand.signalWait();
		Command command = currentCommand;
		cCopyCommand.signal();
		return command;
	}

	public void processingCommand(Command command) throws InterruptedException, UnsupportedEncodingException {
		
		Gdx.app.log("commands", "id command: " + command.idCommand);
		if (idWaitCommand != Command.none && idWaitCommand == command.idCommand)
		{			
			idWaitCommand = Command.none;
			currentCommand = command;
			cWaitCommand.signal();
			cCopyCommand.signalWait();
			return;
		}

		if (command.idCommand == Command.addUser) //игрок id появился в локации
		{
			int ArrPtr = 0;
			byte[] idArr = new byte[4];
			for (int j=0;j<4;j++)
			{
				idArr[j] = command.data[ArrPtr];
				ArrPtr++;
			}
			byte[] ShipNameLenArr = new byte[4];
			for (int j=0;j<4;j++)
			{
				ShipNameLenArr[j] = command.data[ArrPtr];
				ArrPtr++;
			}
			byte[] ShipNameArr = new byte[NetController.byteArrayToInt(ShipNameLenArr)];
			for (int j=0;j<NetController.byteArrayToInt(ShipNameLenArr);j++)
			{
				ShipNameArr[j] = command.data[ArrPtr];
				ArrPtr++;
			}
			byte[] xArr = new byte[4];
			for (int j=0;j<4;j++)
			{
				xArr[j] = command.data[ArrPtr];
				ArrPtr++;
			}
			byte[] yArr = new byte[4];
			for (int j=0;j<4;j++)
			{
				yArr[j] = command.data[ArrPtr];
				ArrPtr++;
			}
			int UserId = NetController.byteArrayToInt(idArr); //  Id игрока который зашел
			int UserX = NetController.byteArrayToInt(xArr);   //  координата X где он появился
			int UserY = NetController.byteArrayToInt(yArr);   //  координата Y где он появился
			String ShipName = new String(ShipNameArr, "UTF-16LE");  // имя корабля			
			cController.addCommand(new AddUserCommand(UserId, UserX, UserY, ShipName));
			Gdx.app.log("commands", "add command (id: "+ UserId + ", x: " + UserX + ", y: " + UserY + ", shipname: " + ShipName);
		}
		
		if (command.idCommand == Command.touchUser) //игрок id тыкнул в экран
		{
			int ArrPtr =0;
			byte[] idArr = new byte[4];
			for (int j=0;j<4;j++)
			{
				idArr[j] = command.data[ArrPtr];
				ArrPtr++;
			}
			byte[] xArr = new byte[4];
			for (int j=0;j<4;j++)
			{
				xArr[j] = command.data[ArrPtr];
				ArrPtr++;
			}
			byte[] yArr = new byte[4];
			for (int j=0;j<4;j++)
			{
				yArr[j] = command.data[ArrPtr];
				ArrPtr++;
			}
			int UserId = NetController.byteArrayToInt(idArr);      //   id игрока
			int UserTouchX = NetController.byteArrayToInt(xArr);   //   X тыка 
			int UserTouchY = NetController.byteArrayToInt(yArr);   //   Y тыка			
			cController.addCommand(new SetTargetCommand(UserId, UserTouchX, UserTouchY));
			Gdx.app.log("commands", "touch command (id: "+ UserId + ", x: " + UserTouchX + ", y: " + UserTouchY);
		}

		if (command.idCommand == Command.removeUser) //игрок id пропал из локации. хз куда делся) эт не важно)
		{
			int ArrPtr =0;
			byte[] idArr = new byte[4];
			for (int j=0;j<4;j++)
			{
				idArr[j] = command.data[ArrPtr];
				ArrPtr++;
			}
			int UserId = NetController.byteArrayToInt(idArr);      //   id игрока которого над удалить из локации
			cController.addCommand(new RemoveUserCommand(UserId));
			Gdx.app.log("commands", "remove command (id: "+ UserId);
		}
	}
}
