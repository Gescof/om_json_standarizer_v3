package es.upm.etsisi.entities.omjson;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Reperesenta una traza de una colección de observaciones.
 * @author Guillermo, Yan Liu
 *
 */
@Document(collection="omCollectionTrazas")
@JsonIgnoreProperties({"_id"})
public class ObservationCollecionTraza {
	private ObservationCollection omCollection;
	private String id;
	
	public ObservationCollecionTraza()
	{
		this.omCollection = new ObservationCollection();
	}
		
	/**
	 * Devuelve el contenido como objeto de tipo ObservationCollection.
	 * @return ObservationCollection omCollection
	 * @see ObservationCollection
	 */
	@JsonUnwrapped
	public ObservationCollection getOmCollection() {
		return omCollection;
	}
	
	/**
	 * Establece el objeto ObservationCollection de la clase.
	 * @param omCollection
	 * @see ObservationCollection
	 */
	@JsonUnwrapped
	public void setOmCollection(ObservationCollection omCollection) {
		this.omCollection = omCollection;
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

	/** Devuelve en formato de cadena este objeto siguiendo el patrón OM-JSON.
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "{" + omCollection + "}";
	}
	
}
