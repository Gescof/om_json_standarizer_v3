package es.upm.etsisi.services;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import es.upm.etsisi.entities.mota.MotaMeasureTraza;
import es.upm.etsisi.entities.omjson.Geometry;
import es.upm.etsisi.entities.omjson.Member;
import es.upm.etsisi.entities.omjson.ObservationCollecionTraza;
import es.upm.etsisi.entities.omjson.OmMember;
import es.upm.etsisi.persistence.DAOObservationCollections;

/**
 * @author Guillermo, Yan Liu
 * @version 1.0
 */
@Service
public class GestorOmCollections {
	@Autowired
	private DAOObservationCollections daoOmCollections;
	
	/**
	 * @param omCollection
	 * @return
	 */
	public boolean altaOmCollection(ObservationCollecionTraza omCollection) {
		boolean result = false;
		daoOmCollections.save(omCollection);
		result = true;
		return result;
	}
	
	/**
	 * @param id
	 * @return
	 */
	public boolean bajaOmCollection(String id) {
		boolean result = false;
		daoOmCollections.deleteByOmCollection_id(id);
		result = true;
		return result;
	}
	
	/**
	 * @param id
	 * @return
	 */
	public Optional<ObservationCollecionTraza> getOmCollection(String id) {
		return daoOmCollections.findByOmCollection_id(id);
	}
	
	/**
	 * @return
	 */
	public List<ObservationCollecionTraza> getListaOmCollections() {
		return daoOmCollections.findAll();
	}
	
	/**
	 * Lee el fichero motaMeasures.json y 
	 * genera una traza OMJson-Collection por cada línea de texto.
	 * @throws IOException
	 */
	public List<JSONObject> generateOMJsonFromFile() throws IOException 
	{
		File readFile = new File("motaMeasures.json");
		Scanner scanner = new Scanner(readFile);		
		List<JSONObject> jsonArrayList = new ArrayList<>();
		String motaTrazaStr;			
		ObjectMapper objectMapper = new ObjectMapper();
		
		ObservationCollecionTraza omTraza = new ObservationCollecionTraza();
		List<OmMember> members = new ArrayList<>();
		OmMember omMember;
		Geometry geometry;
		Member member;
		
		while (scanner.hasNext()) {
			motaTrazaStr = scanner.next();
			MotaMeasureTraza motaTraza = objectMapper.readValue(motaTrazaStr, MotaMeasureTraza.class);
			omTraza.getOmCollection().setId(motaTraza.getMotaMeasure().getMotaId());
			omTraza.getOmCollection().getPhenomenomTime().setDate(motaTraza.getMotaMeasure().getTimestamp().getDate());
			
			geometry = new Geometry();
			geometry.setType("Point");
			geometry.setCoordinates(motaTraza.getMotaMeasure().getGeometry().getCoordinates());
			omMember = new OmMember();
			omMember.setId("geometry" + motaTraza.getMotaMeasure().getMotaId());
			omMember.setType("Geometry-Observation");
			omMember.getResultTime().setDate(motaTraza.getMotaMeasure().getTimestamp().getDate());
			omMember.setResultType(geometry);
			members.add(omMember);
			
			member = new Member();
			member.setValue(motaTraza.getMotaMeasure().getMeasures().getTemperature().getValue());
			member.setUom("https://en.wikipedia.org/wiki/Celsius");
			omMember = new OmMember();
			omMember.setId("temperature" + motaTraza.getMotaMeasure().getMotaId());
			omMember.setType("Category-Observation");
			omMember.getResultTime().setDate(motaTraza.getMotaMeasure().getTimestamp().getDate());
			omMember.setResultType(member);
			members.add(omMember);
			
			member = new Member();
			member.setValue(motaTraza.getMotaMeasure().getMeasures().getHumidity().getValue());
			member.setUom("https://en.wikipedia.org/wiki/Relative_humidity");
			omMember = new OmMember();
			omMember.setId("humidity" + motaTraza.getMotaMeasure().getMotaId());
			omMember.setType("Category-Observation");
			omMember.getResultTime().setDate(motaTraza.getMotaMeasure().getTimestamp().getDate());
			omMember.setResultType(member);
			members.add(omMember);
			
			member = new Member();
			member.setValue(motaTraza.getMotaMeasure().getMeasures().getLuminosity().getValue());
			member.setUom("https://en.wikipedia.org/wiki/Lux");
			omMember = new OmMember();
			omMember.setId("luminosity" + motaTraza.getMotaMeasure().getMotaId());
			omMember.setType("Category-Observation");
			omMember.getResultTime().setDate(motaTraza.getMotaMeasure().getTimestamp().getDate());
			omMember.setResultType(member);
			members.add(omMember);

			omTraza.getOmCollection().setMembers(members);
			jsonArrayList.add(new JSONObject(objectMapper.writeValueAsString(omTraza)));
			members.clear();
		}			
		scanner.close();
		return jsonArrayList;
	}
	
	/**
	 * Obtiene un documento sin estandarizar almacenado en la base de datos de Azure Cosmos DB
	 * y genera una traza OMJson-Collection.
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public boolean parseMotaTrazaToOmJson(MotaMeasureTraza motaTraza) throws JsonParseException, JsonMappingException, IOException 
	{
		boolean result = false;
		ObservationCollecionTraza omTraza = new ObservationCollecionTraza();
		List<OmMember> members = new ArrayList<>();
		OmMember omMember;
		Geometry geometry;
		Member member;

		omTraza.getOmCollection().setId(motaTraza.getMotaMeasure().getMotaId());
		omTraza.getOmCollection().getPhenomenomTime().setDate(motaTraza.getMotaMeasure().getTimestamp().getDate());
			
		geometry = new Geometry();
		geometry.setType("Point");
		geometry.setCoordinates(motaTraza.getMotaMeasure().getGeometry().getCoordinates());
		omMember = new OmMember();
		omMember.setId("geometry" + motaTraza.getMotaMeasure().getMotaId());
		omMember.setType("Geometry-Observation");
		omMember.getResultTime().setDate(motaTraza.getMotaMeasure().getTimestamp().getDate());
		omMember.setResultType(geometry);
		members.add(omMember);
		
		member = new Member();
		member.setValue(motaTraza.getMotaMeasure().getMeasures().getTemperature().getValue());
		member.setUom("https://en.wikipedia.org/wiki/Celsius");
		omMember = new OmMember();
		omMember.setId("temperature" + motaTraza.getMotaMeasure().getMotaId());
		omMember.setType("Category-Observation");
		omMember.getResultTime().setDate(motaTraza.getMotaMeasure().getTimestamp().getDate());
		omMember.setResultType(member);
		members.add(omMember);
		
		member = new Member();
		member.setValue(motaTraza.getMotaMeasure().getMeasures().getHumidity().getValue());
		member.setUom("https://en.wikipedia.org/wiki/Relative_humidity");
		omMember = new OmMember();
		omMember.setId("humidity" + motaTraza.getMotaMeasure().getMotaId());
		omMember.setType("Category-Observation");
		omMember.getResultTime().setDate(motaTraza.getMotaMeasure().getTimestamp().getDate());
		omMember.setResultType(member);
		members.add(omMember);

		member = new Member();
		member.setValue(motaTraza.getMotaMeasure().getMeasures().getLuminosity().getValue());
		member.setUom("https://en.wikipedia.org/wiki/Lux");
		omMember = new OmMember();
		omMember.setId("luminosity" + motaTraza.getMotaMeasure().getMotaId());
		omMember.setType("Category-Observation");
		omMember.getResultTime().setDate(motaTraza.getMotaMeasure().getTimestamp().getDate());
		omMember.setResultType(member);
		members.add(omMember);

		omTraza.getOmCollection().setMembers(members);
		altaOmCollection(omTraza);
		result = true;
		
		return result;
	}
	
}
