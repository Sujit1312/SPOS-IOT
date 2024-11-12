import java.util.Scanner;

public class LRU1 {
    public static int min(int counter[], int nFrames) {
        int minimum = counter[0];
        int pos = 0;
        for (int i = 0; i < nFrames; i++) {
            if (minimum > counter[i]) {
                pos = i;
                minimum = counter[i];
            }
        }
        return pos;
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n, recent = 0, pageFault = 0, nFrames;

        // Input number of pages
        System.out.print("Enter the number of pages: ");
        n = s.nextInt();

        int pageString[] = new int[n];

        // Input page reference string
        System.out.print("Enter the page reference string: ");
        for (int i = 0; i < n; i++) {
            pageString[i] = s.nextInt();
        }

        // Input number of frames
        System.out.print("\nEnter the number of frames: ");
        nFrames = s.nextInt();

        int frames[] = new int[nFrames];
        int counter[] = new int[nFrames];

        // Initialize frames and counters
        for (int i = 0; i < nFrames; i++) {
            frames[i] = 0;
            counter[i] = 0;
        }

        // Process each page in the reference string
        for (int i = 0; i < n; i++) {
            int flag = 0;

            // Check if the page is already in a frame
            for (int j = 0; j < nFrames; j++) {
                if (frames[j] == pageString[i]) {
                    flag = 1;
                    counter[j] = recent++;
                    break;
                }
            }

            // If the page is not in a frame, place it in an empty frame
            if (flag == 0) {
                for (int j = 0; j < nFrames; j++) {
                    if (frames[j] == 0) {
                        frames[j] = pageString[i];
                        counter[j] = recent++;
                        flag = 1;
                        pageFault++;
                        break;
                    }
                }
            }

            // If all frames are full, replace the least recently used page
            if (flag == 0) {
                int PositionToreplace = min(counter, nFrames);
                frames[PositionToreplace] = pageString[i];
                counter[PositionToreplace] = recent++;
                pageFault++;
            }

            // Display the current state of the frames
            for (int j = 0; j < nFrames; j++) {
                System.out.print(frames[j] + " ");
            }
            System.out.println();
        }

        // Output the total number of page faults
        System.out.print("\nPage Faults: " + pageFault);
    }
}
