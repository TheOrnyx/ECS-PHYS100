import ecs100.UI;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.Random;

public class Main {

    public static final Random random = new Random();

    static final long tickRateMS = 10;
    public void setupGUI(){
        UI.initialise();
        UI.setWindowSize(1000, 700);
        UI.addButton("regretful", this::start);

    }

    public void start(){
        effector colliisionObj = new effector(500);
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