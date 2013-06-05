package com.game.rania.net;

import java.io.DataInputStream;
import java.io.InputStream;

import com.badlogic.gdx.Gdx;
import com.game.rania.RaniaGame;

public class Receiver extends Thread
{	
	private volatile boolean mFinish = false;
	
	public void finish()
    {
        mFinish = true;
    }
	
	public void run()
	{
		try
		{
			InputStream sin = RaniaGame.mUser.socket.getInputStream();
			DataInputStream in = new DataInputStream(sin);
			byte[] bytesCom = new byte[4];
			byte[] bytesLen = new byte[4];
			int Command = 0;
			int Length = 0;
			byte[] data;
			do
	        {
				if (!mFinish)
				{
					in.read(bytesCom);
					in.read(bytesLen);
					Command = RaniaGame.nController.byteArrayToInt(bytesCom);
					Length = RaniaGame.nController.byteArrayToInt(bytesLen);
					data = new byte[Length];
					in.read(data);
					if (Command == 12) //игрок id появился в локации
					{
						Gdx.app.log("receiver", "Command " + Command);
						int ArrPtr =0;
						byte[] idArr = new byte[4];
						for (int j=0;j<4;j++)
						{
							idArr[j]=data[ArrPtr];
							ArrPtr++;
						}
						byte[] ShipNameLenArr = new byte[4];
						for (int j=0;j<4;j++)
						{
							ShipNameLenArr[j]=data[ArrPtr];
							ArrPtr++;
						}
						byte[] ShipNameArr = new byte[RaniaGame.nController.byteArrayToInt(ShipNameLenArr)];
						for (int j=0;j<RaniaGame.nController.byteArrayToInt(ShipNameLenArr);j++)
						{
							ShipNameArr[j]=data[ArrPtr];
							ArrPtr++;
						}
						byte[] xArr = new byte[4];
						for (int j=0;j<4;j++)
						{
							xArr[j]=data[ArrPtr];
							ArrPtr++;
						}
						byte[] yArr = new byte[4];
						for (int j=0;j<4;j++)
						{
							yArr[j]=data[ArrPtr];
							ArrPtr++;
						}
						int UserId = RaniaGame.nController.byteArrayToInt(idArr); //  Id игрока который зашел
						int UserX = RaniaGame.nController.byteArrayToInt(xArr);   //  координата X где он появился
						int UserY = RaniaGame.nController.byteArrayToInt(yArr);   //  координата Y где он появился
						String ShipName = new String(ShipNameArr, "UTF-16LE");  // имя корабля
						Gdx.app.log("receiver", "UserId " + UserId);
						Gdx.app.log("receiver", "UserX " + UserX);
						Gdx.app.log("receiver", "UserY " + UserY);
						Gdx.app.log("receiver", "ShipName " + ShipName);
					}
					if (Command == 13) //игрок id тыкнул в экран
					{
						Gdx.app.log("receiver", "Command " + Command);
						int ArrPtr =0;
						byte[] idArr = new byte[4];
						for (int j=0;j<4;j++)
						{
							idArr[j]=data[ArrPtr];
							ArrPtr++;
						}
						byte[] xArr = new byte[4];
						for (int j=0;j<4;j++)
						{
							xArr[j]=data[ArrPtr];
							ArrPtr++;
						}
						byte[] yArr = new byte[4];
						for (int j=0;j<4;j++)
						{
							yArr[j]=data[ArrPtr];
							ArrPtr++;
						}
						int UserId = RaniaGame.nController.byteArrayToInt(idArr);      //   id игрока
						int UserTouchX = RaniaGame.nController.byteArrayToInt(xArr);   //   X тыка 
						int UserTouchY = RaniaGame.nController.byteArrayToInt(yArr);   //   Y тыка
						Gdx.app.log("receiver", "UserId " + UserId);
						Gdx.app.log("receiver", "UserTouchX " + UserTouchX);
						Gdx.app.log("receiver", "UserTouchY " + UserTouchY);
					}
					if (Command == 14) //игрок id пропал из локации. хз куда делся) эт не важно)
					{
						Gdx.app.log("receiver", "Command " + Command);
						int ArrPtr =0;
						byte[] idArr = new byte[4];
						for (int j=0;j<4;j++)
						{
							idArr[j]=data[ArrPtr];
							ArrPtr++;
						}
						int UserId = RaniaGame.nController.byteArrayToInt(idArr);      //   id игрока которого над удалить из локации
						Gdx.app.log("receiver", "UserId " + UserId);
					}
				}
	        }
	        while(true);
		}
		catch (Exception ex)
		{
			Gdx.app.log("receiver", "Error " + ex.getMessage());
		}
	}
}
