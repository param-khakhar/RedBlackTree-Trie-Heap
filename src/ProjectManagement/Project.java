package ProjectManagement;
import java.util.Queue;

import PriorityQueue.MaxHeap;

public class Project {
	
	private String name;
	private int Priority;
	private int budget;
	
	Project(String s,String p,String b){
		name = s;
		Priority = Integer.parseInt(p);
		budget = Integer.parseInt(b);
	}
	
	public int getBudget() {
		return budget;
	}
	
	public int getPriority() {
		return Priority;
	}
	
	public String getName() {
		return name;
	}
	
	public void setBudget(int n) {
		budget = n;
	}
}
