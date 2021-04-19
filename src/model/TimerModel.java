package model;

import java.util.concurrent.Callable;

public class TimerModel {
    public static final int MIN_TO_SEC = 60;
    public static String secToMin(int sec){
        int div, mod;
        div = sec / 60;
        mod = sec % 60;
        return String.format("%02d:%02d", div, mod);
    }


    private final int initialWait;
    private final int interval;
    private final Runnable functionToRun;
    private Runnable onTick = null;

    private Thread timerThread;
    private int remaining;
    private boolean isStarted;
    private boolean isStopped;
    private boolean isPaused;

    public TimerModel(int initialWait, int interval, Runnable functionToRun){
        this.initialWait = initialWait;
        this.interval = interval;
        this.functionToRun = functionToRun;

        this.remaining = this.initialWait;
        this.timerThread = new Thread(this::startTimerLoop);

        this.isStarted = false;
        this.isPaused = false;
        this.isStopped = false;
    }

    private void startTimerLoop(){
        while (true){
            if (onTick!= null) onTick.run();
            if (remaining == 0) {
                remaining = interval;
                functionToRun.run();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                // TODO: What to do when exception happens?
            }
            if (!isStopped && !isPaused) remaining--;
        }
    }

    public void setOnTickAction(Runnable onTick){
        this.onTick = onTick;
    }

    public void start() throws TimerStartTwiceException {
        if (isStarted){
            throw new TimerStartTwiceException();
        }

        this.isStarted = true;
        this.isPaused = false;
        this.isStopped = false;
        timerThread.start();
    }

    public void stop(){
        this.isStopped = true;
        remaining = initialWait;
    }

    public void pause(){
        this.isPaused = true;
    }

    public void resume(){
        isPaused = false;
        isStopped = false;
    }

    public int getRemainingSeconds(){
        return remaining;
    }
}
