import java.io.Serializable;

public class crash extends Thread implements Serializable {
    int size = 50;
    int x;
    int y;
    public crash(int x,int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public void run(){
        while (size != 0) {
            size -= 10;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
