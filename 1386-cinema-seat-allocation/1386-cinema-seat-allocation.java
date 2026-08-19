import java.util.*;

class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {

        Map<Integer, Integer> map = new HashMap<>();

        // Store reserved seats as bitmask
        for (int[] seat : reservedSeats) {
            int row = seat[0];
            int col = seat[1];

            map.put(
                row,
                map.getOrDefault(row, 0) | (1 << (col - 1))
            );
        }

        // Rows with no reservations can always fit 2 families
        int ans = (n - map.size()) * 2;

        // Seats 2,3,4,5
        int left = 0b0000011110;

        // Seats 4,5,6,7
        int middle = 0b0001111000;

        // Seats 6,7,8,9
        int right = 0b111100000;

        for (int seats : map.values()) {

            boolean leftFree = (seats & left) == 0;
            boolean middleFree = (seats & middle) == 0;
            boolean rightFree = (seats & right) == 0;

            if (leftFree && rightFree) {
                ans += 2;
            } 
            else if (leftFree || middleFree || rightFree) {
                ans += 1;
            }
        }

        return ans;
    }
}