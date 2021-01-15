/**
 * A B+ tree generic node
 * Abstract class with common methods and data. Each kind of node implements this class.
 * @param <TKey> the data type of the key
 * @param <TValue> the data type of the value
 */
abstract class BPTreeNode<TKey extends Comparable<TKey>, TValue> {
	
	protected Object[] keys;
	protected int keyTally;
	protected int m;
	protected BPTreeNode<TKey, TValue> parentNode;
	protected BPTreeNode<TKey, TValue> leftSibling;
	protected BPTreeNode<TKey, TValue> rightSibling;
	protected static int level = 0;
        public BPTreeNode<TKey, TValue> root;
	
	protected BPTreeNode() 
	{
		this.keyTally = 0;
		this.parentNode = null;
		this.leftSibling = null;
		this.rightSibling = null;
	}

	public int getKeyCount() 
	{
		return this.keyTally;
	}
	
	@SuppressWarnings("unchecked")
	public TKey getKey(int index) 
	{
		return (TKey)this.keys[index];
	}

	public void setKey(int index, TKey key) 
	{
		this.keys[index] = key;
	}

	public BPTreeNode<TKey, TValue> getParent() 
	{
		return this.parentNode;
	}

	public void setParent(BPTreeNode<TKey, TValue> parent) 
	{
		this.parentNode = parent;
	}	
	
	public abstract boolean isLeaf();
	
	/**
	 * Print all nodes in a subtree rooted with this node
	 */
	@SuppressWarnings("unchecked")
	public void print(BPTreeNode<TKey, TValue> node)
	{
		level++;
		if (node != null) {
			System.out.print("Level " + level + " ");
			node.printKeys();
			System.out.println();

			// If this node is not a leaf, then 
        		// print all the subtrees rooted with this node.
        		if (!node.isLeaf())
			{	BPTreeInnerNode inner = (BPTreeInnerNode<TKey, TValue>)node;
				for (int j = 0; j < (node.m); j++)
    				{
        				this.print((BPTreeNode<TKey, TValue>)inner.references[j]);
    				}
			}
		}
		level--;
	}

	/**
	 * Print all the keys in this node
	 */
	protected void printKeys()
	{
		System.out.print("[");
    		for (int i = 0; i < this.getKeyCount(); i++)
    		{
        		System.out.print(" " + this.keys[i]);
    		}
 		System.out.print("]");
	}
        
        @Override
        public String toString() 
	{
            String sline="";
		sline+="[";
    		for (int i = 0; i < this.getKeyCount(); i++)
    		{
        		sline+=" " + this.keys[i];
    		}
 		sline+="]";
                return sline;
	}


	////// You may not change any code above this line //////

	////// Implement the functions below this line //////
	
	/**
	 * Search a key on the B+ tree and return its associated value. If the given key 
	 * is not found, null should be returned.
	 */
	public TValue search(TKey key) 
	{  
            return null;
	}

	/**
	 * Insert a new key and its associated value into the B+ tree. The root node of the
	 * changed tree should be returned.
	 */
	public BPTreeNode<TKey, TValue> insert(TKey key, TValue value) 
	{
		return null;
	}
	/**
	 * Delete a key and its associated value from the B+ tree. The root node of the
	 * changed tree should be returned.
	 */
	public BPTreeNode<TKey, TValue> delete(TKey key) 
	{
            return null;
	}
	
//==============================================================================
        public int middle(int num) {
            if(num%2>0)
                return (num/2)+1;
            else 
                return num/2+1;
        }
//==============================================================================
        public Object[][] fillArrayInsert(TKey key, TValue val, BPTreeLeafNode<TKey, TValue> node) {
            Object ret[][] = new Object[m+1][m+1];
            boolean bset=false;
            int ifill=0;
            for(int i=0; ifill<m+1 & i<node.keyTally;) {
                if(node.getKey(i).compareTo(key)<0) {
                    ret[ifill][0] = node.getKey(i);
                    ret[ifill][1] = node.getValue(i);
                    ifill++;
                    i++;
                }
                else { 
                    if(bset==false) {
                        ret[ifill][0] = key;
                        ret[ifill][1] = val;
                        ifill++;
                        bset=true;
                    }
                    else {
                        ret[ifill][0] = node.getKey(i);
                        ret[ifill][1] = node.getValue(i);
                        ifill++;    
                        i++;
                    }
                }
            }
            if(bset==false) {
                ret[ifill][0] = key;
                ret[ifill][1] = val;       
            }
                
            return ret;
        }
//==============================================================================
public Object[] InnerfillArrayInsert(TKey key, BPTreeInnerNode<TKey, TValue> node) {
            Object ret[] = new Object[m+1];
            boolean bset=false;
            for(int i=0, ifill=0; ifill<m+1;) {
                if(node.getKey(i).compareTo(key)<0) {
                    ret[ifill] = node.getKey(i);
                    ifill++;
                    i++;
                }
                else { 
                    if(bset==false) {
                        ret[ifill] = key;
                        ifill++;
                        bset=true;
                    }
                    else {
                        ret[ifill]= node.getKey(i);
                        ifill++;    
                        i++;
                    }
                }
            }
            return ret;
        }        

//==============================================================================
       
        public BPTreeNode<TKey, TValue> FindRoot() {
            if(this.parentNode==null) {
                return this;
            } 
            else {
                BPTreeInnerNode<TKey, TValue> up = (BPTreeInnerNode<TKey, TValue>)(this.parentNode);
                while(up.parentNode!=null)
                    up=(BPTreeInnerNode<TKey, TValue>)up.parentNode;
                return up;
            }
        }
        
        public boolean NodeContains(TKey key) {
            for(int i=0; i<this.getKeyCount(); i++)
                if(this.keys[i].equals(key))
                    return true;
            return false;
        }
        
//==============================================================================

        public Object[][] RedistArrayFill(BPTreeLeafNode<TKey, TValue> left, BPTreeLeafNode<TKey, TValue> right, BPTreeInnerNode<TKey, TValue> par) {
            Object[][] array = new Object[2][m*2+1];
            for(int i=0; i<left.keyTally; i++) {
                array[0][i] = left.getKey(i);
                array[1][i] = left.getValue(i);
            }
            for(int i=0, j=left.keyTally; i<right.keyTally; j++,i++) {
                array[0][j] = right.getKey(i);
                array[1][j] = right.getValue(i);
            }
            return array;
        }
        
//==============================================================================

    public Object[][] InnerArrayfillForMerge(BPTreeInnerNode<TKey, TValue> left, BPTreeInnerNode<TKey, TValue> right) {
        Object[][] array = new Object[2][m*2+1];
        for(int i=0; i<left.keyTally; i++) {
            array[0][i] = left.getKey(i);
            array[1][i] = left.getChild(i);
        }
        array[1][left.keyTally] = left.getChild(left.keyTally);
        
        for(int i=0, j=left.keyTally; i<right.keyTally; i++) {
            array[0][j] = right.getKey(i);
            array[1][j+1] = right.getChild(i);
        }
        array[1][left.keyTally+right.keyTally] = right.getChild(right.keyTally);
        
        return array;
    }
        
        
    public int WhereisChild(BPTreeInnerNode<TKey, TValue> par, BPTreeNode<TKey, TValue> child) {
    int i=0;
    while(par.getChild(i).equals(child)==false)
        i++;
    return i;
}
}