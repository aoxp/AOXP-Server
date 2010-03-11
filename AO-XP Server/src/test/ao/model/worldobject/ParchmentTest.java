package ao.model.worldobject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.easymock.Capture;
import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ao.model.character.Character;
import ao.model.inventory.Inventory;
import ao.model.spell.Spell;
import ao.model.spell.effect.Effect;
import ao.model.worldobject.properties.ParchmentProperties;;

public class ParchmentTest extends AbstractItemTest {

	private Parchment parchment1;
	private Parchment parchment2;
	private Spell spell1;
	private Spell spell2;
	@Before
	public void setUp() throws Exception {
		spell1 = new Spell(new Effect[] {}, 0, 0, 0, "foo", "foo", false, 1, 2, "OHL VOR PEK");
		ParchmentProperties props1 = new ParchmentProperties(WorldObjectType.PARCHMENT, 1, "Dardo Mágico", 1, true, 300, 0, null, null, false, spell1);
		parchment1 = new Parchment(props1, 1);
		
		spell2 = new Spell(new Effect[] {}, 0, 0, 0, "foo", "foo", false, 1, 2, "Rahma Nañarak O'al");
		ParchmentProperties props2 = new ParchmentProperties(WorldObjectType.PARCHMENT, 1, "Terrible hambre de Igor", 100, true, 200, 0, null, null, false, spell2);
		parchment2 = new Parchment(props2, 5);		
		
		object = parchment1;
		objectProps = props1;
		ammount = 1;
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetSpell() {
		assertEquals(parchment1.getSpell(), spell1);
		assertEquals(parchment2.getSpell(), spell2);
	}
	
	@Test
	public void testUseWithCleanup() {
		
		Inventory inventory = EasyMock.createMock(Inventory.class);
		
		Character character = EasyMock.createMock(Character.class);
		EasyMock.expect(character.getInventory()).andReturn(inventory).anyTimes();
		
		// Consumption of parchment1 requires these 2 calls.
		inventory.cleanup();
		character.addSpell(parchment1.getSpell());
		
		EasyMock.replay(inventory, character);
		
		parchment1.use(character);
		
		EasyMock.verify(inventory, character);
	}
	
	@Test
	public void testUseWithoutCleanup() {
		
		Inventory inventory = EasyMock.createMock(Inventory.class);
		
		Character character = EasyMock.createMock(Character.class);		
		EasyMock.expect(character.getInventory()).andReturn(inventory).anyTimes();
		
		Capture<Spell> capture = new Capture<Spell>();
		
		// Consumption of parchment1 requires just a call to addSpell
		character.addSpell(EasyMock.capture(capture));
		
		EasyMock.replay(inventory, character);
		
		parchment2.use(character);
		
		/// Make sure the value is in the correct range
		assertEquals(capture.getValue(), parchment2.getSpell());
		
		EasyMock.verify(inventory, character);
	}
	
	@Test
	public void testClone() {
		Parchment clone1 = (Parchment) parchment1.clone();
		assertEquals(clone1.getAmount(), parchment1.getAmount());
		assertEquals(clone1.getSpell(), parchment1.getSpell());
	
		assertFalse(clone1 == parchment1);
		
		Parchment clone2 = (Parchment) parchment2.clone();
		assertEquals(clone2.getAmount(), parchment2.getAmount());
		assertEquals(clone2.getSpell(), parchment2.getSpell());
		
		assertFalse(clone2 == parchment2);
	}
	
}
