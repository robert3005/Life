package example2 ;

import java.awt.BorderLayout;
import javax.swing.*;

class Example2Gui {

    public void example2() {

        final JLabel  aLabel   = new JLabel("0",SwingConstants.CENTER);
// Here we add a button, but leave the handling 
// of pressing the button for later.
        final JButton increase = new JButton("+1");

        final JFrame frame = new JFrame();
        frame.setSize(200,200);
// The second argument to add tells the layout manager 
// that the label goes at the top (north) of the container.
        frame.add(aLabel,BorderLayout.NORTH);
// The contents of the CENTER region will expand to 
// fill the rest of the frame. 
        frame.add(increase,BorderLayout.CENTER);
        frame.setVisible(true);
    }
}

public class Example2  {
    public static void main(final String [] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                final Example2Gui gui = new Example2Gui();
                gui.example2();
            }
        });
    }

}
