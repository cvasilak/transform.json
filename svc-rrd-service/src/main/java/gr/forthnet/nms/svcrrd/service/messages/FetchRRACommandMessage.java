package gr.forthnet.nms.svcrrd.service.messages;

import java.util.HashMap;

public class FetchRRACommandMessage extends CommandMessage {
	
	private HashMap<String, String> params;
	
	public FetchRRACommandMessage() {
		super("fetchRRA");
	}

	public HashMap<String, String> getParams() {
		return params;
	}

	public void setParams(HashMap<String, String> params) {
		this.params = params;
	}
}
