import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class main extends JFrame {
    public static void main(String[] args) throws IOException {
        JFrame frame = new JFrame("A User Friendly Novel");
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setContentPane(new BackgroundPane()); //BackgroundPane holds both GUI's

        frame.pack();
    }

}
