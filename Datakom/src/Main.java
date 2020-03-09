public class Main {
    public static void main(String[] args) {
        TUI tui = new TUI();
        Controller con = new Controller(tui);
        con.start();
    }
}