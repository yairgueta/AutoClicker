package model;

public class TimerStartTwiceException extends ModelException {
    public TimerStartTwiceException() {
        super("Cannot start a timer twice! start it once and pause / stop and resume it!");
    }
}
