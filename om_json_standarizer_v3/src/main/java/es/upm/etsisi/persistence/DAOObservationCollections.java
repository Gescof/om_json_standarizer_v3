package es.upm.etsisi.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import es.upm.etsisi.entities.omjson.ObservationCollecionTraza;

/**
 * @author Guillermo, Yan Liu
 * @version 1.0
 */
@Repository
//@Sofia2Repository
public interface DAOObservationCollections 
extends MongoRepository<ObservationCollecionTraza, String> {
	
	public List<ObservationCollecionTraza> findAll();
	
	public Optional<ObservationCollecionTraza> findByOmCollection_id(String id);
	
	public void deleteByOmCollection_id(String id);
	
}
