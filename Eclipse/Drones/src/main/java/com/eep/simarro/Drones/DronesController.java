package com.eep.simarro.Drones;


import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Drones")
class DronesController {
	
	
	private final DronesRepository DronesRepository;

    private DronesController(DronesRepository DronesRepository) {
          this.DronesRepository = DronesRepository;
       }

	@GetMapping("/{requestedId}")
	private ResponseEntity<Drones> findById(@PathVariable Long requestedId) {
		Optional<Drones> DronesOptional = DronesRepository.findById(requestedId);
		 if (DronesOptional.isPresent()) {
			 return ResponseEntity.ok(DronesOptional.get());
		    } else {
		        return ResponseEntity.notFound().build();
		    }
	    
	
	}
}
