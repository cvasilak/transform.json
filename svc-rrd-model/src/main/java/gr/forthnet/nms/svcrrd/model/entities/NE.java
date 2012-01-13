package gr.forthnet.nms.svcrrd.model.entities;

import java.util.Set;

public class NE {
	
	private String prefixID;
	private Set<Group> groups;

	public static enum Type {
		BANDWIDTH,
		PERCENT,
		ABSOLUTE
	}
	
	public NE() {
	}
	
	public String getPrefixID() {
		return prefixID;
	}

	public void setPrefixID(String prefixID) {
		this.prefixID = prefixID;
	}

	public Set<Group> getGroups() {
		return groups;
	}

	public void setGroups(Set<Group> list) {
		this.groups = list;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (Group group: groups) {
			builder.append(group).append("\n");
		}
		
		return builder.toString();
		
	}
}
