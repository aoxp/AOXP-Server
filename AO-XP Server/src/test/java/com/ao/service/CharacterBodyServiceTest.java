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
	
	private int DarkelfMaleBody = 3;
	private int DarkelfFemaleBody = 3;
	private int DwarfMaleBodyBody = 300;
	private int DwarfFemaleBody = 300;
	private int ElfMaleBody = 2;
	private int ElfFemaleBody = 2;
	private int GnomeMaleBody = 300;
	private int GnomeFemaleBody = 300;
	private int HumanMaleBody = 1;
	private int HumanFemaleBody = 1;
	
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
	
	private static final int INVALID_BODY = 0;
	
	private static final int VALID_DARKELF_MALE_BODY = 3;
	private static final int VALID_DARKELF_FEMALE_BODY = 3;
	private static final int VALID_DWARF_MALE_BODY = 300;
	private static final int VALID_DWARF_FEMALE_BODY = 300;
	private static final int VALID_ELF_MALE_BODY = 2;
	private static final int VALID_ELF_FEMALE_BODY = 2;
	private static final int VALID_GNOME_MALE_BODY = 300;
	private static final int VALID_GNOME_FEMALE_BODY = 300;
	private static final int VALID_HUMAN_MALE_BODY = 1;
	private static final int VALID_HUMAN_FEMALE_BODY = 1;
	
	@Before
	public void setUp() throws Exception {
		characterBodyService = new CharacterBodyServiceImpl(headsDarkelfMale, headsDarkelfFemale, 
				headsDwarfMale, headsDwarfFemale, headsElfMale, headsElfFemale, headsGnomeMale, 
				headsGnomeFemale, headsHumanMale, headsHumanFemale, DarkelfMaleBody, DarkelfFemaleBody, 
				DwarfMaleBodyBody, DwarfFemaleBody, ElfMaleBody, ElfFemaleBody, GnomeMaleBody, 
				GnomeFemaleBody, HumanMaleBody, HumanFemaleBody);
	}

	@After
	public void tearDown() throws Exception {
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
		assertFalse(characterBodyService.getBody(Race.DARK_ELF, Gender.MALE) == INVALID_BODY);
		assertTrue(characterBodyService.getBody(Race.DARK_ELF, Gender.MALE) == VALID_DARKELF_MALE_BODY);
		
		assertFalse(characterBodyService.getBody(Race.DARK_ELF, Gender.FEMALE) == INVALID_BODY);
		assertTrue(characterBodyService.getBody(Race.DARK_ELF, Gender.FEMALE) == VALID_DARKELF_FEMALE_BODY);
		
		
		assertFalse(characterBodyService.getBody(Race.DWARF, Gender.MALE) == INVALID_BODY);
		assertTrue(characterBodyService.getBody(Race.DWARF, Gender.MALE) == VALID_DWARF_MALE_BODY);
		
		assertFalse(characterBodyService.getBody(Race.DWARF, Gender.FEMALE) == INVALID_BODY);
		assertTrue(characterBodyService.getBody(Race.DWARF, Gender.FEMALE) == VALID_DWARF_FEMALE_BODY);
		
		
		assertFalse(characterBodyService.getBody(Race.ELF, Gender.MALE) == INVALID_BODY);
		assertTrue(characterBodyService.getBody(Race.ELF, Gender.MALE) == VALID_ELF_MALE_BODY);
		
		assertFalse(characterBodyService.getBody(Race.ELF, Gender.FEMALE) == INVALID_BODY);
		assertTrue(characterBodyService.getBody(Race.ELF, Gender.FEMALE) == VALID_ELF_FEMALE_BODY);
		
		
		assertFalse(characterBodyService.getBody(Race.GNOME, Gender.MALE) == INVALID_BODY);
		assertTrue(characterBodyService.getBody(Race.GNOME, Gender.MALE) == VALID_GNOME_MALE_BODY);
		
		assertFalse(characterBodyService.getBody(Race.GNOME, Gender.FEMALE) == INVALID_BODY);
		assertTrue(characterBodyService.getBody(Race.GNOME, Gender.FEMALE) == VALID_GNOME_FEMALE_BODY);
		
		
		assertFalse(characterBodyService.getBody(Race.HUMAN, Gender.MALE) == INVALID_BODY);
		assertTrue(characterBodyService.getBody(Race.HUMAN, Gender.MALE) == VALID_HUMAN_MALE_BODY);
		
		assertFalse(characterBodyService.getBody(Race.HUMAN, Gender.FEMALE) == INVALID_BODY);
		assertTrue(characterBodyService.getBody(Race.HUMAN, Gender.FEMALE) == VALID_HUMAN_FEMALE_BODY);
	}
}
