package example3;

import java.awt.*;
import javax.swing.*;

class Example3Gui {

    public  void example3() {
        final JLabel  result = new JLabel("0",SwingConstants.CENTER);
// To replace the button with a square array of related 
// buttons, create a JPanel to contain them.
        final JPanel  square = new JPanel();
        final JButton increase = new JButton("+1");
        final JButton decrease = new JButton("-1");
        final JButton red = new JButton("red");
        final JButton clear = new JButton("blank");
        final JFrame frame = new JFrame();

        frame.setSize(200,200);
// The default layout manager for the JPanel square is 
// replaced with GridLayout for a 2 by 2 grid.
        square.setLayout(new GridLayout(2,2));
// Four buttons are created and put inside the square.
        square.add(increase);
        square.add(decrease);
        square.add(red);
        square.add(clear);
        red.setBackground(Color.RED);

        frame.add(result,BorderLayout.NORTH);
// The button in the previous example is replaced by the JPanel.
        frame.add(square,BorderLayout.CENTER);
        frame.setVisible(true);
    }
}

public class Example3 {
    public static void main(String [] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Example3Gui gui = new Example3Gui();
                gui.example3();
            }
        });
    }
}

