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
	
	@Before
	public void setUp() throws Exception {
		characterBodyService = new CharacterBodyServiceImpl(headsDarkelfMale, headsDarkelfFemale, headsDwarfMale, headsDwarfFemale, headsElfMale, headsElfFemale, headsGnomeMale, headsGnomeFemale, headsHumanMale, headsHumanFemale);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testIsValidHead() {
		
		assertEquals(false, characterBodyService.isValidHead(50, Race.HUMAN, Gender.MALE));
	}

}
