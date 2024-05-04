package com.eep.simarro.Drones;

import org.springframework.data.annotation.Id;

record Drones(@Id int id, String name, String apellido, String DE) {
	
}
