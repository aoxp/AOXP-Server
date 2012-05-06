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
package com.ao.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.ao.service.LockingServiceImpl.ObjectLock;

public class LockingServiceTest {

	private LockingServiceImpl lockingService;

	@Before
	public void setUp() throws Exception {
		lockingService = new LockingServiceImpl();
	}

	@Test(expected = ThreadAlreadyLockingException.class)
	public void testLock() throws ThreadAlreadyLockingException {
		Integer test1 = 1;
		Integer test2 = 2;

		lockingService.lock(test1);

		assertTrue(lockingService.lockedObjects.containsKey(System.identityHashCode(test1)));
		assertEquals(1, lockingService.lockedObjects.size());
		assertEquals(1, lockingService.threadLocks.size());
		assertTrue(lockingService.threadLocks.containsKey(Thread.currentThread()));
		List<ObjectLock> locks = lockingService.threadLocks.get(Thread.currentThread());
		assertTrue(locks != null);
		assertEquals(1, locks.size());

		lockingService.lock(test2); // Throw exception!
	}

	@Test
	public void testUnlock() throws ThreadAlreadyLockingException,
	InterruptedException {
		Integer test1 = 1;

		/* Unlock only one object */
		lockingService.lock(test1);
		ObjectLock tmpLock = lockingService.lockedObjects.get(System.identityHashCode(test1));

		testObjectLock(tmpLock, false);

		lockingService.release();

		assertEquals(0, lockingService.lockedObjects.size());
		assertEquals(0, lockingService.threadLocks.size());
		testObjectLock(tmpLock, true);

		/* Unlock multiple objects */
		Integer test2 = 2;
		Integer test3 = 3;
		Integer test4 = 4;

		lockingService.lock(test1, test2, test3, test4);
		assertEquals(4, lockingService.lockedObjects.size());
		assertEquals(1, lockingService.threadLocks.size());
		assertTrue(lockingService.lockedObjects.containsKey(System.identityHashCode(test2)));
		tmpLock = lockingService.lockedObjects.get(System.identityHashCode(test2));

		testObjectLock(tmpLock, false);

		lockingService.release();

		testObjectLock(tmpLock, true);

		assertEquals(0, lockingService.lockedObjects.size());
		assertEquals(0, lockingService.threadLocks.size());
	}

	private void testObjectLock(final ObjectLock lock, final boolean aquire)
	throws InterruptedException {
		/*
		 * We need to create a new thread to test if we can aquire the locks
		 * because Reentrant locks gives the lock to the same thread if asked
		 */
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				if (!aquire) {
					assertFalse(lock.getLock().tryLock());
				} else {
					assertTrue(lock.getLock().tryLock());
					lock.getLock().unlock();
				}
			}
		});
		thread.start();
		thread.join();
	}

	@Test
	public void testMultiThread() throws InterruptedException, ThreadAlreadyLockingException {
		final AtomicInteger test1 = new AtomicInteger(1);
		final AtomicInteger test2 = new AtomicInteger(2);

		Thread thread1 = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					lockingService.lock(test1, test2);
					assertTrue(lockingService.lockedObjects.containsKey(System.identityHashCode(test2)));
					assertTrue(lockingService.threadLocks.containsKey(Thread.currentThread()));
					assertEquals(2, lockingService.threadLocks.get(Thread.currentThread()).size());
					int value = test1.intValue();
					Thread.sleep(1000);
					test1.incrementAndGet();
					assertEquals(value + 1, test1.intValue());
					lockingService.release();
				} catch (Exception e) {
					assertFalse(true);
				}
			}

		});

		Thread thread2 = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					lockingService.lock(test1);
					int value = test1.intValue();
					Thread.sleep(1000);
					test1.decrementAndGet();
					assertEquals(value - 1, test1.intValue());
					lockingService.release();
				} catch (Exception e) {
					assertFalse(true);
				}
			}
		});

		Thread thread3 = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					lockingService.lock(test2);
					assertTrue(lockingService.threadLocks.containsKey(Thread.currentThread()));
					assertTrue(lockingService.lockedObjects.containsKey(System.identityHashCode(test2)));
					int value = test2.intValue();
					Thread.sleep(10);
					test2.decrementAndGet();
					assertEquals(value - 1, test2.intValue());
					lockingService.release();
					assertFalse(lockingService.threadLocks.containsKey(Thread.currentThread()));

					lockingService.lock(test2, test1);
					assertEquals(2, lockingService.threadLocks.get(Thread.currentThread()).size());
					lockingService.release();
					assertFalse(lockingService.threadLocks.containsKey(Thread.currentThread()));
				} catch (Exception e) {
					assertFalse(true);
				}
			}
		});

		thread3.start();
		thread1.start();
		thread2.start();

		final AtomicInteger test3 = new AtomicInteger(3);
		lockingService.lock(test3);
		assertTrue(lockingService.threadLocks.containsKey(Thread.currentThread()));
		lockingService.release();

		thread1.join();
		thread2.join();
		thread3.join();

		assertEquals(0, lockingService.lockedObjects.size());
		assertEquals(0, lockingService.threadLocks.size());
	}

	@After
	public void tearDown() throws Exception {
		lockingService.release();
	}

}
