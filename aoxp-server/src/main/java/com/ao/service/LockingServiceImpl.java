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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * LockingService Implementation
 * Locks objects ordered by their identityHashCode in ascending order
 *
 * @author mvanotti
 */
public class LockingServiceImpl implements LockingService {
	protected Map<Thread, List<ObjectLock>> threadLocks;
	protected Map<Integer, ObjectLock> lockedObjects;


	public LockingServiceImpl() {
		threadLocks = new ConcurrentHashMap<Thread, List<ObjectLock>>();
		lockedObjects = new ConcurrentHashMap<Integer, ObjectLock>();
	}

	/* (non-Javadoc)
	 * @see com.ao.service.LockingService#lock(java.lang.Object[])
	 */
	@Override
	public void lock(Object... objects) throws ThreadAlreadyLockingException {

		Thread currentThread = Thread.currentThread();

		if (threadLocks.containsKey(currentThread)) {
			throw new ThreadAlreadyLockingException();
		}

		List<Object> objectsToLock = Arrays.asList(objects);
		List<ObjectLock> locks = new ArrayList<ObjectLock>(objects.length);

		Collections.sort(objectsToLock, new ObjectIdentityHashCodeComparator());
		for (Object o : objects) {
			ObjectLock lock;

			int identityHashCode = System.identityHashCode(o);

			// Make sure only one thread at a time enters the request area for any given object
			synchronized (o) {
				lock = lockedObjects.get(identityHashCode);

				if (lock == null) {
					lock = new ObjectLock(identityHashCode, new ReentrantLock());
					((ConcurrentHashMap<Integer, ObjectLock>) lockedObjects).putIfAbsent(identityHashCode, lock);
					lock = lockedObjects.get(identityHashCode);
				}

				// After obtaining the lock, we add the lock to the map once again, just in case it was just released by another thread and removed..
				lock.getLock().lock();
				if (null == lockedObjects.get(identityHashCode)) {
					lockedObjects.put(identityHashCode, lock);
				}
			}

			locks.add(lock);
		}

		threadLocks.put(currentThread, locks);
	}

	@Override
	public void release() {
		Thread currentThread = Thread.currentThread();
		List<ObjectLock> locks = threadLocks.get(currentThread);

		if (locks != null) {
			for (ObjectLock lock : locks) {
				lockedObjects.remove(lock.getIdentityHashCode());
				lock.getLock().unlock();
			}
		}

		threadLocks.remove(currentThread);
	}

	/**
	 * Holds a lock related to a identityHashCode
	 */
	protected class ObjectLock {
		private Integer identityHashCode;
		private Lock lock;

		/**
		 * @return the identityHashCode
		 */
		public Integer getIdentityHashCode() {
			return identityHashCode;
		}

		/**
		 * @param identityHashCode the identityHashCode to set
		 */
		public void setIdentityHashCode(Integer identityHashCode) {
			this.identityHashCode = identityHashCode;
		}

		/**
		 * @return the lock
		 */
		public Lock getLock() {
			return lock;
		}

		/**
		 * @param lock the lock to set
		 */
		public void setLock(Lock lock) {
			this.lock = lock;
		}

		/**
		 * @param identityHashCode
		 * @param lock
		 */
		ObjectLock(Integer identityHashCode, Lock lock) {
			super();
			this.identityHashCode = identityHashCode;
			this.lock = lock;
		}
	}

	/***
	 * Comparator that compares two objects by their identityHashCode (using System.identityHashCode function)
	 */
	protected class ObjectIdentityHashCodeComparator implements Comparator<Object> {

		/***
		 * Compares two objects by their identityHashCode.
		 *
		 * @return negative integer if arg1 has a greater identityHashCode than arg0, zero if they are equal,
		 * 	positive otherwise
		 */
		@Override
		public int compare(Object arg0, Object arg1) {
			int obj0 = System.identityHashCode(arg0);
			int obj1 = System.identityHashCode(arg1);

			return obj0 - obj1;
		}
	}
}
