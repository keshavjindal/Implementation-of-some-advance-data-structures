import java.util.Arrays;

public class q3segmentTree 
{
    public static void main(String[] args) 
    {
        int[] arr = new int[20];
        int n = arr.length;

        stree = new int[2 * n + 4];

        buildSegmentTree(arr , 1 , 0 , arr.length-1);

        System.out.println(Arrays.toString(stree)); 
        int qsi = 1;
        int qei = 3;
        System.out.println(solveQuery(1, 0, arr.length-1, qsi, qei));
    }

    public static int[] stree;

    public static void buildSegmentTree(int[] arr, int idx, int lo, int hi) 
    {
        if(lo == hi)
        {
            stree[idx] = arr[lo];
            return;
        }

        int mid = (lo + hi)/2;

        buildSegmentTree(arr,   2 * idx,     lo,      mid);
        buildSegmentTree(arr, 2 * idx + 1,   mid + 1, hi);

        stree[idx] = stree[2 * idx] + stree[2 * idx + 1];
    }

    public static int solveQuery(int idx, int ssi, int sei, int qsi, int qei)
    {
        if(qei < ssi || sei < qsi) return 0;
        else if(ssi >= qsi && sei <= qei)
        {
            return stree[idx];
        }
        else
        {
            int mid = (ssi + sei)/2;

            int left = solveQuery(2 * idx,       ssi, mid,   qsi, qei);
            int right = solveQuery(2 * idx + 1,  mid+1, sei, qsi, qei);

            return left + right;
        }
    }
}