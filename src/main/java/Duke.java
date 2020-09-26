import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Duke {

    public static String humanName;
    public static int taskNumber;

// Storing userSentence into list

    public static Task[] storeList(Task[] listOfThings, String input) throws IllegalInputException { //listOfThings should be array class task?

        int counter = 0;
        int numbering = 1;
        while (true) {
            //System.out.println(counter);
            //System.out.println(listOfThings);
            // set it to false here
            if (listOfThings[counter] == null) {

                listOfThings[counter] = new Task(input);
                System.out.println("\tGot it. I've added this task: ");
                System.out.println("\t" + listOfThings[counter].getTag() +  "[" + listOfThings[counter].getStatusIcon() + "] " + listOfThings[counter].description +  listOfThings[counter].timeDate);
                System.out.println("\tNow you have " + numbering +" task in the list ");
                break;
            }
            counter++;
            numbering++;

        }//end while loop

        return listOfThings;
    }


    //print list
    public static void printListOfThings(Task[] listOfThings) {
        if (listOfThings[0] == null) {
            System.out.println(humanName + "\tthe list is empty!");
            return;
        }
        System.out.println("\tHere are the tasks in your list: ");
        for (int i = 1; i <= listOfThings.length; i++) {

            System.out.println("\t" + i + ". " + listOfThings[i-1].getTag() +  "[" + listOfThings[i-1].getStatusIcon() + "] " + listOfThings[i - 1].description );
            if (listOfThings[i] == null) {
                System.out.println("\tOk that's about everything!");
                return;
            }
        }
    }


    public static Task[] echoCommands(Task[] listOfThings) throws IllegalInputException {

        //int taskNumber;

        String userSentence;
        Scanner input = new Scanner(System.in);
        userSentence = input.nextLine();

        Pattern pattern1 = Pattern.compile((".*" + "bye" + ".*"), Pattern.CASE_INSENSITIVE);
        Matcher matcher1 = pattern1.matcher(userSentence);
        if (matcher1.find()) {
            System.out.println("\tBye! Hope to see you again soon " + humanName);
            System.exit(0);
        }

        Pattern pattern2 = Pattern.compile((".*" + "list" + ".*"), Pattern.CASE_INSENSITIVE);
        Matcher matcher2 = pattern2.matcher(userSentence);
        Pattern pattern3 = Pattern.compile((".*" + "done" + ".*"), Pattern.CASE_INSENSITIVE);
        Matcher matcher3 = pattern3.matcher(userSentence);
        if (matcher2.find()) {
            printListOfThings(listOfThings); //
        }
        else if (matcher3.find()){
            Pattern pattern = Pattern.compile("[^0-9]");
            String numberOnly = pattern.matcher(userSentence).replaceAll("");
            taskNumber = Integer.parseInt(numberOnly);
            System.out.println("\tNice! I've marked this task as done: ");
            listOfThings[taskNumber-1].isDone = true; // not sure about this
            System.out.println(listOfThings[taskNumber-1].getTag()+" ["+listOfThings[taskNumber-1].getStatusIcon()+"] "+ listOfThings[taskNumber - 1].description );
        }
        else{
            listOfThings = storeList(listOfThings, userSentence);

        }

            return listOfThings;
        }



        public static void main (String[] args) throws IllegalInputException {
            Task[] List = new Task[100];

            String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
            System.out.println("Hello from\n" + logo);

            //yyyy/MM/dd
            System.out.println("\tHello! I'm Duke\n\tWhat is your name?");


            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();

            //String humanName;
            Scanner in = new Scanner(System.in);
            System.out.println("What is your name?\n");
            humanName = in.nextLine();

            System.out.println(humanName + " today is: " + (dtf.format(now)));
            System.out.println("What can I do for you?\n");
            //Maybe list out supported commands?

            while (true) {
                //echoCommands();
                List = echoCommands(List);
            }


        }

}
