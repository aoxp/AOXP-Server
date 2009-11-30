/*
    AO-XP Server (XP stands for Cross Platform) is a Java implementation of Argentum Online's server 
    Copyright (C) 2009 Juan Martín Sotuyo Dodero. <juansotuyo@gmail.com>

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

package ao.data.dao;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import ao.model.user.Account;

public class AccountDAOIniTest {

	private AccountDAOIni dao;
	private static final String CHARACTER_NICK = "test";
	private static final String CHARFILES_PATH = "src/test/resources/charfiles/";
	
	@Before
	public void setUp() throws Exception {
		dao = new AccountDAOIni(CHARFILES_PATH);
	}

	@Test
	public void testRetrieve() throws DAOException {
		Account acc = dao.retrieve(CHARACTER_NICK);
		
		Assert.assertNotNull(acc);
		
		// Enssure we get the requested character and not another one.
		Assert.assertEquals(acc.getName(), CHARACTER_NICK);
	}

}
