public class Main {
	

    public static void main(String[] args) 
    {
	BTree<Integer> tree = new BTree<Integer>(2); // A B-Tree with order 4 (2*m)
        Integer value;
   /*
	tree.insert(20);
	tree.insert(10);
	tree.insert(30);
	tree.insert(50);
	tree.insert(40);
	tree.insert(60);
	tree.insert(90);
	tree.insert(70);
	tree.insert(80);

	System.out.println("Structure of the constucted tree is : ");
	tree.print();

	Integer value = 70;
	tree.delete(value);
	System.out.println("Structure of the tree after delete of : " + value);
	tree.print();

	value = 60;
	tree.delete(value);
	System.out.println("Structure of the tree after delete of : " + value);
	tree.print();

	value = 50;
	tree.delete(value);
	System.out.println("Structure of the tree after delete of : " + value);
	tree.print();
        
        value = 90;
	tree.delete(value);
	System.out.println("Structure of the tree after delete of : " + value);
	tree.print();
        
        value = 40;
	tree.delete(value);
	System.out.println("Structure of the tree after delete of : " + value);
	tree.print();
        
        tree.delete(40);
        
        value = 30;
	tree.delete(value);
	System.out.println("Structure of the tree after delete of : " + value);
	tree.print();
        
        value = 10;
	tree.delete(value);
	System.out.println("Structure of the tree after delete of : " + value);
	tree.print();
        
        value = 20;
	tree.delete(value);
	System.out.println("Structure of the tree after delete of : " + value);
	tree.print();
        
        value = 50;
	tree.delete(value);
	System.out.println("Structure of the tree after delete of : " + value);
	tree.print();
        
        value = 80;
	tree.delete(value);
	System.out.println("Structure of the tree after delete of : " + value);
	tree.print();

	/* Expected Output:
	Structure of the constucted tree is : 
Level 1 [ 40]
Level 2 [ 20]
Level 3 [ 10]
Level 3 [ 30]
Level 2 [ 60]
Level 3 [ 50]
Level 3 [ 70 80 90]

Structure of the tree after delete of : 70
Level 1 [ 20 40 60]
Level 2 [ 10]
Level 2 [ 30]
Level 2 [ 50]
Level 2 [ 80 90]

Structure of the tree after delete of : 60
Level 1 [ 20 40 80]
Level 2 [ 10]
Level 2 [ 30]
Level 2 [ 50]
Level 2 [ 90]

Structure of the tree after delete of : 50
Level 1 [ 20 40]
Level 2 [ 10]
Level 2 [ 30]
Level 2 [ 80 90]

Structure of the tree after delete of : 90
Level 1 [ 20 40]
Level 2 [ 10]
Level 2 [ 30]
Level 2 [ 80]

Structure of the tree after delete of : 40
Level 1 [ 20]
Level 2 [ 10]
Level 2 [ 30 80]

Structure of the tree after delete of : 30
Level 1 [ 20]
Level 2 [ 10]
Level 2 [ 80]

Structure of the tree after delete of : 10
Level 1 [ 20 80]

Structure of the tree after delete of : 20
Level 1 [ 80]

Structure of the tree after delete of : 50
Level 1 [ 80]

Structure of the tree after delete of : 80
	*/
   /*
        
        System.out.println("Tree 2:");
        tree.insert(150);
	tree.insert(12);
	tree.insert(456);
	tree.insert(65);
	tree.insert(72);
	tree.insert(53);
	tree.insert(324);
	tree.insert(234);
	tree.insert(80);
        tree.insert(33);
	tree.insert(67);
	tree.insert(87);
	tree.insert(101);
	tree.insert(146);
	tree.insert(98);
	tree.insert(177);
	tree.insert(267);
	tree.insert(328);
        tree.insert(6);
	tree.insert(7);
	tree.insert(1);
	tree.insert(44);
	tree.insert(165);
	tree.insert(201);
	tree.insert(82);
	tree.insert(213);
	tree.insert(66);
        tree.insert(89);
        tree.insert(265);
        tree.insert(312);
        tree.insert(187);
        
        System.out.println("Structure of the constucted tree is : ");
	tree.print();
        
        value = 213;
	tree.delete(value);
        //tree.root.references[3].Children();
	System.out.println("Structure of the tree after delete of : " + value);
	tree.print();
        
        value = 44;
	tree.delete(value);
	System.out.println("Structure of the tree after delete of : " + value);
	tree.print();
        
        value = 234;
	tree.delete(value);
	System.out.println("Structure of the tree after delete of : " + value);
	tree.print();
        
        value = 146;
	tree.delete(value);
	System.out.println("Structure of the tree after delete of : " + value);
	tree.print();
        
        value = 177;
	tree.delete(value);
	System.out.println("Structure of the tree after delete of : " + value);
	tree.print();

        
        value = 80;
	tree.delete(value);
	System.out.println("Structure of the tree after delete of : " + value);
	tree.print();
*/
   /*
   for(int i=1; i<26; i++)
       tree.insert(i);
   tree.print();
   
   for(int i=30; i>5; i--) {
       System.out.println("Structure of the tree after delete of : " + i);
       tree.delete(i);
       tree.print();
   }
   
   System.out.println("Structure of the tree after delete of : " + 5);
       tree.delete(5);
       tree.print();
       
       System.out.println("Structure of the tree after delete of : " + 4);
       tree.delete(4);
       tree.print();
       
       System.out.println("Structure of the tree after delete of : " + 3);
       tree.delete(3);
       tree.print();
       
       System.out.println("Structure of the tree after delete of : " + 3);
       tree.delete(3);
       tree.print();
       
       System.out.println("Structure of the tree after delete of : " + 2);
       tree.delete(2);
       tree.print();
       
       System.out.println("Structure of the tree after delete of : " + 1);
       tree.delete(1);
       tree.print();
       
       System.out.println("Structure of the tree after delete of : " + 0);
       tree.delete(0);
       tree.print();
        
     */
   
    tree.insert(20);
    tree.insert(10);
    tree.insert(30);
    tree.insert(50);
    tree.insert(40);
    tree.insert(60);
    tree.insert(90);
    tree.insert(70);
    tree.insert(80);
    tree.insert(31);
    tree.insert(21);
    tree.insert(22);
    tree.insert(23);
    tree.insert(24);
    tree.insert(25);
    tree.insert(26);
    tree.insert(11);
    tree.insert(12);
    tree.insert(13);
    tree.insert(14);
    tree.insert(15);
    tree.insert(16);
    tree.insert(17);
    tree.insert(18);
    
    System.out.println("Structure of the constucted tree is : ");
	tree.print();

	value = 30;//this should be 70
	tree.delete(value);
	System.out.println("Structure of the tree after delete of : " + value);
	tree.print();

	value = 50;
	tree.delete(value);
	System.out.println("Structure of the tree after delete of : " + value);
	tree.print();

	value = 15;
	tree.delete(value);
	System.out.println("Structure of the tree after delete of : " + value);
	tree.print();

	value = 90;
	tree.delete(value);
	System.out.println("Structure of the tree after delete of : " + value);
	tree.print();

	value = 80;
	tree.delete(value);
	System.out.println("Structure of the tree after delete of : " + value);
	tree.print();
        
        value = 14;
	tree.delete(value);
	System.out.println("Structure of the tree after delete of : " + value);
	tree.print();
        
        value = 11;
	tree.delete(value);
	System.out.println("Structure of the tree after delete of : " + value);
	tree.print();
        
        value = 13;
	tree.delete(value);
	System.out.println("Structure of the tree after delete of : " + value);
	tree.print();
        
        value = 10;
	tree.delete(value);
	System.out.println("Structure of the tree after delete of : " + value);
	tree.print();
        
        value = 70;
	tree.delete(value);
	System.out.println("Structure of the tree after delete of : " + value);
	tree.print();
        
        value = 40;
	tree.delete(value);
	System.out.println("Structure of the tree after delete of : " + value);
	tree.print();
        
        value = 21;
	tree.delete(value);
	System.out.println("Structure of the tree after delete of : " + value);
	tree.print();
        
        value = 16;
	tree.delete(value);
	System.out.println("Structure of the tree after delete of : " + value);
	tree.print();
        /*
        value = 60;
	tree.delete(value);
	System.out.println("Structure of the tree after delete of : " + value);
	tree.print();
        
        value = 22;
	tree.delete(value);
	System.out.println("Structure of the tree after delete of : " + value);
	tree.print();
        
        value = 24;
	tree.delete(value);
	System.out.println("Structure of the tree after delete of : " + value);
	tree.print();
        /*
        value = 13;
	tree.delete(value);
	System.out.println("Structure of the tree after delete of : " + value);
	tree.print();
        
        value = 13;
	tree.delete(value);
	System.out.println("Structure of the tree after delete of : " + value);
	tree.print();
        
        value = 13;
	tree.delete(value);
	System.out.println("Structure of the tree after delete of : " + value);
	tree.print();
        
        value = 13;
	tree.delete(value);
	System.out.println("Structure of the tree after delete of : " + value);
	tree.print();
        */
        

    }


    
}