package com.ao.model.worldobject.factory;

/**
 * Exception for errors while building WorldObjects
 * @author jsotuyod
 */
public class WorldObjectFactoryException extends Exception {

	private static final long serialVersionUID = -8073725671591764207L;

	/**
	 * Creates a new exception with the given cause.
	 * @param e The cause of the exception.
	 */
	public WorldObjectFactoryException(Throwable e) {
		super(e);
	}
}
