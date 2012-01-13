package gr.forthnet.nms.svcrrd.service.messages;

import gr.forthnet.nms.svcrrd.model.entities.Group;

import java.util.Set;

public class FetchRRACommandMessageReply extends CommandMessageReply {
	
	private Set<Group> groups;
	
	public FetchRRACommandMessageReply() {
		super("fetchRRA");
	}

	public Set<Group> getGroups() {
		return groups;
	}

	public void setGroups(Set<Group> groups) {
		this.groups = groups;
	}
}
