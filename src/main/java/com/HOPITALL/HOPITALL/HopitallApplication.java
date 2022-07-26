package com.HOPITALL.HOPITALL;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.HOPITALL.HOPITALL.entities.Patient;
import com.HOPITALL.HOPITALL.repositiry.PateintRepository;
import com.HOPITALL.HOPITALL.securityConfig.service.SecurityService;

@SpringBootApplication
// public class HopitallApplication implements CommandLineRunner {
	public class HopitallApplication {
	@Autowired
	private PateintRepository pateintRepository;
	public static void main(String[] args) {
		
		SpringApplication.run(HopitallApplication.class, args);
	}
	
	// @Override
	// public void run(String... args) throws Exception {
	// 	for (int i = 0; i < 50; i++) {
			
	// 		pateintRepository.save(new Patient(null,("Ali"+i),new Date(),(Math.random()<=0.5? false:true),(55+i)));
	// 	}
		
		
		
	// 	// List<Patient>patients =pateintRepository.findAll();
	// 	// for (Patient patient : patients) {
	// 	// 	System.out.println("------------------------");
	// 	// 	System.out.println(patient.getName());
	// 	// 	System.out.println((patient.getMalade())==false?"malade":"gerir");
	// 	// 	System.out.println(patient.getScore());
	// 	// }
	// 	Patient p= pateintRepository.findById(3L).orElse(null);
	// 	if(p!=null){
	// 					System.out.println(p.getName());
	// 		System.out.println((p.getMalade())==false?"malade":"gerir");
	// 		System.out.println(p.getScore());
	// 	}
	// }
		//@Bean
		CommandLineRunner commandLineRunner(PateintRepository pateintRepository){
			return args ->{
				pateintRepository.save( new Patient(null,"Noe",new Date(),false,75));
				pateintRepository.save( new Patient(null,"Frank",new Date(),false,82));
				pateintRepository.save( new Patient(null,"alix",new Date(),true,75));
				pateintRepository.save( new Patient(null,"Noemie",new Date(),false,16));
			};
		}

		// @Override
		// public void run(String... args) throws Exception {
			
		// 	pateintRepository.findAll().forEach(p ->{System.out.println(p.getName());});
		// }
		//@Bean
		CommandLineRunner saveUser(SecurityService securityService){
			return args -> {
				securityService.saveNewUser("abc", "1234", "1234");
				securityService.saveNewUser("xyz", "789", "789");
				securityService.saveNewUser("abcd", "789", "789");

				securityService.saveNewRole("USER", "");
				securityService.saveNewRole("ADMIN", "");

				securityService.addRoleToUser("abc", "USER");
				securityService.addRoleToUser("abc", "ADMIN");
				securityService.addRoleToUser("xyz", "USER");
				securityService.addRoleToUser("abcd", "USER");
			};
		}

		@Bean
		PasswordEncoder passwordEncoder(){
			return new BCryptPasswordEncoder();
		}
}
