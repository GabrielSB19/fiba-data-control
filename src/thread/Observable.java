package thread;

import java.io.BufferedReader;
import model.FibaDataCenter;
import model.Player;

public class Observable extends Thread {
    private FibaDataCenter fDataCenter;
    private IObserver listener;
    private BufferedReader br;

    public Observable(FibaDataCenter fDataCenter, BufferedReader br) {
        this.fDataCenter = fDataCenter;
        this.br = br;
    }

    public void setListener(IObserver listener) {
        this.listener = listener;
    }

    public void importData() {
        listener.onInit();
        try {
            String line = br.readLine();
            line = br.readLine();
            while (line != null) {
                String parts[] = line.split(",");
                Player py = new Player(parts[0], Integer.parseInt(parts[1]), parts[2], Integer.parseInt(parts[3]),
                        Integer.parseInt(parts[5]), Integer.parseInt(parts[7]), Integer.parseInt(parts[4]),
                        Integer.parseInt(parts[6]));
                fDataCenter.addPlayer(py);
                line = br.readLine();
            }
            br.close();
        } catch (Exception e) {
            System.out.println("Import Error");
        }
        listener.onFinish();
    }

    @Override
    public void run() {   
        importData();
    }

}
