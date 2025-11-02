import java.util.*;

public class DiskScheduling {

    // Function for FCFS Disk Scheduling
    static void fcfs(int[] requests, int head) {
        int totalMovement = 0;
        System.out.println("\n--- FCFS (First Come First Serve) ---");
        System.out.print("Sequence of Head Movement: " + head);

        for (int i = 0; i < requests.length; i++) {
            totalMovement += Math.abs(head - requests[i]);
            head = requests[i];
            System.out.print(" -> " + head);
        }

        double average = (double) totalMovement / requests.length;
        System.out.println("\nTotal Head Movement: " + totalMovement);
        System.out.printf("Average Head Movement: %.2f\n", average);
    }

    // Function for SSTF Disk Scheduling
    static void sstf(int[] requests, int head) {
        boolean[] visited = new boolean[requests.length];
        int totalMovement = 0, completed = 0;
        int current = head;

        System.out.println("\n--- SSTF (Shortest Seek Time First) ---");
        System.out.print("Sequence of Head Movement: " + head);

        while (completed < requests.length) {
            int minDistance = Integer.MAX_VALUE;
            int index = -1;

            // Find the nearest unvisited request
            for (int i = 0; i < requests.length; i++) {
                if (!visited[i]) {
                    int distance = Math.abs(current - requests[i]);
                    if (distance < minDistance) {
                        minDistance = distance;
                        index = i;
                    }
                }
            }

            visited[index] = true;
            totalMovement += minDistance;
            current = requests[index];
            completed++;
            System.out.print(" -> " + current);
        }

        double average = (double) totalMovement / requests.length;
        System.out.println("\nTotal Head Movement: " + totalMovement);
        System.out.printf("Average Head Movement: %.2f\n", average);
    }

    // Main method
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Disk Scheduling Simulation (FCFS & SSTF)");
        System.out.print("Enter number of requests: ");
        int n = sc.nextInt();

        int[] requests = new int[n];
        System.out.println("Enter the disk requests:");
        for (int i = 0; i < n; i++) {
            requests[i] = sc.nextInt();
        }

        System.out.print("Enter initial head position: ");
        int head = sc.nextInt();

        fcfs(requests, head);
        sstf(requests, head);

        sc.close();
    }
}
