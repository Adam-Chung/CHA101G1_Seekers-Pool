package tw.idv.Seeker_Pool_Merge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@EnableTransactionManagement 
@EnableAsync 
@SpringBootApplication  
@ServletComponentScan //(basePackages = {"fong","JobCase","Administrator"})
public class SeekerPoolMergeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SeekerPoolMergeApplication.class, args);
	}
	
	@Bean
	public ServerEndpointExporter serverEndpointExporter() {
		return new ServerEndpointExporter();
	}

}
