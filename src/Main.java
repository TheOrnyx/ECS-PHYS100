import ecs100.UI;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.Random;

public class Main {

    public static final Random random = new Random();
    public static effector colliisionObj = new effector(500);
    private static Main main = new Main();
    private static mouseListener mouse = new mouseListener();


    static final long tickRateMS = 10;
    public void setupGUI(){
        UI.initialise();
        UI.setWindowSize(1000, 800);
        UI.addSlider("change collider", 100, 700, colliisionObj.getY() ,(double v) -> colliisionObj.sliderListen(v));
        UI.setMouseListener(mouse::mousePosition);
    }

    public void start(double x, double y){
        fallObject johnathan = new fallObject(x, y, colliisionObj);
        final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(johnathan::startPhys, 0, 10, TimeUnit.MILLISECONDS);
        johnathan.draw();
        johnathan.startPhys();
    }
    public static void main(String[] args) {
        mouse.mouseInit(colliisionObj, main);
        main.setupGUI();
    }



}