	Code:
#include <stdio.h> #include <stdbool.h>
int main() { int n, m;
printf("Enter number of processes: "); scanf("%d", &n);
printf("Enter number of resource types: "); scanf("%d", &m);
int alloc[n][m], max[n][m], need[n][m], avail[m];
// Input Allocation Matrix printf("\nEnter Allocation Matrix:\n"); for (int i = 0; i < n; i++)
for (int j = 0; j < m; j++) scanf("%d", &alloc[i][j]);
// Input Maximum Matrix printf("\nEnter Maximum Matrix:\n"); for (int i = 0; i < n; i++)
for (int j = 0; j < m; j++) scanf("%d", &max[i][j]);
// Input Available Resources printf("\nEnter Available Resources:\n"); for (int j = 0; j < m; j++)
scanf("%d", &avail[j]);
// Calculate Need Matrix = Max - Allocation for (int i = 0; i < n; i++)
for (int j = 0; j < m; j++)
need[i][j] = max[i][j] - alloc[i][j];

bool finish[n];
for (int i = 0; i < n; i++) finish[i] = false;

int safeSequence[n]; int work[m];

for (int i = 0; i < m; i++) work[i] = avail[i];

 
	int count = 0;
while (count < n) { bool found = false;
for (int i = 0; i < n; i++) { if (!finish[i]) {
bool canRun = true;
for (int j = 0; j < m; j++) { if (need[i][j] > work[j]) {
canRun = false; break;
}
}
if (canRun) {
for (int k = 0; k < m; k++) work[k] += alloc[i][k];
safeSequence[count++] = i; finish[i] = true;
found = true;
}
}
}
if (!found) {
printf("\nSystem is in UNSAFE state (deadlock may occur).\n");
return 1;
}
}
// If system is in a safe state
printf("\nSystem is in SAFE state.\nSafe sequence is: "); for (int i = 0; i < n; i++)
printf("P%d ", safeSequence[i]); printf("\n");
return 0;
}
