package PTBac2;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class PTBac2 implements ActionListener {

    JFrame frame;
    JTextArea textfield;

    JButton[] numberButtons = new JButton[10];
    JButton[] functionButtons = new JButton[8];

    JButton decButton, equButton, delButton, clrButton, negButton, AButton, BButton, CButton;
    JPanel panel;
    Font myFont = new Font("Arial", Font.PLAIN, 30);

    double x1 = 0, x2 = 0, a = 0, b = 0, c = 0, delta = 0;

    public PTBac2() {
        frame = new JFrame("giai phuong trinh bac 2: a*(x^2)+bx+c=0");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(520, 600);
        frame.setLayout(null);

        textfield = new JTextArea();
        textfield.setBounds(50, 25, 400, 120);
        textfield.setFont(myFont);
        textfield.setEditable(false);

        decButton = new JButton(".");
        equButton = new JButton("=");
        delButton = new JButton("Del");
        clrButton = new JButton("Clr");
        negButton = new JButton("(-)");
        AButton = new JButton("=a");
        BButton = new JButton("=b");
        CButton = new JButton("=c");

        functionButtons[0] = decButton;
        functionButtons[1] = equButton;
        functionButtons[2] = delButton;
        functionButtons[3] = clrButton;
        functionButtons[4] = negButton;
        functionButtons[5] = AButton;
        functionButtons[6] = BButton;
        functionButtons[7] = CButton;
        for (int i = 0; i < 8; i++) {
            functionButtons[i].addActionListener(this);
            functionButtons[i].setFont(myFont);
            functionButtons[i].setBackground(new Color(255, 220, 180)); 
            functionButtons[i].setFocusable(false);
        }

        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(this);
            numberButtons[i].setFont(myFont);
            numberButtons[i].setBackground(new Color(176, 224, 230));
            numberButtons[i].setFocusable(false);
        }

        negButton.setBounds(50, 480, 100, 50);
        delButton.setBounds(150, 480, 100, 50);
        clrButton.setBounds(250, 480, 100, 50);

        panel = new JPanel();
        panel.setBounds(50, 150, 400, 300);
        panel.setLayout(new GridLayout(4, 4, 10, 10));
       panel.setBackground(new Color(245, 245, 245));

        panel.add(numberButtons[1]);
        panel.add(numberButtons[2]);
        panel.add(numberButtons[3]);
        panel.add(AButton);
        panel.add(numberButtons[4]);
        panel.add(numberButtons[5]);
        panel.add(numberButtons[6]);
        panel.add(BButton);
        panel.add(numberButtons[7]);
        panel.add(numberButtons[8]);
        panel.add(numberButtons[9]);
        panel.add(CButton);
        panel.add(decButton);
        panel.add(numberButtons[0]);
        panel.add(equButton);

        frame.add(panel);
        frame.add(negButton);
        frame.add(delButton);
        frame.add(clrButton);
        frame.add(textfield);
         frame.getContentPane().setBackground(new Color(211, 211, 211)); 
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        for (int i = 0; i < 10; i++) {

            if (e.getSource() == numberButtons[i]) {
                textfield.setText(textfield.getText().concat(String.valueOf(i)));
            }
        }
        if (e.getSource() == decButton) {
            textfield.setText(textfield.getText().concat("."));
        }

        if (e.getSource() == AButton) {
            try {
                a = Double.parseDouble(textfield.getText());
                textfield.setText(textfield.getText().concat("=a"));
            } catch (NumberFormatException g) {
                textfield.setText("hay truyen vao so!");
            }

            CompletableFuture.runAsync(() -> {
                try {
                    TimeUnit.SECONDS.sleep(2); // Dừng 2 giây
                    textfield.setText("");
                } catch (InterruptedException f) {
                    f.printStackTrace();
                }
            });

        }
        if (e.getSource() == BButton) {

            try {
                b = Double.parseDouble(textfield.getText());
                textfield.setText(textfield.getText().concat("=b"));
            } catch (NumberFormatException g) {
                textfield.setText("hay truyen vao so!");
            }
            CompletableFuture.runAsync(() -> {
                try {
                    TimeUnit.SECONDS.sleep(2); // Dừng 2 giây
                    textfield.setText("");
                } catch (InterruptedException f) {
                    f.printStackTrace();
                }
            });

        }
        if (e.getSource() == CButton) {
            try {
                c = Double.parseDouble(textfield.getText());
                textfield.setText(textfield.getText().concat("=c"));
            } catch (NumberFormatException g) {
                textfield.setText("hay truyen vao so!");
            }
            CompletableFuture.runAsync(() -> {
                try {
                    TimeUnit.SECONDS.sleep(1);
                    textfield.setText("");
                } catch (InterruptedException f) {
                    f.printStackTrace();
                }
            });

        }
        if (e.getSource() == equButton) {
            delta = (b * b) - (4 * a * c);
            if (a == 0) {
                textfield.setText("Phuong trinh vo nghiem!\n(a=0)");
            } else if (delta < 0) {
                textfield.setText("Phuong trinh vo nghiem!\n(delta<0)");
            } else {
                x1 = ((-1) * b + Math.sqrt(delta)) / (2 * a);
                x2 = ((-1) * b - Math.sqrt(delta)) / (2 * a);
                textfield.setText("x1=" + x1 + "\nx2=" + x2);
            }

        }
        if (e.getSource() == clrButton) {
            textfield.setText("");
        }
        if (e.getSource() == delButton) {
            String string = textfield.getText();
            textfield.setText("");
            for (int i = 0; i < string.length() - 1; i++) {
                textfield.setText(textfield.getText() + string.charAt(i));
            }
        }
        if (e.getSource() == negButton) {
            double temp = Double.parseDouble(textfield.getText());
            temp *= -1;
            textfield.setText(String.valueOf(temp));
        }

    }

}
