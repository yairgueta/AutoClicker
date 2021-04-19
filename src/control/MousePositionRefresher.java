package control;

public class MousePositionRefresher extends Thread {
    private static final long refreshingFreq = 50L; // in ms
    private final AutoClickerController controller;

    public MousePositionRefresher(AutoClickerController controller){
        super();
        this.controller = controller;
    }

    @Override
    public void run(){
        while (true){
            controller.refreshMousePosition();
            try {
                Thread.sleep(refreshingFreq);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
