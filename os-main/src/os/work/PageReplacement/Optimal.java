package os.work.PageReplacement;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Optimal {
    private int capacity;
    private int[] pages;
    private JTextArea outputArea;

    public Optimal(int capacity, int[] pages, JTextArea outputArea) {
        this.capacity = capacity;
        this.pages = pages;
        this.outputArea = outputArea;
    }

    public void run() {
        outputArea.setText("运行 OPT 算法...\n");
        List<Integer> memory = new ArrayList<>(capacity);
        int pageFaults = 0;

        for (int i = 0; i < pages.length; i++) {
            if (!memory.contains(pages[i])) {
                if (memory.size() < capacity) {
                    memory.add(pages[i]);
                } else {
                    int index = findOPTIndex(memory, i);
                    memory.set(index, pages[i]);
                }
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

    private int findOPTIndex(List<Integer> memory, int currentIndex) {
        int farthest = currentIndex, index = -1;
        for (int i = 0; i < memory.size(); i++) {
            int j;
            for (j = currentIndex; j < pages.length; j++) {
                if (memory.get(i) == pages[j]) {
                    if (j > farthest) {
                        farthest = j;
                        index = i;
                    }
                    break;
                }
            }
            if (j == pages.length) return i;
        }
        return index == -1 ? 0 : index;
    }
}

