import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreatorGUI extends JPanel {
    private TreePane treePane;
    private JPanel toolbar;
    private JButton newItem;
    private JButton deleteItem;
    private JButton startOver;
    private JButton save;
    private JButton load;

    public CreatorGUI() {
        setLayout(new BorderLayout());

        //made a BoxLayout for the buttons so they would line up properly along the top
        toolbar = new JPanel();
        toolbar.setLayout(new BoxLayout(toolbar, BoxLayout.X_AXIS));
        newItem = new JButton("Add Item");
        deleteItem = new JButton("Delete Item");
        startOver = new JButton("Start Over");
        save = new JButton("Save Game");
        load = new JButton("Load Game");
        newItem.addActionListener(new ButtonListener());
        deleteItem.addActionListener(new ButtonListener());
        startOver.addActionListener(new ButtonListener());
        save.addActionListener(new ButtonListener());
        load.addActionListener(new ButtonListener());
        toolbar.add(newItem);
        toolbar.add(deleteItem);
        toolbar.add(startOver);
        toolbar.add(save);
        toolbar.add(load);
        add(toolbar, BorderLayout.NORTH);

        //adding tree to panel
        treePane = new TreePane();
        add(treePane, BorderLayout.CENTER);

    }
    //simply calling methods from TreePane
    //looks so clean :D
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
                treePane.createList();
            }
        }
    }
}
