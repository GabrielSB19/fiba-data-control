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
        try {
            String line = br.readLine();
            line = br.readLine();
            long start = System.currentTimeMillis();
            while (line != null) {
                String parts[] = line.split(",");
                Player py = new Player(parts[0], Integer.parseInt(parts[1]), parts[2], Integer.parseInt(parts[3]),
                        Integer.parseInt(parts[4]), Integer.parseInt(parts[5]), Integer.parseInt(parts[6]),
                        Integer.parseInt(parts[7]));
                fDataCenter.addPlayer(py);
                line = br.readLine();
            }
            long end = System.currentTimeMillis();
            System.out.println(end - start);
            br.close();
            listener.onFinish();
        } catch (Exception e) {
            System.out.println("Error al importar");
        }
    }

    @Override
    public void run() {
        listener.onInit();
        importData();
    }

}
