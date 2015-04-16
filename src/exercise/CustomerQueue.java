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
    
    //The synchronized-method ensures that only one Barber has access to the next customer at the time
	public synchronized Customer getNextCustomer(int barberpos) {
		while (isEmpty()){
			gui.println("Barber nr." + barberpos + " is waiting");
			try{
				//If the queue is empty, the Barbers is put in a waiting-state
				wait();
			}
			catch (InterruptedException error){
				gui.println("Barber no." + barberpos + " has a new customer!");
			}
		}
		
		int min = 0; //zero customers in the waitingroom
		int place = 18; // There is 18 places available in the waiting room
		//Finding the next available chair
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
		
		// If the queue was full notify the others of the new empty place
		if(currentLength == newQueue.length-1){
			notifyAll();
		}
		Customer customer = newQueue[place];
		newQueue[place] = null;
		return customer;
	}
	//Checking whether the queue is empty
	private boolean isEmpty() {
		for(int i=0; i<newQueue.length; i++){
			if(newQueue[i]!=null)
				return false;
		}
		return true;
	}
	//Checking whether the queue is full
	private boolean isFull(){
		for(int i=0; i<newQueue.length; i++){
			if(newQueue[i]==null)
				return false;
		}
		return true;
	}

	public synchronized void addCustomer(Customer customer) {
		//If the queue is full, the doorman is put in a wait-state
		while(isFull()){
			gui.println("The doorman has to wait for an available chair.");
			try{
				wait();
			}
			catch (InterruptedException error){
				gui.println("A chair is available.");
			}
		}
		//If the waitingroom is not full:
		// Add the new customer to the empty chair in the waiting room
		for(int i=0; i<newQueue.length; i++){
			if(newQueue[i] == null){
				newQueue[i] = customer;
				gui.fillLoungeChair(i, customer);
				break;
			}
		}
		currentLength = currentLength +1; //adding customers to the queue
		
		//If the waiting room has been empty, notify that there has come a new customer
		//Wake the barbers form wait-state
		if(currentLength==1){
			//notifyAll();
		}
		gui.println("A new customer has arrived the waitingroom.");
		
	}
}