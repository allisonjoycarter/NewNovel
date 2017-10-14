import javax.swing.*;
import java.awt.*;

public class main extends JFrame {
    public static void main(String[] args) {
        JFrame frame = new JFrame("A User Friendly Novel");
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setContentPane(new BackgroundPane()); //BackgroundPane holds both GUI's

        frame.pack();
    }

}
