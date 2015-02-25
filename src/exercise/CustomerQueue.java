package exercise;

/**
 * This class implements a queue of customers as a circular buffer.
 */
public class CustomerQueue {
	/**
	 * Creates a new customer queue.
	 * @param queueLength	The maximum length of the queue.
	 * @param gui			A reference to the GUI interface.
	 */
	
	Private Customer[] newQueue;
	private int maxQueue;
	private Gui gui;
	
	
    public CustomerQueue(int queueLength, Gui gui) {
		this.maxQueue = queueLength;
		this.gui = gui;
		newQueue = new Customer[maxQueue];
	}


	public Customer getNextCustomer(int barberpos) {
		// TODO Auto-generated method stub
		return null;
	}

	// Add more methods as needed
}