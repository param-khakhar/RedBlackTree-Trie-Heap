package Trie;


import Util.NodeInterface;


public class TrieNode<T> implements NodeInterface<T> {
	
	private TrieNode<T> [] children;
	private TrieNode<T> parent;
	private int count;
	private char key;
	private T value;
	private int size;
	private int level;
	private boolean endOfword;
	public TrieNode(char k,int s) {
		key = k;
		size = s;
		children = new TrieNode[size];
		endOfword = false;
	}
	
	public char getKey() {
		return key;
	}
	
	public void setKey(char c) {
		key = c;
	}
	
	public int getLevel() {
		return level;
	}
	
	public void setLevel(int l) {
		level = l;
	}
	
	public TrieNode<T> [] getChildren() {
		return children;
	}
	
	public TrieNode<T> getParent(){
		return parent;
	}
	
	public void setParent(TrieNode<T> p) {
		parent = p;
	}

    @Override
    public T getValue() {
        return value;
    }
    
    public void setVal(T v) {
    	value = v;
    }
    
    public void setEndOfword(boolean b) {
    	endOfword = b;
    }
    
    public boolean isEndOfword() {
    	return endOfword;
    }
    
    public int getCount() {
    	return count;
    }
    
    public void setCount(int s) {
    	count = s;
    }
}