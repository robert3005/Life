/*
  * an example of moving items in a screen 
  */
package example6;
import java.awt.Container;
import java.awt.GridLayout;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.*;

class Example6v extends JFrame {
    JButton buttons[];
    int rows ;
    int size;
    ExampleListener listen = new ExampleListener();
    Container contentPane;

    void place() {
        for (int i=0; i< buttons.length ; i++) {
            contentPane.add(buttons[i]);
            if ((buttons[i].getText()).equals("<-")) {
                buttons[i].addActionListener(listen);
            }
        }
        validate();
    }

    void shuffle() {
        JButton left= buttons[0];
        for (int i=1; i < size ; i++) {
            buttons[i-1]=buttons[i];
        }
        buttons[size-1]=left;
    }

    public void start(int theRows) {
        rows = theRows;
        setSize(rows*70,rows*70);
        size=rows*rows;
        contentPane =  getContentPane();
        setLayout(new GridLayout(rows,rows));
        buttons = new JButton[size];
        for (int i=0; i < size-1; i++) {
            buttons[i]= new JButton(Integer.toString(i));
        }
        buttons[size-1]= new JButton("<-");
        place();
        setVisible(true);
    }

    class ExampleListener implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            contentPane.removeAll();
            shuffle();
            place();
        }
    }
}

public class Example6 {
    public static void main(String[] args) {
        int rows=8;
        if (args.length > 0) {
            try {
                rows = Integer.parseInt(args[0]);
            } catch (NumberFormatException except) {
                System.err.println("The argument (rows) should be an integer");
            }
        }

        Example6v example = new Example6v();
        example.start(rows);
    }
}

