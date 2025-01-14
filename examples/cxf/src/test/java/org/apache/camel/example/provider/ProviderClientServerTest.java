/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.camel.example.provider;

import org.apache.camel.example.cxf.provider.Client;
import org.apache.camel.test.AvailablePortFinder;
import org.apache.camel.test.spring.junit5.CamelSpringTestSupport;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.AbstractXmlApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProviderClientServerTest extends CamelSpringTestSupport {
    
    int port;
    
    @Test
    void testClientInvocation() throws Exception {
        // set the client's service access point
        Client client = new Client("http://localhost:" + port + "/GreeterContext/SOAPMessageService");
        // invoke the services
        String response = client.invoke();
        
        assertEquals("Greetings from Apache Camel!!!! Request was  Hello Camel!!", response, "Get a wrong response");
    }

    @Override
    protected AbstractXmlApplicationContext createApplicationContext() {
        port = AvailablePortFinder.getNextAvailable();
        System.setProperty("port", String.valueOf(port));
        
        return new ClassPathXmlApplicationContext(new String[]{"/META-INF/spring/CamelCXFProviderRouteConfig.xml"});
    }

}
