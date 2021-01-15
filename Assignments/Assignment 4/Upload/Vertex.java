/**
 * Name: Liam Burgess
 * Student Number: 18015001
 */

public class Vertex {
   
    public Coordinates coords;
    Vertex [] Neighbors = new Vertex[0];
    boolean u = false;
    boolean d = false;
    boolean b = false;
    
    public Vertex(int lev, int r, int c) {
        coords = new Coordinates(lev, r, c);
    }
    
    public void AddNeighbor(Vertex v) {
        Vertex hold[] = new Vertex[Neighbors.length+1];
        System.arraycopy(Neighbors, 0, hold, 0, Neighbors.length);
        hold[Neighbors.length] = v; 
    }
    
    public Vertex[] getAdj() {
        return Neighbors;
    }
    
    public Vertex RetUp() {
        if(u==false)
            return null;
        
        for(int i=0; i<Neighbors.length; i++) {
            if(Neighbors[i].coords.RetEle(0)>this.coords.level)
                return Neighbors[i];
        }
        return null;
    }
    
    public Vertex RetDown() {
        if(d==false)
            return null;
        
        for(int i=0; i<Neighbors.length; i++) {
            if(Neighbors[i].coords.RetEle(0)<this.coords.level)
                return Neighbors[i];
        }
        return null;
    }
    
    /*
        1 = U
        2 = D
        3 = B 
    */
    public void setLadder(int iset) { 
        switch(iset) {
            case 1: u=true;
            break;
            case 2: d=true;
            break;
            case 3: {
                u=true;
                b=true;
                d=true;
            }
            break;
        }
    }
    
    public boolean GetLadder(int i) {
        switch(i) {
            case 1: return u;
            case 2: return b;
            default: return d;
        }
    }
    
    public String toString() {
        String sline;
        sline = ("Level: "+(this.coords.RetEle(1)+1)+" Row: "+(this.coords.RetEle(2)+1)+" Colum: "+ (this.coords.RetEle(3)+1));
        return sline;
    }
    
}
