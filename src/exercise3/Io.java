package exercise3;

import exercise3.EventQueue;
import exercise3.Gui;
import exercise3.Process;
import exercise3.Queue;
import exercise3.Statistics;



public class Io {
	private Queue ioQueue;
	private Statistics statistics;
	private long ioWait;
	private Gui gui;

	private Process activeProcess = null;


	public Io(Queue ioQueue, Statistics statistics, EventQueue eventQueue, long ioWait, Gui g) {
		this.ioQueue = ioQueue;
		this.statistics = statistics;
		this.gui = g;
		this.ioWait = ioWait;

	}

	public boolean addProcess(Process p) {
		ioQueue.insert(p);
		if (activeProcess == null) {
			start();
			return true;
		} else {
			return false;
		}
	}

	public Process start() {
		if (ioQueue.isEmpty()) {
			return null;
		}
		Process p = (Process) ioQueue.removeNext();
		activeProcess = p;
		gui.setIoActive(p);
		return p;
	}

	public Process getProcess() {
		Process p = activeProcess;
		activeProcess = null;
		gui.setIoActive(activeProcess);
		return p;
	}

	public long getIoTime() {
		return (long) (Math.random() * (ioWait * 2) + ioWait / 2);

	}

	public void updateTime(long timePassed) {
		statistics.ioQueueLengthTime += ioQueue.getQueueLength() * timePassed;
		if (ioQueue.getQueueLength() > statistics.largestIOQueue) {
			statistics.largestIOQueue = ioQueue.getQueueLength();
		}
	}
}