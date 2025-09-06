#include <stdio.h>
#include <limits.h>

struct Process {
    int pid, at, bt, ct, tat, wt;
};

int main() {
    int n;
    printf("Enter number of processes: ");
    scanf("%d", &n);

    struct Process p[n];
    for (int i = 0; i < n; i++) {
        p[i].pid = i + 1;
        printf("Enter Arrival Time of P%d: ", i + 1);
        scanf("%d", &p[i].at);
        printf("Enter Burst Time of P%d: ", i + 1);
        scanf("%d", &p[i].bt);
        p[i].ct = 0;
    }

    int completed = 0, time = 0;
    while (completed != n) {
        int idx = -1, minBT = INT_MAX;
        for (int i = 0; i < n; i++) {
            if (p[i].at <= time && p[i].ct == 0 && p[i].bt < minBT) {
                minBT = p[i].bt;
                idx = i;
            }
        }

        if (idx != -1) {
            time += p[idx].bt;
            p[idx].ct = time;
            completed++;
        } else {
            time++;
        }
    }

    float totalTAT = 0, totalWT = 0;
    printf("\n--- Non-Preemptive SJF Scheduling ---\n");
    printf("PID\tAT\tBT\tCT\tTAT\tWT\n");
    for (int i = 0; i < n; i++) {
        p[i].tat = p[i].ct - p[i].at;
        p[i].wt = p[i].tat - p[i].bt;
        totalTAT += p[i].tat;
        totalWT += p[i].wt;
        printf("%d\t%d\t%d\t%d\t%d\t%d\n", p[i].pid, p[i].at, p[i].bt, p[i].ct, p[i].tat, p[i].wt);
    }
    printf("\nAverage TAT: %.2f\n", totalTAT / n);
    printf("Average WT: %.2f\n", totalWT / n);

    return 0;
}
