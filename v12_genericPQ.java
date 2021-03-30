import java.util.*;

public class v12_genericPQ<T> 
{
    ArrayList<T> list;
    Comparator comp;
    
    public v12_genericPQ()
    {
        list = new ArrayList<>();
        comp = null;
    }

    public v12_genericPQ(Comparator comp)
    {
        list = new ArrayList<>();
        this.comp = comp;
    }

    public v12_genericPQ(T[] arr)
    {
        // brute force
        // list = new ArrayList<>();
        // for(int val : arr)
        // {
        //     this.add(val);
        // }

        // optimised
        list = new ArrayList<>();
        for(T val : arr)
        {
            list.add(val);
        }

        int lastbnda = list.size() - 1;
        int parentoflastbnda = (lastbnda - 1)/2;

        for(int i=parentoflastbnda; i>=0; i--)
        {
            downheapify(i);
        }
    }

    public void add(T val)
    {
        list.add(val);
        upheapify(list.size() - 1);
    }

    private boolean isSmaller(int i, int j)
    {
        if(comp == null)
        {
            Comparable ith = (Comparable)list.get(i);
            Comparable jth = (Comparable)list.get(j);
    
            if(ith.compareTo(jth) < 0) return true;
            else return false;
        }
        else
        {
            T ith = list.get(i);
            T jth = list.get(j);

            if(comp.compare(ith , jth) < 0) return true;
            else return false;
        }
    }

    private void upheapify(int i)
    {
        if(i == 0) return;

        int pi = (i - 1)/2;

        // WE ARE MAKING A MIN HEAP.
        if(isSmaller(i, pi) == true)
        {
            swap(i , pi);
            upheapify(pi);
        }
    }

    private void swap(int i, int j)
    {
        T temp = list.get(i);
        list.set(i , list.get(j));
        list.set(j, temp);
    }

    public T peek()
    {
        if(list.size() == 0)
        {
            System.out.println("priority queue is empty");
            return null;
        }

        return list.get(0);
    }

    public T remove()
    {
        if(list.size() == 0)
        {
            System.out.println("priority queue is empty");
            return null;
        }  
        
        swap(0 , list.size() - 1);
        T val = list.remove(list.size() - 1);
        downheapify(0);
        return val;
    }

    private void downheapify(int pi)
    {
        int minidx = pi;

        int lci = 2 * pi + 1;
        if(lci<list.size() && isSmaller(lci,minidx) == true)
        {
            minidx = lci;
        }

        int rci = 2 * pi + 2;
        if(rci<list.size() && isSmaller(rci,minidx) == true)
        {
            minidx = rci;
        }

        if(minidx == pi) return;
        else
        {
            swap(minidx, pi);
            downheapify(minidx);
        }
    }

    public int size()
    {
        return list.size();
    }


    /////////////////////////////////////////////////////////////
    // Main fn to test our class.
    public static void main(String[] args) 
    {
        // v12_genericPQ<Student> pq = new v12_genericPQ<>();
        // v12_genericPQ<Student> pq = new v12_genericPQ<>(new HtComparator());
        v12_genericPQ<Student> pq = new v12_genericPQ<>(new WtComparator());

        pq.add(new Student(5, 170, 80));
        pq.add(new Student(1, 171, 82));
        pq.add(new Student(2, 172, 78));
        pq.add(new Student(4, 173, 86));
        pq.add(new Student(3, 174, 85));

        System.out.println(pq.remove());
        System.out.println(pq.remove());
        System.out.println(pq.remove());
        System.out.println(pq.remove());
        System.out.println(pq.remove());
    }

    static class Student implements Comparable<Student>
    {
        int rno;
        int ht;
        int wt;

        Student(int rno, int ht, int wt)
        {
            this.rno = rno;
            this.ht = ht;
            this.wt = wt;
        }

        public int compareTo(Student o)
        {
            return this.rno - o.rno;
        }

        public String toString()
        {
            return "rno " + this.rno + "    ht " + this.ht + "     wt " + this.wt;
        }
    }

    static class HtComparator implements Comparator<Student>
    {
        public int compare(Student s1 , Student s2)
        {
            return s1.ht - s2.ht;
        }
    }

    static class WtComparator implements Comparator<Student>
    {
        public int compare(Student s1 , Student s2)
        {
            return s1.wt - s2.wt;
        }
    }
}