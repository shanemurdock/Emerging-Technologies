/**
 * 
 * Student Name:Shane Murdock
 * Student number: 2899741
 *
 */
import java.util.concurrent.Callable;
import java.util.concurrent.*;
import java.util.*;
public class Assignment2 {
	public static void main(String[] args) {
    //Question 1 
		int f[] = new int[1000000];
    	Frequency f1 = new Frequency(f,0,250000);
		Frequency f2 = new Frequency(f,250000,500000);
		Frequency f3 = new Frequency(f,500000,750000);
		Frequency f4 = new Frequency(f,750000,1000000);
		//Start each thread 
		f1.start();
		f2.start();
		f3.start();
		f4.start();
		try
		{
			// delay each thread till the one before is finished
			f1.join();
			f2.join();
			f3.join();
			f4.join();
			
		}
		catch(InterruptedException e){}
		// Create ints for a counting 
		int even = 0;
		int odd = 0;
		
	// Populate and print array
		for(int i = 0; i < f.length; i++) {
			// System.out.print(f[i]+", ");
			// System.out.print(f[i]+", ");
			
			if(f[i] %2 == 0)
				even++;
			else if(f[i]%2 == 1)
				odd++;
			

	}
	
			
		// print array and frequency
		System.out.println();
		System.out.println("Freq of even: " + even);
		System.out.println("Freq of od: " + odd);
        //========================================
    //Question 2
		ThreadPoolExecutor pool = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
        int number[] = new int[1000000];
        init(number);
        ArrayList<Future<Integer>> future = new ArrayList<Future<Integer>>();
        for (int j = 0; j < number.length; j++) 
        {
            Future<Integer> p = pool.submit(new CallableMethod(number));
            future.add(p);

        }
        // create array to store results
        int result[] = new int[number.length];
        for (int j = 0; j < result.length; j++) 
        {
            try 
            {
                Future<Integer> p = future.get(j);
                result[j] = p.get();
            } 
            catch (InterruptedException e){} 
            catch (ExecutionException e){};
        }
        // System.out.println("The Large Number in array is: " + n);
        // System.out.println("The : " + frequency);
        pool.shutdown();
       
            System.out.print(result);
  
    	
    
    //=======================================
  }
  static void init(int dd[])
    {
        for(int i = 0; i < dd.length;i++)
        dd[i] = (int)(Math.random()*10);
    }
}
//Code for threads for Question 1=========================
class Frequency extends Thread
{
	private int data[];
	private int lowerBound;
	private int upperBound; 
	public Frequency(int list[], int l, int u)
	{
		lowerBound = l;
		upperBound = u;
		data = list;
	}
	public void run()
	{
		//create random number gen to throw dice
		Random dice = new Random();
		for(int i = lowerBound; i <= upperBound; i++){
			data [i] = dice.nextInt(1000);
		}
		
	}
	public int getResult(int result)
	{
		return data[result];
	}
	
}



//=======================================================
//Code for Callable class here
		
class CallableMethod implements Callable<Integer>
{

    //@SuppressWarnings("unused")
    private int[] num;

    public CallableMethod(int [] num)
    {
        this.num = num;
    }

    public Integer call() throws Exception
    {

        int n = num[0];
        int frequency = 0;

        for(int i=1; i< num.length; i++)
        {

               if(n < num[i]){
                        n = num[i];
               }
        }

         for(int i = 1; i< num.length; i++)
         {
           if (n == num[i])
           {
                frequency++;
            }
        }

        System.out.println("Largest Number is : " + n);
        System.out.println("frequency of occurence : " + frequency);
        return frequency;
    }
}