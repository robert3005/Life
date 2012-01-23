package example4a;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// The class extends JFrame instead of creating a new JFrame object.
class Example4aGui extends JFrame implements ActionListener {
    private int value = 0;
    private final JLabel result = new JLabel("0",SwingConstants.CENTER);

    public  void example4a() {

        final JButton increase = new JButton("+1");
        final JButton decrease = new JButton("-1");
        final JButton red = new JButton("red");
        final JButton blank = new JButton("clear");
        final JPanel square = new JPanel();

        setSize(300,300);

        red.setBackground(Color.RED);
        square.setLayout(new GridLayout(2,2));
        square.add(increase);
        square.add(decrease);
        square.add(red);
        square.add(blank);

        add(result,BorderLayout.NORTH);
        add(square,BorderLayout.CENTER);

// this refers to the JFrame.
        increase.addActionListener(this);
        decrease.addActionListener(this);
        red.addActionListener(this);
        blank.addActionListener(this);
        setVisible(true);
    }

  
// An actionPerformed method must be defined in this class.
// No inner class is needed.
        public  void actionPerformed(final ActionEvent event) {
            /* only do this if the buttons are related and
               the action is short */
            // the button pressed
            final JButton sent = (JButton)event.getSource();
            // the button's label
            final String label = sent.getText();

            // Use the button's text to select the action
            if (label.equals("+1")) {
                value++;
            } else if (label.equals("-1")) {
                value--;
            } else if (label.equals("red")) {
                result.setForeground(Color.RED);
            } else {
                value=0;
                result.setForeground(Color.BLACK);
            }
            // update the value shown in the label
            result.setText(Integer.toString(value));
        }
    }


public class Example4a {

    public static void main(String [] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Example4aGui gui = new Example4aGui();
                gui.example4a();
            }
        });
    }

}
