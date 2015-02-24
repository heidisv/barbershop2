package exercise;

/**
 * This class implements the doorman's part of the
 * Barbershop thread synchronization example.
 */
public class Doorman extends Thread{
	/**
	 * Creates a new doorman.
	 * @param queue		The customer queue.
	 * @param gui		A reference to the GUI interface.
	 */
	
	private CustomerQueue queue;
	private Gui gui;
	private boolean running;

	public Doorman(CustomerQueue queue, Gui gui) { 
		this.queue = queue;
		this.gui = gui;
	}

	/**
	 * Starts the doorman running as a separate thread.
	 */
	public void startThread() {
		running = true;
		start();
	}

	/**
	 * Stops the doorman thread.
	 */
	public void stopThread() {
		running = false;
	}

	// Add more methods as needed
}