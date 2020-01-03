package io.apprio.springms.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment

import javax.annotation.PostConstruct

@Configuration
class AppConfig {
    @Autowired
    private Environment env;

    @PostConstruct
    private void configureSSL() {
      //System.setProperty("javax.net.debug", "all");
      System.setProperty("https.protocols", "TLSv1.2");
        
      System.setProperty("javax.net.ssl.trustStore", "C:\\Users\\ashish.nayyar-b\\projects\\spring-ms\\src\\main\\resources\\keystore\\truststore.jks"); 
      System.setProperty("javax.net.ssl.trustStorePassword",'password');
    }
}
