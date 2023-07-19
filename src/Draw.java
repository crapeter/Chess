import javax.swing.*;
import java.awt.*;

public class Draw implements Images, SetupVars{
    public static boolean white = true;
    public void draw() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(950, 950);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        textField.setBackground(Color.white.darker());
        textField.setForeground(Color.black);
        textField.setFont(new Font("Verdana", Font.PLAIN, 75));
        textField.setHorizontalAlignment(JLabel.CENTER);
        textField.setText("Chess");
        textField.setOpaque(true);

        titlePanel.setLayout(new BorderLayout());
        titlePanel.setBounds(0, 0, 800, 100);

        panel.setLayout(new GridLayout(8, 8));
        panel.setEnabled(false);
        panel.setBackground(Color.black);

        for (int i = 0; i < 64; i++) {
            buttons[i] = new JButton();
            panel.add(buttons[i]);
            buttons[i].setFocusable(false);

            if (white)
                buttons[i].setBackground(new Color(95, 126, 250).brighter());
            else
                buttons[i].setBackground(new Color(0, 31, 156));

            if (i % 8 != 7)
                white = !white;
        }
        titlePanel.add(textField);
        frame.add(titlePanel, BorderLayout.NORTH);
        frame.add(panel);
        frame.setVisible(true);
    }
}
