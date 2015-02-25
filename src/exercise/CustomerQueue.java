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
		for(int i = 0; i<newQueue.length; i++){
			if(newQueue[i]!=null){
				if(min==0){
					min = newQueue[i].getCustomerID();
							place = i;
	
				}
			}
		}
		gui.println("Customer no." + newQueue[place].getCustomerID() +" is fetched from waitingroom");
		gui.emptyLoungeChair(place);
		
		currentLength = currentLength-1; //reducing number of customers in the queue
		
		if(currentLength == newQueue.length-1){
			notifyAll();
		}
		Customer customer = newQueue[place];
		newQueue[place] = null;
		return customer;
	}

	private boolean isEmpty() {
		for(int i=0; i<newQueue.length; i++){
			if(newQueue[i]!=null)
				return false;
		}
		return true;
	}
	
	private boolean isFull(){
		for(int i=0; i<newQueue.length; i++){
			if(newQueue[i]==null)
				return false;
		}
		return true;
	}

	public synchronized void addCustomer(Customer customer) {
		while(isFull()){
			gui.println("The doorman has to wait for an available chair.");
			try{
				wait();
			}
			catch (InterruptedException error){
				gui.println("A chair is available.");
			}
		}
		for(int i=0; i<newQueue.length; i++){
			if(newQueue[i] == null){
				newQueue[i] = customer;
				gui.fillLoungeChair(i, customer);
				break;
			}
		}
		currentLength = currentLength +1; //adding customers to the queue
		
		if(currentLength==1){
			notifyAll();
		}
		gui.println("A new customer has arrived the waitingroom.");
		
	}
}