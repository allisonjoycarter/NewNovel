import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreatorGUI extends JPanel {
    private TreePane treePane;
    private JPanel toolbar;
    private JButton newItem;
    private JButton deleteItem;
    private JButton moveUp;
    private JButton moveDown;
    private JButton startOver;
    private JButton save;
    private JButton load;

    public CreatorGUI() {
        setLayout(new BorderLayout());

        toolbar = new JPanel();
        toolbar.setLayout(new BoxLayout(toolbar, BoxLayout.X_AXIS));
        newItem = new JButton("Add Item");
        deleteItem = new JButton("Delete Item");
        moveUp = new JButton("Move Item Up");
        moveDown = new JButton("Move Item Down");
        startOver = new JButton("Start Over");
        save = new JButton("Save Game");
        load = new JButton("Load Game");
        newItem.addActionListener(new ButtonListener());
        deleteItem.addActionListener(new ButtonListener());
        moveUp.addActionListener(new ButtonListener());
        moveDown.addActionListener(new ButtonListener());
        startOver.addActionListener(new ButtonListener());
        save.addActionListener(new ButtonListener());
        load.addActionListener(new ButtonListener());
        toolbar.add(newItem);
        toolbar.add(deleteItem);
        toolbar.add(startOver);
        toolbar.add(save);
        toolbar.add(load);
        add(toolbar, BorderLayout.NORTH);

        treePane = new TreePane();
        add(treePane, BorderLayout.CENTER);

    }
    class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == newItem) {
                treePane.addObject("Set Name");
            } else if (e.getSource() == deleteItem) {
                treePane.removeCurrentNode();
            } else if (e.getSource() == startOver) {
                treePane.clear();
            } else if (e.getSource() == save) {
                treePane.serialize();
            }
            if (e.getSource() == load) {
                treePane.deserialize();
            }
        }
    }
}
