/*
 * JBoss, Home of Professional Open Source Copyright 2009, Red Hat Middleware
 * LLC, and individual contributors by the @authors tag. See the copyright.txt
 * in the distribution for a full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it under the
 * terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This software is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this software; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA, or see the FSF
 * site: http://www.fsf.org.
 */
package gr.forthnet.nms.svcrrd.service.tests;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.hornetq.api.core.TransportConfiguration;
import org.hornetq.core.remoting.impl.netty.NettyConnectorFactory;
import org.hornetq.jms.client.HornetQConnectionFactory;
import org.hornetq.jms.client.HornetQQueue;
import org.junit.Test;

/**
 * HornetQ client that uses HornetQ API to connect to a remote server and
 * send a message to a queue.
 *
 * @author Daniel Bevenius
 *
 */
public class HornetQClient {
    
    /**
     * The queue to send to.
     */
    private static final String QUEUE_NAME = "svc_rrd";
    
    /**
     * Only execution point for this application.
     * @param ignored not used.
     * @throws Exception if something goes wrong.
     */
    @Test
    public void test() {
        HornetQConnectionFactory hornetQConnectionFactory = null;
        Connection connection = null;
        Session session = null;
        
        try {
            hornetQConnectionFactory = new HornetQConnectionFactory(false, new TransportConfiguration(NettyConnectorFactory.class.getName()));
            connection = hornetQConnectionFactory.createConnection();
            connection.start();
            
            session = connection.createSession(false, DeliveryMode.NON_PERSISTENT);
            final MessageProducer producer = session.createProducer(new HornetQQueue(QUEUE_NAME));
            producer.send(session.createTextMessage("Hello World!"));
            System.out.println("Message sent. Please see server console output");
        } catch (Exception e) {
        	e.printStackTrace();
        }finally {
            ClientUtil.closeSession(session);
            ClientUtil.closeConnection(connection);
            ClientUtil.closeConnectionFactory(hornetQConnectionFactory);
        }
    }
}
