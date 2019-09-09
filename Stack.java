import java.util.ArrayList;

/**
 * Created by Utkarsh on 8/30/18.
 */

 
public class Stack<T>

{   int top;
int capacity;

     ArrayList<T> arrayList=new ArrayList<>();




  /*  public T top() throws IndexOutOfBoundsException
    {
        //Similar to peek

        // Method should return the top element of the stack
        // This does not remove the element from the stack
        // Incase the stack is empty, it should throw an error, 
        // with the message "Empty Stack"

        // add your own return statement
        if(top<=-1)
            throw new IndexOutOfBoundsException("Empty Stack");


        return arrayList.get(top);
    }
    */

    public Stack(int capacity)

    {
        this.top=-1;
        this.capacity=capacity;
    }

    public boolean IsEmpty(){

        if(top<=-1)
            return true;

        return false;//remove this line and return the appropriate answer
    }

    public boolean push(T val){

        top++;

        arrayList.add(top,val);
        return true;


        //Write your code here
        //Push the new element on the stack 
        //If the element was added successfully, return true
        //If the element was not added, return false

    }

    public T pop() throws IndexOutOfBoundsException{
        if(top<=-1)
            throw new IndexOutOfBoundsException("Empty Stack");

        top--;
        return arrayList.remove(top+1);



    }

    public T peek() throws IndexOutOfBoundsException{
        if(top<=-1)
            throw new IndexOutOfBoundsException("Empty Stack");

        return arrayList.get(top);

    }

    public int size()
    {
                     return arrayList.size();
    }
}
