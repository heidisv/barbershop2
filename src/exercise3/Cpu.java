package exercise3;

public class Cpu {
	
	//TODO: ADD comments in the whole class!!!!!!!!!!!!!!!!!!!!!!!
	
	private Queue cpuQueue;
	private Statistics statistics;
	private Process activeProcess;
	private Gui gui;
	private long maxCpuTime;
	
	public Cpu(Queue cpuQueue, long maxCpuTime, Statistics statistics, Gui gui){
		this.cpuQueue = cpuQueue;
		this.maxCpuTime = maxCpuTime;
		this.statistics = statistics;
	}
	
	public void insertProcess(Process p){
		cpuQueue.insert(p);
	}
	
	public boolean isIdle(){
		return this.activeProcess == null;
	}
	
	public Process getActive(){
		Process p = this.activeProcess;
		this.activeProcess = null;
		return p;
	}
	
	public long getMaxCpuTime(){
		return maxCpuTime;
	}
	
	public Process start(){
		if (!this.cpuQueue.isEmpty()){
			Process p = (Process) this.cpuQueue.removeNext();
			this.activeProcess = p;
			gui.setCpuActive(p);
			return p;
		}
		else{
			this.activeProcess = null;
			gui.setCpuActive(null);
			return this.activeProcess;
		}
	}
	
	public void updateTime(long timePassed){
		this.statistics.cpuQueueLengthTime += this.cpuQueue.getQueueLength() * timePassed;
		if (this.cpuQueue.getQueueLength() > this.statistics.cpuQueueLargestLength){
			this.statistics.cpuQueueLargestLength = this.cpuQueue.getQueueLength();
		}
	}

}
