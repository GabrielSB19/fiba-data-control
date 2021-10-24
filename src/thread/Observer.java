package thread;

import java.io.BufferedReader;

import controller.FibaController;
import model.FibaDataCenter;

public class Observer implements IObserver {

    private FibaController fController;
    private Observable obs;

    public Observer(FibaController fController,FibaDataCenter fDataCenter,BufferedReader br) {
        this.fController = fController;
        obs = new Observable(fDataCenter,br);
    }

    public void init() {
        obs.setListener(this);
        obs.start();
    }

    @Override
    public void onInit() {
        fController.pBar.setVisible(true);
    }

    @Override
    public void onFinish() {
        fController.pBar.setVisible(false);
        fController.onTablePlayers();
    }

}
