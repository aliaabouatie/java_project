import java.util.Arrays;
public class Statistics2 {
    // Java implementation of the approach


        // Function to find the winner of the game
        static void find_winner(String str, int n)
        {

            // To store the strings for both the players
            String str1 = "", str2 = "";
            for (int i = 0; i < n; i++)
            {

                // If the index is even
                if (i % 2 == 0)
                {

                    // Append the current character
                    // to player A's string
                    str1 += str.charAt(i);
                }

                // If the index is odd
                else
                {

                    // Append the current character
                    // to player B's string
                    str2 += str.charAt(i);
                }
            }

            // Sort both the strings to get
            // the lexicographically smallest
            // string possible
            char a[] = str1.toCharArray();
            Arrays.sort(a);
            char b[] = str2.toCharArray();
            Arrays.sort(b);
            str1 = new String(a);
            str2 = new String(b);

            // Compare both the strings to
            // find the winner of the game
            if (str1.compareTo(str2) < 0)
                System.out.print("A");
            else if (str1.compareTo(str2) > 0)
                System.out.print("B");
            else
                System.out.print("Tie");
        }

        // Driver code
        public static void main(String[] args)
        {
            String str = "geeksforgeeks";
            int n = str.length();

            find_winner(str, n);
        }
    }



