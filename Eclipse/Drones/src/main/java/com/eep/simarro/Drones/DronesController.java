package com.eep.simarro.Drones;

import java.net.URI;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/Drones")
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
	private ResponseEntity<Void> createDrones(@RequestBody Drones newDronesRequest, UriComponentsBuilder ucb) {
		Drones savedDrones = DronesRepository.save(newDronesRequest);
		URI locationOfNewDrones = ucb.path("Drones/{id}").buildAndExpand(savedDrones.id()).toUri();
		return ResponseEntity.created(locationOfNewDrones).build();
	}

	@GetMapping
	private ResponseEntity<List<Drones>> findAll(Pageable pageable) {
		Page<Drones> page = DronesRepository.findAll(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
				pageable.getSortOr(Sort.by(Sort.Direction.ASC, "id"))));
		return ResponseEntity.ok(page.getContent());
	}
}
