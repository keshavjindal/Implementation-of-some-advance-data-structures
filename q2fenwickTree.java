public class q2fenwickTree
{
    public static void main(String[] args) 
    {
        int[] givenArray = {5 , 10 , 15 , 20 , 25};

        int[] ftree = preProcessing(givenArray);

        System.out.println(findsum(ftree , 0 , 1)); 

        addDelta(ftree, 0, 5);
        
        System.out.println(findsum(ftree , 0 , 1));
    }

    public static int findsum(int[] ftree, int i , int j)
    {
        return sumQuery(ftree, i + 1, j + 1); // because 1-based indexing.
    }

    public static void addDelta(int[] ftree, int i , int val)
    {
        update(ftree, i + 1, val); // because 1-based indexing.
    }

    public static int[] preProcessing(int[] arr)
    {
        int n = arr.length;
        int[] newarr = new int[n + 1];
        int[] ftree = new int[n + 1];

        for(int i=1; i<=n; i++)
        {
            newarr[i] = arr[i - 1];
        }

        for(int i=1; i<=n; i++)
        {
            int val = newarr[i];
            update(ftree, i, val);
        }

        return ftree;
    }

    public static void update(int[] ftree , int i , int val)
    {
        while(i < ftree.length)
        {
            ftree[i] += val;
            i = i + (i & (-i));
        }
    }

    public static int sumQuery(int[] ftree , int i , int j)
    {
        if(i == 1) return sum(ftree, j);
        else return sum(ftree, j) - sum(ftree, i - 1);
    }

    public static int sum(int[] ftree , int i)
    {
        int ans = 0;
        while(i > 0)
        {
            ans += ftree[i];
            i = i - (i & (-i));
        }
        return ans;
    }
}