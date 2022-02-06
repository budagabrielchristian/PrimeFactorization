import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;

public class PrimeFactorize implements ActionListener {
    private final JFrame frame;
    protected JLabel displayPrimeFactors, title;
    protected JTextField inputNumberField;
    protected JButton submitNumberButton;
    protected Integer inputtedNumber;

    PrimeFactorize() {
        Font textFont = new Font("Georgia", Font.PLAIN, 30);

        inputNumberField = new JTextField(200);
        inputNumberField.setHorizontalAlignment(JTextField.CENTER);
        inputNumberField.setBounds(90, 130, 400, 100);
        inputNumberField.setPreferredSize(new Dimension(150, 100));
        inputNumberField.setFont(textFont);

        submitNumberButton = new JButton("Submit Number");
        submitNumberButton.setFont(textFont);
        submitNumberButton.setBounds(150, 300, 300, 100);
        submitNumberButton.setBackground(Color.WHITE);
        submitNumberButton.setFocusable(false);
        submitNumberButton.addActionListener(this);

        title = new JLabel("Input a number to get its factors.");
        title.setFont(textFont);
        title.setBounds(70, 0, 600,100);

        displayPrimeFactors = new JLabel();
        displayPrimeFactors.setBounds(150, 450,600,100);
        displayPrimeFactors.setFont(textFont);

        frame = new JFrame("Prime Factorization Program");
        frame.setSize(new Dimension(600, 600));
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(displayPrimeFactors);
        frame.add(inputNumberField);
        frame.add(submitNumberButton);
        frame.add(title);
        frame.setVisible(true);
    }

    public boolean verifyForStringInput(String input) {
        try {
            Integer number = Integer.parseInt(input);
            return true;
        } catch (NumberFormatException number) {
            return false;
        }
    }

    public Integer convertToInteger(String input) {
        Integer number = Integer.parseInt(input);
        return number;
    }

    public String getPrimeFactors(Integer input) {
        LinkedList<Integer> primeFactors = new LinkedList<>();
        HashMap<Integer, Integer> sortedPrimeFactors = new HashMap<>();
        String outputFactors = "";

        if(input == 0){
            return null;
        }
        int divideInputBy = 2;
        while (input > 1) {
            if (input % divideInputBy == 0) {
                primeFactors.add(divideInputBy);
                input = input / divideInputBy;
            } else {
                divideInputBy++;
            }
            for (int factor : primeFactors) {
                int occurrences = Collections.frequency(primeFactors, factor);
                sortedPrimeFactors.put(factor, occurrences);
            }
        }
        for(int key:sortedPrimeFactors.keySet()){
            outputFactors+=key+"^"+sortedPrimeFactors.get(key)+"; ";
        }
        return outputFactors;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitNumberButton) {
            String input = inputNumberField.getText();

            if (verifyForStringInput(input)) {
                inputtedNumber = convertToInteger(input);
                displayPrimeFactors.setText("Factors: " + getPrimeFactors(inputtedNumber));
            } else {
                JOptionPane.showConfirmDialog(frame, "Invalid Input", "!", JOptionPane.CLOSED_OPTION);
            }
        }
    }
}
