package ProjectManagement;

public class Job implements Comparable<Job> {
	
	//private User u;
	private String user;
	private String name;
	private int runTime;
	private String project;
	private String Status;
	private int Priority;
	private int completedTime;
	
	Job(String s,String t,String u1,String p){
		project = t;
		user = u1;
		name = s;
		runTime = Integer.parseInt(p);
		Status = "NOT FINISHED";
	}
	
	public String getUser() {
		return user;
	}
	
	public void setCompletedTime(int t) {
		completedTime = t;
	}
	
	public int getCompletedTime() {
		return completedTime;
	}
	
	public String getProject() {
		return project;
	}
	
	public int getPriority() {
		return Priority;
	}
	
	public void setPriority(int s) {
		Priority = s;
	}
	
	public void setStatus(String s) {
		Status = s;
	}
	
	public String getStatus() {
		return Status;
	}
	
	public String toString() {
		return name;
	}
	
	public String getName() {
		return name;
	}
	
	public int getRunTime() {
		return runTime;
	}

    @Override
    public int compareTo(Job job) {
        if(Priority < job.getPriority()) {
        	return -1;
        }
        else if(Priority > job.getPriority()) {
        	return 1;
        }
        else {
        	return 0;
        }
    }
}