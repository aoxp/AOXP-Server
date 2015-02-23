package com.ao.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.ao.model.character.Gender;
import com.ao.model.character.Race;
import com.ao.utils.RangeParser;

public class CharacterBodyServiceTest {

	protected CharacterBodyService characterBodyService;

	private List<Integer> headsDarkelfMale = RangeParser.parseIntegers("201-221");
	private List<Integer> headsDarkelfFemale = RangeParser.parseIntegers("270-288");
	private List<Integer> headsDwarfMale = RangeParser.parseIntegers("301-319");
	private List<Integer> headsDwarfFemale = RangeParser.parseIntegers("370-384");
	private List<Integer> headsElfMale = RangeParser.parseIntegers("101-122");
	private List<Integer> headsElfFemale = RangeParser.parseIntegers("170-188");
	private List<Integer> headsGnomeMale = RangeParser.parseIntegers("401-416");
	private List<Integer> headsGnomeFemale = RangeParser.parseIntegers("470-484");
	private List<Integer> headsHumanMale = RangeParser.parseIntegers("1-40");
	private List<Integer> headsHumanFemale = RangeParser.parseIntegers("70-89");

	private int darkelfMaleBody = 3;
	private int darkelfFemaleBody = 4;
	private int dwarfMaleBody = 300;
	private int dwarfFemaleBody = 301;
	private int elfMaleBody = 2;
	private int elfFemaleBody = 5;
	private int gnomeMaleBody = 302;
	private int gnomeFemaleBody = 303;
	private int humanMaleBody = 1;
	private int humanFemaleBody = 6;

	private static final int INVALID_HEAD = 0;

	private static final int VALID_DARKELF_MALE_HEAD = 201;
	private static final int VALID_DARKELF_FEMALE_HEAD = 270;
	private static final int VALID_DWARF_MALE_HEAD = 301;
	private static final int VALID_DWARF_FEMALE_HEAD = 370;
	private static final int VALID_ELF_MALE_HEAD = 101;
	private static final int VALID_ELF_FEMALE_HEAD = 170;
	private static final int VALID_GNOME_MALE_HEAD = 401;
	private static final int VALID_GNOME_FEMALE_HEAD = 470;
	private static final int VALID_HUMAN_MALE_HEAD = 1;
	private static final int VALID_HUMAN_FEMALE_HEAD = 70;

	@Before
	public void setUp() throws Exception {
		characterBodyService = new CharacterBodyServiceImpl(headsDarkelfMale, headsDarkelfFemale,
				headsDwarfMale, headsDwarfFemale, headsElfMale, headsElfFemale, headsGnomeMale,
				headsGnomeFemale, headsHumanMale, headsHumanFemale, darkelfMaleBody, darkelfFemaleBody,
				dwarfMaleBody, dwarfFemaleBody, elfMaleBody, elfFemaleBody, gnomeMaleBody,
				gnomeFemaleBody, humanMaleBody, humanFemaleBody);
	}

	@Test
	public void testValidHead() {
		assertFalse(characterBodyService.isValidHead(INVALID_HEAD, Race.DARK_ELF, Gender.MALE));
		assertTrue(characterBodyService.isValidHead(VALID_DARKELF_MALE_HEAD, Race.DARK_ELF, Gender.MALE));

		assertFalse(characterBodyService.isValidHead(INVALID_HEAD, Race.DARK_ELF, Gender.FEMALE));
		assertTrue(characterBodyService.isValidHead(VALID_DARKELF_FEMALE_HEAD, Race.DARK_ELF, Gender.FEMALE));


		assertFalse(characterBodyService.isValidHead(INVALID_HEAD, Race.DWARF, Gender.MALE));
		assertTrue(characterBodyService.isValidHead(VALID_DWARF_MALE_HEAD, Race.DWARF, Gender.MALE));

		assertFalse(characterBodyService.isValidHead(INVALID_HEAD, Race.DWARF, Gender.FEMALE));
		assertTrue(characterBodyService.isValidHead(VALID_DWARF_FEMALE_HEAD, Race.DWARF, Gender.FEMALE));


		assertFalse(characterBodyService.isValidHead(INVALID_HEAD, Race.ELF, Gender.MALE));
		assertTrue(characterBodyService.isValidHead(VALID_ELF_MALE_HEAD, Race.ELF, Gender.MALE));

		assertFalse(characterBodyService.isValidHead(INVALID_HEAD, Race.ELF, Gender.FEMALE));
		assertTrue(characterBodyService.isValidHead(VALID_ELF_FEMALE_HEAD, Race.ELF, Gender.FEMALE));


		assertFalse(characterBodyService.isValidHead(INVALID_HEAD, Race.GNOME, Gender.MALE));
		assertTrue(characterBodyService.isValidHead(VALID_GNOME_MALE_HEAD, Race.GNOME, Gender.MALE));

		assertFalse(characterBodyService.isValidHead(INVALID_HEAD, Race.GNOME, Gender.FEMALE));
		assertTrue(characterBodyService.isValidHead(VALID_GNOME_FEMALE_HEAD, Race.GNOME, Gender.FEMALE));


		assertFalse(characterBodyService.isValidHead(INVALID_HEAD, Race.HUMAN, Gender.MALE));
		assertTrue(characterBodyService.isValidHead(VALID_HUMAN_MALE_HEAD, Race.HUMAN, Gender.MALE));

		assertFalse(characterBodyService.isValidHead(INVALID_HEAD, Race.HUMAN, Gender.FEMALE));
		assertTrue(characterBodyService.isValidHead(VALID_HUMAN_FEMALE_HEAD, Race.HUMAN, Gender.FEMALE));
	}

	@Test
	public void GetBody() {
		assertEquals(darkelfMaleBody, characterBodyService.getBody(Race.DARK_ELF, Gender.MALE));

		assertEquals(darkelfFemaleBody, characterBodyService.getBody(Race.DARK_ELF, Gender.FEMALE));

		assertEquals(dwarfMaleBody, characterBodyService.getBody(Race.DWARF, Gender.MALE));

		assertEquals(dwarfFemaleBody, characterBodyService.getBody(Race.DWARF, Gender.FEMALE));

		assertEquals(elfMaleBody, characterBodyService.getBody(Race.ELF, Gender.MALE));

		assertEquals(elfFemaleBody, characterBodyService.getBody(Race.ELF, Gender.FEMALE));

		assertEquals(gnomeMaleBody, characterBodyService.getBody(Race.GNOME, Gender.MALE));

		assertEquals(gnomeFemaleBody, characterBodyService.getBody(Race.GNOME, Gender.FEMALE));

		assertEquals(humanMaleBody, characterBodyService.getBody(Race.HUMAN, Gender.MALE));

		assertEquals(humanFemaleBody, characterBodyService.getBody(Race.HUMAN, Gender.FEMALE));
	}
}
