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
	
	private Customer[] newQueue;
	private int currentLength;
	private Gui gui;
	
	
    public CustomerQueue(int queueLength, Gui gui) {
		
		this.gui = gui;
		newQueue = new Customer[queueLength];
		currentLength = 0;
		
	}

	public synchronized Customer getNextCustomer(int barberpos) {
		while (isEmpty()){
			gui.println("Barber nr." + barberpos + " is waiting");
			try{
				wait();
			}
			catch (InterruptedException error){
				gui.println("Barber no." + barberpos + " has a new customer!");
			}
		}
		
		int min = 0;
		int place = 18; // There is 18 places available in the waiting room
		
	}

	private boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	public void addCustomer(Customer customer) {
		// TODO Auto-generated method stub
		
	}

	// Add more methods as needed
}