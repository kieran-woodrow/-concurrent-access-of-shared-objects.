
import java.util.logging.Logger;
public class OptimisticToDoList {

    private static final Logger LOGGER = Logger.getLogger("global");


    Task head=new Task("");
    Task tail = new Task("");
    int size;

    OptimisticToDoList(String[] taskNames) 
    {
    
        Task temp = head;
        head.setNextTask(tail);

        for(int i = 0; i < taskNames.length; i++)
        {
            Task t = new Task(taskNames[i]);
            temp.setNextTask(t);
            temp=temp.getNextTask();
        }

        temp.setNextTask(tail);

        size = taskNames.length;
    }

    public void addTask(String name) 
    {
        while(true){

            Task pred = head;
            Task current = pred.next;
            boolean status;

            while(current != tail && current.getTaskName().compareTo(name) < 0){
                pred = current;
                current = current.next;
            }

            pred.lock();
            current.lock();

            try{
                if(validate(pred, current)){
                    if(current.nameOfTask.equals(name)){
                        status = false;
                        LOGGER.info("Group member "+ThreadID.get()+ " could not add task "+name+ " as it is already in the list");
                        return;

                    } else{

                        Task node = new Task(name);
                        LOGGER.info("Group member "+ThreadID.get()+ " added task "+name);
                        node.next = current;
                        size=size+1;
                        pred.next = node;
                        status = true;
                        return;
                    }
                }
            } finally{
                pred.unlock();
                current.unlock();
            }
        }
    }

    public void removeTask(String name)
    {

        while(true){

            Task pred = head;
            Task current = pred.next;
            boolean status;

            while(current != tail && current.nameOfTask.compareTo(name) < 0){

                pred = current;
                current=current.next;
            }

            pred.lock();
            current.lock();

            try{
                if(validate(pred, current) ){
                    if(current.nameOfTask.equals(name)){
                        pred.next = current.next;
                        size=size-1;
                        status = true;
                        LOGGER.info("Group member "+ThreadID.get()+ " removed task "+name);
                        return;

                    }else{
                        status = false;
                        LOGGER.info("Group member "+ThreadID.get()+ " could not remove task "+name+ " as it was not in the list");
                        return;
                    }
                }
            } finally{
                pred.unlock();
                current.unlock();
            }
        }
	}

    public void printList()
    {
        String listNames="";

        Task t = head.getNextTask();

        while(t.next != null)
        {
            listNames += t.getTaskName()+", ";
            t = t.getNextTask();
        }

        listNames=listNames.substring(0, listNames.length()-2); //or -2 to accouny for comma and space

        LOGGER.fine("Todo List Size: "+size);

        LOGGER.fine(listNames);
    }

    private boolean validate(Task predecessor, Task current) {

        Task node = head;

        while(node.nameOfTask.compareTo(predecessor.nameOfTask) <= 0)
        {
            if( node == predecessor)
                return predecessor.next == current;

            node=node.next;
        }

    return false;
 }

}
