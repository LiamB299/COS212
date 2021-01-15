/*
Complete your details...
Name and Surname: 
Student/staff Number:
*/

public class DoubleThreadedDSW<T extends Comparable<? super T>> extends DoubleThreadedBST<T>
{
	/*
	TODO: The DoubleThreadedDSW class inherits from the 
	DoubleThreadedBST class. A lot of the functionality 
	required for an DSW tree will be handled in your 
	DoubleThreadedBST class. You will have to override all 
	appropriate methods inherited from DoubleThreadedBST 
	in order to create a functional tree balanced by the
	DSW algorithm.
	
	You must add any additional methods or data fields which 
	you might need to accomplish your task.
	*/
	
	public DoubleThreadedDSW()
	{
		/*
		The default constructor.
		*/
	}
	
	public void balance()
	{
		/*
		You must implement the DSW algorithm in order to
		balance this tree. Be sure to perform any updates
		that are required for any of the threads in the tree.
		*/
            
			if(this.getRoot==null)
				return;
			
            this.backbone();
            
            int num = this.getNumberOfNodes()+1;
            int flr = 0, m=1;
            while(num!=0 && num!=1) {
                num /=2;
                flr++;
            }
            for(int i=0; i<flr; i++) {
                m *=2;
            }
            m--;
            num = this.getNumberOfNodes();
            
            int iter = num - m;
            DTNode<T> hold=this.getRoot().right;
            //System.out.println(hold.data);
            if(iter>0)
                rotLeft(hold);
            iter--;
            for(int i= iter-1; i>=0; i--) {
                
                hold=hold.right.right;
                //System.out.println(hold.data);
                rotLeft(hold);
            }
            
            while(m>1) {
                m = m/2;
               // System.out.println("\nM="+m);
                hold=this.getRoot().right;
                if(m>0) {
                   // System.out.println(hold.data);
                    rotLeft(hold);
                }
                    
                for(int i= m-1; i>0; i--) {
                    hold = hold.right.right;
                  //  System.out.println(hold.data);
                    rotLeft(hold);   
            }
                //left rotate about every second
            }
	}
        
        public void backbone() {
            DTNode<T> hold = this.getRoot(), child=null;
            while(hold !=null) {
                //System.out.println(hold.data);
                if(hold.hasLeftThread==false && hold.left!=null) {
                    child = hold.left;
                    
                    //child.WhereIs();
                    rotRight(child);
                    //child.WhereIs();
                    hold=child;
                }
                else
                    hold=hold.right;
            }
            //System.out.println(this.inorderReverseDetailed());
        }
        
        public void rotRight(DTNode<T> node) {
            
           // System.out.println("Rotating about: "+ node.data);
        
            DTNode<T> hold, gr, par;
            
            if(node == null)
                return;
            
            if(node.up.right==node)
                return;
            
//==============================================================================//rotation about root
//==============================================================================//par is root 

            if(node.up==this.getRoot()) {
                this.setRoot(node);
                    
                    if(node.hasRightThread==false)
                        hold = node.right;
                    else
                        hold=null;
                    
                    par= node.up;
                    node.right = par;
                    node.up = null;
                    par.up = node;
                    par.left = null;
                    
                    
                    
                    if(hold==null) {
                        thleft(par);
                        node.hasRightThread=false;
                    }
                    else {
                        par.left = hold;
                        hold.up = par;
                    }
                    
                    
             //       node.WhereIs();
                    
                    
            }
            
//==============================================================================//general

            else {
                par = node.up;
                gr = node.up.up;
                
                //System.out.println("Grand "+gr.data);
                //System.out.println("Par "+par.data);
                //System.out.println("Child "+node.data);
                
                if(node.hasRightThread==false && node.right!=null)
                    hold=node.right;
                else hold=null;
                
                if(gr.right==par)
                    gr.right=node;
                else
                    gr.left=node;
                node.up = gr;
                
                node.right = par;
                par.up = node;
                
                par.left = null;
                
                if(hold==null) {
                    thleft(par);
                    node.hasRightThread=false;
                }
                else {
                    par.left = hold;
                    hold.up = par;
                }
                    
            }     
        }
        
        public void rotLeft(DTNode<T> node) {
            DTNode<T> hold, gr, par;
            
            if(node == null)
                return;
            
            if(node.up.left==node)
                return;
            
            
//==============================================================================//rotation about root
//==============================================================================//par is root 

            
            if(node.up==this.getRoot()) {
                this.setRoot(node);
                par = node.up;
                    
                if(node.hasLeftThread==false)
                    hold = node.left;
                else
                    hold=null;
                    
                node.left = par;
                node.up = null;
                par.up = node;
                par.right = null;
                    
                if(hold==null) {
                    thright(par);
                    node.hasLeftThread=false;
                }
                else {
                    hold.up = par;
                    par.right = hold;
                } 
            }   
//==============================================================================//general
            else {
                par = node.up;
                gr = node.up.up;
                
                if(node.hasLeftThread==false && node.left!=null)
                    hold=node.left;
                else hold=null;
                
                if(gr.left==par)
                    gr.left=node;
                else
                    gr.right=node;
                node.up = gr;
                
                node.left = par;
                par.up = node;
                par.right = null;
                
                if(hold==null) {
                    thright(par);
                    node.hasLeftThread=false;
                }
                else {
                    par.right = hold;
                    hold.up = par;
                }
            }    
        }     
}
