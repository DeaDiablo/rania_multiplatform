package com.game.rania.net;

import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.game.rania.Config;
import com.game.rania.controller.CommandController;
import com.game.rania.controller.Controllers;
import com.game.rania.controller.command.AddPlanetCommand;
import com.game.rania.controller.command.AddUserCommand;
import com.game.rania.controller.command.ChatNewMessageCommand;
import com.game.rania.controller.command.RemoveUserCommand;
import com.game.rania.controller.command.SetTargetCommand;
import com.game.rania.controller.command.SwitchScreenCommand;
import com.game.rania.model.Domain;
import com.game.rania.model.Nebula;
import com.game.rania.model.Player;
import com.game.rania.model.Target;
import com.game.rania.model.User;
import com.game.rania.model.Location;
import com.game.rania.model.Planet;
import com.game.rania.model.items.DeviceType;
import com.game.rania.model.items.Droid;
import com.game.rania.model.items.Engine;
import com.game.rania.model.items.Equip;
import com.game.rania.model.items.Fuelbag;
import com.game.rania.model.items.Hyper;
import com.game.rania.model.items.ItemCollection;
import com.game.rania.model.items.ItemType;
import com.game.rania.model.items.Radar;
import com.game.rania.model.items.Shield;
import com.game.rania.model.items.Ship;
import com.game.rania.model.items.Weapon;
import com.game.rania.screen.MainMenu;
import com.game.rania.userdata.Command;
import com.game.rania.userdata.Client;
import com.game.rania.userdata.IOStream;
import com.game.rania.utils.Condition;

public class NetController {

	private Receiver receiver = null;
	private CommandController cController = null;
	private Client mClient = null;
	private int ProtocolVersion = 4;
	
	public NetController(CommandController commandController){
		cController = commandController;
	}
	
	public void dispose(){
		if (receiver != null)
			receiver.stopThread();
	}

	public void sendTouchPoint(int x, int y, int currentX, int currentY)
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
			mClient.stream.sendCommand(Command.touchPlayer, data);
		}
		catch (Exception ex)
		{

		}
	}
	
	public void sendTarget(Target target)
	{
		byte[] data = new byte[8];
		byte[] targetTypeArr = intToByteArray(target.type);
		byte[] targetArr = intToByteArray(target.id);
		System.arraycopy(targetTypeArr, 0, data, 0, 4);
		System.arraycopy(targetArr, 0, data, 4, 4);
		try
		{
			mClient.stream.sendCommand(Command.setTarget, data);
		}
		catch (Exception ex)
		{

		}
	}
	
	public int getServerTime(){
		return mClient.serverTime;
	}

	public boolean login(String Login, String Password)
	{
		mClient = new Client();
		mClient.login = Login;
		mClient.socket = null;
		mClient.isLogin = false;
		try
		{
			mClient.socket = new Socket(InetAddress.getByName(Config.serverAddress), Config.serverPort);
			if (mClient.socket.isConnected())
			{
				mClient.stream = new IOStream(mClient.socket.getInputStream(), mClient.socket.getOutputStream());
				byte[] LoginArr = Login.getBytes("UTF-16LE");
				byte[] LoginLenArr = intToByteArray(LoginArr.length);
				byte[] ProtocolVersionArr = intToByteArray(ProtocolVersion);
				byte[] data = new byte[LoginArr.length+LoginLenArr.length+ProtocolVersionArr.length];
				System.arraycopy(ProtocolVersionArr, 0, data, 0, 4);
				System.arraycopy(LoginLenArr, 0, data, 4, 4);
				System.arraycopy(LoginArr, 0, data, 8, LoginArr.length);
				mClient.stream.sendCommand(Command.login, data);
				mClient.stream.sendCommand(Command.password, Password.getBytes("UTF-16LE"));
				Command answer = mClient.stream.readCommand();
				if (answer.idCommand == Command.login)
				{
					mClient.isLogin = true;
					mClient.serverTime = GetIntValue(answer.data, new AddressCommand());
					receiver = new Receiver(mClient, this);
					receiver.start();
					return true;
				}
				
				if (answer.idCommand == Command.faillogin)
					mClient.isLogin = false;
				if (answer.idCommand == Command.failversion)
					mClient.isLogin = false;
			}
		}
		catch (Exception ex)
		{
			return false;
		}
		return false;
	}
	
	public void disconnect()
	{
		try
		{
			if (mClient != null && mClient.socket.isConnected() && mClient.isLogin) {
				mClient.stream.sendCommand(Command.disconnect);
				receiver.stopThread();
				mClient.socket.shutdownInput();
				mClient.socket.shutdownOutput();
				mClient.socket.close();
			}
		}
		catch (Exception ex)
		{
			
		}
	}
	public void sendChatMessage(String Message, int channel)
	{
		String toPilot = "";
		if (Message.isEmpty())
			return;

		try {
			byte[] ChannelArr = intToByteArray(channel);
			byte[] MessageArr = Message.getBytes("UTF-16LE");
			byte[] MessageLenArr = intToByteArray(MessageArr.length);
			byte[] toPilotArr = toPilot.getBytes("UTF-16LE");
			byte[] toPilotLenArr = intToByteArray(toPilotArr.length);
			byte[] data = new byte[ChannelArr.length+MessageArr.length+MessageLenArr.length+toPilotArr.length+toPilotLenArr.length];
			System.arraycopy(ChannelArr, 0, data, 0, 4);
			System.arraycopy(MessageLenArr, 0, data, 4, 4);
			System.arraycopy(MessageArr, 0, data, 8, MessageArr.length);
			System.arraycopy(toPilotLenArr, 0, data, 8+MessageArr.length, 4);
			System.arraycopy(toPilotArr, 0, data, 8+MessageArr.length+4, toPilotArr.length);
			mClient.stream.sendCommand(Command.message, data);
		
		} catch (Exception ex) {
		}
	}
	
	public void clientRelogin()
	{
		//mClient.relogin
	}
	
	public ItemCollection getItems()
	{
		ItemCollection iCollect = new ItemCollection();
		try
		{
			mClient.stream.sendCommand(Command.items);
			Command command = waitCommand(Command.items);
			AddressCommand ArrPtr = new AddressCommand();
			int ItemsCount = GetIntValue(command.data, ArrPtr);
			for (int i=0; i<ItemsCount; i++)
			{
				int item_id = GetIntValue(command.data, ArrPtr);
				int item_itemType = GetIntValue(command.data, ArrPtr);
				int Item_DescriptionLen = GetIntValue(command.data, ArrPtr);
				String item_description = GetStringValue(command.data, ArrPtr, Item_DescriptionLen);
				int item_volume = GetIntValue(command.data, ArrPtr);
				int item_region_id = GetIntValue(command.data, ArrPtr);
				switch (item_itemType)
				{
					case ItemType.device:
					{
						int DeviceVendorLen = GetIntValue(command.data, ArrPtr);
						String device_vendorStr = GetStringValue(command.data, ArrPtr, DeviceVendorLen);
						int device_deviceType = GetIntValue(command.data, ArrPtr);
						int device_durability = GetIntValue(command.data, ArrPtr);
						switch (device_deviceType)
						{
							case DeviceType.ship:
							{
								int ship_slot_weapons = GetIntValue(command.data, ArrPtr);
								int ship_slot_droids = GetIntValue(command.data, ArrPtr);
								int ship_slot_shield = GetIntValue(command.data, ArrPtr);
								int ship_slot_hyper = GetIntValue(command.data, ArrPtr);
								Ship ship = new Ship();
								ship.id = item_id;
								ship.itemType = item_itemType;
								ship.description = item_description;
								ship.volume = item_volume;
								ship.region_id = item_region_id;
								ship.vendorStr = device_vendorStr;
								ship.deviceType = device_deviceType;
								ship.durability = device_durability;
								ship.slot_weapons = ship_slot_weapons;
								ship.slot_droids = ship_slot_droids;
								ship.slot_shield = ship_slot_shield;
								ship.slot_hyper = ship_slot_hyper;
								iCollect.items.add(ship);
								break;
							}
							case DeviceType.engine:
							{
								int engine_power = GetIntValue(command.data, ArrPtr);
								int engine_economic = GetIntValue(command.data, ArrPtr);
								Engine engine = new Engine();
								engine.id = item_id;
								engine.itemType = item_itemType;
								engine.description = item_description;
								engine.volume = item_volume;
								engine.region_id = item_region_id;
								engine.vendorStr = device_vendorStr;
								engine.deviceType = device_deviceType;
								engine.durability = device_durability;
								engine.power = engine_power;
								engine.economic = engine_economic;
								iCollect.items.add(engine);
								break;
							}
							case DeviceType.fuelbag:
							{
								int fuelbag_compress = GetIntValue(command.data, ArrPtr);
								Fuelbag fuelbag = new Fuelbag();
								fuelbag.id = item_id;
								fuelbag.itemType = item_itemType;
								fuelbag.description = item_description;
								fuelbag.volume = item_volume;
								fuelbag.region_id = item_region_id;
								fuelbag.vendorStr = device_vendorStr;
								fuelbag.deviceType = device_deviceType;
								fuelbag.durability = device_durability;
								fuelbag.compress = fuelbag_compress;
								iCollect.items.add(fuelbag);
								break;
							}
							case DeviceType.droid:
							{
								int droid_power = GetIntValue(command.data, ArrPtr);
								int droid_time_reload = GetIntValue(command.data, ArrPtr);
								Droid droid = new Droid();
								droid.id = item_id;
								droid.itemType = item_itemType;
								droid.description = item_description;
								droid.volume = item_volume;
								droid.region_id = item_region_id;
								droid.vendorStr = device_vendorStr;
								droid.deviceType = device_deviceType;
								droid.durability = device_durability;
								droid.power = droid_power;
								droid.time_reload = droid_time_reload;
								iCollect.items.add(droid);
								break;
							}
							case DeviceType.shield:
							{
								int shield_power = GetIntValue(command.data, ArrPtr);
								Shield shield = new Shield();
								shield.id = item_id;
								shield.itemType = item_itemType;
								shield.description = item_description;
								shield.volume = item_volume;
								shield.region_id = item_region_id;
								shield.vendorStr = device_vendorStr;
								shield.deviceType = device_deviceType;
								shield.durability = device_durability;
								shield.power = shield_power;
								iCollect.items.add(shield);
								break;
							}
							case DeviceType.hyper:
							{
								int hyper_radius = GetIntValue(command.data, ArrPtr);
								int hyper_time_start = GetIntValue(command.data, ArrPtr);
								int hyper_time_reload = GetIntValue(command.data, ArrPtr);
								Hyper hyper = new Hyper();
								hyper.id = item_id;
								hyper.itemType = item_itemType;
								hyper.description = item_description;
								hyper.volume = item_volume;
								hyper.region_id = item_region_id;
								hyper.vendorStr = device_vendorStr;
								hyper.deviceType = device_deviceType;
								hyper.durability = device_durability;
								hyper.radius = hyper_radius;
								hyper.time_start = hyper_time_start;
								hyper.time_reload = hyper_time_reload;
								iCollect.items.add(hyper);
								break;
							}
							case DeviceType.radar:
							{
								int radar_radius = GetIntValue(command.data, ArrPtr);
								int radar_defense = GetIntValue(command.data, ArrPtr);
								Radar radar = new Radar();
								radar.id = item_id;
								radar.itemType = item_itemType;
								radar.description = item_description;
								radar.volume = item_volume;
								radar.region_id = item_region_id;
								radar.vendorStr = device_vendorStr;
								radar.deviceType = device_deviceType;
								radar.durability = device_durability;
								radar.radius = radar_radius;
								radar.defense = radar_defense;
								iCollect.items.add(radar);
								break;
							}
							case DeviceType.weapon:
							{
								int weapon_weaponType = GetIntValue(command.data, ArrPtr);
								int weapon_radius = GetIntValue(command.data, ArrPtr);
								int weapon_power = GetIntValue(command.data, ArrPtr);
								int weapon_time_start = GetIntValue(command.data, ArrPtr);
								int weapon_time_reload = GetIntValue(command.data, ArrPtr);
								Weapon weapon = new Weapon();
								weapon.id = item_id;
								weapon.itemType = item_itemType;
								weapon.description = item_description;
								weapon.volume = item_volume;
								weapon.region_id = item_region_id;
								weapon.vendorStr = device_vendorStr;
								weapon.deviceType = device_deviceType;
								weapon.durability = device_durability;
								weapon.weaponType = weapon_weaponType;
								weapon.radius = weapon_radius;
								weapon.power = weapon_power;
								weapon.time_start = weapon_time_start;
								weapon.time_reload = weapon_time_reload;
								iCollect.items.add(weapon);
								break;
							}
						}
						break;
					}
				}
			}
		}
		catch (Exception ex)
		{
			
		}
		return iCollect;
	}

	
	public HashMap<Integer, User> getNearUsers()
	{
		HashMap<Integer, User> UsersMap = new HashMap<Integer, User>();
		try
		{
			mClient.stream.sendCommand(Command.users);
			Command command = waitCommand(Command.users);
			AddressCommand ArrPtr = new AddressCommand();
			int UsersCount = GetIntValue(command.data, ArrPtr);
			for (int i=0; i<UsersCount; i++)
			{
				int UserId = GetIntValue(command.data, ArrPtr);
				int ShipNameLen = GetIntValue(command.data, ArrPtr);
				String ShipName = GetStringValue(command.data, ArrPtr, ShipNameLen);
				int loginLen = GetIntValue(command.data, ArrPtr);
				String login = GetStringValue(command.data, ArrPtr, loginLen);
				int UserX = GetIntValue(command.data, ArrPtr);
				int UserY = GetIntValue(command.data, ArrPtr);
				int UserTargetX = GetIntValue(command.data, ArrPtr);
				int UserTargetY = GetIntValue(command.data, ArrPtr);
				int UserDomain = GetIntValue(command.data, ArrPtr);
				User userShip = new User(UserId, UserX, UserY, ShipName, "", UserDomain);
				userShip.login = login;
				userShip.setPositionTarget(UserTargetX, UserTargetY);
				UsersMap.put(userShip.id, userShip);
			}
		}
		catch (Exception ex)
		{

		}
		return UsersMap;
	}

	public HashMap<Integer, Planet> getPlanets(int idLocation, boolean wait)
	{
		HashMap<Integer, Planet> planets = new HashMap<Integer, Planet>();
		try
		{
			mClient.stream.sendCommand(Command.planets, intToByteArray(idLocation));
			if (!wait)
				return null;
			
			Command command = waitCommand(Command.planets);
			AddressCommand ArrPtr = new AddressCommand(4);
			int PlanetsCount = GetIntValue(command.data, ArrPtr);
			for (int i=0; i<PlanetsCount; i++)
			{
				int PlanetId = GetIntValue(command.data, ArrPtr);
				int PlanetNameLen = GetIntValue(command.data, ArrPtr);
				String PlanetName = GetStringValue(command.data, ArrPtr, PlanetNameLen);
				int PlanetType = GetIntValue(command.data, ArrPtr);
				int PlanetSpeed = GetIntValue(command.data, ArrPtr);
				int PlanetOrbit = GetIntValue(command.data, ArrPtr);
				int PlanetRadius = GetIntValue(command.data, ArrPtr);
				Color color = GetColorValue(command.data, ArrPtr);
				Color atmColor = GetColorValue(command.data, ArrPtr);
				int PlanetDomain = GetIntValue(command.data, ArrPtr);
				int PlanetAtmosphere_speedX = GetIntValue(command.data, ArrPtr);
				int PlanetAtmosphere_speedY = GetIntValue(command.data, ArrPtr);
				Planet planet = new Planet(PlanetId, PlanetName, PlanetType, PlanetRadius, PlanetSpeed, PlanetOrbit, idLocation, PlanetDomain, PlanetAtmosphere_speedX, PlanetAtmosphere_speedY);
				planet.color  = color;
				planet.atmColor  = atmColor;
				planets.put(PlanetId, planet);
			}
		}
		catch (Exception ex)
		{
			clientRelogin();
		}
		return planets;
	}
	
	public HashMap<Integer, Location> getAllLocations()
	{
		HashMap<Integer, Location> locations = new HashMap<Integer, Location>();
		try
		{
			mClient.stream.sendCommand(Command.locations);
			Command command = waitCommand(Command.locations);
			AddressCommand ArrPtr = new AddressCommand();
			int LocationsCount = GetIntValue(command.data, ArrPtr);
			for (int i=0; i<LocationsCount; i++)
			{
				Location Loc   = new Location();
				Loc.id = GetIntValue(command.data, ArrPtr);
				int StarNameLen = GetIntValue(command.data, ArrPtr);
				Loc.starName = GetStringValue(command.data, ArrPtr, StarNameLen);
				Loc.starType = GetIntValue(command.data, ArrPtr);
				Loc.x = GetIntValue(command.data, ArrPtr);
				Loc.y = GetIntValue(command.data, ArrPtr);
				Loc.starRadius = GetIntValue(command.data, ArrPtr);
				Loc.domain = GetIntValue(command.data, ArrPtr);
				locations.put(Loc.id, Loc);
			}
		}
		catch (Exception ex)
		{
			clientRelogin();
		}
		return locations;
	}
	
	public HashMap<Integer, Nebula> getAllNebulas()
	{
		HashMap<Integer, Nebula> nebulas = new HashMap<Integer, Nebula>();
		try
		{
			mClient.stream.sendCommand(Command.nebulas);
			Command command = waitCommand(Command.nebulas);
			AddressCommand ArrPtr = new AddressCommand();
			int NebulasCount = GetIntValue(command.data, ArrPtr);
			for (int i=0;i<NebulasCount;i++)
			{
				int NebId = GetIntValue(command.data, ArrPtr);
				int NebType = GetIntValue(command.data, ArrPtr);
				int NebX = GetIntValue(command.data, ArrPtr);
				int NebY = GetIntValue(command.data, ArrPtr);
				int NebScale = GetIntValue(command.data, ArrPtr);
				int NebAngle = GetIntValue(command.data, ArrPtr);
				Nebula Neb = new Nebula(NebId, NebType, NebX, NebY, NebAngle, NebScale);
				nebulas.put(Neb.id, Neb);
			}
		}
		catch (Exception ex)
		{
			clientRelogin();
		}
		return nebulas;
	}
	
	public HashMap<Integer, Domain> getAllDomains()
	{
		HashMap<Integer, Domain> domains = new HashMap<Integer, Domain>();
		try
		{
			mClient.stream.sendCommand(Command.domains);
			Command command = waitCommand(Command.domains);
			AddressCommand ArrPtr = new AddressCommand();
			int DomainsCount = GetIntValue(command.data, ArrPtr);
			for (int i=0;i<DomainsCount;i++)
			{
				Domain domain = new Domain();
				domain.id = GetIntValue(command.data, ArrPtr);
				domain.color = GetColorValue(command.data, ArrPtr);
				int DomainNameLen = GetIntValue(command.data, ArrPtr);
				domain.domainName = GetStringValue(command.data, ArrPtr, DomainNameLen);
				domain.x = GetIntValue(command.data, ArrPtr);
				domain.y = GetIntValue(command.data, ArrPtr);
				domains.put(domain.id, domain);
			}
		}
		catch (Exception ex)
		{
			clientRelogin();
		}
		return domains;
	}
	
	public Player getUserData()
	{
		try
		{
			mClient.stream.sendCommand(Command.player);

			Command command = waitCommand(Command.player);

			AddressCommand ArrPtr = new AddressCommand();
			int UserId = GetIntValue(command.data, ArrPtr);			
			int UserX = GetIntValue(command.data, ArrPtr);
			int UserY = GetIntValue(command.data, ArrPtr);
			int UserDomain = GetIntValue(command.data, ArrPtr);
			int UserInPlanet = GetIntValue(command.data, ArrPtr);
			int PnameLen = GetIntValue(command.data, ArrPtr);
			String PName = GetStringValue(command.data, ArrPtr, PnameLen);			
			int SnameLen = GetIntValue(command.data, ArrPtr);			
			String SName = GetStringValue(command.data, ArrPtr, SnameLen);			
			Player player = new Player(UserId, UserX, UserY, PName, SName, UserDomain, UserInPlanet);
			player.equips = new ArrayList<Equip>();
			player.login = mClient.login;
			return player;
		}
		catch (Exception ex)
		{
			clientRelogin();
		}
		return null;
	}

	public List<Equip> getEquips(String userLogin)
	{
		try
		{
			byte[] userLoginArr = userLogin.getBytes("UTF-16LE");
			byte[] userLoginLenArr = intToByteArray(userLoginArr.length);
			byte[] data = new byte[userLoginLenArr.length+userLoginArr.length];
			System.arraycopy(userLoginLenArr, 0, data, 0, 4);
			System.arraycopy(userLoginArr, 0, data, 4, userLoginArr.length);
			mClient.stream.sendCommand(Command.equip, data);
			Command command = waitCommand(Command.equip);
			
			AddressCommand ArrPtr = new AddressCommand();
			List<Equip> Res = new ArrayList<Equip>();
			int eqCount = GetIntValue(command.data, ArrPtr);
			for (int i=0;i<eqCount;i++)
			{
				int item_id = GetIntValue(command.data, ArrPtr);
				int iType = GetIntValue(command.data, ArrPtr);
				int dType = GetIntValue(command.data, ArrPtr);
				int in_use = GetIntValue(command.data, ArrPtr);
				int condition = GetIntValue(command.data, ArrPtr);
				int location = GetIntValue(command.data, ArrPtr);
				Equip eq = new Equip();
				eq.in_use = false;
				if (in_use==1) {eq.in_use=true;}
				eq.condition = condition;
				eq.location = location;
				eq.item = null;
				if (iType==1)
				{
					switch (dType)
					{
						case DeviceType.droid:
						{
							Droid item = Controllers.locController.items.getDroid(item_id);
							eq.item = item;
							break;
						}
						case DeviceType.engine:
						{
							Engine item = Controllers.locController.items.getEngine(item_id);
							eq.item = item;
							break;
						}
						case DeviceType.fuelbag:
						{
							Fuelbag item = Controllers.locController.items.getFuelbag(item_id);
							eq.item = item;
							break;
						}
						case DeviceType.hyper:
						{
							Hyper item = Controllers.locController.items.getHyper(item_id);
							eq.item = item;
							break;
						}
						case DeviceType.radar:
						{
							Radar item = Controllers.locController.items.getRadar(item_id);
							eq.item = item;
							break;
						}
						case DeviceType.shield:
						{
							Shield item = Controllers.locController.items.getShield(item_id);
							eq.item = item;
							break;
						}
						case DeviceType.ship:
						{
							Ship item = Controllers.locController.items.getShip(item_id);
							eq.item = item;
							break;
						}
						case DeviceType.weapon:
						{
							Weapon item = Controllers.locController.items.getWeapon(item_id);
							eq.item = item;
							break;
						}
					}
				}
				Res.add(eq);
			}
			return Res;
		}
		catch (Exception ex)
		{

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
		if (idWaitCommand != Command.none && idWaitCommand == command.idCommand)
		{			
			idWaitCommand = Command.none;
			currentCommand = command;
			cWaitCommand.signal();
			cCopyCommand.signalWait();
			return;
		}
		
		switch (command.idCommand) {
		case Command.addUser:
		{
			AddressCommand ArrPtr = new AddressCommand();
			int UserId = GetIntValue(command.data, ArrPtr);
			int ShipNameLen = GetIntValue(command.data, ArrPtr);
			String ShipName = GetStringValue(command.data, ArrPtr, ShipNameLen);
			int loginLen = GetIntValue(command.data, ArrPtr);
			String login = GetStringValue(command.data, ArrPtr, loginLen);
			int UserX = GetIntValue(command.data, ArrPtr);
			int UserY = GetIntValue(command.data, ArrPtr);
			int TargetX = GetIntValue(command.data, ArrPtr);
			int TargetY = GetIntValue(command.data, ArrPtr);
			int UserDomain = GetIntValue(command.data, ArrPtr);
			User user = new User(UserId, UserX, UserY, ShipName, "", UserDomain);
			user.login = login;
			user.setPositionTarget(TargetX, TargetY);
			cController.addCommand(new AddUserCommand(user));
			break;
		}
		case Command.touchUser:
		{
			AddressCommand ArrPtr = new AddressCommand();
			int UserId = GetIntValue(command.data, ArrPtr);
			int UserTouchX = GetIntValue(command.data, ArrPtr);
			int UserTouchY = GetIntValue(command.data, ArrPtr);
			cController.addCommand(new SetTargetCommand(UserId, UserTouchX, UserTouchY));
			break;
		}
		case Command.removeUser:
		{
			int UserId = GetIntValue(command.data, new AddressCommand());
			cController.addCommand(new RemoveUserCommand(UserId));
			break;
		}
		case Command.disconnect:
		{
			try
			{
				receiver.stopThread();
				mClient.socket.shutdownInput();
				mClient.socket.shutdownOutput();
				mClient.socket.close();
				cController.addCommand(new SwitchScreenCommand(new MainMenu()));
			}
			catch (Exception ex)
			{
			}
		}
		case Command.message:
		{
			AddressCommand ArrPtr = new AddressCommand();
			int 	channel 	= GetIntValue(command.data, ArrPtr);
			int 	messageLen 	= GetIntValue(command.data, ArrPtr);
			String 	message 	= GetStringValue(command.data, ArrPtr, messageLen);
			int 	nameLen 	= GetIntValue(command.data, ArrPtr);
			String 	userName 	= GetStringValue(command.data, ArrPtr, nameLen);
			int 	toPilotLen 	= GetIntValue(command.data, ArrPtr);
			String 	toPilot 	= GetStringValue(command.data, ArrPtr, toPilotLen);
			cController.addCommand(new ChatNewMessageCommand(userName, channel, message, toPilot));
			break;
		}
		case Command.planets:
		{
			AddressCommand ArrPtr = new AddressCommand(0);
			int locID = GetIntValue(command.data, ArrPtr);
			int PlanetsCount = GetIntValue(command.data, ArrPtr);
			for (int i=0; i<PlanetsCount; i++)
			{
				int PlanetId      = GetIntValue(command.data, ArrPtr);
				int PlanetNameLen = GetIntValue(command.data, ArrPtr);
				String PlanetName = GetStringValue(command.data, ArrPtr, PlanetNameLen);
				int PlanetType 	  = GetIntValue(command.data, ArrPtr);
				int PlanetSpeed   = GetIntValue(command.data, ArrPtr);
				int PlanetOrbit   = GetIntValue(command.data, ArrPtr);
				int PlanetRadius  = GetIntValue(command.data, ArrPtr);
				Color PlanetColor = GetColorValue(command.data, ArrPtr);
				Color AtmColor = GetColorValue(command.data, ArrPtr);
				int PlanetDomain = GetIntValue(command.data, ArrPtr);
				int PlanetAtmosphere_speedX = GetIntValue(command.data, ArrPtr);
				int PlanetAtmosphere_speedY = GetIntValue(command.data, ArrPtr);
				Planet planet = new Planet(PlanetId, PlanetName, PlanetType, PlanetRadius, PlanetSpeed, PlanetOrbit, locID, PlanetDomain, PlanetAtmosphere_speedX, PlanetAtmosphere_speedY);
				planet.color = PlanetColor;
				planet.atmColor = AtmColor;
				cController.addCommand(new AddPlanetCommand(planet));
			}
			break;
		}

		default:
			break;
		}
	}

	private int GetIntValue(byte[] data, AddressCommand AC)
	{
		int Res=0;
		byte[] Arr = new byte[4];
		System.arraycopy(data, AC.address, Arr, 0, 4);
		AC.delta(4);
		Res = byteArrayToInt(Arr);
		return Res;
	}

	private String GetStringValue(byte[] data, AddressCommand AC, int SL)
	{
		String Res = "";
		byte[] Arr = new byte[SL];
		System.arraycopy(data, AC.address, Arr, 0, SL);
		AC.delta(SL);
		try {
			Res = new String(Arr, "UTF-16LE");
		} catch (UnsupportedEncodingException e) {
			Gdx.app.log("Получение строки", "Ошибка: " + e.getMessage());
		}
		return Res;
	}
	
	 private Color GetColorValue(byte[] data, AddressCommand AC)
	 {
		 byte[] Arr = new byte[4];
		 System.arraycopy(data, AC.address, Arr, 0, 4);
		 AC.delta(4);
		 char R = (char)(Arr[0]&0xFF);
		 char G = (char)(Arr[1]&0xFF);
		 char B = (char)(Arr[2]&0xFF);
		 char A = (char)(Arr[3]&0xFF);
		 Color Res =	new Color( R / 255.0f, G / 255.0f, B / 255.0f, A / 255.0f);
		 return Res;
	 }
	
	class AddressCommand {
		public AddressCommand() {
			this.address = 0;
		}
		public AddressCommand(int start){
			this.address = start;
		}
		
		public int address;
		public void delta(int delta){
			address += delta;
		}
	}
}
