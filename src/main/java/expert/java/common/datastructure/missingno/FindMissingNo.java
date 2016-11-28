package expert.java.common.datastructure.missingno;

/**
 * Created by Patrick on 1/27/2015.
 */
public class FindMissingNo {
    /* getMissingNo takes array and size of array as arguments*/
    public static int getMissingNo(int a[], int n)
    {
        int i;
        int x1 = a[0]; /* For xor of all the elemets in arary */
        int x2 = 1; /* For xor of all the elemets from 1 to n+1 */

        for (i = 1; i< n; i++)
            x1 = x1^a[i];
        System.out.println("x1="+x1);

        for ( i = 2; i <= n+1; i++)
            x2 = x2^i;
        System.out.println("x2="+x2);

        return (x1^x2);
    }

    /*program to test above function */
    public static void main(String[] args)
    {
        int a[] = {1, 2, 4, 5, 6};
        int miss = getMissingNo(a, 5);
        System.out.printf("%d", miss);

    }
}
