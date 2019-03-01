package es.upm.etsisi.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.minsait.onesait.platform.client.springboot.aspect.IoTBrokerRepository;

import es.upm.etsisi.entities.mota.MotaMeasureTraza;

/**
 * @author Guillermo, Yan Liu
 * @version 1.0
 */
@Repository
@IoTBrokerRepository("MotaMeasuresJ")
public interface DAOMotaMeasures 
extends MongoRepository<MotaMeasureTraza, String> {
	
	//@IoTBrokerQuery("select * from MotaMeasuresJ")
	public List<MotaMeasureTraza> findAll();
	
	public Optional<MotaMeasureTraza> findByMotaMeasure_motaId(String id);
	
	//@IoTBrokerDelete
	public void deleteByMotaMeasure_motaId(String id);
	
}
