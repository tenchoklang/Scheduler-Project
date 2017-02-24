/*
 * Author: Tenzin Choklang
 * Date: May 19, 2016
 * Class: Data Structures CS 313
 * Project: Project 3 CPU scheduler
 * 
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Project3 {

	public static void main(String[] args) throws IOException {
		
		ArrayList<Job> myAL = new ArrayList<Job>();
		
		try {

			String CurrentLine;

			BufferedReader br = new BufferedReader(new FileReader("project3.txt"));

			while ((CurrentLine = br.readLine()) != null) {
				int index = 0;
				String[] tmp = CurrentLine.split(" ");    //get each string without spaces and store them into the array
				
				Job newJob = new Job();
				
				for(String s: tmp)
				{
				
					switch(index)
					{
					case 0:
						newJob.setID(Integer.parseInt(tmp[index]));//turn id into a int and setID
						break;
						
					case 1:
						newJob.setPriority(Integer.parseInt(tmp[index]));//turn priority into a int and setpriority
						break;
						
					case 2:
						newJob.setArrivalTime(Integer.parseInt(tmp[index]));//arrivaltime
						break;
						
					case 3:
						newJob.setjobLength(Integer.parseInt(tmp[index]));//joblength
						break;
						
					default:
						System.out.println("INVALID");
					}
					
					index++;
				}
				myAL.add(newJob);//add that object to arrayList
				
				
			}

		} catch (IOException e) {
			e.printStackTrace();
		 
		}//end of try catch
				
	Scheduler newScheduler = new Scheduler(myAL);//pass arrayList with all job objects to class scheduler
	
	newScheduler.startScheduler();

	}
}