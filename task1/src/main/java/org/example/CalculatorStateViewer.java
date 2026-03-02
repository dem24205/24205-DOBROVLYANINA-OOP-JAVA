package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Графический просмотрщик сохранённых состояний калькулятора.
 * Загружает JSON файл и отображает стек, переменные и историю команд.
 */
public class CalculatorStateViewer {
    private static final Logger logger = LoggerFactory.getLogger(CalculatorStateViewer.class);

    /**
     * Точка входа в программу.
     * Принимает имя JSON файла как аргумент командной строки.
     * Загружает состояние и отображает его в окне.
     *
     * @param args аргументы командной строки (первый аргумент - имя файла)
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            logger.warn("Usage: java CalculatorStateViewer <filename.json>");
            return;
        }

        String filename = args[0];
        File file = new File(filename);
        if (!file.exists()) {
            logger.error("File does not exist: {}", filename);
            return;
        }

        try {
            ObjectMapper mapper = new ObjectMapper();
            CalculatorState state = mapper.readValue(file, CalculatorState.class);

            JFrame frame = new JFrame("Calculator state");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 500);

            JLabel stackLabel = new JLabel("Stack");
            stackLabel.setFont(new Font("Monospaced", Font.BOLD, 14));
            DefaultListModel<String> stackModel = new DefaultListModel<>();
            JList<String> stackList = new JList<>(stackModel);
            stackList.setFont(new Font("Monospaced", Font.PLAIN, 12));
            if (state.getStack() != null) {
                List<Double> reversed = new ArrayList<>(state.getStack());
                Collections.reverse(reversed);
                for (Double value : reversed) {
                    stackModel.addElement(String.valueOf(value));
                }
            }
            JScrollPane stackScroll = new JScrollPane(stackList);
            JPanel leftPanel = new JPanel(new BorderLayout());
            leftPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
            leftPanel.add(stackLabel, BorderLayout.NORTH);
            leftPanel.add(stackScroll, BorderLayout.CENTER);

            JLabel varsLabel = new JLabel("Variables");
            varsLabel.setFont(new Font("Monospaced", Font.BOLD, 14));
            DefaultListModel<String> varsModel = new DefaultListModel<>();
            JList<String> varsList = new JList<>(varsModel);
            varsList.setFont(new Font("Monospaced", Font.PLAIN, 12));
            if (state.getVariables() != null) {
                for (String key : state.getVariables().keySet()) {
                    varsModel.addElement(key + " = " + state.getVariables().get(key));
                }
            }
            JScrollPane varsScroll = new JScrollPane(varsList);
            JPanel centerPanel = new JPanel(new BorderLayout());
            centerPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
            centerPanel.add(varsLabel, BorderLayout.NORTH);
            centerPanel.add(varsScroll, BorderLayout.CENTER);

            JLabel historyLabel = new JLabel("Command history");
            historyLabel.setFont(new Font("Monospaced", Font.BOLD, 14));
            JTextArea historyArea = new JTextArea();
            historyArea.setEditable(false);
            historyArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
            historyArea.setRows(10);
            if (state.getHistory() != null) {
                for (String cmd : state.getHistory()) {
                    historyArea.append("> " + cmd + "\n");
                }
            }
            JScrollPane historyScroll = new JScrollPane(historyArea);
            JPanel rightPanel = new JPanel(new BorderLayout());
            rightPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
            rightPanel.add(historyLabel, BorderLayout.NORTH);
            rightPanel.add(historyScroll, BorderLayout.CENTER);

            JPanel mainPanel = new JPanel(new GridLayout(1, 3, 10, 0));
            mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
            mainPanel.add(leftPanel);
            mainPanel.add(centerPanel);
            mainPanel.add(rightPanel);
            frame.add(mainPanel);
            frame.setVisible(true);
        } catch (Exception e) {
            logger.error("Error loading file: {}", e.getMessage());
        }
    }
}