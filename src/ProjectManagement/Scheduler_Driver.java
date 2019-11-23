package ProjectManagement;

import PriorityQueue.Auxiliary;
import PriorityQueue.MaxHeap;
import PriorityQueue.PriorityQueueDriverCode;
import RedBlack.RBTree;
import Trie.Trie;
import Trie.TrieNode;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class Scheduler_Driver extends Thread implements SchedulerInterface {


	public static void main(String[] args) throws IOException {
        Scheduler_Driver scheduler_driver = new Scheduler_Driver();
      //  System.out.println("jsdokfojs");
        File file;
        if (args.length == 0) {
            URL url = PriorityQueueDriverCode.class.getResource("INP");
            file = new File(url.getPath());
        } else {
            file = new File(args[0]);
        }
        scheduler_driver.execute(file);
    }

    public void execute(File file) throws IOException {

        URL url = Scheduler_Driver.class.getResource("INP");
        file = new File(url.getPath());

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            System.err.println("Input file Not found. "+file.getAbsolutePath());
        }
        String st;
        while ((st = br.readLine()) != null) {
            String[] cmd = st.split(" ");
            if (cmd.length == 0) {
                System.err.println("Error parsing: " + st);
                return;
            }

            switch (cmd[0]) {
                case "PROJECT":
                    handle_project(cmd);
                    break;
                case "JOB":
                    handle_job(cmd);
                    break;
                case "USER":
        //        	System.out.println(cmd[1]);
                    handle_user(cmd[1]);
          //          System.out.println("USER!");
                    break;
                case "QUERY":
                    handle_query(cmd[1]);
                    break;
                case "":
                    handle_empty_line();
                    break;
                case "ADD":
                    handle_add(cmd);
                    break;
                default:
                    System.err.println("Unknown command: " + cmd[0]);
            }
        }


        run_to_completion();

        print_stats();

    }

    Trie<Project> trie = new Trie();
    MaxHeap<Job> jobs = new MaxHeap();
    RBTree<String,Job> jobs2 = new RBTree(); 
    RBTree<String,User> users = new RBTree();
    int globalTime;
    ArrayList<Job> completed = new ArrayList<Job>();
    ArrayList<Job> notCompleted = new ArrayList<Job>();
    LinkedList<Auxiliary<Job>> NotReady = new LinkedList<Auxiliary<Job>>();
    String budgPro;
    
 /**1. USER: Create the user with given user name.
    2. PROJECT: Create a project. NAME PRIORITY BUDGET
    3. JOB: Create a job. NAME PROJECT USER RUNTIME
    4. QUERY: Return the status of the Job queried.
    5. ADD: Increase the budget of the project. PROJECT BUDGET
    6. EMPTY LINE: Let the scheduler execute a single JOB.**/

    @Override
    public void run() {
        // till there are JOBS
        schedule();
    }


    @Override
    public void run_to_completion() {
    	
    	while(jobs.getSize()>0) {
    		
    	Auxiliary<Job> currAux;
    	Job currJob;
    	boolean executed = false;
    		System.out.println("Running code");
    		System.out.println("Remaining jobs: "+jobs.getSize());
    		while(!executed) {
    		
    			currAux = jobs.extractMax2();
    			currJob = currAux.getSecond();
    			Project currProject = trie.search(currJob.getProject()).getValue();
    			System.out.println("Executing: "+currJob.getName()+" from: "+currJob.getProject());
    		
    			if(currJob.getRunTime()<=currProject.getBudget()) {
        	
    				currJob.setStatus("COMPLETED");
    				globalTime += currJob.getRunTime();
    				currJob.setCompletedTime(globalTime);
    				currProject.setBudget(currProject.getBudget()-currJob.getRunTime());
    				System.out.println("Project: "+currProject.getName()+" budget remaining: "+currProject.getBudget());
    				completed.add(currJob);
    				executed = true;
    				System.out.println("System execution completed");
    			}
    	
    			else {
    				currJob.setStatus("REQUESTED");
    				System.out.println("Un-sufficient budget.");
    				NotReady.add(currAux);
    			}
    		}
    	}
    	
    	Iterator<Auxiliary<Job>> l = NotReady.iterator();
    	Auxiliary<Job> preAux;
    	Job preJob;
    	
    	while(l.hasNext()) {
    		
			preAux = l.next();
			notCompleted.add(preAux.getSecond());
			l.remove();
    	}
    }

    @Override
    public void handle_project(String[] cmd) {
    	
    	System.out.println("Creating project");
    	Project p = new Project(cmd[1],cmd[2],cmd[3]);
    	trie.insert(p.getName(), p);
    	
    }

    @Override
    public void handle_job(String[] cmd) {
    	
    	System.out.println("Creating job");
    	String s = cmd[2];
    	TrieNode<Project> t = trie.search(s);
    	User u = users.search(cmd[3]).getValue();
    	
    	if(u == null) {
    		System.out.println("No such user exists: "+cmd[3]);
    	}
    	
    	if(t == null) {
    		System.out.println("No such project exists. "+cmd[2]);
    	}
    	
    	else if((t!=null)&&(u!=null)) 
    	{
    	int n = t.getValue().getPriority();
    	Job j = new Job(cmd[1],cmd[2],cmd[3],cmd[4]);
    	j.setPriority(n);
    	jobs.insert(j);
    	jobs2.insert(j.getName(), j);
    	
    	}
    }

    @Override
    public void handle_user(String name) {
    	
    	//System.out.println("HERE");
    	System.out.println("Creating user");
    	User u = new User(name);
    	users.insert(name, u);
    }

    @Override
    public void handle_query(String key) {
    	
    	System.out.println("Querying");
    	Job enquiry = jobs.search(key);
    	Job enquiry2 = null;
    	Job enquiry3 = jobs2.search(key).getValue();
    	if(enquiry3 != null) {
    		System.out.println(key+": "+enquiry3.getStatus());
    	}
    	else {
    		System.out.println(key+": NO SUCH JOB");
    	}
    }

    @Override
    public void handle_empty_line() {
    	
    	schedule();
    }

    @Override
    public void handle_add(String[] cmd) {
    	
    	System.out.println("ADDING Budget");
    	String find = cmd[1];
    	int n = Integer.parseInt(cmd[2]);
    	TrieNode<Project> p = trie.search(find);
    	if(p!=null) {
    	p.getValue().setBudget(p.getValue().getBudget()+n);
    	budgPro = cmd[1]; 
    	Auxiliary<Job> preAux;
		Iterator<Auxiliary<Job>> l = NotReady.iterator();
		while(l.hasNext()) {
			preAux = l.next();
			if(preAux.getSecond().getProject().equals(budgPro)) {
				jobs.insert2(preAux);
			//System.out.println(preAux.getSecond()+" "+preAux.getFirst());
				l.remove();
			}
		}
    	}
    	else {
    	System.out.println("No such project exists. "+cmd[2]);
    	}
    }

    @Override
    public void print_stats() {
    	
    	System.out.println("--------------STATS---------------");
    	System.out.println("Total jobs done: "+completed.size());
    	for(int i=0;i<completed.size();i++) {
    		Job j = completed.get(i);
    		System.out.println("Job{user='"+j.getUser()+"', project='"+j.getProject()+"', jobstatus="+j.getStatus()+", execution_time="+j.getRunTime()+", end_time="+j.getCompletedTime()+", name='"+j.getName()+"'}");
    	}
    	
    	System.out.println("------------------------");
    	System.out.println("Unfinished jobs: ");
       	for(int i=0;i<notCompleted.size();i++) {
    		Job j = notCompleted.get(i);
    		System.out.println("Job{user='"+j.getUser()+"', project='"+j.getProject()+"', jobstatus="+j.getStatus()+", execution_time="+j.getRunTime()+", end_time=null"+", name='"+j.getName()+"'}");
    	}
       	System.out.println("Total unfinished jobs: "+notCompleted.size());
       	System.out.println("--------------STATS DONE---------------");
    }

    @Override
    public void schedule() {
    	
    	System.out.println("Running code");
    	Auxiliary<Job> currAux;
    	Job currJob;
    	boolean executed = false;
    		System.out.println("Remaining jobs: "+jobs.getSize());
    		while((!executed)&&(jobs.getSize()>0)) {
    			
    			currAux = jobs.extractMax2();
    			currJob = currAux.getSecond();
    			Project currProject = trie.search(currJob.getProject()).getValue();
    			System.out.println("Executing: "+currJob.getName()+" from: "+currJob.getProject());
    		
    			if(currJob.getRunTime()<=currProject.getBudget()) {
        	
    				currJob.setStatus("COMPLETED");
    				globalTime += currJob.getRunTime();
    				currJob.setCompletedTime(globalTime);
    				currProject.setBudget(currProject.getBudget()-currJob.getRunTime());
    				System.out.println("Project: "+currProject.getName()+" budget remaining: "+currProject.getBudget());
    				completed.add(currJob);
    				executed = true;
    		
    			}
    	
    			else {
    				System.out.println("Un-sufficient budget.");
    				NotReady.add(currAux);
    			}
    		}
    		System.out.println("Execution cycle completed");
    }   
}
//Check Status of the Projects.