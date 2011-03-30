/**
 * 
 */
package com.ao.data.dao.ini;

import static org.junit.Assert.*;

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
	
	private static final int FIRST_CITY = 1;
	private static final int FIRST_CITY_MAP = 34;
	private static final int FIRST_CITY_X = 44;
	private static final int FIRST_CITY_Y = 88;
	private static final int LAST_CITY = 7;
	private static final int LAST_CITY_MAP = 151;
	private static final int LAST_CITY_X = 40;
	private static final int LAST_CITY_Y = 48;
	
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
		
		City[] cities = dao.retrieveAll();
	
		assertEquals(FIRST_CITY_MAP,cities[FIRST_CITY - 1].getMap());
		assertEquals(FIRST_CITY_X,cities[FIRST_CITY - 1].getX());
		assertEquals(FIRST_CITY_Y,cities[FIRST_CITY - 1].getY());
		assertEquals(LAST_CITY_MAP,cities[LAST_CITY - 1].getMap());
		assertEquals(LAST_CITY_X,cities[LAST_CITY - 1].getX());
		assertEquals(LAST_CITY_Y,cities[LAST_CITY - 1].getY());
	}

}
