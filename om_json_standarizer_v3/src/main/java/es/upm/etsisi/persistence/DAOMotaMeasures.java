package es.upm.etsisi.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import es.upm.etsisi.entities.mota.MotaMeasureTraza;

/**
 * @author Guillermo, Yan Liu
 * @version 1.0
 */
@Repository
//@Sofia2Repository
public interface DAOMotaMeasures 
extends MongoRepository<MotaMeasureTraza, String> {
	
	public List<MotaMeasureTraza> findAll();
	
	public Optional<MotaMeasureTraza> findByMotaMeasure_motaId(String id);
	
	public void deleteByMotaMeasure_motaId(String id);
	
}
