import java.io.*;
import java.util.*;
import java.util.stream.*;

import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'matrixRotation' function below.
     *
     * The function accepts following parameters:
     *  1. 2D_INTEGER_ARRAY matrix
     *  2. INTEGER r
     */

    public static void matrixRotation(List<List<Integer>> matrix, int r) {

        int m = matrix.size();
        int n = matrix.get(0).size();

        // Number of layers
        int layers = Math.min(m, n) / 2;

        for (int layer = 0; layer < layers; layer++) {

            List<Integer> elements = new ArrayList<>();

            int top = layer;
            int left = layer;
            int bottom = m - 1 - layer;
            int right = n - 1 - layer;

            // Top row: left -> right
            for (int j = left; j <= right; j++) {
                elements.add(matrix.get(top).get(j));
            }

            // Right column: top+1 -> bottom
            for (int i = top + 1; i <= bottom; i++) {
                elements.add(matrix.get(i).get(right));
            }

            // Bottom row: right-1 -> left
            for (int j = right - 1; j >= left; j--) {
                elements.add(matrix.get(bottom).get(j));
            }

            // Left column: bottom-1 -> top+1
            for (int i = bottom - 1; i > top; i--) {
                elements.add(matrix.get(i).get(left));
            }

            // Effective rotation
            int len = elements.size();
            int shift = r % len;

            // Anti-clockwise rotation
            List<Integer> rotated = new ArrayList<>();

            for (int i = shift; i < len; i++) {
                rotated.add(elements.get(i));
            }

            for (int i = 0; i < shift; i++) {
                rotated.add(elements.get(i));
            }

            int index = 0;

            // Put values back into the matrix

            // Top row: left -> right
            for (int j = left; j <= right; j++) {
                matrix.get(top).set(j, rotated.get(index++));
            }

            // Right column: top+1 -> bottom
            for (int i = top + 1; i <= bottom; i++) {
                matrix.get(i).set(right, rotated.get(index++));
            }

            // Bottom row: right-1 -> left
            for (int j = right - 1; j >= left; j--) {
                matrix.get(bottom).set(j, rotated.get(index++));
            }

            // Left column: bottom-1 -> top+1
            for (int i = bottom - 1; i > top; i--) {
                matrix.get(i).set(left, rotated.get(index++));
            }
        }

        // Print the final matrix
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(matrix.get(i).get(j));

                if (j < n - 1) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
}

public class Solution {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader =
            new BufferedReader(new InputStreamReader(System.in));

        String[] firstMultipleInput =
            bufferedReader.readLine()
                .replaceAll("\\s+$", "")
                .split(" ");

        int m = Integer.parseInt(firstMultipleInput[0]);
        int n = Integer.parseInt(firstMultipleInput[1]);
        int r = Integer.parseInt(firstMultipleInput[2]);

        List<List<Integer>> matrix = new ArrayList<>();

        IntStream.range(0, m).forEach(i -> {
            try {
                matrix.add(
                    Stream.of(
                        bufferedReader.readLine()
                            .replaceAll("\\s+$", "")
                            .split(" ")
                    )
                    .map(Integer::parseInt)
                    .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        Result.matrixRotation(matrix, r);

        bufferedReader.close();
    }
}

Output:
Input (stdin)
4 4 1
1 2 3 4
5 6 7 8
9 10 11 12
13 14 15 16
Your Output (stdout)
2 3 4 8
1 7 11 12
5 6 10 16
9 13 14 15
Expected Output
2 3 4 8
1 7 11 12
5 6 10 16
9 13 14 15