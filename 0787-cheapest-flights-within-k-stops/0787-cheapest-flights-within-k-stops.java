
class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        List<int[]>[] graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] flight : flights) {
            graph[flight[0]].add(new int[]{flight[1], flight[2]});
        }

        int[] cost = new int[n];
        Arrays.fill(cost, Integer.MAX_VALUE);
        cost[src] = 0;

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{src, 0});

        int stops = 0;

        while (!queue.isEmpty() && stops <= k) {
            int size = queue.size();
            int[] tempCost = cost.clone();

            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll();
                int city = curr[0];
                int currCost = curr[1];

                for (int[] neighbor : graph[city]) {
                    int nextCity = neighbor[0];
                    int price = neighbor[1];

                    if (currCost + price < tempCost[nextCity]) {
                        tempCost[nextCity] = currCost + price;
                        queue.offer(new int[]{nextCity, tempCost[nextCity]});
                    }
                }
            }

            cost = tempCost;
            stops++;
        }

        return cost[dst] == Integer.MAX_VALUE ? -1 : cost[dst];
    }
}