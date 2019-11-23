package PriorityQueue;
import java.util.ArrayList;
public class MaxHeap<T extends Comparable<T>> implements PriorityQueueInterface<T> {
	
	ArrayList<Auxiliary<T>> heap;
	int last;
	int trick = 0;
    public MaxHeap(){
    	heap = new ArrayList<Auxiliary<T>>();
    	last = 1;
    	heap.add(null);
    }
    
    public int getSize() {
    	return last-1;
    }
    
    public int parent(int i) {
    	return i/2;
    }
    
    public int left(int i) {
    	return 2*i;
    }
    
    public int right(int i) {
    	return 2*i+1;
    }
    
    @Override
    public void insert(T element) {
    	heap.add(last,new Auxiliary<T>(trick+1,element));
    	//System.out.println(trick+1 + " "+ element);
    	last += 1;
    	if(last != 1) {
    		trick = last > heap.get(last-1).getFirst() ? last : heap.get(last-1).getFirst();
    	}
    	else {
    		trick = heap.get(last-1).getFirst();
    	}
    	int curr = last-1;
    	int par = parent(curr);
    	T temp;
    	boolean inserted = false;
    	while((par>1)&&(!inserted)) {
    		if((heap.get(par)).compareTo(heap.get(curr))<0) {
    			swap(heap,par,curr);
    			curr = par;
    			par = parent(par);
    		}
    		else {
    			inserted = true;
    		}
    	}
    	if(par!=0) {
    	if(heap.get(par).compareTo(heap.get(curr))<0) {
    		swap(heap,par,curr);
    	}
    	}
    }
    
    public void insert2(Auxiliary<T> aux) {
    	heap.add(last, aux);
    	last += 1;
    	int curr = last-1;
    	int par = parent(curr);
    	T temp;
    	boolean inserted = false;
    	while((par>1)&&(!inserted)) {
    		if((heap.get(par)).compareTo(heap.get(curr))<0) {
    			swap(heap,par,curr);
    			curr = par;
    			par = parent(par);
    		}
    		else {
    			inserted = true;
    		}
    	}
    	if(par!=0) {
    	if(heap.get(par).compareTo(heap.get(curr))<0) {
    		swap(heap,par,curr);
    		}
    	}
    }

    @Override
    public T extractMax() {
    	
    	if(last != 1) {
        int curr = last-1;
        T result = heap.get(1).getSecond();
        swap(heap,1,curr);
        heap.set(curr, null);
        heapify(1);
        last -= 1;
        return result;
    	}
    	else {
    		return null;
    	}
    }
    
    public Auxiliary<T> extractMax2(){
    	
    	if(last != 1) {
            int curr = last-1;
            Auxiliary<T> result = heap.get(1);
            swap(heap,1,curr);
            heap.set(curr, null);
            heapify(1);
            last -= 1;
            return result;
        	}
        	else {
        		return null;
        	}
    }
    
    public void swap(ArrayList<Auxiliary<T>> l,int i,int j) {
    	Auxiliary<T> temp = l.get(i);
    	l.set(i, l.get(j));
    	l.set(j,temp);
    }
    
    public void heapify(int i) {
    	
    	int s = last-1;
    	Auxiliary<T> largest = heap.get(i);
    	String large = "";
    	if(left(i) <= s) {
    		if(heap.get(left(i))!=null) {
    		if(heap.get(left(i)).compareTo(heap.get(i)) > 0) {
    			largest = heap.get(left(i));
    			large = "left";
    		} 
    	}
    }
    	if(right(i) <= s) {
    		if(heap.get(right(i))!=null) {
    		if(heap.get(right(i)).compareTo(largest) > 0) {
    			largest = heap.get(right(i));
    			large = "right";
    		}
    	}
    }
    	
    	if(large.equals("right")) {
    		
    		swap(heap,i,right(i));
    		heapify(right(i));
    		
    	}
    	
    	else if(large.equals("left")) {
    		
    		swap(heap,i,left(i));
    		heapify(left(i));
    		
    	}
    	
    	else {
    		return;
    	}
    }
    
    public T search(String key) {
    	for(int i=1;i<heap.size();i++) {
    		if(heap.get(i)!=null) {
    		if(heap.get(i).getSecond().toString().equals(key)) {
    			return heap.get(i).getSecond();
    			}
    		}
    	}
    	return null;
    }
}