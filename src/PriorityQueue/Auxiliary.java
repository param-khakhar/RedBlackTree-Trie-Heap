package PriorityQueue;

public class Auxiliary<T extends Comparable<T>> {
	
	private int first;
	private T second;
	Auxiliary(int f,T s){
		first = f;
		second = s;
		
	}
	
	public int getFirst() {
		return first;
	}
	
	public void setFirst(int a) {
		first = a;
	}
	
	public void setSecond(T a) {
		second = a;
	}
	
	public T getSecond() {
		return second;
	}
	
	public int compareTo(Auxiliary<T> a) {
		if(second.compareTo(a.getSecond())==0) {
			//System.out.println("This"+second+first);
			//System.out.println("This"+a.getSecond()+a.getFirst());
			if(first<a.getFirst()) {
				return 1;
			}
			else if(first>a.getFirst()) {
				return -1;
			}
			else {
				return 0;
			}
		}
		else {
			return second.compareTo(a.getSecond());
		}
	}
}
