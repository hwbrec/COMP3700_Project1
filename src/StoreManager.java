import javax.swing.*;
import java.util.Scanner;

public class StoreManager {
    public static String DBMS = "SQLite";
    public static final String DB_FILE = "H:\\COMP3700_Project1\\Project1_Database.db";

    IDataAdapter adapter = null;
    private static StoreManager instance = null;

    public static StoreManager getInstance() {
    
        if (instance == null) {

            String dbfile = DB_FILE;
            if (dbfile.length() == 0) {
                JFileChooser fc = new JFileChooser();
                if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
                    dbfile = fc.getSelectedFile().getAbsolutePath();
            }
            instance = new StoreManager(DBMS, dbfile);
        }
        return instance;
    }

    private StoreManager(String dbms, String dbfile) {
        if (dbms.equals("Oracle"))
            adapter = new OracleDataAdapter();
        else
        if (dbms.equals("SQLite"))
            adapter = new SQLiteDataAdapter();

        adapter.connect(dbfile);
        /*ProductModel product = adapter.loadProduct(3);

        System.out.println("Loaded product: " + product);*/
    }

    public IDataAdapter getDataAdapter() {
        return adapter;
    }

    public void setDataAdapter(IDataAdapter a) {
        adapter = a;
    }


    public void run() {
        MainUI ui = new MainUI();
        ui.view.setVisible(true);
    }

    public static void main(String[] args) {
        System.out.println("Welcome to the store management system!");
//        StoreManager.getInstance().init();
        Scanner userIn = new Scanner(System.in);
        System.out.print("Enter 0 to use SQLite or 1 to use Oracle: ");
        int userChoice = userIn.nextInt();
        if (userChoice == 0) {
           DBMS = "SQLite";
        }
        else if (userChoice == 1) {
           DBMS = "Oracle";
        }
        StoreManager.getInstance().run();
    }

}
