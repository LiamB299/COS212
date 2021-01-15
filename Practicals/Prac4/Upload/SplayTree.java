/**
 * Name: Liam Burgess
 * 
 * Student number: u18015001
 */

public class SplayTree<T extends Comparable<T>> {

    public TreeNode<T> root;

    public SplayTree() {
        root = null;
    }

    /**
     * Returns true if the key exists in the tree, otherwise false
     * @param key
     * @return 
     */
    public Boolean contains(T key) {
        TreeNode<T> hold = root;

        while (hold != null) {

            if (hold.key.equals(key)) {
                break;
            }

            if (hold.key.compareTo(key) > 0) {
                hold = hold.left;
            } else {
                hold = hold.right;
            }
        }
        if (hold == null) {
            return false;
        }

        if (hold.key.equals(key))
            return true;
        
        return false;
    }

    /**
     * Insert the given key into the tree.
     * Duplicate keys should be ignored.
     * No Splaying should take place.
     */
    public void insert(T key) {
        /*
        if(root==null) {
            root = new TreeNode<T>(key);
            return;
        }
        
        if (contains(key)==true)
            return;
        
        TreeNode hold=root;
        while(true) {
            if(hold.key.compareTo(key)<0) { //left minues right
                if(hold.right==null) {
                    hold.right=new TreeNode<T>(key);
                    return;
                }
                else {
                    hold = hold.right;
                    continue;
                }
            }
            else {
                if(hold.left==null) {
                    hold.left =  new TreeNode<T>(key);
                    return;
                }
                else {
                    hold = hold.left;
                }
            }
        }*/
        
        TreeNode<T> p = root, prev =null;
        while(p!=null) {
            prev = p;
            
            if(p.key.equals(key))
                return;
            
            if(p.key.compareTo(key)<0)
                p = p.right;
            else
                p = p.left;
        }
        if(root==null) {
            root = new TreeNode<>(key);
        }
        else if(prev.key.compareTo(key)<0) {
            prev.right = new TreeNode<>(key);
            prev.right.parent = prev;
        }
        else {
            prev.left = new TreeNode<>(key);
            prev.left.parent = prev;
        }
    }

    /**
     * Return the successor of the given key.
     * If the given key does not exist return null.
     * If the given key does not have a successor, return null.
     */
    public T findSuccessor(T key) {
        TreeNode<T> hold = root;
      
        while(hold!=null) {
            
            if(hold.key.equals(key))
                break;
            
            if(hold.key.compareTo(key)>0)
                hold = hold.left;
            else
                hold = hold.right;
        }
        if(hold==null)
            return null;
        
        TreeNode maxNode = root;
        while (maxNode.right != null)
        {
            maxNode = maxNode.right;
            }
        if (maxNode.key.equals(hold.key))
            return null;
        
        if(hold.key.equals(key)) {
            if(hold.right==null) {
                return hold.parent.key;
            }
            if(hold.right.left==null)
                return hold.right.key;
            else {
                TreeNode<T> find = hold.right.left;
                while(find.left!=null) {
                    find = find.left;
                }
                return find.key;
            }
        }
        return null;
    }
//==============================================================================
    /**
     * Move the accessed key closer to the root using the semi-splaying strategy.
     * If the key does not exist, insert it without splaying
     */
    public void access(T key) {
        
        if(root==null)
            return;
        
        TreeNode<T> hold = root;
        
        while(hold!=null) {
            if(hold.key.equals(key)) {
                semisplay(hold);
                return;
            
            }
            
            if(hold.key.compareTo(key)>0)
                hold = hold.left;
            else
                hold = hold.right;
        }
        }
        
        /*
        
        if(root.key==key) { //root access no change
            return;
        }
        
        TreeNode hold;
        
        if(root.left.key.equals(key)) {        //parent is root access- shift to root
                hold = root.left;
                root.left = hold.right;
                hold.right = root;
                root = hold;
                return;
        }
        else if(root.right.equals(key)) {
                hold = root.right;
                root.right = hold.left;
                hold.left = root;
                root = hold;
                return;
        }
//==============================================================================  

        TreeNode great, gran, par, child;
        great=root;
        
        if(great.key.compareTo(key)>0) { //go left
            if(great.left==null) {
                return;
            }
            else
                gran = great.left;
        }
        else {
            if(great.right==null)
                return;
            else
                gran = great.right;
        }
        
//==============================================================================

        if(gran.left.key.equals(key)) {
                hold = root.left;
                root.left = hold.right;
                hold.right = root;
                root = hold;
                return;
        }
        if(gran.right.key.equals(key)) {
                hold = root.right;
                root.right = hold.left;
                hold.left = root;
                root = hold;
                return;
        }
        
        
        if(gran.key.compareTo(key)>0) {
            if(gran.left==null)
                return;
            else 
                par = gran.left;
        }
        else {
            if(gran.right==null) {
                return;
            }
            else
                par = gran.right;
        }
        
        if(par.key.equals(key)) {
                if(gran.right==par) { //left-right
                    if(great.left==par) {
                        great.left = par;
                        gran.right = par.left;
                        par.left = gran;
                    }
                    else {
                        great.right = gran.left;
                        gran.left = great;
                        root = gran;
                    }
                        return;
                    }
        }        
                
        if(par.key.compareTo(key)>0) {
            if(par.left==null)
                return;
            else 
                child = par.left;
        }
        else {
            if(par.right==null) {
                return;
            }
            else
                child = par.right;
        }        
                
                
        
        
//==============================================================================  

        while(true) {
            if(child==null) { 
                return;
            }
            if(child.key.equals(key)) {
                if((child==par.left && par==gran.left)) {  //homogenous left-left
                    gran.left = par.right;
                    par.right = gran;
                    if(great.left==gran) 
                        great.left = par;
                    else
                        great.right = par; 
                }
                else if(child==par.right && par==gran.right) { //homogenous right-right
                    gran.right = par.left;
                    par.left = gran;
                    if(great.right==gran) 
                        great.right = par;
                    else
                        great.left = par;
                }
                else { //heterogenous
                    if(child==par.right && par==gran.left) {
                        par.right = child.left;
                        child.left = par;
                        if(gran.left==par) 
                            gran.left = child;
                        else
                            gran.right = child;
                    }
                    else if(child==par.left && par==gran.right) {
                        par.left = child.right;
                        child.right = par;
                        if(gran.right==par) 
                            gran.right = child;
                        else
                            gran.left = child;
                    }
                    
                }
                return;
            } //if
            great = gran;
            gran = par;
            par = child;
        if(child.key.compareTo(par)<0) //key is greater
            child=child.right;
        else
            child = child.left;
        }//while */
    
    public void conRot(TreeNode<T> gr, TreeNode<T> par, TreeNode<T> ch, TreeNode<T> desc) {
        if(gr!=null) {
            if(gr.right == ch.parent)
                gr.right = ch;
            else gr.left = ch;
        }
        else root =ch;
        if(desc!= null) {
            desc.parent = par;
        }
        par.parent = ch;
        ch.parent = gr;
    }
    
    public void rotR(TreeNode<T> p) {
        p.parent.left = p.right;
        p.right = p.parent;
        conRot(p.parent.parent, p.right , p, p.right.left);
    }
    
    public void rotL(TreeNode<T> p) {
        p.parent.right = p.left;
        p.left = p.parent;
        conRot(p.parent.parent, p.left, p, p.left.right);
    }
    
    private void semisplay(TreeNode<T> p) {
        while(p!=root) {
            if(p.parent.parent==null) {
                if(p.parent.left==p) {
                    rotR(p);
                }
                else rotL(p);
            }
            else if(p.parent.left==p) {
                if(p.parent.parent.left==p.parent) {
                    rotR(p.parent);
                    p = p.parent;
                }
                else {
                    rotR(p);
                    rotL(p);
                }
                
            }
            else {
                if(p.parent.parent.right ==p.parent) {
                    rotL(p.parent);
                    p = p.parent;
                }
                else {
                    rotL(p);
                    rotR(p);
                }
                if(root==null)
                    root=p;
            }
        }
    }
    
//==============================================================================    
    /*public boolean nextCon(T Key, TreeNode hold) {
        if(hold==null) {
            return false;
        }
        if(hold.key==Key) {
            return true;
        }

        boolean b1 = nextCon(Key, hold.left);
        boolean b2 = nextCon(Key, hold.right);
        if(b1==true || b2==true) {
            return true;
        }
        else
            return false;
    }
    
    /*public T nextSucc(T key, TreeNode<T> hold) {
        if(hold==null) {
            return null;
        }
        if(hold.key==key) {
            if(hold.right==null) {
                return null;
            }
            if(hold.right.left==null)
                return hold.right.key;
            else {
                TreeNode<T> find = hold.right.left;
                while(find.left!=null) {
                    find = find.left;
                }
                return find.key;
            }
        }

        T b1 = nextSucc(key, hold.left);
        T b2 = nextSucc(key, hold.right);
        
        if(b1!=null) 
            return b1;
        else if(b1!=null) 
            return b2;
        else
            return null;
    }*/
}