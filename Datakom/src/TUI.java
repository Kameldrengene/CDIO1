import java.util.Scanner;

public class TUI {
    private Scanner scan;
    
    public TUI() {
        this.scan = new Scanner(System.in);
    }
    
    public void showMessage(String message) {
        System.out.println(message);
    }
    
    public int showMenu(String message, String... menuItems) {
        System.out.println(message);
        
        int choice;
        for(choice = 1; choice < menuItems.length + 1; ++choice) {
            System.out.println(choice + ". " + menuItems[choice - 1]);
        }
        
        boolean var4 = false;
        
        while(true) {
            choice = this.inputNumber();
            if (choice >= 1 && choice <= menuItems.length + 1) {
                return choice;
            }
            
            System.out.println("Indtast venligst et nummer fra listen");
        }
    }
    
    private int inputNumber() {
        while(true) {
            try {
                int choice = Integer.parseInt(this.scan.nextLine());
                return choice;
            } catch (NumberFormatException var3) {
                System.out.println("Indtast venligst et tal");
            }
        }
    }
}
