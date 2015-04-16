package exercise3;

public class Cpu {
	private Queue queue;
	private Statistics statistics;
	public long maxCpuTime;
	public Process current;

    public Cpu(Queue queue, long maxCpuTime, Statistics statistics) {
		this.queue = queue;
		this.maxCpuTime = maxCpuTime;
		this.statistics = statistics;
		this.current = null;
    }

	public boolean isIdle() {
		return (current == null);
	}

	public boolean isEmpty() {
		return this.queue.isEmpty();
	}

	public Process switchProcess(Gui gui, long clock) {
		if (!this.isIdle()) {
			this.current.updateCpuTimeNeeded(this.maxCpuTime);
			this.insertProcess(this.current);
			this.current.leftCpu(clock);
		}

		Process next = null;
		if (!this.isEmpty()) {
			next = (Process) this.queue.removeNext();
			next.enteredCpu(clock);
		}
		this.current = next;
		gui.setCpuActive(next);

		return next;
	}

	public void insertProcess(Process p) {
		p.incrTimesInReadyQueue();
		this.queue.insert(p);
	}

	public void timePassed(long timePassed) {
		if (this.isIdle())
			this.statistics.cpuTimeSpentWaiting += timePassed;
		else
			this.statistics.cpuTimeSpentProcessing += timePassed;

		this.statistics.cpuQueueLengthTime += this.queue.getQueueLength()*timePassed;
		if (this.queue.getQueueLength() > this.statistics.cpuQueueLargestLength)
			this.statistics.cpuQueueLargestLength = this.queue.getQueueLength();
    }
    
    public void processCompleted() {
    	this.current = null;
	}
}