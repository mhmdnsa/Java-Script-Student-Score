import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class KarnamehApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new KarnamehGUI().createAndShowGUI());
    }
}

class KarnamehGUI {
    private JTextField[] gradeFields = new JTextField[10];
    private JLabel resultLabel = new JLabel(" ");
    private JLabel prizeLabel = new JLabel(" ");

    private String[] lessons = {
            "Riazi", "Fizik", "Shimi", "Zaban", "Tarikh",
            "Joghrafia", "Adabiat", "Din o Zendegi", "Fannavari", "Varzesh"
    };

    public void createAndShowGUI() {
        JFrame frame = new JFrame("Karnameh Danesh Amouz");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 550);
        frame.setLayout(new BorderLayout());

        // Title
        JLabel title = new JLabel("🔖 Karnameh Darsi", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        frame.add(title, BorderLayout.NORTH);

        // Main form (center)
        JPanel formPanel = new JPanel(new GridLayout(lessons.length, 2, 10, 5));
        for (int i = 0; i < lessons.length; i++) {
            formPanel.add(new JLabel(lessons[i] + ":", SwingConstants.RIGHT));
            gradeFields[i] = new JTextField();
            formPanel.add(gradeFields[i]);
        }
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        frame.add(formPanel, BorderLayout.CENTER);

        // Bottom area: Button + result + prize
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));

        JButton calcButton = new JButton("Mohasebe Moadel");
        calcButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        bottomPanel.add(calcButton);

        resultLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        resultLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        bottomPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        bottomPanel.add(resultLabel);

        prizeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        prizeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        prizeLabel.setForeground(Color.BLUE);
        bottomPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        bottomPanel.add(prizeLabel);

        frame.add(bottomPanel, BorderLayout.SOUTH);

        // Button action
        calcButton.addActionListener(e -> calculateAverage());

        frame.setVisible(true);
    }

    private void calculateAverage() {
        double sum = 0;
        int count = 0;
        try {
            for (JTextField field : gradeFields) {
                double grade = Double.parseDouble(field.getText());
                if (grade < 0 || grade > 20) {
                    throw new NumberFormatException();
                }
                sum += grade;
                count++;
            }

            double average = sum / count;
            resultLabel.setText("Moadel: " + String.format("%.2f", average));

            if (average >= 18) {
                String[] prizes = {
                        "🎁 Kart hadiye 100 toman",
                        "🎧 Headphone bi-sim",
                        "☕ Mag Gaming",
                        "🎟️ Belit Concert",
                        "🎮 Daste Bazi"
                };
                String prize = prizes[new Random().nextInt(prizes.length)];
                prizeLabel.setText("🎉 Aferin! Jayeze: " + prize);
            } else {
                prizeLabel.setText("Talash kon ta dafe badi jayeze begiri 💪");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Lotfan hame nomre ha ra be dorosti vared kon (0 ta 20)", "Khatā",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}