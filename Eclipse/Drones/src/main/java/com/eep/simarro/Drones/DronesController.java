package com.eep.simarro.Drones;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Drones")
class DronesController {

	@GetMapping("/{requestedId}")
	private ResponseEntity<Drones> findById(@PathVariable Long requestedId) {
		 if (requestedId.equals(2L)) {
	    Drones Drones = new Drones(2,"Mario", "Garcia", "4DRC F11 PRO");
	    return ResponseEntity.ok(Drones);
	  } else {
	        return ResponseEntity.notFound().build();
	    }
	
	}
}