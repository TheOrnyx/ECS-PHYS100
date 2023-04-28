import ecs100.UI;
import ecs100.UISliderListener;

public class mouseListener {
    private effector collider;
    private Main man;

    public void mouseInit(effector collider, Main man){
        this.collider = collider;
        this.man = man;
    }
    public void mousePosition(String action, double x, double y) {
        if (action.equals("pressed")){
            man.start(x, y);
        }
    }

}
