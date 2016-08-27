package expert.java.common.datastructure.primeno;

/**
 * Created by patrick on 1/16/15.
 */
/*
    This class writes out Prime numbers. A number is prime if it is
    divisible by 1 and the number itself and no other number.
*/

public class PrimeNumber
{
    // This method tests whether a given number is prime or not.
    public static boolean isPrime ( int num )
    {
        boolean prime = true;
        /*假设a*b=m，且a<=b，那么a<=sqrt(m)
              5*11=55, 5<=sqrt(55)=7
              开根号 sqrt
         */
        int limit = (int) Math.sqrt ( num );

        for ( int i = 2; i <= limit; i++ )
        {
            if ( num % i == 0 )
            {
                prime = false;
                break;
            }
        }

        return prime;
    }

    public static void main ( String[] args )
    {
        // This loop writes out all the prime numbers less than 1000.
        for ( int i = 2; i <= 1000; i++ )
        {
            if ( isPrime ( i ) ) {
                System.out.println(i);
            }
        }
    }

}
