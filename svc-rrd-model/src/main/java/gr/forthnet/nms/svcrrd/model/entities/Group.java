package gr.forthnet.nms.svcrrd.model.entities;

import java.util.Set;

public class Group {
	
	private String name;
	private Set<RRA> rras;

	public Group() {
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<RRA> getRras() {
		return rras;
	}

	public void setRras(Set<RRA> rras) {
		this.rras = rras;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		
		builder.append("\t\t\tgroup-name:").append(name).append(", ");
		
		for(RRA rra: rras) {
			builder.append("[").append(rra).append("],");
		}
		
		return builder.toString();
		
	}
}
