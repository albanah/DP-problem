import java.util.HashMap;
import java.util.Map;

class Main
{
    // Function to check if a cell `(i, j)` is valid or not
    public static boolean isValid(int[][] M, int i, int j) {
        return (i >= 0 && i < M.length && j >= 0 && j < M.length);
    }

    // Find the longest path starting from cell `(i, j)` formed by adjacent
    // numbers in a matrix
    public static String findLongestPath(int[][] M, int i, int j,
                                         Map<String, String> lookup)
    {
        // if the cell is invalid
        if (!isValid (M, i, j)) {
            return null;
        }

        // construct a unique map key from dynamic elements of the input
        String key = i + "|" + j;

        // if the subproblem is seen for the first time, solve it and
        // store its result in a map
        if (!lookup.containsKey(key))
        {
            // string to store path starting `(i, j)`
            String path = null;

            // recur top cell if its value is +1 of value at `(i, j)`
            if (i > 0 && M[i - 1][j] - M[i][j] == 1) {
                path = findLongestPath(M, i - 1, j, lookup);
            }

            // recur right cell if its value is +1 of value at `(i, j)`
            if (j + 1 < M.length && M[i][j + 1] - M[i][j] == 1) {
                path = findLongestPath(M, i, j + 1, lookup);
            }

            // recur bottom cell if its value is +1 of value at `(i, j)`
            if (i + 1 < M.length && M[i + 1][j] - M[i][j] == 1) {
                path = findLongestPath(M, i + 1, j, lookup);
            }

            // recur left cell if its value is +1 of value at `(i, j)`
            if (j > 0 && M[i][j - 1] - M[i][j] == 1) {
                path = findLongestPath(M, i, j - 1, lookup);
            }

            // note that as the matrix contains all distinct elements,
            // there is only one path possible from the current cell

            if (path != null) {
                lookup.put(key, M[i][j] + " — " + path);
            }
            else {
                lookup.put(key, String.valueOf(M[i][j]));
            }
        }

        // return path starting from `(i, j)`
        return lookup.get(key);
    }

    public static void main(String[] args)
    {
        int M[][] =
                {
                        { 10, 13, 14, 21, 23 },
                        { 11, 9, 22, 2, 3 },
                        { 12, 8, 1, 5, 4 },
                        { 15, 24, 7, 6, 20 },
                        { 16, 17, 18, 19, 25 }
                };

        String result = "";                 // stores the longest path found so far
        String str;                         // stores current path
        long res_size = Long.MIN_VALUE;     // stores number of elements in `result`

        // create a map to store solutions to subproblems
        Map<String, String> lookup = new HashMap<>();

        // from each cell `(i, j)`, find the longest path starting from it
        for (int i = 0; i < M.length; i++)
        {
            for (int j = 0; j < M.length; j++)
            {
                // `str` would be like `1 — 2 — 3 — 4 — 5 —`
                str = findLongestPath(M, i, j, lookup);

                // find the number of elements involved in the current path
                long size = str.chars().filter(ch -> ch == '—').count();

                // update result if a longer path is found
                if (size > res_size)
                {
                    result = str;
                    res_size = size;
                }
            }
        }

        // print the path
        System.out.println(result);
    }
}




