package su.demands.cobt;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class CObT {

    public static void main(String[] args) {

        Controller controller = new Controller();

        JFrame form = new JFrame("Code Optimizer by Triads");

        form.setResizable(false);
        URL iconURL = CObT.class.getClassLoader().getResource("image/icon.png");
        assert iconURL != null;
        form.setIconImage(new ImageIcon(iconURL).getImage());
        form.setSize(325, 260);
        form.setLayout(null);
        form.setLocation(300,200);

        JLabel label = new JLabel("Enter the triad equation:");
        label.setBounds(80,0,200, 20);
        form.getContentPane().add(label);
        final JTextArea textArea = new JTextArea(10, 40);
        textArea.setBounds(0,20,325,160);
        form.getContentPane().add(textArea);

        final JButton buttonOptimize = new JButton("optimize");
        buttonOptimize.setBounds(20,185,120,30);
        final JButton buttonClear = new JButton("clear");
        buttonClear.setBounds(170,185,120,30);

        buttonOptimize.addActionListener(event -> {
            try {
                textArea.setEditable(false);

                controller.processTriads(textArea.getText());

                textArea.append("\n");

                while (controller.hasMoreLines()) {
                    textArea.append(controller.nextLine());
                }
            } catch (Exception x) {
                JOptionPane.showMessageDialog(form, "Invalid input!","Error",JOptionPane.ERROR_MESSAGE);
            }
        });

        buttonClear.addActionListener(event -> {
            textArea.setText("");
            textArea.setEditable(true);
        });

        form.getContentPane().add(buttonOptimize);
        form.getContentPane().add(buttonClear);

        form.setVisible(true);
    }
}
