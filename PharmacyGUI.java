import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;

public class PharmacyGUI extends JFrame {
    private PharmacyQueue pharmacyQueue;
    private JTextArea textArea;
    private JTextField nameField;
    private JTextField priorityField;

    public PharmacyGUI() {
        pharmacyQueue = new PharmacyQueue();
        setTitle("Pharmacy Priority Queue");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        textArea = new JTextArea();
        textArea.setEditable(false);
        add(new JScrollPane(textArea), BorderLayout.CENTER);

        JPanel inputPanel = new JPanel();
        nameField = new JTextField(10);
        priorityField = new JTextField(5);
        JButton addButton = new JButton("Add Medication");
        JButton nextButton = new JButton("Next Medication");

        inputPanel.add(new JLabel("Medication Name:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Priority:"));
        inputPanel.add(priorityField);
        inputPanel.add(addButton);
        inputPanel.add(nextButton);
        add(inputPanel, BorderLayout.SOUTH);

        addButton.addActionListener(new AddMedicationListener());
        nextButton.addActionListener(new NextMedicationListener());
    }

    private class AddMedicationListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = nameField.getText();
            int priority;
            try {
                priority = Integer.parseInt(priorityField.getText());
                Medication medication = new Medication(name, priority);
                pharmacyQueue.addMedication(medication);
                textArea.append("Added: " + name + " with priority " + priority + "\n");
                saveToFile(name, priority);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(PharmacyGUI.this, "Please enter a valid priority number.");
            }
        }
    }

    private class NextMedicationListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!pharmacyQueue.isEmpty()) {
                Medication nextMed = pharmacyQueue.getNextMedication();
                textArea.append("Next Medication: " + nextMed.getName() + " with priority " + nextMed.getPriority() + "\n");
            } else {
                textArea.append("No medications in the queue.\n");
            }
        }
    }

    private void saveToFile(String name, int priority) {
        try (FileWriter writer = new FileWriter("Test.txt", true)) {
            writer.write("Medication: " + name + ", Priority: " + priority + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PharmacyGUI gui = new PharmacyGUI();
            gui.setVisible(true);
        });
    }
}
