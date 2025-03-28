import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class SudokuGUI {
    private static final int SIZE = 9;
    private static final int SUBGRID = 3;
    private int[][] board;
    private JTextField[][] cells;

    public SudokuGUI() {
        board = new int[SIZE][SIZE];
        generateSudoku();
        createGUI();
    }

    private void generateSudoku() {
        Random random = new Random();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = (random.nextInt(9) + 1) * (random.nextBoolean() ? 1 : 0); 
            }
        }
    }

    private void createGUI() {
        JFrame frame = new JFrame("Sudoku");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLayout(new GridLayout(SIZE, SIZE));

        cells = new JTextField[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                JTextField cell = new JTextField();
                cell.setHorizontalAlignment(JTextField.CENTER);
                if (board[i][j] != 0) {
                    cell.setText(String.valueOf(board[i][j]));
                    cell.setEditable(false);
                    cell.setBackground(Color.LIGHT_GRAY);
                }
                frame.add(cell);
                cells[i][j] = cell;
            }
        }

        JButton validateButton = new JButton("Validar");
        validateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isValidSudoku()) {
                    JOptionPane.showMessageDialog(frame, "Parabéns! Sudoku resolvido corretamente.");
                } else {
                    JOptionPane.showMessageDialog(frame, "Erro! Há números repetidos.");
                }
            }
        });
        frame.add(validateButton);
        frame.setVisible(true);
    }

    private boolean isValidSudoku() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                String text = cells[i][j].getText();
                if (!text.matches("[1-9]") && !text.isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SudokuGUI::new);
    }
}
