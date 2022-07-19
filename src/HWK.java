import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Scanner;


public class HWK extends JFrame {
    private MyPanel mp = null;

    public static void main(String[] args) {
        new HWK();
    }

    public HWK() {
        System.out.println("是否继续上局游戏？[Y/N]");
        Scanner scanner = new Scanner(System.in);
        mp = new MyPanel(scanner.next().charAt(0));
        new Thread(mp).start();
        this.add(mp);
        this.setSize(1250, 750);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addKeyListener(mp);
        this.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e){
                record.save();
            }
        });
    }

}

