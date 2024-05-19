	package com.eep.simarro.Drones;
	
	import java.util.List;
	import java.util.Optional;
	
	import org.springframework.http.HttpStatus;
	import org.springframework.http.ResponseEntity;
	import org.springframework.web.bind.annotation.DeleteMapping;
	import org.springframework.web.bind.annotation.GetMapping;
	import org.springframework.web.bind.annotation.PathVariable;
	import org.springframework.web.bind.annotation.PostMapping;
	import org.springframework.web.bind.annotation.PutMapping;
	import org.springframework.web.bind.annotation.RequestBody;
	import org.springframework.web.bind.annotation.RequestMapping;
	import org.springframework.web.bind.annotation.RestController;
	
	@RestController
	@RequestMapping("/drones")
	class DronesController {
	
		private final DronesRepository DronesRepository;
	
		private DronesController(DronesRepository DronesRepository) {
			this.DronesRepository = DronesRepository;
		}
	
		@GetMapping("/{requestedId}")
		private ResponseEntity<Drones> findById(@PathVariable Integer requestedId) {
			Optional<Drones> DronesOptional = DronesRepository.findById(requestedId);
			if (DronesOptional.isPresent()) {
				return ResponseEntity.ok(DronesOptional.get());
			} else {
				return ResponseEntity.notFound().build();
			}
		}
	
		@PostMapping
		public ResponseEntity<Drones> createDrones(@RequestBody Drones newDronesRequest) {
			Drones savedDrones = DronesRepository.save(newDronesRequest);
			return new ResponseEntity<>(savedDrones, HttpStatus.CREATED);
		}
	
		@GetMapping
		private ResponseEntity<List<Drones>> findAll() {
			List<Drones> DronesList = (List<Drones>) DronesRepository.findAll();
			return ResponseEntity.ok(DronesList);
		}
	
		@PutMapping("/{requestedId}")
		private ResponseEntity<Drones> putDrones(@PathVariable Integer requestedId, @RequestBody Drones dronesUpdate) {
			Drones updatedDrones = new Drones(requestedId, dronesUpdate.getPrecio(), dronesUpdate.getColor(),
					dronesUpdate.getDE());
			DronesRepository.save(updatedDrones);
			return ResponseEntity.ok(updatedDrones);
		}
	
		@DeleteMapping("/{id}")
		public ResponseEntity<Void> deleteDrones(@PathVariable Integer id) {
			DronesRepository.deleteById(id);
			return ResponseEntity.noContent().build();
		}
	}
