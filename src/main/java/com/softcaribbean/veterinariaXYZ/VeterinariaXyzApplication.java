package com.softcaribbean.veterinariaXYZ;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/*
(exclude ={SecurityAutoConfiguration.class})
*/
@SpringBootApplication
public class VeterinariaXyzApplication {

	public static void main(String[] args) {
		SpringApplication.run(VeterinariaXyzApplication.class, args);
		System.out.println("Bienvenido... Ya puedes probar el programa :)");
	}

}
