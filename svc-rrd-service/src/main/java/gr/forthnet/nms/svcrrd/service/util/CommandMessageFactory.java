package gr.forthnet.nms.svcrrd.service.util;

import gr.forthnet.nms.svcrrd.model.entities.NE;
import gr.forthnet.nms.svcrrd.service.messages.RegistrationCommandMessage;
import gr.forthnet.nms.svcrrd.service.messages.RegistrationCommandMessageReply;

import java.util.Set;

public class CommandMessageFactory {

	public static RegistrationCommandMessage createRegistrationCommandMessage(
			String instanceId, String listeningQueue, Set<NE> nes) {
		RegistrationCommandMessage message = new RegistrationCommandMessage();

		message.setInstanceId(instanceId);
		message.setListeningQueue(listeningQueue);
		message.setNes(nes);

		return message;
	}

	public static RegistrationCommandMessageReply createRegistrationCommandMessageReply(
			String instanceId, boolean status) {
		RegistrationCommandMessageReply message = new RegistrationCommandMessageReply();

		message.setInstanceId(instanceId);
		message.setStatus(true);

		return message;
	}
}
