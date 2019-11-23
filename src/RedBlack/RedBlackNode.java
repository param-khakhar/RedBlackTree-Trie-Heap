package RedBlack;
import java.util.ArrayList;
import Util.RBNodeInterface;
import java.util.List;

public class RedBlackNode<T extends Comparable, E> implements RBNodeInterface<E> {
	
	private String color;
	private RedBlackNode<T,E> left;
	private RedBlackNode<T,E> right;
	private RedBlackNode<T,E> parent;
	private E value;
	private T key;
	private List<E> values;
	
	RedBlackNode(String s,E v,T k){
		
		color = s;
		value = v;
		key = k;
		if(!color.equals("EXT")) {
		values = new ArrayList<E>();
		values.add(value);
		}

	}
	
	public RedBlackNode<T,E> getLeft(){
		return left;
	}
	
	public RedBlackNode<T,E> getRight(){
		return right;
	}
	
	public RedBlackNode<T,E> getParent(){
		return parent;
	}
	
	public void setLeft(RedBlackNode<T,E> l) {
		left = l;
	}
	
	public void setRight(RedBlackNode<T,E> r) {
		right = r;
	}
	
	public void setParent(RedBlackNode<T,E> p) {
		parent = p;
	}
	
	public String getColor() {
		return color;
	}
	
	public void setColor(String c) {
		color = c;
	}
	
	public T getKey() {
		return key;
	}
	
	public void setKey(T k) {
		key = k;
	}
	
	public void addValue(E v) {
		values.add(v);
	}
	
    @Override
    public E getValue() {
        return value;
    }
    
    public int compareTo(RedBlackNode<T,E> b) {
    	//System.out.println(key.toString());
    	//System.out.println("Comparedto");
    	//System.out.println(b.getKey().toString());
    	//System.out.println(key.toString().compareTo(b.getKey().toString()));
    	return key.toString().compareTo(b.getKey().toString());
    }

    @Override
    public List<E> getValues() {
        return values;
    }
}
