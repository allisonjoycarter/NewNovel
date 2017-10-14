import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class StoryGUI extends JPanel {
    private JMenuBar menuBar; //game menu
    private JMenu menu;
    private JMenuItem restart;
    private JMenuItem quit;
    private JMenuItem save;
    private JMenuItem load;
    private JTextArea text;
    private JScrollPane scrollPane; //scrollPane to contain text
    private JLabel picture; //story background (it is a *visual* novel after all)
    private JButton back;
    private JButton forward;
    private BufferedImage background = ImageIO.read(getClass().getResourceAsStream("mountains1.png"));


    //choice option buttons. May need more when tree is incorporated
    //for loop of buttons?
    private JButton button1;
    private JButton button2;

    public StoryGUI() throws IOException {
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        text = new JTextArea(5, 30);

        text.setOpaque(false);
        text.setEditable(false);
        text.setLineWrap(true);
        text.setWrapStyleWord(true);
//        text.setFont(font);
        text.setText("Text Goes Here");
//        text.addKeyListener(keyListener);
//        text.addMouseListener(mouseListener);

        restart = new JMenuItem("Restart");
        quit = new JMenuItem("Quit");
        save = new JMenuItem("Save");
        load = new JMenuItem("Load");
        menu = new JMenu("Menu");
        menuBar = new JMenuBar();
        menu.add(restart);
        menu.add(quit);
        menu.add(save);
        menu.add(load);
        menuBar.add(menu);
        menuBar.setSize(new Dimension(50, 28));

        constraints.fill = GridBagConstraints.NORTHEAST;
        constraints.weightx = 0;
        constraints.gridx = 2;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.NORTHEAST;
        menuBar.setPreferredSize(new Dimension(42, 27));
        add(menuBar, constraints);

        back = new JButton("Back");
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 10;
        constraints.gridx = 0;
        constraints.gridy = 0;
        add(back, constraints);

        forward = new JButton("Forward");
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 10;
        constraints.gridx = 1;
        constraints.gridy = 0;
        add(forward, constraints);


        scrollPane = new JScrollPane(text) {
            @Override
            protected void paintComponent(Graphics g) {
                if (scrollPane.isAncestorOf(text)) {
                    g.setColor(new Color(255, 255, 255, 50));
                    g.fillRect(0, 0, getWidth(), getHeight());
                }
            }
        };
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.PAGE_END;
        constraints.ipady = 30;
        constraints.weightx = 0;
        constraints.gridwidth = 3;
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.insets = new Insets(10, 5, 10, 5);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setOpaque(false);
        scrollPane.setViewportBorder(null);
        scrollPane.setBorder(null);
        add(scrollPane, constraints);

        button1 = new JButton("Option 1");
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 0.5;
        constraints.ipady = 0;
        constraints.gridwidth = 1;
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.insets = new Insets(0, 5, 5, 2);
        button1.setEnabled(false);
        add(button1, constraints);
//        button1.addActionListener(buttonListener);

        button2 = new JButton("Option 2");
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 0.5;
        constraints.gridx = 1;
        constraints.gridy = 3;
        constraints.insets = new Insets(0, 3, 5, 5);
        constraints.anchor = GridBagConstraints.PAGE_END;
        button2.setEnabled(false);
        add(button2, constraints);
//        button2.addActionListener(buttonListener);

        picture = new JLabel(new ImageIcon(background));
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 0.5;
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.ipady = 60;
        constraints.gridwidth = 3;
        constraints.gridheight = 2;
//        constraints.insets = new Insets(5, 2, 5, 2);
        add(picture, constraints);
    }
}
