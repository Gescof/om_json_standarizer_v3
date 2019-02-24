package es.upm.etsisi.entities.mota;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Reperesenta una traza de una MotaMeasure.
 * @author Guillermo, Yan Liu
 *
 */
@Document(collection="motaMeasureTrazas")
@JsonIgnoreProperties({"_id"})
public class MotaMeasureTraza {
	@JsonUnwrapped
	private MotaMeasure motaMeasure;
	private String id;

	public MotaMeasureTraza()
	{
		this.motaMeasure = new MotaMeasure();
	}

	public MotaMeasureTraza(MotaMeasure motaMeasure, String id) {
		this.motaMeasure = motaMeasure;
		this.id = id;
	}

	/**
	 * Devuelve el contenido como objeto de tipo MotaMeasure.
	 * @return MotaMeasure
	 * @see MotaMeasure
	 */
	@JsonGetter("MotaMeasure")
	public MotaMeasure getMotaMeasure() {
		return motaMeasure;
	}
	
	/**
	 * Establece el objeto MotaMeasure de la clase.
	 * @param MotaMeasure
	 * @see MotaMeasure
	 */
	@JsonSetter("MotaMeasure")
	public void setMotaMeasure(MotaMeasure motaMeasure) {
		this.motaMeasure = motaMeasure;
	}	
	
	/**
	 * Devuelve el identificador de la traza que establece Azure Cosmos DB.
	 * @return _id
	 */
	@JsonGetter("_id")	
	public String getId() {
		return id;
	}

	/**
	 * Establece el identificador de la traza para Azure Cosmos DB.
	 * @param _id
	 */
	@JsonSetter("_id")	
	public void setId(String id) {
		this.id = id;
	}

	/** Devuelve en formato de cadena este objeto siguiendo el patrón JSON.
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "{" + motaMeasure + "}";
	}
	
}
