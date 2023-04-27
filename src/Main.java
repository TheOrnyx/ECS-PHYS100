import ecs100.UI;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.Random;

public class Main {

    public static final Random random = new Random();
    public effector colliisionObj = new effector(500);

    static final long tickRateMS = 10;
    public void setupGUI(){
        UI.initialise();
        UI.setWindowSize(1000, 800);
        UI.addButton("regretful", this::start);
        UI.addSlider("change collider", 100, 700, colliisionObj.getY() ,(double v) -> colliisionObj.sliderListen(v));
    }

    public void start(){
        fallObject johnathan = new fallObject(random.nextDouble(600)+20, 40, colliisionObj);
        mouseListener mouse = new mouseListener();
        mouse.mouseInit(colliisionObj);
        final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(johnathan::startPhys, 0, 10, TimeUnit.MILLISECONDS);
        johnathan.draw();
        johnathan.startPhys();
    }
    public static void main(String[] args) {

        Main main = new Main();
        main.setupGUI();
    }



}