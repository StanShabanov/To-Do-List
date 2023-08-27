// Stan Shabanov
// 1/31/22
// CO TODO list manager
import java.util.*;
import java.io.*;
// This main method takes in and uses other methods for each function that the user
// enters. It allows the user to quit, add, save, load, mark items that are on the todo list
public class TodoListManager {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Welcome to your TODO List Manager!"); 
        System.out.println("What would you like to do?");
        System.out.print("(A)dd TODO, (M)ark TODO as done, (L)oad TODOs, (S)ave TODOs, (Q)uit? ");
        Scanner console = new Scanner(System.in);
        ArrayList<String> todos = new ArrayList<>();
        String input = console.nextLine();
        while (!input.equalsIgnoreCase("Q")){
            if(onward(input)){
                if(input.equalsIgnoreCase("A")){
                addItem(console, todos);  
                }
                else if (input.equalsIgnoreCase("S")){
                saveItems(console, todos);
                }
                else if (input.equalsIgnoreCase("L")){
                loadItems(console, todos);
                }
                else if (input.equalsIgnoreCase("M")){
                markItemAsDone(console, todos);
                }
            }
            printTodos(todos);
            System.out.println("What would you like to do?");
            System.out.print("(A)dd TODO, (M)ark TODO as done, (L)oad TODOs, (S)ave TODOs, (Q)uit? ");
            input = console.nextLine();
        }
    }
    //This method checks to see if the answer the user put was valid to execute and put into the todo list 
    // parameter: 
    // - String of letters the user answered 
    // returns:
    // - returns if the input of user is valid or not to use 
    public static boolean onward(String input){
        boolean validinput;
        String possibleLetter = "ASLM";
        if(possibleLetter.indexOf(input.toUpperCase()) >= 0){
            validinput = true;
        }
        else {
            validinput = false;
            System.out.println("Unknown input: " + input);
        }
        return validinput;
    }
    //This method allows the user to see what is in the todo list that they have made. 
    //If they had nothing it will give them a different prompt
    //parameters: Arraylist of Strings named todo that is used to determine what items are in the todo list
    //no returns
    public static void printTodos(List<String> todos) {
        System.out.println("Today's TODOs:");
        if(todos.size()==0){
            System.out.println("  You have nothing to do yet today! Relax!");
        }
        else {
            for(int i = 1; i <= todos.size(); i++){
                System.out.println("  " + i + ": " + todos.get(i - 1));
            }
        }
    }
    //This method takes in their todo list items and add it to thier lists. It also allows them to put where
    //each item they want to place in the list.
    //parameters: 
    //- Scanner console to take in inputs to add to list
    //- ArrayList of strings named todo that is used to figure out how many and what are the items are in the list
    //no returns
    public static void addItem(Scanner console, List<String> todos) {
        System.out.print("What would you like to add? ");
        String input = console.nextLine();
        if(todos.size() == 0){
            todos.add(input);
        }
        else {
            System.out.print("Where in the list should it be (1-" + (todos.size() + 1) + ")? (Enter for end): ");
            String scan = console.nextLine();
            if(scan == ""){
                todos.add(input);
            }
            else {
                int integer = Integer.parseInt(scan);
                todos.add(integer - 1, input);
            }
        }
    }
    //This method allows the user to load items inside the list and uses the name of 
    // a file that they used to execute the program
    // parameters:
    // - Scanner that has been initalized in main method to be used to find user answers
    // - List of strings used in other methods to tell what is in the list to be used
    // no returns
    public static void loadItems(Scanner console, List<String> todos)
                                throws FileNotFoundException {
        System.out.print("File name? ");
        String files = console.nextLine();
        while(todos.size() > 0 ){
            todos.remove(0);
        }
        Scanner items = new Scanner(new File(files));
        while(items.hasNextLine()){
            String todoList = items.nextLine();
            todos.add(todoList);
        }
    }
     //This method allows the user to mark items that they have added to thier list to be
     //completed and if the user has nothing in list it prompts a different prompt
     //parameters:
     // - Scanner console initalized in the main method to get user input
     // - List of strings used in other methods to tell what is in the list to be used
     // no returns 
     public static void markItemAsDone(Scanner console, List<String> todos) {
        if(todos.size() >= 1){
            System.out.print("Which item did you complete (1-" + todos.size() + ")? ");
            String input = console.nextLine();
            int delete = Integer.parseInt(input);
            todos.remove(delete - 1);
        }
        else {
            System.out.println("All done! Nothing left to mark as done!");
        }
    }
    //This method allows the user to save items to the list given a file name
    // - Scanner console initalized in the main method to get user input
    // - List of strings used in other methods to tell what is in the list to be used
    // no returns 
    public static void saveItems(Scanner console, List<String> todos)
                                throws FileNotFoundException {
        System.out.print("File name? ");
        String files = console.nextLine();
        PrintStream answer = new PrintStream(new File(files));
        for(int i = 0; i < todos.size(); i++){
            answer.println(todos.get(i));
        }
    }
}
