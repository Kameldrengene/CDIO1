import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Scanner;

public class Controller {
    TUI tui;
    
    public Controller(TUI tui) {
        this.tui = tui;
    }
    
    public void start() {
        while(true) {
            int choice = this.tui.showMenu("Vælg et menupunkt", new String[]{"Skriv mail", "Afslut"});
            System.out.println();
            switch(choice) {
                case 1:
                    this.sendMail();
                default:
                    System.out.println("");
                    break;
                case 2:
                    System.out.println("Programmet lukkes...");
                    return;
            }
        }
    }
    
    public void sendMail() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Fil: ");
        File file = new File(scan.nextLine());
    
        try {
            String encoded = toBase64(file);
            System.out.println(encoded);
        } catch (IOException e) {
            e.printStackTrace();
        }
    
    }
    
    //https://www.javagists.com/convert-an-image-to-base64-string-in-java
    private static String toBase64(File file) throws IOException {
        FileInputStream fileInputStreamReader = new FileInputStream(file);
        byte[] bytes = new byte[(int)file.length()];
        fileInputStreamReader.read(bytes);
        return new String(Base64.getMimeEncoder().encode(bytes), "UTF-8");
    }
    
    
}