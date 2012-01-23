package example7 ;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

class Example7Gui {

    private final JLabel result = new JLabel("0",SwingConstants.CENTER);
    private int value = 0;

    public  void example7() {

        final JFrame frame = new JFrame();
        frame.setSize(300,300);
// Create a slider specifying the range and starting value of the knob.
        final JSlider slide = new JSlider(JSlider.VERTICAL,-10,10,0);
// Define the scale to be shown on the slide.
        slide.setMajorTickSpacing(5);
        slide.setMinorTickSpacing(1);
        slide.setPaintTicks(true);
        slide.setPaintLabels(true);
// Create a border around the slider and its scale.
        slide.setBorder(
            BorderFactory.createEmptyBorder(0,0,10,0));

        frame.add(result,BorderLayout.CENTER);
        frame.add(slide,BorderLayout.EAST);
        slide.addChangeListener(new ChangeValue());
        frame.setVisible(true);
    }

    class ChangeValue implements ChangeListener {

        public void stateChanged(final ChangeEvent expn) {
            final JSlider source = (JSlider)expn.getSource();
// The listener is called when the slider moves. 
// Here it only changes the label at the end of the movement.
            if (!source.getValueIsAdjusting()) {
                value = (int)source.getValue();
                result.setText(Integer.toString(value));
            }
        }
    }
}

public class Example7 {

    public static void main(String []argv) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Example7Gui gui = new Example7Gui();
                gui.example7();
            }
        });
    }
}
