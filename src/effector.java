import ecs100.*;

import java.awt.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
public class effector {
    public double colliderY;
    public effector(double initY){
        this.colliderY = initY;
        this.draw();
    }

    public void draw(){
        UI.setColor(Color.black);
        UI.fillRect(10, colliderY, 800, 900);
    }

    public double getY(){
        return colliderY;
    }

    public void sliderListen(double slide){
        UI.eraseRect(10, colliderY, 800, 900);
        this.colliderY = slide;
    }
}
