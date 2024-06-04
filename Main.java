import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.io.FileInputStream;

public class Main {
	private static final Logger LOGGER = Logger.getLogger("global");
    public static void main(String[] args) {
		
		configureLogger();
        LOGGER.setLevel(Level.FINE);
        String[] arr = new String[10];
        arr[0]="A";
        arr[1]="C";
        arr[2]="E";
        arr[3]="G";
        arr[4]="I";
        arr[5]="K";
        arr[6]="M";
        arr[7]="O";
        arr[8]="Q";
        arr[9]="S";



        OptimisticToDoList list ;

        //Customer c1 = new Customer(lock);
       // Customer c2 = new Customer(lock);
       // Customer c3 = new Customer(lock);

      //  for (int i =0;i<CustomerOptions.runs ;i++) {
       //     c1.start();
      //     c2.start();
      //      c3.start();
      //  }

        //CompareAndSwapConsensus <Integer> stack;
        //ConsensusThread [] n=null;
       GroupMember[] group = new GroupMember[7];
         
          

        for(int i=0;i<1;i++)
        {
        	list=new OptimisticToDoList(arr);
        	
        	
        	group[0]= new GroupMember(list,true,"B");
            group[1]= new GroupMember(list,false,"E");
            group[2]= new GroupMember(list,false,"G");
            group[3]= new GroupMember(list,true,"O");
            group[4]= new GroupMember(list,true,"F");
            group[5]= new GroupMember(list,false,"P");
            group[6]= new GroupMember(list,false,"A");
        	
        	/*for(int j=0;j<MemberOptions.adders;j++)
        	{
        		group[j]=new GroupMember(list,true,"B");
        	}
            for( j=adders;j<MemberOptions.adders+MemberOptions+removers;j++)
            {
                n[j]=new Customer(lock);
            }*/
        	
            list.printList();   

        	for(int j=0;j<7;j++)
        	{
        		group[j].start();
        	}
        	
        	for(int j=0;j<6;j++)
        	{
        		try
        		{
        			group[j].join();
        		}
        		
        		catch (InterruptedException e)
      			{
        			e.printStackTrace();
        		}
        	}
       
            //ThreadID.reset();
            list.printList();   
        }
       
        /*
        The scenario will need to run for the amount of runs
        specified in the CustomerOptions.java file. You can overwrite
        the default CustomerOptions values in this main method as follows:

            CustomerOptions.delay = 50;

        This value will then reflect in all classes that access CustomerOptions.delay.


        HINT - Make sure the thread IDs are 0 and 1 in each run.
        Take a look at the functions offered in ThreadID.java that you
        could use to enforce this.
        */
        
    }

    private static void configureLogger() {
        try {
            LogManager.getLogManager().readConfiguration(new FileInputStream("logging.properties"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
