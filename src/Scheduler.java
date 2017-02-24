import java.util.ArrayList;

public class Scheduler {


		private ArrayList<Job> jobList;
		private BinaryHeap<Job> newBinaryHeap = new BinaryHeap<Job>();
		private int index = 0;
		private int joblength=0;
		private int total=1;
		
		public Scheduler(ArrayList<Job> jobList){//gets arrayList from main class
			
			this.jobList = jobList;
		}
		
		public void startScheduler()
		{
			for(index =0; index<jobList.size();index++)//loops through for the amount of objects in arrayList
			{
				
				if(jobList.get(jobList.size()-1) ==jobList.get(index))//last index in the arrayList has been reached
				{
				
					if(jobList.get(index).getArrivalTime()!=-1)// != -1 means that Job is not in BinaryHeap so we just display it
					{
						display(jobList.get(index));
						
					}else{
						Job newJob = jobList.get(index);
						while(newBinaryHeap.isEmpty()!=true)//deletes if there are any job objects in the heap, 
						{
							newJob = newBinaryHeap.deleteMinimum();
						}
						display(newJob);
					}
				}
				
				else
				{
				
					if(jobList.get(index).getArrivalTime()==-1)// if AT is -1 we know it is already in the heap
					{
						Job displaythisJob=newBinaryHeap.deleteMinimum();// so we just delete Min
						display(displaythisJob);
						arriveWhile(displaythisJob);
					}
					
					else
					{
						
						Job displaythisJob = returnJob(index);
						display(displaythisJob);
						arriveWhile(displaythisJob);
						
					}
				}
			}
		}
		
		private void arriveWhile(Job displaythisJob)//checks if any jobs arrived while a Job is running, if it does arrive it is inserted into the heap
		{
			for(int b=index; b<jobList.size();b++)
			{
				if(jobList.get(b)!=displaythisJob && jobList.get(b).getArrivalTime()!=-1)//to avoid inserting duplicates of the same object
				{//need to fix this
					
					if(jobList.get(b).getArrivalTime() <= displaythisJob.getjobLength() || jobList.get(b).getArrivalTime()<total)
					{
						System.out.println("job with ID: " + jobList.get(b).getID() + " has arrived at time "+ jobList.get(b).getArrivalTime()+ " while job ^ was running");
						newBinaryHeap.insert(jobList.get(b));//insert if the a job arrives while a job is running
						jobList.get(b).setArrivalTime(-1);
					}
				}
			}
		}
		
		private Job returnJob(int index)//returns a Job to pass to the display method
		{
	
			Job currentJob = jobList.get(index);
			Job nextJob = jobList.get(index+1);
			int next= index+1;
			
			if(currentJob.getArrivalTime() == nextJob.getArrivalTime())//if two jobs have the same arrival Time
			{
				while(currentJob.getArrivalTime() == nextJob.getArrivalTime())//checks of the next next job, and the next next next job...etc have the same arrival times
				{
					
					newBinaryHeap.insert(currentJob);//insert only the currentJob 
					System.out.println("job with ID: " + currentJob.getID() + " has arrived");
					next += 1;//to get next next Job etc
					currentJob.setArrivalTime(-1);//set AT to -1, -1 is used as a status to let me know whether an object is already inside the heap or not
					currentJob = nextJob;
					nextJob = jobList.get(next);//set nextJob to the job after nextJob
					
					
					
					if(jobList.get(jobList.size()-1) == nextJob || currentJob.getArrivalTime() != nextJob.getArrivalTime())//end of the arrayList has been reached||arrival time not same
					{
						
						if(currentJob.getArrivalTime() == nextJob.getArrivalTime())//if arrivalTimes are the same then insert both current and next Job into the heap
						{
							newBinaryHeap.insert(currentJob);
							newBinaryHeap.insert(nextJob);
							nextJob.setArrivalTime(-1);
							
							System.out.println("JOB with ID: " + currentJob.getID() + " has arrived");
							System.out.println("JOB with ID: " + nextJob.getID() + " has arrived");
						}
						else//only insert the currentjob
						{
						newBinaryHeap.insert(currentJob);
						System.out.println("JOB with ID: " + currentJob.getID() + " has arrived");
						}
						currentJob.setArrivalTime(-1);

						break;
					}	
					
				}
		
				Job HipriorityJob = newBinaryHeap.deleteMinimum();
	
				return HipriorityJob;//return the highest priority job amongst the jobs with the same arrival time
				
			}
			
			else
			{
					currentJob.setArrivalTime(-1);
					return currentJob;//else return the job that arrived first		
			}
		}

		
		private void display(Job displaythisJob)//displays a job, Time slice based on its jobLength
		{
			
			joblength = 0;
			
			System.out.println("||||||||||||||||||||||||||||");
			while(displaythisJob.getjobLength() > joblength)
			{
				System.out.println(total+" \t"+ joblength + "\t--> JOB WITH ID "+ displaythisJob.getID()+ " AND PRIORITY " + displaythisJob.getPriority()+ " IS RUNNING");
				joblength++;
				total++;
			}
			System.out.println("||||||||||||||||||||||||||||");
		
		}
}
