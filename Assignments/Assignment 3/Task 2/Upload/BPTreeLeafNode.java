/**
 * A B+ tree leaf node
 * @param <TKey> the data type of the key
 * @param <TValue> the data type of the value
 */
class BPTreeLeafNode<TKey extends Comparable<TKey>, TValue> extends BPTreeNode<TKey, TValue> {
	
	private Object[] values;
	
	public BPTreeLeafNode(int order) {
		this.m = order;
		// The strategy used here first inserts and then checks for overflow. 
		// Thus an extra space is required i.e. m instead of m-1.
		// You can change this if needed.
		this.keys = new Object[m]; //compare with key until item is found
		this.values = new Object[m]; //return this after correct item is found
	}

	@SuppressWarnings("unchecked")
	public TValue getValue(int index) { //pass in index used to find key
		return (TValue)this.values[index];
	}

	public void setValue(int index, TValue value) { //opposite but same
		this.values[index] = value;
	}
	
	@Override
	public boolean isLeaf() {
		return true;
	}

	////// You should not change any code above this line //////

	////// Implement functions below this line //////
//==============================================================================        
        @Override
        public TValue search(TKey key) 
	{
            BPTreeLeafNode<TKey, TValue> hold = (BPTreeLeafNode<TKey, TValue>)(this);
            while(true) {
                if(hold==null)
                    return null;
                if(hold.getKey(hold.keyTally-1).compareTo(key)<0)
                    hold = (BPTreeLeafNode<TKey, TValue>)(hold.rightSibling);
                else {
                    for(int i=0; i<hold.keyTally; i++)
                        if(hold.getKey(i).equals(key))
                            return hold.getValue(i);
                    return null;
                }
            }
	}
//==============================================================================        
        /**
	 * Insert a new key and its associated value into the B+ tree. The root node of the
	 * changed tree should be returned.
	 */
	public BPTreeNode<TKey, TValue> insert(TKey key, TValue value) //if this insert is called, there is only the root or found way to the leaf for inserting
	{
            if(this.isLeaf()==false)
                return null;
                    
            root = (BPTreeLeafNode<TKey, TValue>)(this); 
            
            if(this.keyTally==0) {
                this.keyTally++;
                this.setKey(0, key);
                this.setValue(0, value);
                return root;
            }
            
            if(this.keyTally<m) {
                int ifind =0;
                while(ifind<this.keyTally) {
                    if(this.getKey(ifind).compareTo(key)<0) 
                        ifind++;
                    else
                        break;
                }
                if(ifind==this.keyTally) {
                    this.setKey(ifind, key);
                    this.setValue(ifind, value);
                }
                else {
                    for(int i=this.keyTally; i!=ifind; i--) {
                        this.setKey(i, this.getKey(i-1));
                        this.setValue(i, this.getValue(i-1));
                    }
                    this.setKey(ifind, key);
                    this.setValue(ifind, value);
                }
                this.keyTally++;
                return this;
            }
            else {
                BPTreeInnerNode<TKey, TValue> par = new BPTreeInnerNode<>(m);
                Object array[][];
                array = fillArrayInsert(key, value, this);
                //System.out.println(array[0][1]);
                int imid = middle(m+1);
                
                par.setKey(0, (TKey)(array[imid-1][0]));
                BPTreeLeafNode<TKey, TValue> left  = new BPTreeLeafNode<>(m), right = new BPTreeLeafNode<>(m);
                left.keyTally=imid-1;
                right.keyTally=m+2 - imid;
                for(int i=0; i<imid-1; i++) {
                    left.setKey(i, (TKey)(array[i][0]));
                    left.setValue(i,(TValue)(array[i][1]));
                }
                for(int i=imid-1, j=0; i<m+1; i++, j++) {
                    right.setKey(j, (TKey)(array[i][0]));
                    right.setValue(j,(TValue)(array[i][1]));
                }
                par.setChild(0, left);
                left.parentNode = par;
                left.rightSibling = right;
                right.parentNode = par;
                right.leftSibling = left;
                par.setChild(1, right);
                
                par.keyTally=1;
                return par;
            }
	}
//==============================================================================

public BPTreeNode<TKey, TValue> delete(TKey key) //delete and consider redist/ merge
	{
            BPTreeLeafNode<TKey, TValue> hold = this;
            if(hold.getKey(hold.keyTally-1).compareTo(key)<0)
                hold = (BPTreeLeafNode<TKey, TValue>)hold.rightSibling;
            
            int ifind=0;
            while(ifind<hold.keyTally) {
                if(hold.getKey(ifind).equals(key))
                    break;
                else if (hold.getKey(ifind).compareTo(key)<0) 
                    ifind++;
                else 
                    return this.FindRoot();
            }
            if(ifind==hold.keyTally) //not found
                return this.FindRoot();
            
            if(hold.keyTally-1>=(m/2) -1) { //no underflow
                for(int i=ifind; i<hold.keyTally-1; i++) {
                    hold.setKey(i, hold.getKey(i+1));
                    hold.setValue(i, hold.getValue(i+1));
                }
                    hold.setKey(hold.keyTally-1, null);
                    hold.setValue(hold.keyTally-1, null);
                    hold.keyTally--;
                    return this.FindRoot();
            }
            else { //left, right, merge!
                if(hold.keyTally==1) {
                    hold.setKey(ifind, null);
                    hold.keyTally=0;
                }
                else {
                int j=0;
                while(hold.getKey(j).equals(key)==false)
                    j++;
                
                if(j==hold.keyTally-1) {
                    hold.setItems(hold.getKey(j-1), hold.getValue(j-1), j);
                    hold.keyTally-=2;
                }
                else {
                for(int i=hold.keyTally-1; i!=j; i--) {
                    hold.setItems(hold.getKey(i), hold.getValue(i), i-1);
                    hold.keyTally--;
                }
                
                hold.setItems(null, null, hold.keyTally);
                hold.keyTally--; 
                }
                }
                if(hold.leftSibling==null) {
                    if(hold.rightSibling.getKeyCount()>(m/2)-1) {
                        hold = Redist(hold, (BPTreeLeafNode<TKey, TValue>)hold.rightSibling, key);
                        return hold.FindRoot();
                    }
                    else {
                        return Merge(key, hold, hold.rightSibling);
                    }
                }
                else if(hold.rightSibling==null) {
                    if(hold.leftSibling.getKeyCount()>(m/2)-1) {
                        hold = Redist((BPTreeLeafNode<TKey, TValue>)hold.rightSibling, hold, key);
                        return hold.FindRoot();
                    }
                    else {
                        return Merge(key, hold.leftSibling, hold);
                    }
                    
                }
                else {
                    if(hold.leftSibling.getKeyCount()>(m/2)-1) {
                        hold = Redist((BPTreeLeafNode<TKey, TValue>)hold.leftSibling, hold, key);
                        return hold.FindRoot();
                    }
                    else if(hold.rightSibling.getKeyCount()>(m/2)-1) {
                            hold = Redist(hold, (BPTreeLeafNode<TKey, TValue>)hold.rightSibling, key);
                            return hold.FindRoot();
                    }
                    else {
                        return Merge(key,hold.leftSibling, hold);
                    }
                }
                
            }
	}      
//==============================================================================
public BPTreeLeafNode<TKey, TValue> Redist(BPTreeLeafNode<TKey, TValue> left, BPTreeLeafNode<TKey, TValue> right, TKey key) {
    int itally = left.keyTally + right.keyTally, ipar = WhereisChild((BPTreeInnerNode<TKey, TValue>)left.parentNode, left), imid= middle(itally);
    BPTreeLeafNode<TKey, TValue> Newleft = new BPTreeLeafNode<>(m), Newright = new BPTreeLeafNode<>(m);
    BPTreeInnerNode<TKey, TValue> par = (BPTreeInnerNode<TKey, TValue>)right.parentNode;
    Object[][] array = RedistArrayFill((BPTreeLeafNode<TKey, TValue>)left, (BPTreeLeafNode<TKey, TValue>)right, (BPTreeInnerNode<TKey, TValue>)right.parentNode);
    
    imid-=1;
    
    
    for(int i=0; i<imid; i++) 
        Newleft.setItems(array[0][i],array[1][i],i);

    for(int i=Newleft.keyTally, j=0; i<itally; i++, j++)
        Newright.setItems(array[0][i], array[1][i], j);
    
    par.setKey(ipar, Newright.getKey(0));
    
    par.setChild(ipar, Newleft);
    par.setChild(ipar+1, Newright);
    
    Newleft.rightSibling = Newright;
    Newright.leftSibling = Newleft;
    
    Newleft.parentNode = Newright.parentNode = par;
    Newleft.leftSibling = left.leftSibling;
    
    if(Newleft.leftSibling!=null)
        left.leftSibling.rightSibling = Newleft;
    
    Newright.rightSibling = right.rightSibling;
    
    if(Newright.rightSibling!=null)
        right.rightSibling.leftSibling=Newright;
    
    return Newleft;
}

public void setItems(Object key, Object val, int i) {
    this.setKey(i, (TKey)key);
    this.setValue(i, (TValue)val);
    this.keyTally++;
}

//==============================================================================

public BPTreeInnerNode<TKey, TValue> Merge(TKey key, BPTreeNode<TKey, TValue> left, BPTreeNode<TKey, TValue>right) {
    
    if(left.isLeaf()==true) { //merge at leaf
        BPTreeInnerNode<TKey, TValue> par = (BPTreeInnerNode<TKey, TValue>)left.parentNode;
        Object [][] array  = RedistArrayFill((BPTreeLeafNode<TKey, TValue>)left, (BPTreeLeafNode<TKey, TValue>)right, par);
        BPTreeLeafNode<TKey, TValue> leaf  = new BPTreeLeafNode<>(m);
        for(int i=0; i<(right.keyTally+left.keyTally); i++) {
            leaf.setItems(array[0][i], array[1][i], i);
        }
        int ipar = WhereisChild((BPTreeInnerNode<TKey, TValue>)left.parentNode, left);
        if(ipar!=par.getKeyCount()) {
            leaf.leftSibling=left.leftSibling;
            leaf.rightSibling = right.rightSibling;
            if(leaf.leftSibling!=null)
                left.leftSibling.rightSibling = leaf;
            if(leaf.rightSibling!=null)
                leaf.rightSibling.leftSibling = leaf;
            
            if(ipar!=par.keyTally-1) {
                for(int i=par.keyTally-1; i!=ipar; i--) {
                    par.setKey(i-1, par.getKey(i));
                }
                for(int i=par.keyTally; i!=ipar; i--) {
                    par.setChild(i-1, par.getChild(i));
                }
            }
            else {
                par.setKey(ipar, null);
                par.setChild(ipar+1, null);
                par.setChild(ipar, leaf);
            }
            
            
           par.setChild(par.keyTally, null);
           par.keyTally--;
           par.setChild(ipar, leaf); 
        }
        else {
            par.setKey(par.getKeyCount()-1, null);
            par.keyTally--;
            leaf.rightSibling =null;
            leaf.leftSibling = left.leftSibling;
            left.leftSibling.rightSibling = leaf;
            par.setChild(ipar, leaf);
        }
        if(par.keyTally<m/2) {
            return (BPTreeInnerNode<TKey, TValue>)fixParent(leaf).FindRoot();
        }
        else 
            return (BPTreeInnerNode<TKey, TValue>)par.FindRoot();
    }
    else { //merge in middle 
        BPTreeInnerNode<TKey, TValue> par = (BPTreeInnerNode<TKey, TValue>)left.parentNode;
        int ipar = WhereisChild((BPTreeInnerNode<TKey, TValue>)left.parentNode, right);
        Object array[][] = InnerArrayfillForMerge((BPTreeInnerNode<TKey, TValue>)left,(BPTreeInnerNode<TKey, TValue>)right);
        BPTreeInnerNode<TKey, TValue> inner = new BPTreeInnerNode<>(m);
        
        if(ipar+1!=par.keyTally-1) {
            for(int i=par.keyTally-1; i!=ipar; i--) {
                    par.setKey(i-1, par.getKey(i));
                }
                for(int i=par.keyTally; i!=ipar; i--) {
                    par.setChild(i-1, par.getChild(i));
                }
            par.keyTally--;
        }
        else {
            par.setChild(ipar, null);
            par.setKey(ipar, null);
            par.keyTally--;
        }
        
        for(int i=0; i<left.keyTally+right.keyTally; i++) {
            inner.setKey(i, (TKey)array[0][i]);
            inner.setChild(i,(BPTreeNode<TKey, TValue>)array[1][i]);
        }
        
        par.setChild(ipar, inner);
        
        inner.leftSibling = left.leftSibling;
        inner.rightSibling = right.rightSibling;
        
        if(inner.leftSibling!=null)
            inner.leftSibling.rightSibling = inner;
        if(inner.rightSibling!=null)
            inner.rightSibling.leftSibling = inner;
            
        if(par.keyTally<(m/2)-1) {
            return (BPTreeInnerNode<TKey, TValue>)fixParent(inner).FindRoot();
        }
        else 
            return (BPTreeInnerNode<TKey, TValue>)par.FindRoot();
            
    }
    
}

public BPTreeNode<TKey, TValue> fixParent(BPTreeNode<TKey, TValue> child) {
    BPTreeInnerNode<TKey, TValue> par = (BPTreeInnerNode<TKey, TValue>)child.parentNode;
    if(child.parentNode.parentNode==null) {
        root = child.parentNode;
        if(root.keyTally!=0)
            return root;
        if(root.getKeyCount()==0 && child.isLeaf()==false) {
            child = (BPTreeInnerNode<TKey, TValue>)child;
            BPTreeInnerNode<TKey, TValue> left, right;
            BPTreeInnerNode<TKey, TValue> hold = new BPTreeInnerNode<>(m);
            if(child.rightSibling==null) {
                left = (BPTreeInnerNode<TKey, TValue>)par.getChild(0);
                right = (BPTreeInnerNode<TKey, TValue>)child;     
            }
            else {
                right = (BPTreeInnerNode<TKey, TValue>)par.getChild(0);
                left = (BPTreeInnerNode<TKey, TValue>)child; 
            }
            Object array[][] = new Object[2][2*m+1];
                for(int i=0; i<left.keyTally; i++) {
                    array[0][i] = left.getKey(i);
                    array[1][i] = left.getChild(i);
                }
                for(int i=0, j=left.keyTally; i<right.keyTally; j++, i++) {
                    array[0][j] = right.getKey(i);
                    array[1][j] = right.getChild(i);
                }
            //------------------------------------------------------------------
            for(int i=0; i<left.keyTally+right.keyTally; i++) {
                hold.setKey(i, (TKey)array[0][i]);
                hold.setChild(i, (BPTreeNode<TKey, TValue>)array[1][i]);
            hold.parentNode=null;
            return hold;
            }
        }
        else {
            BPTreeLeafNode<TKey, TValue> left, right;
            BPTreeLeafNode<TKey, TValue> hold = new BPTreeLeafNode<>(m);
            if(child.rightSibling==null) {
                left = (BPTreeLeafNode<TKey, TValue>)par.getChild(0);
                right = (BPTreeLeafNode<TKey, TValue>)child;     
            }
            else {
                right = (BPTreeLeafNode<TKey, TValue>)par.getChild(0);
                left = (BPTreeLeafNode<TKey, TValue>)child; 
            }
            Object array[][] = new Object[2][2*m+1];
                for(int i=0; i<left.keyTally; i++) {
                    array[0][i] = left.getKey(i);
                    array[1][i] = left.getValue(i);
                }
                for(int i=0, j=left.keyTally; i<right.keyTally; j++, i++) {
                    array[0][j] = right.getKey(i);
                    array[1][j] = right.getValue(i);
                }
            //------------------------------------------------------------------
            for(int i=0; i<left.keyTally+right.keyTally; i++) {
                hold.setItems(array[0][i], array[1][i], i);
            hold.parentNode=null;
            return hold;
            }
        }
    }
    else {
        BPTreeNode<TKey, TValue> hold; 
        if(par.leftSibling==null)
            hold = Merge(null, child, child.rightSibling);
        else if(par.rightSibling==null)
            hold = Merge(null, child.leftSibling, child);
        else 
            hold = Merge(null, child.leftSibling, child);
        return hold.FindRoot();
    }
    return this;
}


}
