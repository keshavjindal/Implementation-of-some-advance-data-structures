// The preprocessing and solvequery fn's are written:
// to calculate minimum ele in given range.

// we can also do max.
// we can also do sum , gcd (by traversing on binary of right - left + 1)

public class q4part1sparseTableMin
{
    public static void main(String[] args) 
    {
        int[] arr = {2,14,-1,5,7,9,4,18,-3,7,17,19,-9,12,0};

        int[][] sprseTble = preProcessing(arr);
        
        System.out.println(solveQuery(sprseTble, 0, 2));
    }   

    public static int solveQuery(int[][] sprseTble , int left , int right)
    {
        int totalEle = right - left + 1;
        int logfloor = (int)Math.floor(Math.log(totalEle)/Math.log(2));

        int first = sprseTble[left][logfloor];
        int second = sprseTble[right - (int)Math.pow(2 , logfloor) + 1][logfloor];

        return Math.min(first , second);
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

                sprseTble[i][j] = Math.min(val1 , val2);
            }
        }
        
        return sprseTble;
    }
}