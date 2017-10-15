import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;

public class BackgroundPane extends JLayeredPane{
    private JComboBox comboBox;
    private JPanel comboBoxPanel;
    private JPanel storyGUI;
    private JPanel creatorGUI;
    private String GAME = "Game"; //combo box label
    private String CREATOR = "Creator"; //combo box label

    //Panel that holds combo box and comboBoxPanel
    public BackgroundPane() throws IOException {
        setLayout(new BorderLayout()); //to put combo box above game/creator panels

        //Panel that holds Story GUI and CreatorGUI
        comboBoxPanel = new JPanel();
        comboBoxPanel.setLayout(new CardLayout());

        //adding GUI's to comboBoxPanel
        storyGUI = new StoryGUI();
        creatorGUI = new ListMakerGUI();
        comboBoxPanel.add(storyGUI, GAME);
        comboBoxPanel.add(creatorGUI, CREATOR);

        //sets up combo box
        String comboBoxItems[] = {GAME, CREATOR};
        comboBox = new JComboBox(comboBoxItems);
        comboBox.setEditable(false);
        comboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                CardLayout cardLayout = (CardLayout) (comboBoxPanel.getLayout());
                cardLayout.show(comboBoxPanel, (String) e.getItem());
            }
        });

        //adding the combo box and the panel with GUI's to BorderLayout
        add(comboBox, BorderLayout.NORTH);
        add(comboBoxPanel, BorderLayout.CENTER);
    }
}
