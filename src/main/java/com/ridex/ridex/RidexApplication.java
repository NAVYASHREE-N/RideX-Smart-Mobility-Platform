package com.ridex.ridex;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ridex.ridex.entity.Driver;
import com.ridex.ridex.repository.DriverRepository;

@SpringBootApplication
public class RidexApplication implements CommandLineRunner {

	@Autowired
	private DriverRepository driverRepository;

	public static void main(String[] args) {

		SpringApplication.run(
				RidexApplication.class,
				args
		);

	}

	@Override
	public void run(String... args) throws Exception {

		// AVOID DUPLICATES

		if(driverRepository.count() > 0){
			return;
		}

		// CREATE 20 DRIVERS

		for(int i=1; i<=20; i++){

			Driver driver = new Driver();

			driver.setFullName(
					"Driver " + i
			);

			driver.setEmail(
					"driver" + i + "@ridex.com"
			);

			driver.setPhone(
					"98765432" + i
			);

			driver.setLicenseNumber(
					"LIC" + (1000+i)
			);

			// VEHICLE TYPES

			if(i % 3 == 0){

				driver.setVehicleModel(
						"Bike Taxi"
				);

			}

			else if(i % 2 == 0){

				driver.setVehicleModel(
						"Auto Rickshaw"
				);

			}

			else{

				driver.setVehicleModel(
						"Sedan Cab"
				);

			}

			driver.setVehicleNumber(
					"KA01AB" + (100+i)
			);

			driver.setRating(4.5);

			driver.setStatus(
					Driver.DriverStatus.AVAILABLE
			);

			driverRepository.save(driver);

		}

		System.out.println(
				"20 Drivers Added Successfully!"
		);

	}

}
