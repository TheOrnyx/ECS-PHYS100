import ecs100.UI;

public class fallObject {
    private double X;
    private double Y;
    private final double squareSize = 40;
    static final double tickRate = Main.tickRateMS;
    final double ticksPerSecond = (double)1000/Main.tickRateMS;
    static final double gravity_Sec = 196;
    static final double gravityTick = 0.196;
    private double acceleration = gravityTick;

    private double velocity = 0;
    private effector collider;

    public fallObject(double initX, double initY, effector collider){
        this.X = initX;
        this.Y = initY;
        this.collider = collider;
    }

    public void draw(){
        UI.drawRect(X, Y, squareSize, squareSize);
    }

    public void startPhys(){
        if (collisionCheck()) {
            UI.eraseRect(X, Y, squareSize, squareSize);
            this.velocity = velocity+acceleration;
            this.Y = this.Y + velocity;
            this.draw();
            collider.draw();
        }
        else{
            UI.eraseOval(X, Y, squareSize, squareSize);
            this.Y = collider.getY()-squareSize;
            this.velocity = (this.velocity*-1)+this.velocity*0.1;
            this.draw();
        }
    }

    public boolean collisionCheck(){
        return this.Y + velocity+squareSize <= collider.getY();
    }


}
