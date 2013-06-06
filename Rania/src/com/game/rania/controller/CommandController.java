package com.game.rania.controller;

import java.util.Vector;

import com.game.rania.controller.command.ControllerCommand;

public class CommandController {

	private Vector<ControllerCommand> commands = new Vector<ControllerCommand>();
	private MainController mController = null;
	
	public CommandController(MainController controller){
		mController = controller;
	}
	
	public synchronized void addCommand(ControllerCommand command){
		commands.add(command);
	}
	
	public synchronized void removeCommand(ControllerCommand command){
		commands.remove(command);
	}

	public synchronized void removeCommand(int num){
		commands.remove(num);
	}
	
	public synchronized void clearCommands(){
		commands.clear();
	}
	
	public synchronized void updateCommands(float deltaTime){
		for (ControllerCommand command : commands) {
			command.update(mController, deltaTime);
		}
		commands.clear();
	}
}
