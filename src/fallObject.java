import ecs100.UI;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class fallObject {
    private double X;
    private double Y;
    private final double squareSize = 40;
    /*static final double tickRate = Main.tickRateMS;
    final double ticksPerSecond = (double)1000/Main.tickRateMS;
    static final double gravity_Sec = 196;*/
    static final double gravityTick = 0.196;
    private double acceleration = gravityTick;

    private double velocity = 0;
    private effector collider;

    private ArrayList<fallObject> objectsVec;
    private double index;

    public fallObject(double initX, double initY, effector collider, ArrayList<fallObject> objectsVec){
        this.X = initX;
        this.Y = initY;
        this.collider = collider;
        this.objectsVec = objectsVec;
        this.index = this.objectsVec.indexOf(this);
        this.draw();
        final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(this::startPhys, 0, 10, TimeUnit.MILLISECONDS);
    }

    public void draw(){
        UI.drawRect(X, Y, squareSize, squareSize);
    }

    public void startPhys(){
        boolean checked = objectCollision();
        if (!checked) {
            if (collisionCheck()) {
                UI.eraseRect(X, Y, squareSize, squareSize);
                this.velocity = velocity + acceleration;
                this.Y = this.Y + velocity;
                this.draw();
                collider.draw();

            } else {
                UI.eraseOval(X, Y, squareSize, squareSize);
                this.Y = collider.getY() - squareSize;
                this.velocity = (this.velocity * -1) + this.velocity * 0.1;
                this.draw();
            }
        }
        /*else {
            this.Y -= 0.5;
        }*/
        UI.eraseRect(X, Y, squareSize, squareSize);
        this.draw();
        UI.println(this.velocity);
    }

    public boolean objectCollision(){
        for (fallObject obj: objectsVec){
            if (objectsVec.indexOf(obj) != objectsVec.indexOf(this)){
                double distance = distance_check(this.X, this.Y, obj.getX(), obj.getY());
                if (distance <= squareSize){
                    if (this.Y <= obj.getY() && !(Math.abs(obj.getVelocity()) < 1)){
                        this.setVelocity(Math.abs(obj.getVelocity())*-1);
                        obj.setVelocity(0);
                        UI.eraseRect(X, Y, squareSize, squareSize);
                        this.Y = obj.getY()-squareSize;
                        return true;
                    }
                    else if (this.Y <= obj.getY() && Math.abs(obj.getVelocity()) < 1){
                        UI.eraseRect(X, Y, squareSize, squareSize);
                        this.Y = obj.getY() - squareSize;
                        this.velocity = (this.velocity * -1) + this.velocity * 0.1;
                        return true;
                    }
                    /*else if (this.Y >= obj.getY()){
                        obj.setVelocity(-1*this.getVelocity());
                    }*/
                }

            }
        }
        return false;
    }

    public boolean collisionCheck(){
        return this.Y + velocity+squareSize <= collider.getY();
    }

    public double distance_check(double x1, double y1, double x2, double y2){
        return Math.sqrt(Math.pow(x1-x2, 2)+Math.pow(y1-y2, 2));
    }

    public double getX() {
        return X;
    }

    public double getY() {
        return Y;
    }

    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }

    public double getVelocity() {
        return velocity;
    }

    public void setY(double y) {
        Y = y;
    }
}
