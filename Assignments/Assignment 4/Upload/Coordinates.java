/**
 * Name: Liam Burgess
 * Student Number: 18015001
 */

public class Coordinates {

    public Integer level;
    public Integer row;
    public Integer col;

    public Coordinates(Integer lev, Integer r, Integer c) {
        level = lev;
        row = r;
        col = c;
    }
    
    public void SetCo(Integer lev, Integer r, Integer c) {
        level = lev;
        row = r;
        col = c;
    }
    
    public Integer RetEle(int i) {
        switch(i) {
            case 1: return level;
            case 2: return row;
            case 3: return col;
        }
        return 0;
    }

}