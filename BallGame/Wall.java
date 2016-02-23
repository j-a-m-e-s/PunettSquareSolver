
public class Wall {
	double[] start = new double[2];// First anchor point for Wall x position is start[0] y position is start[1] (the walls are lines)
	double[] end = new double[2];// Second anchor point for Wall, x position is end[0] y position is end[1]
    private int Wall_State; //declares the type/color the of the Wall
    public static final int RED_WALL = 0;// value for Wall state/type red
    public static final int GREEN_WALL = 1;// value for Wall state/type green
    
    //Construcor: Create new Wall object
    Wall(double[] newStart,double[] newEnd, int state)
    {
    	start[0] = newStart[0];// sets first anchor position x for created object Wall
    	start[1] = newStart[1];// sets first anchor position y for created object Wall
    	end[0] = newEnd[0];// sets second anchor position x for created object Wall
    	end[1] = newEnd[1];// sets second anchor position y for created object Wall
    	Wall_State = state; // sets state/type of Wall for created object Wall
    }
}
