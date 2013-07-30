package com.game.rania.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.game.rania.controller.command.ControllerCommand;

public class CommandController {

	private List<ControllerCommand> commands = Collections.synchronizedList(new ArrayList<ControllerCommand>());
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
