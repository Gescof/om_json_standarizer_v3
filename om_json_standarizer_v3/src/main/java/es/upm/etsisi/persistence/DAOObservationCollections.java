package es.upm.etsisi.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.minsait.onesait.platform.client.springboot.aspect.IoTBrokerRepository;

import es.upm.etsisi.entities.omjson.ObservationCollecionTraza;

/**
 * @author Guillermo, Yan Liu
 * @version 1.0
 */
@Repository
@IoTBrokerRepository("OMJson")
public interface DAOObservationCollections 
extends MongoRepository<ObservationCollecionTraza, String> {
	
	public List<ObservationCollecionTraza> findAll();
	
	public Optional<ObservationCollecionTraza> findByOmCollection_id(String id);
	
	public void deleteByOmCollection_id(String id);
	
}
