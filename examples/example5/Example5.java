package example5;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class Example5Gui  {
    private int value = 0;
    private int rows = 2 ;
// To find the location of the program at runtime.
    private final Class myClass = Example5.class;
    private final JLabel result = new JLabel("0",SwingConstants.CENTER);
    private ImageIcon downButtonIcon ;
    private ImageIcon upButtonIcon ;

    protected void  example5(final int theRows) {
// Assumes the .png files are in the same location as the program. 
// Creates the icons.
        upButtonIcon =   new ImageIcon(myClass.getResource("Up.png"));
        downButtonIcon = new ImageIcon(myClass.getResource("Down.png"));
        final JButton upButton = new JButton();
        final JFrame frame = new JFrame();
        rows = theRows;
        frame.setSize(300,300);
        final JButton plusMinus = new JButton("+/-");
        final JButton red = new JButton("red");
        final JButton blank = new JButton("clear");
        final ExampleListener listen = new ExampleListener();
        final ExampleMouseAdapter mouseListen = new ExampleMouseAdapter();
        red.setBackground(Color.red);
        final JPanel square = new JPanel(new GridLayout(rows,rows));
        square.add(plusMinus);
        square.add(red);
        square.add(upButton);
        square.add(blank);

        frame.add(result,BorderLayout.NORTH);
        frame.add(square,BorderLayout.CENTER);

        plusMinus.addMouseListener(mouseListen);
        red.addActionListener(listen);
        upButton.addActionListener(listen);
        blank.addActionListener(listen);
// Put the icon in the button.
        upButton.setIcon(upButtonIcon);
        frame.setVisible(true);
    }

    class ExampleListener implements ActionListener {

        public void actionPerformed(final ActionEvent event) {

             // the button pressed
            final JButton sent = (JButton)event.getSource();
            // the button's label
            final String label = sent.getText();

            if (label.equals("red")) {
                result.setForeground(Color.red);
            } else if (label.equals("clear")) {
                value=0;
                result.setForeground(Color.black);
            } else if ( sent.getIcon() == upButtonIcon) {
// The icon in a JButton can be accessed or changed.
                sent.setIcon(downButtonIcon);
            } else {
                sent.setIcon(upButtonIcon);
            }
            result.setText(Integer.toString(value));
        }
    }


// To implement a MouseListener you have to provide code for 5 methods.
// In this example only one of the possible events is used, but the other 
// methods would still have to be written (as empty methods).
// In this case it is more convenient to extend MouseAdapter and 
// override the method that is required.
    class ExampleMouseAdapter extends MouseAdapter {

        public void mouseClicked(MouseEvent event) {
            // this overrides the empty method in Mouse Adapter

// The most convenient way of finding out which mouse button was clicked
// from the event is to use SwingUtilities.isLeftButton and
// SwingUtilities.isRightButton.
            if (SwingUtilities.isLeftMouseButton(event)) {
                value++;
            } else if (SwingUtilities.isRightMouseButton(event)) {
                value--;
            }
            result.setText(Integer.toString(value));
        }
    }
}

public class Example5 {
    public static void main(String[] args) {
        //  get the program parameter
        final int rows;
        if (args.length > 0) {
            try {
                rows = Integer.parseInt(args[0]);
                javax.swing.SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        Example5Gui gui = new Example5Gui();
                        gui.example5(rows);
                    }
                });
            } catch (NumberFormatException except) {
                System.err.println("The first parameter should be a number");
                System.exit(1);
            }
        } else {
            System.err.println("The number of rows should be given as an argument");
        }
    }
}
