import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

class MyPanel extends JPanel implements Runnable, KeyListener {
    ArrayList<Tank> enemys = new ArrayList();
    Tank player;
    ArrayList<crash> crashes = new ArrayList();

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A:
                player.moveLeft();
                break;
            case KeyEvent.VK_S:
                player.moveDown();
                break;
            case KeyEvent.VK_D:
                player.moveRight();
                break;
            case KeyEvent.VK_W:
                player.moveUp();
                break;
            case KeyEvent.VK_J:
                player.shoot();
                break;
        }
        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run() {
        while (true) {
            this.repaint();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public MyPanel(char choice) {
        if(!(choice == 'Y' || choice == 'y')) {
            if(choice != 'N' && choice != 'n') System.out.println("选择有误，开始新的游戏");
            player = new Tank(500, 500, 1, 1);
            for (int i = 0; i < 3; i++) {
                Tank tank = new Tank(250 * i + 250, 100, 3, 2);
                enemys.add(tank);
                tank.start();
            }
        }else{
            ArrayList data = record.load();
            enemys = (ArrayList<Tank>) data.get(0);
            crashes = (ArrayList<crash>) data.get(1);
            player = (Tank) data.get(2);
            for(int i = 0;i<enemys.size();i++) {
                enemys.get(i).start();
                for (int j = 0; j < enemys.get(i).getBullets().size(); j++) {
                    enemys.get(i).getBullets().get(j).start();
                }
            }
            for (int i = 0; i < crashes.size(); i++) {
                crashes.get(i).start();
            }
            for (int i = 0; i < player.getBullets().size(); i++) {
                player.getBullets().get(i).start();
            }
        }
        new record(enemys, crashes, player);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0, 0, 1000, 750);
        g.setFont(new Font("宋体",Font.BOLD,18));
        g.drawString("剩余敌方：",1010,100);
        g.drawString(enemys.size() + " ",1100,100);
        drawTank(player, g);
        for(int i = 0;i<enemys.size();i++){
            Tank enemy = enemys.get(i);
            if (enemy.isAlive()) {
                drawTank(enemy, g);
            }else{
                enemys.remove(enemy);
            }
        }

        for(int i = 0;i<enemys.size();i++){
            Tank enemy = enemys.get(i);
            for(int j = 0;j<enemy.getBullets().size();j++){
                bullet bullet = enemy.getBullets().get(j);
                if(bullet.alive()) {
                    g.drawOval(bullet.getX(), bullet.getY(), 10, 10);
                }else{
                    enemy.getBullets().remove(bullet);
                }
            }
        }

        for(int i = 0;i<player.getBullets().size();i++){
            bullet bullet = player.getBullets().get(i);
            if(bullet.alive()) {
                for (int j = 0; j < enemys.size(); j++) {
                    Tank enemy = enemys.get(j);
                    if(bullet.getX() < enemy.getX()+ 30 && bullet.getX()> enemy.getX() - 30&&
                            bullet.getY() < enemy.getY()+ 30 && bullet.getY()> enemy.getY() - 30){
                        player.getBullets().remove(bullet);
                        crash crash = new crash(enemy.getX(),enemy.getY());
                        crashes.add(crash);
                        crash.start();
                        enemy.setAlive(false);
                        enemys.remove(enemy);
                    }
                }
                g.drawOval(bullet.getX(), bullet.getY(), 10, 10);
            }else{
                player.getBullets().remove(bullet);
            }
        }

        for(int i = 0;i<crashes.size();i++){
            crash crash = crashes.get(i);
            g.drawOval(crash.x,crash.y,crash.size,crash.size);
        }
    }

    public void drawTank(Tank tank, Graphics g) {
        int x = tank.getX();
        int y = tank.getY();
        int d = tank.getD();
        int type = tank.getType();
        switch (type) {
            case 1:
                g.setColor(Color.GREEN);
                break;
            case 2:
                g.setColor(Color.CYAN);
                break;
        }
        switch (d) {
            case 1:
                g.fill3DRect(x - 20, y - 30, 10, 60, false);
                g.fill3DRect(x + 10, y - 30, 10, 60, false);
                g.fill3DRect(x - 10, y - 20, 20, 40, false);
                g.drawLine(x, y, x, y - 40);
                g.fillOval(x - 10, y - 10, 20, 20);
                break;
            case 2:
                g.fill3DRect(x - 30, y - 20, 60, 10, false);
                g.fill3DRect(x - 30, y + 10, 60, 10, false);
                g.fill3DRect(x - 20, y - 10, 40, 20, false);
                g.drawLine(x, y, x + 40, y);
                g.fillOval(x - 10, y - 10, 20, 20);
                break;
            case 3:
                g.fill3DRect(x - 20, y - 30, 10, 60, false);
                g.fill3DRect(x + 10, y - 30, 10, 60, false);
                g.fill3DRect(x - 10, y - 20, 20, 40, false);
                g.drawLine(x, y, x, y + 40);
                g.fillOval(x - 10, y - 10, 20, 20);
                break;
            case 4:
                g.fill3DRect(x - 30, y - 20, 60, 10, false);
                g.fill3DRect(x - 30, y + 10, 60, 10, false);
                g.fill3DRect(x - 20, y - 10, 40, 20, false);
                g.drawLine(x, y, x - 40, y);
                g.fillOval(x - 10, y - 10, 20, 20);
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}