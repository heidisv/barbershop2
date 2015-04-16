package exercise3;

public class Cpu {

	private Queue cpuQueue;
	private Statistics statistics;

	/**
	 * Creates a new memory device with the given parameters.
	 * 
	 * @param memoryQueue
	 *            The memory queue to be used.
	 * @param memorySize
	 *            The amount of memory in the memory device.
	 * @param statistics
	 *            A reference to the statistics collector.
	 */
	public Cpu(Queue cpuQueue, Statistics statistics) {
		this.cpuQueue = cpuQueue;
		this.statistics = statistics;
	}

	/**
	 * Adds a process to the memory queue.
	 * 
	 * @param p
	 *            The process to be added.
	 * @return
	 */
	public Process switchProcess() {
		if (cpuQueue.getQueueLength() > 0) {
			Object o = cpuQueue.removeNext();
			cpuQueue.insert(o);
			return getCurrent();
		}
		return null;
	}

	public void insert(Process p) {
		cpuQueue.insert(p);
		//update statistics
		statistics.nofTimesPlacedInCPUQueue++;
	}

	public Process removeActive() {
		if (cpuQueue.getQueueLength() > 0) {
			return (Process) cpuQueue.removeNext();
		}
		return null;
	}

	public Process getCurrent() {
		if (cpuQueue.getQueueLength() > 0) {
			return (Process) cpuQueue.getNext();
		}
		return null;
	}

	public int size() {
		return cpuQueue.getQueueLength();
	}
	
	public void timePassed(long timePassed) {
		statistics.cpuQueueLengthTime += cpuQueue.getQueueLength()
				* timePassed;
		if (cpuQueue.getQueueLength() > statistics.cpuQueueLargestLength) {
			statistics.cpuQueueLargestLength = cpuQueue.getQueueLength();
		}
	}

}