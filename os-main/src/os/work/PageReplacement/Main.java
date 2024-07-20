package os.work.PageReplacement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class Main extends JFrame {
    private JTextArea outputArea;
    private JButton btnOPT, btnFIFO, btnLRU;
    private JTextField inputPages, inputCapacity;
    private int[] pages;
    private int capacity;

    public Main() {
        setTitle("页面置换算法演示");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);

        inputPages = new JTextField(20);
        inputCapacity = new JTextField(5);
        btnOPT = new JButton("OPT");
        btnFIFO = new JButton("FIFO");
        btnLRU = new JButton("LRU");

        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("页面序列:"));
        inputPanel.add(inputPages);
        inputPanel.add(new JLabel("内存容量:"));
        inputPanel.add(inputCapacity);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnOPT);
        buttonPanel.add(btnFIFO);
        buttonPanel.add(btnLRU);

        btnOPT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (parseInput()) {
                    runOPT();
                }
            }
        });

        // 为FIFO按钮添加动作监听器
        btnFIFO.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (parseInput()) {
                    runFIFO();
                }
            }
        });

        btnLRU.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (parseInput()) {
                    runLRU();
                }
            }
        });

        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private boolean parseInput() {
        try {
            String[] pageStrings = inputPages.getText().split(",");
            pages = Arrays.stream(pageStrings).mapToInt(Integer::parseInt).toArray();
            capacity = Integer.parseInt(inputCapacity.getText());
            if (capacity <= 0) throw new NumberFormatException();
            return true;
        } catch (NumberFormatException e) {
            outputArea.setText("输入无效。请输入以逗号分隔的整数页面序列和一个正整数容量。\n");
            return false;
        }
    }

    private void runOPT() {
        Optimal optimal = new Optimal(capacity, pages, outputArea);
        optimal.run();
    }

    // 运行FIFO算法的方法
    private void runFIFO() {
        FIFO fifo = new FIFO(capacity, pages, outputArea);
        fifo.run();
    }

    // 运行LRU算法的方法
    private void runLRU() {
        LRU lru = new LRU(capacity, pages, outputArea);
        lru.run();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main().setVisible(true);
            }
        });
    }
}
