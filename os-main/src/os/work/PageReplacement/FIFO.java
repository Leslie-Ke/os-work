package os.work.PageReplacement;

import javax.swing.*;
import java.util.*;

public class FIFO {
    private int capacity;
    private int[] pages;
    private JTextArea outputArea;

    public FIFO(int capacity, int[] pages, JTextArea outputArea) {
        this.capacity = capacity;
        this.pages = pages;
        this.outputArea = outputArea;
    }

    public void run() {
        outputArea.setText("运行 FIFO 算法...\n");
        Queue<Integer> memory = new LinkedList<>();
        int pageFaults = 0;

        for (int page : pages) {
            if (!memory.contains(page)) {
                if (memory.size() == capacity) {
                    memory.poll();
                }
                memory.add(page);
                pageFaults++;
                outputArea.append("页面缺失: " + memory + "\n");
            } else {
                outputArea.append("页面命中: " + memory + "\n");
            }
        }
        double faultRate = (double) pageFaults / pages.length * 100;
        outputArea.append("总页面缺失: " + pageFaults + "\n");
        outputArea.append("缺页率: " + String.format("%.2f", faultRate) + "%\n");
    }
}
