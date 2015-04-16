package exercise3;

public class Io {
	private Queue queue;
	private Statistics statistics;
	public long avgIoTime;
	public Process current;

    public Io(Queue queue, long avgIoTime, Statistics statistics) {
		this.queue = queue;
		this.avgIoTime = avgIoTime;
		this.statistics = statistics;
		this.current = null;
    }

	public boolean isIdle() {
		return (current == null);
	}

	public boolean isEmpty() {
		return this.queue.isEmpty();
	}

	public Process processIo(Gui gui, long clock) {
		this.statistics.nofProcessedIoOp++;
		Process next = null;
		if (!this.isEmpty()) {
			next = (Process) this.queue.removeNext();
			next.enteredIo(clock);
		}
		this.current = next;
		gui.setIoActive(next);

		return next;
	}

	public void insertProcess(Process p) {
		p.incrTimesInIoQueue();
		this.queue.insert(p);
	}

	public void timePassed(long timePassed) {
		this.statistics.ioQueueLengthTime += this.queue.getQueueLength()*timePassed;
		if (this.queue.getQueueLength() > this.statistics.ioQueueLargestLength)
			this.statistics.ioQueueLargestLength = this.queue.getQueueLength();
    }
    
    public void processCompleted() {
    	this.current = null;
	}
}
