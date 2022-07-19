import java.io.*;
import java.util.ArrayList;
import java.util.Properties;

public class record {
    private static ArrayList<Tank> enemys;
    private static ArrayList<crash> crashes;
    private static Tank player;
    private static Properties prop=null;
    private static String path = "C:\\Users\\EvanHyde\\Desktop\\Tank_log\\config.cls";
    public record(ArrayList<Tank> enemys,ArrayList<crash> crashes,Tank player){
        this.enemys = enemys;
        this.crashes = crashes;
        this.player = player;
    }
    public static ArrayList load(){
        ArrayList arrayList = new ArrayList();
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(path)));
            Object enemys = ois.readObject();
            Object crashes = ois.readObject();
            Object player = ois.readObject();
            arrayList.add(enemys);
            arrayList.add(crashes);
            arrayList.add(player);
            ois.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    public static void save(){
        try {
            ObjectOutputStream bos = new ObjectOutputStream(new FileOutputStream(new File(path)));
            bos.writeObject(enemys);
            bos.writeObject(crashes);
            bos.writeObject(player);
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
