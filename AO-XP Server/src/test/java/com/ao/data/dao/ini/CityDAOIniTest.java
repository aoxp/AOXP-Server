/**
 * 
 */
package com.ao.data.dao.ini;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;

import org.ini4j.Ini;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.ao.data.dao.exception.DAOException;
import com.ao.model.map.City;

/**
 * @author Zaxtor
 *
 */
public class CityDAOIniTest {

	private CityDAOIni dao;
	
	private static final String CITIES_DAT_PATH = "src/test/resources/ciudades.dat";
	
	private static final String INIT_HEADER = "INIT";
	private static final String NUM_CITIES_KEY = "NumCities";
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		dao = new CityDAOIni(CITIES_DAT_PATH);
	}
	
	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link com.ao.data.dao.ini.CityDAOIni#retrieveAll()}.
	 */
	@Test
	public final void testRetrieveAll() {
		
		Ini iniFile = null;
		
		try {
			// Make sure the reader is closed, since Ini4J gives no guarantees.
			Reader reader = new BufferedReader(new FileReader(CITIES_DAT_PATH));
			iniFile = new Ini(reader);
			reader.close();
		} catch (Exception e) {
			fail("Loading of cities failed with message " + e.getMessage());
		}
		
		int totalCities = Integer.parseInt(iniFile.get(INIT_HEADER, NUM_CITIES_KEY));
		
		
		City[] cities = dao.retrieveAll();
		
		assertEquals(totalCities,cities.length);
		
	}

}
