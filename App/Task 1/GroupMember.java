
import java.util.logging.Logger;

public class GroupMember extends Thread {

    private static final Logger LOGGER = Logger.getLogger("global");

    Boolean status;
    String name;
    String result;

    public OptimisticToDoList optimisticList;

    public GroupMember(OptimisticToDoList list, Boolean adder, String taskName){

        status = adder;
        name = taskName;
        optimisticList = list;

    }

    public void run(){

      if( status == true)
      {
        result = "add";
        LOGGER.info("Group member "+ThreadID.get()+ " is going to try "+result+ " a task");
        optimisticList.addTask(name);
         
          
      }
        
      else
      {
        result = "remove";
        LOGGER.info("Group member "+ThreadID.get()+ " is going to try "+result+ " a task");
        optimisticList.removeTask(name);
          
      }

     

    }
}
