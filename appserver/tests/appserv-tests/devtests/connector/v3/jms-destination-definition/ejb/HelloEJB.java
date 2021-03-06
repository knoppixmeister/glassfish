/*
 * Copyright (c) 2017, 2020 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0, which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * This Source Code may also be made available under the following Secondary
 * Licenses when the conditions for such availability set forth in the
 * Eclipse Public License v. 2.0 are satisfied: GNU General Public License,
 * version 2 with the GNU Classpath Exception, which is available at
 * https://www.gnu.org/software/classpath/license.html.
 *
 * SPDX-License-Identifier: EPL-2.0 OR GPL-2.0 WITH Classpath-exception-2.0
 */

package com.sun.s1asdev.ejb.ejb30.hello.session3;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.Stateless;
import jakarta.jms.Destination;
import jakarta.jms.JMSDestinationDefinition;
import jakarta.jms.JMSDestinationDefinitions;
import javax.naming.InitialContext;

@JMSDestinationDefinitions(
        value = {
                @JMSDestinationDefinition(
                        description = "global-scope resource defined by @JMSDestinationDefinition",
                        name = "java:global/env/HelloEJB_ModByDD_JMSDestination",
                        interfaceName = "jakarta.jms.Queue",
                        resourceAdapter = "jmsra",
                        destinationName = "myPhysicalQueue"
                ),

                @JMSDestinationDefinition(
                        description = "global-scope resource defined by @JMSDestinationDefinition",
                        name = "java:global/env/HelloEJB_Annotation_JMSDestination",
                        interfaceName = "jakarta.jms.Queue",
                        resourceAdapter = "jmsra",
                        destinationName = "myPhysicalQueue"
                ),

                @JMSDestinationDefinition(
                        description = "application-scope resource defined by @JMSDestinationDefinition",
                        name = "java:app/env/HelloEJB_Annotation_JMSDestination",
                        interfaceName = "jakarta.jms.Topic",
                        resourceAdapter = "jmsra",
                        destinationName = "myPhysicalTopic"
                ),

                @JMSDestinationDefinition(
                        description = "module-scope resource defined by @JMSDestinationDefinition",
                        name = "java:module/env/HelloEJB_Annotation_JMSDestination",
                        interfaceName = "jakarta.jms.Topic",
                        resourceAdapter = "jmsra",
                        destinationName = "myPhysicalTopic"
                ),

                @JMSDestinationDefinition(
                        description = "component-scope resource defined by @JMSDestinationDefinition",
                        name = "java:comp/env/HelloEJB_Annotation_JMSDestination",
                        interfaceName = "jakarta.jms.Queue",
                        resourceAdapter = "jmsra",
                        destinationName = "myPhysicalQueue"
                )
        }
)

@Stateless
public class HelloEJB implements Hello {

    @PostConstruct
    public void postConstruction() {
        System.out.println("In HelloEJB::postConstruction()");
    }

    public void hello() {
        // JMSDestination-Definition through Annotation
        lookupJMSDestination("java:global/env/Appclient_ModByDD_JMSDestination", true);
        lookupJMSDestination("java:global/env/Appclient_Annotation_JMSDestination", true);
        lookupJMSDestination("java:app/env/Appclient_Annotation_JMSDestination", true);
        lookupJMSDestination("java:module/env/Appclient_Annotation_JMSDestination", false);
        lookupJMSDestination("java:comp/env/Appclient_Annotation_JMSDestination", false);

        lookupJMSDestination("java:global/env/Servlet_ModByDD_JMSDestination", true);
        lookupJMSDestination("java:global/env/Servlet_JMSDestination", true);
        lookupJMSDestination("java:app/env/Servlet_JMSDestination", true);
        lookupJMSDestination("java:module/env/Servlet_JMSDestination", false);
        lookupJMSDestination("java:comp/env/Servlet_JMSDestination", false);

        lookupJMSDestination("java:global/env/HelloStatefulEJB_ModByDD_JMSDestination", true);
        lookupJMSDestination("java:global/env/HelloStatefulEJB_Annotation_JMSDestination", true);
        lookupJMSDestination("java:app/env/HelloStatefulEJB_Annotation_JMSDestination", true);
        lookupJMSDestination("java:module/env/HelloStatefulEJB_Annotation_JMSDestination", true);
        lookupJMSDestination("java:comp/env/HelloStatefulEJB_Annotation_JMSDestination", false);

        lookupJMSDestination("java:global/env/HelloEJB_ModByDD_JMSDestination", true);
        lookupJMSDestination("java:global/env/HelloEJB_Annotation_JMSDestination", true);
        lookupJMSDestination("java:app/env/HelloEJB_Annotation_JMSDestination", true);
        lookupJMSDestination("java:module/env/HelloEJB_Annotation_JMSDestination", true);
        lookupJMSDestination("java:comp/env/HelloEJB_Annotation_JMSDestination", true);

        // JMSDestination-Definition through DD
        lookupJMSDestination("java:global/env/Application_DD_JMSDestination", true);
        lookupJMSDestination("java:app/env/Application_DD_JMSDestination", true);

        lookupJMSDestination("java:global/env/Appclient_DD_JMSDestination", true);
        lookupJMSDestination("java:app/env/Appclient_DD_JMSDestination", true);
        lookupJMSDestination("java:module/env/Appclient_DD_JMSDestination", false);
        lookupJMSDestination("java:comp/env/Appclient_DD_JMSDestination", false);

        lookupJMSDestination("java:global/env/Web_DD_JMSDestination", true);
        lookupJMSDestination("java:app/env/Web_DD_JMSDestination", true);
        lookupJMSDestination("java:module/env/Web_DD_JMSDestination", false);
        lookupJMSDestination("java:comp/env/Web_DD_JMSDestination", false);

        lookupJMSDestination("java:global/env/HelloStatefulEJB_DD_JMSDestination", true);
        lookupJMSDestination("java:app/env/HelloStatefulEJB_DD_JMSDestination", true);
        lookupJMSDestination("java:module/env/HelloStatefulEJB_DD_JMSDestination", true);
        lookupJMSDestination("java:comp/env/HelloStatefulEJB_DD_JMSDestination", false);

        lookupJMSDestination("java:global/env/HelloEJB_DD_JMSDestination", true);
        lookupJMSDestination("java:app/env/HelloEJB_DD_JMSDestination", true);
        lookupJMSDestination("java:module/env/HelloEJB_DD_JMSDestination", true);
        lookupJMSDestination("java:comp/env/HelloEJB_DD_JMSDestination", true);

        System.out.println("Stateless EJB lookup jms-destination-definitions successfully!");
    }

    private void lookupJMSDestination(String jndiName, boolean expectSuccess) {
        try {
            System.out.println("Stateless EJB lookup jms destination: " + jndiName);
            InitialContext ic = new InitialContext();
            Destination dest = (Destination) ic.lookup(jndiName);
            System.out.println("Stateless EJB can access jms destination: " + jndiName);
        } catch (Exception e) {
            if (expectSuccess) {
                throw new RuntimeException("Stateless EJB failed to access jms destination: " + jndiName, e);
            }
            System.out.println("Stateless EJB cannot access jms destination: " + jndiName);
            return;
        }
        if (!expectSuccess) {
            throw new RuntimeException("Stateless EJB should not run into here.");
        }
    }
}
