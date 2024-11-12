import java.util.*;

class FCFS {
    public static void main(String[] args) {
        int n;
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter number of processes:");
        n = sc.nextInt();

        if (n <= 0) {
            System.out.println("Number of processes must be positive.");
            return;
        }

        int[] burstTime = new int[n];
        int[] arrivalTime = new int[n];
        int[] process = new int[n];
        int[] waitingTime = new int[n];
        int[] turnaroundTime = new int[n];

        System.out.println("\nEnter Arrival Times:");
        for (int i = 0; i < n; i++) {
            System.out.print("Process[" + (i + 1) + "]: ");
            arrivalTime[i] = sc.nextInt();
        }

        System.out.println("\nEnter Burst Times:");
        for (int i = 0; i < n; i++) {
            System.out.print("Process[" + (i + 1) + "]: ");
            burstTime[i] = sc.nextInt();
            process[i] = i + 1;
            if (burstTime[i] < 0) {
                System.out.println("Burst time cannot be negative.");
                sc.close();
                return;
            }
        }

        // Calculate waiting times for FCFS considering arrival times
        int[] completionTime = new int[n];
        waitingTime[0] = Math.max(0, arrivalTime[0]); // First process waits based on its arrival time
        completionTime[0] = waitingTime[0] + burstTime[0];

        for (int i = 1; i < n; i++) {
            waitingTime[i] = Math.max(0, completionTime[i - 1] - arrivalTime[i]);
            completionTime[i] = arrivalTime[i] + waitingTime[i] + burstTime[i];
        }

        // Calculate turnaround times
        int totalTurnaroundTime = 0;
        System.out.println("\nProcess\tBurst Time\tArrival Time\tWaiting Time\tTurnaround Time");
        for (int i = 0; i < n; i++) {
            turnaroundTime[i] = burstTime[i] + waitingTime[i];
            totalTurnaroundTime += turnaroundTime[i];
            System.out.printf("P%d\t\t%d\t\t%d\t\t%d\t\t%d%n", process[i], burstTime[i], arrivalTime[i], waitingTime[i], turnaroundTime[i]);
        }

        // Calculate average waiting time and average turnaround time
        float averageWaitingTime = (float) Arrays.stream(waitingTime).sum() / n;
        float averageTurnaroundTime = (float) totalTurnaroundTime / n;

        System.out.printf("\nAverage Waiting Time: %.2f%n", averageWaitingTime);
        System.out.printf("Average Turnaround Time: %.2f%n", averageTurnaroundTime);

        sc.close();
    }
}

