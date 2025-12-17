import java.util.*;

public class problem1 {

    public static int largestRectangleHistogram(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;

        int[] newHeights = Arrays.copyOf(heights, heights.length + 1);

        for (int i = 0; i < newHeights.length; i++) {
            while (!stack.isEmpty() && newHeights[stack.peek()] > newHeights[i]) {
                int height = newHeights[stack.pop()];
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                maxArea = Math.max(maxArea, height * width);
            }
            stack.push(i);
        }

        return maxArea;
    }

    public static int maximalRectangle(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;

        int m = matrix.length;
        int n = matrix[0].length;
        int[] heights = new int[n];
        int maxArea = 0;

        for (int i = 0; i < m; i++) {

            for (int j = 0; j < n; j++) {
                heights[j] = matrix[i][j] == 1 ? heights[j] + 1 : 0;
            }

            maxArea = Math.max(maxArea, largestRectangleHistogram(heights));
        }

        return maxArea;
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 1, 0, 0},
                {1, 0, 1, 1},
                {1, 0, 1, 1},
                {0, 1, 0, 0}
        };

        System.out.println(maximalRectangle(matrix));
    }
}
