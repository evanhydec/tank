import java.io.Serializable;
import java.util.ArrayList;

public class Tank extends Thread implements Serializable {
    private int x;
    private int y;
    private int d;
    private int type;
    private boolean isAlive;
    private ArrayList<bullet> bullets = new ArrayList();

    public void shoot(){
        bullet bullet = new bullet(x, y, d);
        bullet.start();
        bullets.add(bullet);
    }


    public ArrayList<bullet> getBullets() {
        return bullets;
    }

    @Override
    public void run(){
        int time = 1;
        while(isAlive){
            if(time == 10){
                time = 0;
                continue;
            }
            if(time == 0){
                d = (int)(Math.random() * 4 + 1);
            }

            if(time == 5){
                shoot();
            }

            switch (d){
                case 1:
                    moveUp();
                    break;
                case 2:
                    moveRight();
                    break;
                case 3:
                    moveDown();
                    break;
                case 4:
                    moveLeft();
                    break;
            }
            time++;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void moveRight(){
        if(x < 955)  x+=5;
        d = 2;
    }
    public void moveLeft(){
        if(x > 30) x-=5;
        d = 4;
    }
    public void moveUp(){
        if(y > 30) y-=5;
        d = 1;
    }
    public void moveDown(){
        if(y < 680) y+=5;
        d = 3;
    }

    public int getType() {
        return type;
    }

    public void setAlive(boolean b){
        isAlive = b;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Tank(int x, int y, int d, int type) {
        isAlive = true;
        this.x = x;
        this.y = y;
        this.d = d;
        this.type = type;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getD() {
        return d;
    }

    public void setD(int d) {
        this.d = d;
    }
}
