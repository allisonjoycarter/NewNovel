import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ListMakerGUI extends JPanel{
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
    private File file;
    private BufferedImage background;

    //choice option buttons. May need more when tree is incorporated
    //for loop of buttons?
    private JButton button1;
    private JButton button2;

    public ListMakerGUI() {
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

        //sets up the menuBar
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

        //constraints for the menuBar
        //wanted to put menuBar in northwest position but couldn't get back/forward to fill the empty space
        //weight, anchors, and fills did not solve this problem
        constraints.fill = GridBagConstraints.NORTHEAST;
        constraints.weightx = 0; //no weight so it doesn't take up extra space
        constraints.gridx = 2;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.NORTHEAST; //keeps menuBar in the northeast position
        menuBar.setPreferredSize(new Dimension(42, 27)); //roughly the same height as the other buttons
        add(menuBar, constraints);

        //constraints for the back button
        back = new JButton("Back");
        constraints.fill = GridBagConstraints.HORIZONTAL; //fills up available horizontal space
        constraints.weightx = 10; //lots of weight so it takes up menuBar's space
        constraints.gridx = 0;
        constraints.gridy = 0;
        add(back, constraints);

        //constraints for the forward button
        forward = new JButton("Forward");
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 10;
        constraints.gridx = 1;
        constraints.gridy = 0;
        add(forward, constraints);

        //makes scrollPane (also the text area) appear slightly transparent
        scrollPane = new JScrollPane(text) {
            @Override
            protected void paintComponent(Graphics g) {
                if (scrollPane.isAncestorOf(text)) {
                    g.setColor(new Color(255, 255, 255, 50)); //alter alpha to change transparency
                    g.fillRect(0, 0, getWidth(), getHeight());
                }
            }
        };
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.PAGE_END; //keeps text area at the bottom
        constraints.ipady = 30; //adds size to the scroll pane
        constraints.weightx = 0;
        constraints.gridwidth = 3;
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.insets = new Insets(20, 20, 50, 20); //adds space around scroll pane
        scrollPane.getViewport().setOpaque(false); //makes scroll pane actually transparent
        scrollPane.setOpaque(false);
        scrollPane.setViewportBorder(null);
        scrollPane.setBorder(null);
        add(scrollPane, constraints);

        //constraints for button2, will need to edit later for user to put more than 2 options?
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



        //sets background image
        picture = new JLabel("Set Background Image");
        picture.setPreferredSize(new Dimension(1000, 563));
        picture.setBorder(new LineBorder(Color.BLACK));
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 0.5;
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.ipady = 60;
        constraints.gridwidth = 3;
        constraints.gridheight = 2;
        picture.addMouseListener(new MouseListener());
//        constraints.insets = new Insets(5, 2, 5, 2);
        add(picture, constraints);
    }
    public class MouseListener implements java.awt.event.MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getSource() == picture) {
                JFileChooser fileChooser = new JFileChooser();
                int value = fileChooser.showOpenDialog(new ListMakerGUI());
                if (value == JFileChooser.APPROVE_OPTION) {
                    file = fileChooser.getSelectedFile();
                    try {
                        background = ImageIO.read(file);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    picture.setIcon(new ImageIcon(getScaledImage(background, 1000, 563)));
                    picture.setText("");
                }
            }
        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }
    }

    //This scales the image received in file chooser
    private Image getScaledImage(Image srcImg, int w, int h){
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();

        return resizedImg;
    }
}
