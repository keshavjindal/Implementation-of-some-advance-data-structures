public class dsu 
{
    private int arraySize;
    private int[] parent;
    private int[] rank;

    public dsu(int arraysize)
    {
        this.arraySize = arraysize;
        
        parent = new int[arraySize];
        for(int i=0; i<parent.length; i++) parent[i] = i;
        
        rank = new int[arraySize];
        for(int i=0; i<rank.length; i++) rank[i] = 1;
    }

    // 4 lines of code
    public int find(int x)
    {
        if(x == parent[x]) return x;

        int leaderofparentofx = find(parent[x]);

        parent[x] = leaderofparentofx; // path compression

        return leaderofparentofx;
    }

    // union by rank
    public void union(int x, int y)
    {
        int lx = find(x);
        int ly = find(y);

        if(lx != ly)
        {
            if(rank[lx] > rank[ly]) // jiski rank jyada vo baap bnta h.
            {
                parent[ly] = lx;
            }
            else if(rank[ly] > rank[lx])
            {
                parent[lx] = ly;
            }
            else
            {
                parent[lx] = ly;
                rank[ly]++;
            }
        }
    }

    public void printset()
    {
        for(int i=0; i<parent.length; i++)
        {
            System.out.println(i + " -> " + find(i));
        }
    }
}