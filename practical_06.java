import java.util.*;

public class PageReplacement {
    
    // Function to simulate FIFO Page Replacement
    public static void fifo(int[] pages, int frames) {
        Queue<Integer> queue = new LinkedList<>();
        int pageFaults = 0;

        System.out.println("\n--- FIFO Page Replacement ---");
        for (int page : pages) {
            if (!queue.contains(page)) {
                if (queue.size() == frames)
                    queue.poll(); // remove oldest page
                queue.add(page);
                pageFaults++;
            }

            System.out.println("Frames: " + queue);
        }
        System.out.println("Total Page Faults (FIFO): " + pageFaults);
    }

    // Function to simulate LRU Page Replacement
    public static void lru(int[] pages, int frames) {
        ArrayList<Integer> list = new ArrayList<>();
        int pageFaults = 0;

        System.out.println("\n--- LRU Page Replacement ---");
        for (int page : pages) {
            if (!list.contains(page)) {
                if (list.size() == frames)
                    list.remove(0); // remove least recently used
                list.add(page);
                pageFaults++;
            } else {
                // move the recently used page to the end (most recently used)
                list.remove((Integer) page);
                list.add(page);
            }

            System.out.println("Frames: " + list);
        }
        System.out.println("Total Page Faults (LRU): " + pageFaults);
    }

    // Main Function
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of frames: ");
        int frames = sc.nextInt();

        System.out.print("Enter number of pages: ");
        int n = sc.nextInt();

        int[] pages = new int[n];
        System.out.println("Enter the page reference string:");
        for (int i = 0; i < n; i++)
            pages[i] = sc.nextInt();

        fifo(pages, frames);
        lru(pages, frames);

        sc.close();
    }
}
