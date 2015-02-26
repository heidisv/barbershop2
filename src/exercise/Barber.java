package exercise;

/**
 * This class implements the barber's part of the
 * Barbershop thread synchronization example.
 */
public class Barber extends Thread{
	/**
	 * Creates a new barber.
	 * @param queue		The customer queue.
	 * @param gui		The GUI.
	 * @param pos		The position of this barber's chair
	 */
	
	private boolean running;
	private CustomerQueue customerQueue;
	private Gui gui;
	private int barberpos;
	
	
	public Barber(CustomerQueue queue, Gui gui, int pos) { 
		customerQueue = queue;
		this.gui = gui;
		this.barberpos = pos;
	}

	/**
	 * Starts the barber running as a separate thread.
	 */
	public void startThread() {
		running = true;
		start();
	}

	/**
	 * Stops the barber thread.
	 */
	public void stopThread() {
		running = false;
	}

	public void run(){
		while(running){
			try{
				gui.barberIsSleeping(barberpos);
				// Waiting/sleeping for a random time
				sleep((long)(Globals.barberSleep*Math.random()));
				gui.barberIsAwake(barberpos);
			}
			catch(InterruptedException error){
			}
			// Getting the next customer in the queue/waitingroom
			gui.fillBarberChair(barberpos, customerQueue.getNextCustomer(barberpos));
		
			// Waiting while the Barber is working on a customer
			try{
				sleep((long)(Globals.barberWork*Math.random()));
			}
			catch(InterruptedException error2){
				
			}
			// The Barber is finished with a customer, and the chair is awailable for a new customer
			gui.emptyBarberChair(barberpos);
		}
	}
}