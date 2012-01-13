package gr.forthnet.nms.svcrrd.service.singleton;

import gr.forthnet.nms.svcrrd.model.entities.Group;
import gr.forthnet.nms.svcrrd.model.entities.NE;
import gr.forthnet.nms.svcrrd.service.messages.RegistrationCommandMessage;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named("Routing")
@ApplicationScoped
@Singleton
@Startup
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
public class RoutingBean {
	
	private final static Logger logger = Logger.getLogger(RoutingBean.class.getName());
	
	// HashMap<prefix-id, HashMap<queue, NE>>();
	private HashMap<String, HashMap<String, NE>> routing;
	
	@PostConstruct
	public void init() {
		logger.info("@PostConstruct()");
		
		routing  =  new HashMap<String, HashMap<String, NE>>();
	}
	
	@PreDestroy
	public void cleanup() {
		logger.info("@PreDestroy()");
	}

	@Lock(LockType.WRITE)
	public void addRouteFromRegistrationCommandMsg(RegistrationCommandMessage message) {
		String listenQueue = message.getListeningQueue();
		
		Set<NE> nes = message.getNes();
		
		for (NE ne: nes) {
			String prefixID = ne.getPrefixID();

			/*
			 *  100 -> bw -> input, output
			 *  100 -> cpu -> cpu5min, cpu5sec
			 *  197 -> dslam -> foo1, foo2
			 */
			
			HashMap<String, NE> details;
			
			if (routing.containsKey(prefixID)) {
				details = routing.get(prefixID);

			} else {
				details = new HashMap<String, NE>();
								
			}
			
			details.put(listenQueue, ne);
			routing.put(prefixID, details);
		}
	}
	
	@Lock(LockType.READ)
	public Set<Group> getRRASforNE(String neID) {
		//HashMap<queue, NE>>();	
		
		Set<Group> groups = new HashSet<Group>();
		
		HashMap<String, NE> nes = routing.get(neID.substring(0, 3));
		
		for (NE ne: nes.values()) {
			groups.addAll(ne.getGroups());
		}

		return groups;
	}
	
	@Lock(LockType.READ)
	public Set<String> getActivePrefixs() {
		Set<String> prefixs = new HashSet<String>();

		prefixs.addAll(routing.keySet());

		return prefixs;
	}
	
	@Lock(LockType.READ)
	public String getConsumerQueueForNE(String prefixID, String rra) {
		return null;
	}
	
	@Lock(LockType.READ)
	public String getConsumerQueueForNEGroup(String prefixID, String groupName) {
		return null;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

		for (Map.Entry<String, HashMap<String, NE>> r : routing.entrySet()) {
			builder.append("prefix-id:" + r.getKey()).append("\n");
			
			for (Map.Entry<String, NE> v: r.getValue().entrySet()) 
				builder.append("\t queue:").append(v.getKey()).append("\n") 
						.append(v.getValue()).append("\n");
			
			builder.append("\n");
		}
		
		return builder.toString();
		
	}
}
