package exercise3;

/**
 * This class contains a lot of public variables that can be updated by other
 * classes during a simulation, to collect information about the run.
 */
import java.text.DecimalFormat;
public class Statistics {
	/** The number of processes that have exited the system */
	public long nofCompletedProcesses = 0;
	/** The number of processes that have entered the system */
	public long nofCreatedProcesses = 0;
	/** The number of processes interrupted by timeout RR */
	public long nofForcedProcessSwitches = 0;
	/** The number of IO operations */
	public long nofIOoperations = 0;
	/** CPU time spent processing */
	public long timeCPUspentProcessing = 0;
	/** CPU time spent waiting */
	//simulationLength - timeCPUspentProcessing;  
	
	
	/** The total time that all completed processes have spent waiting for memory */
	public long totalTimeSpentWaitingForMemory = 0;
	/**
	 * The time-weighted length of the memory queue, divide this number by the
	 * total time to get average queue length
	 */
	public long memoryQueueLengthTime = 0;
	/** The largest memory queue length that has occured */
	public long memoryQueueLargestLength = 0;
	/** The number of times a finished processes has been placed in memory queue. divide by # finished processes*/
	public long nofTimesPlacedInMemoryQueue= 0;

	/** The total time that all completed processes have spent waiting for IO*/
	public long totalTimeSpentWaitingForIO = 0; 
	/** The time-weighted length of the IO queue */
	public long ioQueueLengthTime = 0;
	/** The largest IO queue length that has occured */
	public long ioQueueLargestLength = 0;
	/** The number of times a finished processes has been placed in memory queue. divide by # finished processes*/
	public long nofTimesPlacedInIOQueue= 0;
	/** Time a finished process has been processed in IO*/
	public long timeInIo;
	
	/** The total time that all completed processes have spent waiting for IO*/
	public long totalTimeSpentWaitingForCPU= 0; // 
	/** The time-weighted length of the CPU queue */
	public long cpuQueueLengthTime = 0;
	/** The largest CPU queue length that has occured */
	public long cpuQueueLargestLength = 0;
	/** The number of times a finished processes has been placed in memory queue. divide by # finished processes*/
	public long nofTimesPlacedInCPUQueue= 0;

	public long timeSpentInSystem = 0;

	/**
	 * test
	 * Prints out a report summarizing all collected data about the simulation.
	 * 
	 * @param simulationLength
	 *            The number of milliseconds that the simulation covered.
	 */
	
	public void printReport(long simulationLength) {
		DecimalFormat dec = new DecimalFormat("0.00");
		System.out.println();
		System.out.println("Simulation statistics:");
		System.out.println();
		System.out.println("Number of completed processes: " + nofCompletedProcesses);
		System.out.println("Number of created processes: " + nofCreatedProcesses);
		System.out.println("Number of forced process switches (timeout): " + nofForcedProcessSwitches);
		System.out.println("Number of IO operations: " + nofIOoperations);
		System.out.println("Average throughput (process per second): " + (float) nofCompletedProcesses*100 /(simulationLength/1000)); //ms to seconds
		System.out.println();
		System.out.println("CPU time spent processing: " + timeCPUspentProcessing + " ms");
		System.out.println("CPU time spent waiting: " + (simulationLength-timeCPUspentProcessing) + " ms");
		System.out.println("percentage of CPU utilization: " + dec.format(((float)timeCPUspentProcessing*100/simulationLength)) + " %");
		System.out.println("percentage of CPU wait time: " + dec.format(( simulationLength-(float)timeCPUspentProcessing)*100/simulationLength) + " %"); 
		System.out.println();
		System.out.println("Largest occuring memory queue length: "	+ memoryQueueLargestLength);
		System.out.println("Largest occuring IO queue length: "	+ ioQueueLargestLength);	
		System.out.println("Largest occuring CPU queue length: " + cpuQueueLargestLength);
		System.out.println("Average memory queue length: " + dec.format((float) memoryQueueLengthTime / simulationLength));
		System.out.println("Average IO queue length: " + dec.format((float) ioQueueLengthTime / simulationLength));
		System.out.println("Average CPU queue length: " + dec.format((float) cpuQueueLengthTime / simulationLength));
		if (nofCompletedProcesses > 0) {// avoid division by zero
			System.out.println("Average # of times a finished process has been placed in memory queue: " + dec.format((double)nofTimesPlacedInMemoryQueue/nofCompletedProcesses));
			System.out.println("Average # of times a finished process has been placed in IO queue: " + dec.format((double)nofTimesPlacedInIOQueue/nofCompletedProcesses));
			System.out.println("Average # of times a finished process has been placed in CPU queue: " + dec.format((double)nofTimesPlacedInCPUQueue/nofCompletedProcesses));
			System.out.println();
			System.out.println("Average time spent in system per finished process: " + timeSpentInSystem/nofCompletedProcesses+ " ms");
			System.out.println("Average time spent waiting for memory per finished process: " + totalTimeSpentWaitingForMemory/nofCompletedProcesses + " ms");	
			System.out.println("Average time spent waiting on CPU per finished process: "+ totalTimeSpentWaitingForCPU/nofCompletedProcesses + " ms");
			System.out.println("Average time spent in CPU per finished process: " + timeCPUspentProcessing/nofCompletedProcesses + " ms");	
			System.out.println("Average time spent waiting for IO per process: " + totalTimeSpentWaitingForIO/nofCompletedProcesses + " ms");
			System.out.println("Average time spent in IO per process: " + timeInIo/nofCompletedProcesses + " ms");
		}
	}
}