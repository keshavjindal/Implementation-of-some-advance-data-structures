public class q4part2sparseTableSum
{
    public static void main(String[] args) 
    {
        int[] arr = {2,14,-1,5,7,9,4,18,-3,7,17,19,-9,12,0};

        int[][] sprseTble = preProcessing(arr);
        
        System.out.println(solveQuery(sprseTble, 2 , 1));
    }   

    // for sum
    public static int solveQuery(int[][] sprseTble , int left , int right)
    {
        int totalEle = right - left + 1;
        String s = Integer.toBinaryString(totalEle);

        int ans = 0;
        for(int i=0; i<s.length(); i++)
        {
            char ch = s.charAt(i);

            if(ch == '1')
            {
                ans += sprseTble[left][s.length()-i-1];
                left = left + (int)Math.pow(2 , s.length()-i-1);
            }
        }
        return ans;
    }

    public static int[][] preProcessing(int[] arr)
    {
        int n = arr.length;
        int m = (int)Math.ceil(Math.log(n)/Math.log(2));

        int[][] sprseTble = new int[n][m]; // sparse Table
        
        for(int j=0; j<m; j++)
        {
            for(int i=0; i<n; i++)
            {
                if(j == 0)
                {
                    sprseTble[i][j] = arr[i];
                    continue;
                }

                if(i + (int)Math.pow(2 , j-1) >= n) continue;

                int val1 = sprseTble[i][j-1];
                int val2 = sprseTble[i + (int)Math.pow(2 , j-1)][j-1];

                sprseTble[i][j] = val1 + val2; // Line Of Observance
            }
        }
        
        return sprseTble;
    }
}