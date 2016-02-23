
public class Barrier {
	double[] position = new double[2];// position of Barrier: x is position[0] and y is position[1]
	double radius; //value for radius of Barrier
    private int Barrier_State;//declares for Type of Barrier
    public static final int GREEN_BARRIER = 0;// Value for Barrier type green
    public static final int RED_BARRIER = 1;// value for Barrier type Red
    public static final int GOAL_BARRIER = 2;// Value for Barrier type blue
    
    
    // Constructor: Creates new object Barrier
    Barrier(double[] newPos, double newRadius, int state) {
    	position[0] = newPos[0]; //sets the position x for the created object Barrier
    	position[1] = newPos[1];// sets the position y for the created object Barrier
    	radius = newRadius;  // sets the radius for the created object Barrier
    	Barrier_State = state; //sets the State / type for the created object Barrier
    }
}
