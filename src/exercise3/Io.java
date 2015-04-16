package exercise3;

public class Io {

	private Queue ioQueue;
	private Statistics statistics;
	private Process current = null;

	public Io(Queue ioQueue, Statistics statistics) {
		this.ioQueue = ioQueue;
		this.statistics = statistics;
	}

	public void insert(Object o) {
		if (getCurrent() == null)
			setCurrent((Process) o);
		else
			ioQueue.insert(o);

	}

	public Process remove() {
		Process c = getCurrent();
		if (size() > 0)
			setCurrent((Process) ioQueue.removeNext());
		else
			setCurrent(null);
		return c;
	}

	public int size() {
		return ioQueue.getQueueLength();
	}

	public Process getCurrent() {
		return current;
	}

	private void setCurrent(Process current) {
		this.current = current;

	}
	public void timePassed(long timePassed) {
		statistics.ioQueueLengthTime += ioQueue.getQueueLength() * timePassed;
		if (ioQueue.getQueueLength() > statistics.ioQueueLargestLength) {
			statistics.ioQueueLargestLength = ioQueue.getQueueLength();
		}
	}

}
