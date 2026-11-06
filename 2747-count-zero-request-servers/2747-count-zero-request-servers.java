
import java.util.*;

class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {

        Arrays.sort(logs, (a, b) -> Integer.compare(a[1], b[1]));

        int m = queries.length;

        int[][] q = new int[m][2];

        for (int i = 0; i < m; i++) {
            q[i][0] = queries[i];
            q[i][1] = i;
        }

        Arrays.sort(q, (a, b) -> Integer.compare(a[0], b[0]));

        int[] ans = new int[m];
        int[] freq = new int[n + 1];

        int left = 0;
        int right = 0;
        int activeServers = 0;

        for (int[] query : q) {

            int time = query[0];
            int index = query[1];

            int start = time - x;

            // Add logs within the right boundary
            while (right < logs.length && logs[right][1] <= time) {

                int id = logs[right][0];

                if (freq[id] == 0) {
                    activeServers++;
                }

                freq[id]++;
                right++;
            }

            // Remove logs outside the left boundary
            while (left < right && logs[left][1] < start) {

                int id = logs[left][0];

                freq[id]--;

                if (freq[id] == 0) {
                    activeServers--;
                }

                left++;
            }

            ans[index] = n - activeServers;
        }

        return ans;
    }
}
