package gr.forthnet.nms.svcrrd.service.messages;

import gr.forthnet.nms.svcrrd.service.util.JsonUtil;

public abstract class CommandMessage {

	private String name;
	
	public CommandMessage(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public static CommandMessage decodeMessage(String type, String body) {
		
		CommandMessage message = null;
		
		try {
			if (type.equals("register")) {
				message = JsonUtil.getInstance().registrationCommandMessagefromJSON(body);
			} else if (type.equals("fetchRRA")) {
				message = JsonUtil.getInstance().fetchRRACommandMessagefromJSON(body);
			}
			
		} catch (Exception e) {
			
		}
		
		return message;
	}
}
