package os.work.PageReplacement;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class LRU {
    private int capacity;
    private int[] pages;
    private JTextArea outputArea;

    public LRU(int capacity, int[] pages, JTextArea outputArea) {
        this.capacity = capacity;
        this.pages = pages;
        this.outputArea = outputArea;
    }

    public void run() {
        outputArea.setText("运行 LRU 算法...\n");
        List<Integer> memory = new ArrayList<>(capacity);

        int pageFaults = 0;

        for (int page : pages) {
            if (!memory.contains(page)) {
                if (memory.size() < capacity) {
                    memory.add(page);
                } else {
                    memory.remove(0);
                    memory.add(page);
                }
                pageFaults++;
                outputArea.append("页面缺失: " + memory + "\n");
            } else {
                memory.remove((Integer) page);
                memory.add(page);
                outputArea.append("页面命中: " + memory + "\n");
            }
        }
        double faultRate = (double) pageFaults / pages.length * 100;
        outputArea.append("总页面缺失: " + pageFaults + "\n");
        outputArea.append("缺页率: " + String.format("%.2f", faultRate) + "%\n");
    }
}
