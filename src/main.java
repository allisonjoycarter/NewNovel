import javax.swing.*;

public class main extends JFrame {
    public static void main(String[] args) {
        JFrame frame = new JFrame("A User Friendly Novel");
        JLayeredPane pane = new JLayeredPane();
        pane.add(new StoryGUI(), 0);
        pane.add(new CreatorGUI(), 1);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setContentPane(pane);

        frame.pack();
    }
}
