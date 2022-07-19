import java.io.Serializable;

public class bullet extends Thread implements Serializable {
    private int x;
    private int y;
    private int d;
    private boolean isAlive;

    public bullet(int x,int y,int d){
        this.x = x;
        this.y = y;
        this.d = d;
        this.isAlive = true;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getD() {
        return d;
    }

    public boolean alive() {
        return isAlive;
    }
    public void setAlive(boolean r){
        isAlive = r;
    }
    @Override
    public void run(){
        while (isAlive) {
            switch (d){
                case 1:
                    y-=10;
                    break;
                case 2:
                    x+=10;
                    break;
                case 3:
                    y+=10;
                    break;
                case 4:
                    x-=10;
                    break;
            }
            if(x < 0 || x>1000||y<0||y>750) isAlive = false;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
