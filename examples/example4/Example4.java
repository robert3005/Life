package example4;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class Example4Gui {
    private int value = 0;
    private final JLabel result = new JLabel("0",SwingConstants.CENTER);

    public  void example4() {

        final JButton increase = new JButton("+1");
        final JButton decrease = new JButton("-1");
        final JButton red = new JButton("red");
        final JButton blank = new JButton("clear");
        final JPanel square = new JPanel();

        final JFrame frame = new JFrame();
        frame.setSize(300,300);
// One listener, an ActionListener, is created for all the buttons
// since the program doesn't need to know which mouse button was pressed.
        final ExampleListener listen = new ExampleListener();

        red.setBackground(Color.RED);
        square.setLayout(new GridLayout(2,2));
        square.add(increase);
        square.add(decrease);
        square.add(red);
        square.add(blank);

        frame.add(result,BorderLayout.NORTH);
        frame.add(square,BorderLayout.CENTER);

// The listener is added to the buttons.
        increase.addActionListener(listen);
        decrease.addActionListener(listen);
        red.addActionListener(listen);
        blank.addActionListener(listen);
        frame.setVisible(true);
    }

// The class implementing ActionListener is defined inside the code
// class to be able to access its fileds.
    class ExampleListener implements ActionListener {

// actionPerformed is called when any of the components with this 
// listener are clicked. The event generated is passed as the argument
// event. An event contains a reference to the component that 
// generated it. event.getSource() returns the button that was clicked.
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
}

public class Example4 {

    public static void main(String [] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Example4Gui gui = new Example4Gui();
                gui.example4();
            }
        });
    }

}
