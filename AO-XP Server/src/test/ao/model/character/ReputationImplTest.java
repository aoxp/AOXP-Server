package ao.model.character;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ReputationImplTest {

	private static final int ASSASIN_POINTS = 500;
	private static final int BANDIT_POINTS = 500;
	private static final int BURGEOIS_POINTS = 500;
	private static final int THIEFF_POINTS = 500;
	private static final int NOBLE_POINTS = 500;
	private static final boolean BELONGS_TO_FACTION = false;
	
	private Reputation rep;
	
	@Before
	public void setUp() {
		rep = new ReputationImpl(ASSASIN_POINTS, BANDIT_POINTS, BURGEOIS_POINTS, THIEFF_POINTS, NOBLE_POINTS, BELONGS_TO_FACTION);
	}
	
	@Test
	public void testAddToAssassin() {
		rep.addToAssassin(10);
		
		assertEquals(rep.getAssassin(), ASSASIN_POINTS + 10);
	}

	@Test
	public void testAddToBandit() {
		rep.addToBandit(10);
		
		assertEquals(rep.getBandit(), BANDIT_POINTS + 10);
	}

	@Test
	public void testAddToBourgeois() {
		rep.addToBourgeois(10);
		
		assertEquals(rep.getBourgeois(), BURGEOIS_POINTS + 10);
	}

	@Test
	public void testAddToNoblePoints() {
		rep.addToNoble(10);
		
		assertEquals(rep.getNoble(), NOBLE_POINTS + 10);
	}

	@Test
	public void testAddToThief() {
		rep.addToThief(10);
		
		assertEquals(rep.getThief(), THIEFF_POINTS + 10);
	}

	@Test
	public void testGetAlignment() {
		assertEquals(rep.getAlignment(), Alignment.CRIMINAL);
		
		rep.addToNoble(600);
		
		assertEquals(rep.getAlignment(), Alignment.CITIZEN);
	}

	@Test
	public void testGetAssassin() {
		assertEquals(rep.getAssassin(), ASSASIN_POINTS);
	}

	@Test
	public void testGetBandit() {
		assertEquals(rep.getBandit(), BANDIT_POINTS);
	}

	@Test
	public void testGetBourgeois() {
		assertEquals(rep.getBourgeois(), BURGEOIS_POINTS);
	}

	@Test
	public void testGetThief() {
		assertEquals(rep.getThief(), THIEFF_POINTS);
	}

	@Test
	public void testGetNoble() {
		assertEquals(rep.getNoble(), NOBLE_POINTS);
	}

	@Test
	public void testBelongsToFaction() {
		assertFalse(rep.belongsToFaction());
	}

	@Test
	public void testSetBelongsToFaction() {
		rep.setBelongsToFaction(!BELONGS_TO_FACTION);
		
		assertEquals(rep.belongsToFaction(), !BELONGS_TO_FACTION);
	}

}
