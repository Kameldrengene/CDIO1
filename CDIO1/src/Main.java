import controller.UserLogic;
import dal.UserDAONonPersistent;
import dal.UserDAOSerialisering;
import functionality.Functionality;
import functionality.IFunctionality;
import tui.TUI;

public class Main {
    
    public static void main(String[] args){
        //TODO fix (lasse)
        TUI tui = new TUI();
        IFunctionality functionality = new Functionality();
        int choice = tui.showMenu("Vælg database", "Non-persistent", "Fil på disk", "SQL database");
        
        switch(choice){
            case 1:
                new UserLogic(tui, functionality, new UserDAONonPersistent()).start();
                break;
            case 2:
                new UserLogic(tui, functionality, new UserDAOSerialisering()).start();
                break;
            case 3:
                System.out.println("Ikke implementeret endnu");
                break;
        }
    }
}
