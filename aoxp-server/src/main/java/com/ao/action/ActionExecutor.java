package com.ao.action;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class ActionExecutor<S> {
	private ExecutorService processor = Executors.newSingleThreadExecutor();
	private AtomicInteger pendingJobs = new AtomicInteger(0);

	public final void dispatch(final Action<S> action) {
		pendingJobs.incrementAndGet();

		processor.execute(new Runnable() {
			@Override
			public void run() {
				action.performAction(getService());

				pendingJobs.decrementAndGet();
			}
		});
	}

	protected abstract S getService();

	public int getPendingActionCount() {
		return pendingJobs.intValue();
	}
}
