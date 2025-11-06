import java.util.*;

public class FileAllocation {
    static Scanner sc = new Scanner(System.in);

    static void sequentialAllocation() {
        System.out.print("Enter total number of blocks: ");
        int n = sc.nextInt();
        int[] disk = new int[n];
        Arrays.fill(disk, 0);

        System.out.print("Enter starting block: ");
        int start = sc.nextInt();
        System.out.print("Enter length of file: ");
        int len = sc.nextInt();

        int flag = 0;
        for (int i = start; i < (start + len); i++) {
            if (i >= n || disk[i] == 1) {
                flag = 1;
                break;
            }
        }

        if (flag == 0) {
            for (int i = start; i < (start + len); i++)
                disk[i] = 1;

            System.out.println("File allocated successfully.");
            System.out.print("Blocks occupied: ");
            for (int i = start; i < (start + len); i++)
                System.out.print(i + " ");
            System.out.println();
        } else {
            System.out.println("File cannot be allocated (space not available).");
        }
    }

    static void linkedAllocation() {
        System.out.print("Enter total number of blocks: ");
        int n = sc.nextInt();
        int[] disk = new int[n];
        Arrays.fill(disk, 0);

        System.out.print("Enter number of blocks required: ");
        int len = sc.nextInt();

        System.out.println("Enter the block numbers you want to allocate:");
        int count = 0;
        for (int i = 0; i < len; i++) {
            int block = sc.nextInt();
            if (block >= n || disk[block] == 1) {
                System.out.println("Block " + block + " not available.");
            } else {
                disk[block] = 1;
                count++;
            }
        }

        if (count == len)
            System.out.println("File allocated successfully.");
        else
            System.out.println("File not fully allocated.");
    }

    static void indexedAllocation() {
        System.out.print("Enter total number of blocks: ");
        int n = sc.nextInt();
        int[] disk = new int[n];
        Arrays.fill(disk, 0);

        System.out.print("Enter index block: ");
        int index = sc.nextInt();
        if (index >= n || disk[index] == 1) {
            System.out.println("Index block not available.");
            return;
        }
        disk[index] = 1;

        System.out.print("Enter number of blocks required: ");
        int len = sc.nextInt();
        System.out.println("Enter the block numbers to be allocated:");
        int count = 0;
        for (int i = 0; i < len; i++) {
            int block = sc.nextInt();
            if (block >= n || disk[block] == 1) {
                System.out.println("Block " + block + " not available.");
            } else {
                disk[block] = 1;
                count++;
            }
        }

        if (count == len)
            System.out.println("File allocated successfully using index block " + index);
        else
            System.out.println("File not fully allocated.");
    }

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n--- File Allocation Strategies ---");
            System.out.println("1. Sequential Allocation");
            System.out.println("2. Linked Allocation");
            System.out.println("3. Indexed Allocation");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1 -> sequentialAllocation();
                case 2 -> linkedAllocation();
                case 3 -> indexedAllocation();
                case 4 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice!");
            }
        } while (choice != 4);
    }
}
