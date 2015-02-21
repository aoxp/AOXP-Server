package com.ao.action;


public abstract class Action<S> {

	// TODO : Get this injected appropriately for each action type
	private ActionExecutor<S> executor;

	public final void dispatch() {
		executor.dispatch(this);
	}

	protected abstract void performAction(final S service);
}
