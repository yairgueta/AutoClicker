package control;

import model.MainModel;
import model.ModelException;
import view.AutoClickerView;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AutoClickerController {
    public static void main(String[] args) {
        new AutoClickerController();
    }

    MainModel model;
    AutoClickerView view;
    public AutoClickerController() {
        model = new MainModel();
        view = new AutoClickerView();

        this.addBtnsListeners();

        view.setEnabledStart(true);
        view.setEnabledResume(false);
        view.setEnabledStop(false);
        view.setEnabledPause(false);
        view.setEnabledTimeField(true);

        MousePositionRefresher refresher = new MousePositionRefresher(this);
        refresher.start();
    }

    @FunctionalInterface
    private interface ModelRunnable {
        void run() throws ModelException;
    }

    private class ModelHandleListenerWrapper implements ActionListener{
        ModelRunnable _fun;
        public ModelHandleListenerWrapper(ModelRunnable fun){
            _fun = fun;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                _fun.run();
            }catch (ModelException ex){
                view.throwErrorMessage(ex.getMessage());
            }
        }
    }

    private void addBtnsListeners() {
        view.addStartListener(new ModelHandleListenerWrapper(() -> {
            setTimesInputsToModel();
            model.start();
            view.setEnabledStart(false);
            view.setEnabledResume(false);
            view.setEnabledStop(true);
            view.setEnabledPause(true);
            view.setEnabledTimeField(false);
        }));
        view.addPauseListener(new ModelHandleListenerWrapper(() -> {
            model.pause();
            view.setEnabledStart(false);
            view.setEnabledResume(true);
            view.setEnabledStop(true);
            view.setEnabledPause(false);
            view.setEnabledTimeField(false);
        }));
        view.addStopListener(new ModelHandleListenerWrapper(() -> {
            model.stop();
            view.setEnabledStart(true);
            view.setEnabledResume(true);
            view.setEnabledStop(false);
            view.setEnabledPause(false);
            view.setEnabledTimeField(true);
        }));
        view.addResumeListener(new ModelHandleListenerWrapper(() -> {
            model.resume();
            view.setEnabledStart(false);
            view.setEnabledResume(false);
            view.setEnabledStop(true);
            view.setEnabledPause(true);
            view.setEnabledTimeField(false);
        }));

        view.addTestMouseListener(e -> model.moveMouse(view.getUserMouseInput()));
    }

    private void setTimesInputsToModel(){
        int initTime = view.getInitTime();
        int intervalTime = view.getIntervalTime();

        model.setClicker(initTime, intervalTime, this::updateTimer);
    }

    private void updateTimer(){
        int remainingTime = model.getRemainingSeconds();
        int min = remainingTime / 60;
        int sec = remainingTime % 60;
        String time = String.format("%02d:%02d", min, sec);
        view.updateTimer(time);
    }

    void refreshMousePosition(){
        Point pos = this.model.getMousePos();
        this.view.updateMousePosition(pos.x, pos.y);
    }
}
