package com.eep.simarro.Drones;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.annotation.Id;

record CashCard(@Id Long id, Double amount) {
}

interface DronesRepository extends CrudRepository<Drones, Long> {
}
