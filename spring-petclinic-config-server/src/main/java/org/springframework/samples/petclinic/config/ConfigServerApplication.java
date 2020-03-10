package org.springframework.samples.petclinic.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.context.annotation.PropertySource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.boot.web.servlet.context.ServletWebServerInitializedEvent;
import org.springframework.beans.factory.annotation.Value;
import java.io.File;
import java.io.FileWriter; 
import java.io.IOException;


@EnableConfigServer
@SpringBootApplication
@PropertySource("classpath:port.properties")
public class ConfigServerApplication {

       @Autowired
       Environment environment;

      public static void main(String[] args) {

  		 SpringApplication.run(ConfigServerApplication.class, args);
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
                 myWriter.write("9411\n");
                 myWriter.write("8081\n");
                 myWriter.close();
                 System.out.println("Successfully wrote to the file.");        
		  
	    
    	} catch (IOException e) {
    		System.out.println("Exception Occurred:");
	        e.printStackTrace();
	  }



      }
    }





