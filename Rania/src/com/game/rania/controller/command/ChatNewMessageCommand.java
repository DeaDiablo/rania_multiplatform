package com.game.rania.controller.command;

import com.game.rania.controller.MainController;
import com.game.rania.screen.part.Parts;

public class ChatNewMessageCommand extends ControllerCommand{

	private String text = "";
	
	public ChatNewMessageCommand(String userName, int channel, String message, String toPilot) {
		text = userName + ": ";
		if (!toPilot.isEmpty()) {
			text += toPilot + ", ";
			}
		text += message;
	}

	@Override
	public void update(MainController controller, float deltaTime) {
		if (text.isEmpty())
			return;
		Parts.getSideBar().fieldChat.addText(text);
	}
}
