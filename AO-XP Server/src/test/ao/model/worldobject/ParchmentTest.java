package ao.model.worldobject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import junit.framework.Assert;

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

		Spell[] spells = new Spell[] {};
		
		EasyMock.expect(character.getInventory()).andReturn(inventory).anyTimes();
		EasyMock.expect(character.getSpells()).andReturn(spells).anyTimes();
		
		// Consumption of parchment1 requires these 2 calls.
		inventory.cleanup();
		character.addSpell(spell1);
		
		EasyMock.replay(inventory, character);

		parchment1.use(character);
		
		assertEquals(character.getSpells(), spells);
		
		EasyMock.verify(inventory, character);
	}
	
	@Test
	public void testUseWithoutCleanup() {
		
		Inventory inventory = EasyMock.createMock(Inventory.class);
		
		Character character = EasyMock.createMock(Character.class);
		
		Spell[] spells = new Spell[] {};
		
		EasyMock.expect(character.getInventory()).andReturn(inventory).anyTimes();
		EasyMock.expect(character.getSpells()).andReturn(spells).anyTimes();
		
		// Consumption of parchment1 requires just a call to addSpell.
		character.addSpell(spell2);
		
		EasyMock.replay(inventory, character);
		
		parchment2.use(character);
		
		assertEquals(character.getSpells(), spells);
		
		EasyMock.verify(inventory, character);
	}

	@Test
	public void testUseWithASpellAlreadyLearnt() {
		
		Inventory inventory = EasyMock.createMock(Inventory.class);
		
		Character character = EasyMock.createMock(Character.class);
		
		Spell[] spells = new Spell[] {spell1};
		
		EasyMock.expect(character.getInventory()).andReturn(inventory).anyTimes();
		EasyMock.expect(character.getSpells()).andReturn(spells).anyTimes();
		
		// Consumption of parchment1 already learnt doesn't requires any call.
				
		EasyMock.replay(inventory, character);
		
		parchment1.use(character);
		
		assertEquals(character.getSpells(), spells);
		
		EasyMock.verify(inventory, character);
	}
	
	@Test
	public void testClone() {
		Parchment clone1 = (Parchment) parchment1.clone();
		
		assertEquals(clone1.getAmount(), parchment1.getAmount());
		assertEquals(clone1.getSpell(), parchment1.getSpell());
		assertEquals(clone1.properties, parchment1.properties);		
		assertFalse(clone1 == parchment1);
		
		Parchment clone2 = (Parchment) parchment2.clone();
		
		assertEquals(clone2.getAmount(), parchment2.getAmount());
		assertEquals(clone2.getSpell(), parchment2.getSpell());
		assertEquals(clone2.properties, parchment2.properties);		
		assertFalse(clone2 == parchment2);
	}
	
}
