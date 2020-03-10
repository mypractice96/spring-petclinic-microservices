/*
 * Copyright 2002-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.petclinic.vets;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.samples.petclinic.vets.system.VetsProperties;

import org.springframework.context.annotation.PropertySource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.boot.web.servlet.context.ServletWebServerInitializedEvent;
import org.springframework.beans.factory.annotation.Value;
import java.io.File;
import java.io.FileWriter; 
import java.io.IOException;

/**
 * @author Maciej Szarlinski
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableConfigurationProperties(VetsProperties.class)
@PropertySource("classpath:port.properties")
public class VetsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(VetsServiceApplication.class, args);
	}
}


 @Component
     class MyListener implements ApplicationListener<ServletWebServerInitializedEvent> {
 
     @Value("${port.file.path}")
     private String portsFilePath;

    @Override
      public void onApplicationEvent(final ServletWebServerInitializedEvent event) {
          int thePort = event.getWebServer().getPort();
          System.out.println("Port = "+thePort);


            try {
		  
	         File file = new File(portsFilePath);
                boolean isFileCreated = file.createNewFile();
			 
		 FileWriter myWriter = new FileWriter(portsFilePath,true);
                 myWriter.write(thePort+"\n");
                 myWriter.close();
                 System.out.println("Successfully wrote to the file.");        
		  
	    
    	} catch (IOException e) {
    		System.out.println("Exception Occurred:");
	        e.printStackTrace();
	  }



      }
    }
