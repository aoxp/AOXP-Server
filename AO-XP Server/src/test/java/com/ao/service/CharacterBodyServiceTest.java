package com.ao.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
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
		characterBodyService = new CharacterBodyServiceImpl(headsDarkelfMale, headsDarkelfFemale, headsDwarfMale, headsDwarfFemale, headsElfMale, headsElfFemale, headsGnomeMale, headsGnomeFemale, headsHumanMale, headsHumanFemale);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testIsValidHead() {
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

}
