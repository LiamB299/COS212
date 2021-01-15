
import java.util.Objects;

/**
 * A B+ tree internal node
 * @param <TKey> the data type of the key
 * @param <TValue> the data type of the value
 */
class BPTreeInnerNode<TKey extends Comparable<TKey>, TValue> extends BPTreeNode<TKey, TValue> {
	
	protected Object[] references; 
	
	public BPTreeInnerNode(int order) {
		this.m = order;
		// The strategy used here first inserts and then checks for overflow. 
		// Thus an extra space is required i.e. m instead of m-1/m+1 instead of m.
		// You can change this if needed. 
		this.keys = new Object[m]; //compare with
		this.references = new Object[m + 1]; //Use to go down a level
	}
	
	@SuppressWarnings("unchecked")
	public BPTreeNode<TKey, TValue> getChild(int index) {
		return (BPTreeNode<TKey, TValue>)this.references[index];
	}

	public void setChild(int index, BPTreeNode<TKey, TValue> child) {
		this.references[index] = child;
		if (child != null)
			child.setParent(this);
	}
	
	@Override
	public boolean isLeaf() {
		return false;
	}

	////// You should not change any code above this line //////

	////// Implement functions below this line //////
//==============================================================================        
        /**
	 * Insert a new key and its associated value into the B+ tree. The root node of the
	 * changed tree should be returned.
	 */
	public BPTreeNode<TKey, TValue> insert(TKey key, TValue value) 
	{
            root = (BPTreeInnerNode<TKey, TValue>)(this); 
            BPTreeInnerNode<TKey, TValue> hold = (BPTreeInnerNode<TKey, TValue>)(root);
            BPTreeLeafNode<TKey, TValue> bottom;
            BPTreeNode<TKey, TValue> base;
            while(true) {
                int ifind=0;
                while(ifind<hold.keyTally) {
                    if(hold.getKey(ifind).equals(key))
                        return root;
                    if(hold.getKey(ifind).compareTo(key)<0)
                        ifind++;
                    else
                        break;
                }
                if(getChild(ifind).isLeaf()==true) {
                    bottom = (BPTreeLeafNode<TKey, TValue>)(getChild(ifind));
                    break;
                }
                else 
                    hold = (BPTreeInnerNode<TKey, TValue>)(hold.getChild(ifind));
            }            
//------------------------------------------------------------------------------
            if(bottom.keyTally==m) { //full
                FullSplit(key, value, bottom);
            }
//------------------------------------------------------------------------------            
            else {
                int ifind=0;
                while(ifind<bottom.keyTally)
                    if(bottom.getKey(ifind).compareTo(key)<0)
                        ifind++;
                    else 
                        break;
                
                for(int i=bottom.keyTally; i!=ifind; i--) {
                    bottom.setKey(i, bottom.getKey(i-1));
                    bottom.setValue(i, bottom.getValue(i-1));
                }
                bottom.keyTally++;
                bottom.setKey(ifind, key);
                bottom.setValue(ifind, value);
            }    
            return root;
	}
        
//==============================================================================

        public BPTreeNode<TKey, TValue> FullSplit(TKey key, TValue value, BPTreeLeafNode<TKey, TValue> child) {
            BPTreeInnerNode<TKey, TValue> par = (BPTreeInnerNode<TKey, TValue>)(child.parentNode);
                Object array[][];
                array = fillArrayInsert(key, value, child);
                //System.out.println(array[0][1]);
                int imid = middle(m+1);
                
                BPTreeLeafNode<TKey, TValue> left  = new BPTreeLeafNode<>(m), right = new BPTreeLeafNode<>(m);
                left.keyTally=imid-1;
                right.keyTally=m+2-imid;
                
                for(int i=0; i<imid-1; i++) {
                    left.setKey(i, (TKey)(array[i][0]));
                    left.setValue(i,(TValue)(array[i][1]));
                }
                for(int i=imid-1, j=0; i<m+1; i++, j++) {
                    right.setKey(j, (TKey)(array[i][0]));
                    right.setValue(j,(TValue)(array[i][1]));
                }
                left.parentNode = par;
                left.rightSibling = right;
                right.parentNode = par;
                right.leftSibling = left;
                left.leftSibling = child.leftSibling;
                if(left.leftSibling!=null)
                    left.leftSibling.rightSibling = left;
                right.rightSibling= child.rightSibling;
                if(right.rightSibling!=null)
                    right.rightSibling.leftSibling = right;
                
//------------------------------------------------------------------------------                
                if(par.keyTally==m) { //split parent, redist key, promote new seperator, reiterate until the top of the tree
                    int j=0;
                    while(par.getKey(j).compareTo(right.getKey(0))<0)
                        j++;
                    Object refer[] = new Object[m+3];
                    for(int i=0; i<j; i++)
                        refer[i] = par.getChild(i);
                    refer[j] = left;
                    refer[j+1] = right;
                    int i;
                    for(i=j, j=j+2; i<par.keyTally; j++, i++)
                        refer[j] = par.getChild(i);
//------------------------------------------------------------------------------                    
                    BPTreeInnerNode<TKey, TValue> node = SplitParent(right.getKey(0), par);
                    for(i=0; i<node.keyTally; i++) {
                        node.setChild(i, (BPTreeNode<TKey, TValue>)refer[i]);
                    }
                    
                    j= node.keyTally;
                    node = (BPTreeInnerNode<TKey, TValue>)node.rightSibling;
                    for(i=0; i<node.keyTally; j++, i++)
                        node.setChild(i, (BPTreeNode<TKey, TValue>)refer[j]);
                    return node.FindRoot();
                }      
                else { //add new seperator, shift keys, return
                    int ifind=0;
                    while(ifind<par.keyTally) {
                        if(par.getKey(ifind).equals(key)==false)
                            ifind++;
                    }
                    if(ifind==par.keyTally) {
                        par.keyTally++;
                        par.setKey(ifind, (TKey)(array[imid-1][0]));
                        par.setChild(ifind, left);
                        par.setChild(ifind+1, right);
                        return root;
                    }
                    else {
                        for(int i=par.keyTally; i!=ifind; i--) {
                            par.setKey(i, par.getKey(i-1));
                            par.references[i] = par.references[i-1];
                        }
                        par.setKey(ifind, key);
                        par.setChild(ifind, left);
                        left.rightSibling = right;
                        right.leftSibling = left;
                        par.setChild(ifind+1, right);
                        
                        if(ifind==par.keyTally-1) {
                            left.leftSibling = par.getChild(ifind-1);
                            par.getChild(ifind-1).rightSibling = left;
                        }
                        else if(ifind==0) {
                            right.rightSibling = par.getChild(ifind+2);
                            right.rightSibling.leftSibling = right;
                        }
                        else {
                            left.leftSibling = par.getChild(ifind-1);
                            left.leftSibling.rightSibling = left;
                            right.rightSibling = par.getChild(ifind+2);
                            right.rightSibling.leftSibling = left;
                        }
                    }
                }
                
                return par;
        }
//==============================================================================
        @Override
        public TValue search(TKey key) 
	{  
            BPTreeInnerNode<TKey, TValue> hold = this;
            BPTreeLeafNode<TKey, TValue> bottom=null;
            int ifind;
            while(hold.isLeaf()==false) {
                ifind=0;
                while(hold.getKey(ifind).compareTo(key)<0) {
                    ifind++;
                    if(ifind>hold.keyTally-1)
                        break;
                }
                if(getChild(ifind).isLeaf()==true) {
                        bottom = (BPTreeLeafNode<TKey, TValue>)(getChild(ifind));
                        break;
                    }
                    else 
                        hold = (BPTreeInnerNode<TKey, TValue>)(hold.getChild(ifind));
            }
            return bottom.search(key);
	}
//==============================================================================        
  
        public BPTreeNode<TKey, TValue> delete(TKey key) //find correct child
	{
            BPTreeInnerNode<TKey, TValue> hold = this;
            BPTreeLeafNode<TKey, TValue> bottom=null;
            int ifind;
            while(hold.isLeaf()==false) {
                ifind=0;
                while(hold.getKey(ifind).compareTo(key)<0)
                    if(ifind==hold.keyTally-1)
                        break;
                    else
                        ifind++;
                if(getChild(ifind).isLeaf()==true) {
                        bottom = (BPTreeLeafNode<TKey, TValue>)(getChild(ifind));
                        break;
                    }
                    else 
                        hold = (BPTreeInnerNode<TKey, TValue>)(hold.getChild(ifind));
            }
            return bottom.delete(key);   
	}
//==============================================================================

        public BPTreeInnerNode<TKey, TValue> SplitParent(TKey key, BPTreeInnerNode<TKey, TValue> par) {
            
            if(par==null ) {
                return null;
            }
            else if(par.keyTally!=m)
                return par;
            //------------------------------------------------------------------
                        int ifind=0;
                        while(ifind<par.keyTally) {
                            if(par.getKey(ifind).compareTo(key)<0)
                                ifind++;
                        }
                        if(par.parentNode==null) { //root split
                            BPTreeInnerNode<TKey, TValue> upmost = new BPTreeInnerNode<>(m);
                            Object uparray [] = InnerfillArrayInsert(key, par);
                            int imid = middle(m+1);
                
                            upmost.setKey(0, (TKey)(uparray[imid-1]));
                            BPTreeInnerNode<TKey, TValue> parleft  = new BPTreeInnerNode<>(m), parright = new BPTreeInnerNode<>(m);
                
                            for(int i=0; i<imid-1; i++) {
                                parleft.setKey(i, (TKey)(uparray[i]));
                            }
                            for(int i=imid-1, j=0; i<m+1; i++, j++) {
                                parright.setKey(j, (TKey)(uparray[i]));
                            }
                            
                            //--------------------------------------------------
                            upmost.setChild(0, parleft);
                            parleft.parentNode = par;
                            parleft.rightSibling = parright;
                            parright.parentNode = par;
                            parright.leftSibling = parleft;
                            upmost.setChild(1, parright);
                            parleft.keyTally=imid-1;
                            parright.keyTally=m+2 - imid;
                            upmost.keyTally=1;
                            
                            return parleft;
                        }
                        
                        else {  //split 
                            BPTreeInnerNode<TKey, TValue> left= new BPTreeInnerNode<>(m), right=new BPTreeInnerNode<>(m), child=par;
                            left.rightSibling = right;
                            right.leftSibling = left;
                            par = (BPTreeInnerNode<TKey, TValue>)par.parentNode;
                            int imid = middle(m+1);
                            Object[] keys = InnerfillArrayInsert(key, child);
                                    
                            for(int i=0; i<imid-1; i++) {
                                left.setKey(i, (TKey)(keys[i]));
                            }
                            for(int i=imid-1, j=0; i<m+1; i++, j++) {
                                right.setKey(j, (TKey)(keys[i]));
                            }
//------------------------------------------------------------------------------
                            if(par.parentNode.getKeyCount()==m) { //recurisvely call
                                int j=0;
                                    while(par.getKey(j).compareTo(right.getKey(0))<0)
                                        j++;
                                    Object refer[] = new Object[m+3];
                                    for(int i=0; i<j; i++)
                                        refer[i] = par.getChild(i);
                                refer[j] = left;
                                refer[j+1] = right;
                                int i;
                                for(i=j, j=j+2; i<par.keyTally; j++, i++)
                                    refer[j] = par.getChild(i);
//------------------------------------------------------------------------------                    
                            BPTreeInnerNode<TKey, TValue> node = SplitParent(right.getKey(0), par);
                                for(i=0; i<node.keyTally; i++) {
                                    node.setChild(i, (BPTreeNode<TKey, TValue>)refer[i]);
                                }
                                for(int l=0, o=node.keyTally-1; l<node.keyTally-1;l++, o--) {
                                    node.getChild(l).rightSibling=node.getChild(l+1);
                                    node.getChild(o).leftSibling = node.getChild(o-1);
                                }
                    
                                j= node.keyTally;
                                node = (BPTreeInnerNode<TKey, TValue>)node.rightSibling;
                                for(i=0; i<node.keyTally; j++, i++)
                                    node.setChild(i, (BPTreeNode<TKey, TValue>)refer[j]);
                                
                                for(int l=0, o=node.keyTally-1; l<node.keyTally-1;l++, o--) {
                                    node.getChild(l).rightSibling=node.getChild(l+1);
                                    node.getChild(o).leftSibling = node.getChild(o-1);
                                }
                                return left;
                                    //return node.FindRoot();
                            }
                            else { //fix links and return
                                int j=0;
                                while(par.getKey(j).compareTo(right.getKey(0))<0)
                                    j++;
                                Object refer[] = new Object[m+3];
                                for(int i=0; i<j; i++)
                                    refer[i] = par.getChild(i);
                                
                                for(int l=0, o=par.keyTally-1; l<par.keyTally-1;l++, o--) {
                                    par.getChild(l).rightSibling=par.getChild(l+1);
                                    par.getChild(o).leftSibling = par.getChild(o-1);
                                }
                                
                                refer[j] = left;
                                refer[j+1] = right;
                                int i;
                                for(i=j, j=j+2; i<par.keyTally; j++, i++)
                                    refer[j] = par.getChild(i);
                                
                                for(int l=0, o=par.keyTally-1; l<par.keyTally-1;l++, o--) {
                                    par.getChild(l).rightSibling=par.getChild(l+1);
                                    par.getChild(o).leftSibling = par.getChild(o-1);
                                }
                                
                                return left;
                            }
                            }
                }
}