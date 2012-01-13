package gr.forthnet.nms.svcrrd.service.util;

import gr.forthnet.nms.svcrrd.service.messages.FetchRRACommandMessage;
import gr.forthnet.nms.svcrrd.service.messages.RegistrationCommandMessage;

import org.codehaus.jackson.map.ObjectMapper;

public class JsonUtil {

	private volatile static JsonUtil instance;

	private volatile static ObjectMapper mapper;

	private JsonUtil() {
		mapper = new ObjectMapper();
	}

	public static JsonUtil getInstance() {
		if (instance == null) {
			synchronized (JsonUtil.class) {
				if (instance == null) {  // double-checked-locking
					instance = new JsonUtil();
				}
			}
		}

		return instance;
	}

	public FetchRRACommandMessage fetchRRACommandMessagefromJSON(
			String json) throws Exception {

		FetchRRACommandMessage message = mapper.readValue(json,
				FetchRRACommandMessage.class);
			
		return message;
	}

	public RegistrationCommandMessage registrationCommandMessagefromJSON(
			String json) throws Exception {

		RegistrationCommandMessage message = mapper.readValue(json,
					RegistrationCommandMessage.class);
			
		return message;
	}
	
	public String toJSON(
			Object obj) throws Exception {
		return mapper.writeValueAsString(obj);
	}	
}
