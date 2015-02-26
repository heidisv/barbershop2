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
	
	private CustomerQueue cosQueue;
	private Gui gui;
	private boolean running;

	public Doorman(CustomerQueue queue, Gui gui) { 
		cosQueue = queue;
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

	public void run(){
		while (running){
			try{
				// Wait/sleep for random time
				sleep((long)(Globals.doormanSleep*Math.random()));
			}
			catch (InterruptedException error){
			}
			// Fetch a new customer
			gui.println("Let in a new customer");
			cosQueue.addCustomer(new Customer());
		}
	}
}