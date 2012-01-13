package gr.forthnet.nms.svcrrd.service.routes;

import org.apache.camel.builder.RouteBuilder;
import org.switchyard.component.camel.Route;

@Route(RegistrationRoute.class)
public class RegistrationRouteBuilder extends RouteBuilder {
    
    /**
     * The Camel route is configured via this method.  The from:
     * endpoint is required to be a SwitchYard service.
     */
    public void configure() {
    	from("switchyard://RegistrationRoute").routeId("RegistrationRoute")
    	.to("bean:Routing?method=addRouteFromRegistrationCommandMsg")
    	.log("registration completed");
    }
}
