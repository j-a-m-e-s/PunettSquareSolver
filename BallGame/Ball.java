
public class Ball {
	
	private double radius;  //Radius of Ball
	private double[] position = new double[2];//Position of Ball
	private double[] velocity = new double[2];//Speed or direction of Ball
	private int Ball_State; // Color/state/type of the ball
	public static final int RED_BALL = 0; //Red ball type
	public static final int GREEN_BALL = 1;// green ball type
	public static final int BLUE_BALL = 2;// blue ball type
	
	//Constructor: Creates new object Ball
	Ball(double[] newPos, double[] newVelocity, double currentRadius, int state){
		position[0] = newPos[0];
		position[1] = newPos[1];
		velocity[0] = newVelocity[0];
		velocity[1] = newVelocity[1];
		radius = currentRadius;
		Ball_State = state;
		
	}
	
	//Method for moving the Ball over one time unit
	public void move()
	{
		position[0] += velocity[0];
		position[1] += velocity[1];
	}
	//Logic method for changing the direction Ball is moving
	public void changeDirection(double[] newVelocity){
		
	}
	// Sets the new x and y position of Ball
	public void set(double[] newPos){
		
	}
	// Gets the current x and y position of Ball
	public void get(double[] currentPos){
		
	}
	//Logic method for Ball and Wall collision
	public void collision(Wall wall0){
		
	}
	// Logic method for Ball and Ball collision;
	public void collision(Ball ball0){
		
	}
	// Logic method for Ball and Barrier collision
	public void collision(Barrier barrier0){
		
	}
	// Displays Ball object
	public void display() {
	
	}
	}
	

