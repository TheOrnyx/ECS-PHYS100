import ecs100.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
public class effector {
    private double colliderY;
    public effector(double initY){
        this.colliderY = initY;
        this.draw();
    }

    public void draw(){
        UI.drawLine(10, colliderY, 800, colliderY);
    }

    public double getY(){
        return colliderY;
    }
}
