package xml_learn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Created by useheart on 2019-06-09
 *
 * @author useheart
 */
public class GridBagTest {

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFileChooser chooser = new JFileChooser(".");
            chooser.showOpenDialog(null);
            File file = chooser.getSelectedFile();
            JFrame frame = new FontFrame(file);
            frame.setTitle("GridBagTest");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}

class FontFrame extends JFrame {
    private GridBagPanel gridBag;
    private JComboBox<String> face;
    private JComboBox<String> size;
    private JCheckBox bold;
    private JCheckBox italic;

    public FontFrame(File file) {
        gridBag = new GridBagPanel(file);
        add(gridBag);
        face = (JComboBox<String>) gridBag.get("face");
        size = (JComboBox<String>) gridBag.get("size");
        bold = (JCheckBox) gridBag.get("bold");
        italic = (JCheckBox) gridBag.get("italic");

        face.setModel(new DefaultComboBoxModel<String>(new String[]{
                "Serif", "SansSerif", "Monospaced", "Dialog", "DialogInput"
        }));

        size.setModel(new DefaultComboBoxModel<String>(new String[]{
                "8", "10", "12", "15", "18", "24", "36", "48"
        }));

        ActionListener listener = event -> setSample();

        face.addActionListener(listener);
        size.addActionListener(listener);
        bold.addActionListener(listener);
        italic.addActionListener(listener);

        setSample();
        pack();
    }

    /**
     * This method sets the text sample to the selected font.
     */
    public void setSample() {
        String fontFace = face.getItemAt(face.getSelectedIndex());
        int fontSize = Integer.parseInt(size.getItemAt(size.getSelectedIndex()));
        JTextArea sample = (JTextArea) gridBag.get("sample");
        int fontStyle = (bold.isSelected() ? Font.BOLD : 0) + (italic.isSelected() ? Font.ITALIC : 0);
        sample.setFont(new Font(fontFace, fontStyle, fontSize));
        sample.repaint();
    }
}
