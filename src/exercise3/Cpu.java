package exercise3;

import exercise3.Gui;
import exercise3.Process;
import exercise3.Queue;
import exercise3.Statistics;



public class Cpu {
	/** The queue of processes waiting for free memory */
	private Queue cpuQueue;
	/** A reference to the statistics collector */
	private Statistics statistics;
	/** The amount of memory in the memory device */
	/** The amount of free memory in the memory device */
	private Process activeProcess;
	
	private Gui gui;
	private long maxCpuTime;

	/**
	 * Creates a new memory device with the given parameters.
	 * @param memoryQueue	The memory queue to be used.
	 * @param memorySize	The amount of memory in the memory device.
	 * @param statistics	A reference to the statistics collector.
	 */
    public Cpu(Queue cpuQueue, long maxCpuTime, Statistics statistics, Gui gui) {
		this.cpuQueue = cpuQueue;
		this.statistics = statistics;
		this.gui = gui;
		this.maxCpuTime = maxCpuTime;
    }
    
    public void insertProcess(Process p) {
		cpuQueue.insert(p);
	}
    
    public boolean isIdle() {
    	return this.activeProcess == null;
    }
    
    public Process getActive() {
    	Process p = this.activeProcess;
    	this.activeProcess = null;
    	return p;
    }

	public long getMaxCpuTime() {
		return maxCpuTime;
	}
    
	public Process start() {
		if (!this.cpuQueue.isEmpty()) {
			Process p = (Process) this.cpuQueue.removeNext();
			this.activeProcess = p;
			gui.setCpuActive(p);
			return p;
		}
		else {
			this.activeProcess = null;
			gui.setCpuActive(null);
			return this.activeProcess;
		}
	}
	
	public void updateTime(long timePassed) {
        this.statistics.cpuQueueLengthTime += this.cpuQueue.getQueueLength() * timePassed;
        if (this.cpuQueue.getQueueLength() > this.statistics.cpuQueueLargestLength) {
        	this.statistics.cpuQueueLargestLength = this.cpuQueue.getQueueLength();
        }
    }
}