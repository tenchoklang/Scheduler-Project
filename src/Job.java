public class Job implements Comparable<Job> {

	private int ID;
	private int Priority;
	private int arrivalTime;
	private int jobLength;

	
	public Job(int ID, int Priority, int arrivalTime, int jobLength)
	{
		setID(ID);
		setPriority(Priority);
		setArrivalTime(arrivalTime);
		setjobLength(jobLength);
	}
	
	public Job()
	{
		
	}


	public int getID() {return ID;}
	public void setID(int ID) {
		this.ID = ID;
	}

	public int getPriority() {return Priority;}
	public void setPriority(int Priority) {
		this.Priority = Priority;
	}

	public int getjobLength() {return jobLength;}
	public void setjobLength(int jobLength) {
		this.jobLength = jobLength;
	}

	public int getArrivalTime() {return arrivalTime;}
	public void setArrivalTime(int arrivalTime) {
		this.arrivalTime = arrivalTime;
	}


	@Override
	public int compareTo(Job otherJob) {
		
		if(this.Priority<otherJob.getPriority())
		{
			return -1;
		}
		else if (this.Priority>otherJob.getPriority())
		{
			return 1;
		}
		
		return 0;
	}
 

}