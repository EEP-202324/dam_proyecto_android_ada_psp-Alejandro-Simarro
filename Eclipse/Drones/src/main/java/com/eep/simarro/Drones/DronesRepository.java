package com.eep.simarro.Drones;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

interface DronesRepository extends CrudRepository<Drones, Integer>, PagingAndSortingRepository<Drones, Integer> {

}

	

