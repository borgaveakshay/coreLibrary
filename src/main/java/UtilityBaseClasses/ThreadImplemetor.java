package UtilityBaseClasses;

public abstract class ThreadImplemetor implements Runnable {

	private boolean running;

	Thread threadImplementor;

	public ThreadImplemetor() {

		threadImplementor = new Thread(this);
		setRunning(true);
	}

	public ThreadImplemetor(String name) {

		threadImplementor = new Thread(this);
		threadImplementor.setName(name);
		setRunning(true);
	}

	public synchronized boolean isRunning() {
		return running;
	}

	public synchronized void setRunning(boolean running) {
		this.running = running;
	}

	public Thread getThreadImplementor() {
		return threadImplementor;
	}

	public synchronized void terminate() {

		setRunning(false);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

		try {
			if (isRunning()) {

				performTask();
			}
		} catch (Exception e) {
			setRunning(false);
			System.out.println("Caused Exception");
		}
		System.out.println(threadImplementor.getName() + " Exiting");
	}

	public abstract void performTask();

}
