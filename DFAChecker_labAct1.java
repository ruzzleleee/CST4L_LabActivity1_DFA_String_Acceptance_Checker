import java.util.Scanner;

public class DFAChecker_labAct1 {
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);

        System.out.println("""
                ==== DFA Checker ====
                Language: Strings ending with '01'
                Type 'exit' to close
                """);
        
        while (true) {
            
            System.out.print("Enter a binary string (0 and 1): ");
            String input = scanner.nextLine();
            
            //Exit the program
            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Exiting... Goodbye...");
                break;
            }

            //Validate the input
            if (!isValid(input)) {
                System.out.println("Invalid input... Input binary digits (0 or 1) only...\n");
                continue; 
            }

            //Check if input is empty
            if (input.isEmpty()) {
                System.out.println("Invalid input! String cannot be empty...\n");
                continue;
            }
            
            boolean isAccepted = check(input);
            
            if (isAccepted) {
                System.out.println("Result: Accepted\n");
            } else {
                System.out.println("Result: Rejected\n");
            }
        }
        scanner.close();
    }
    
    //Method to check the string if it is 'Accepted' or 'Rejected'
    public static boolean check(String input) {

        int currentState = 0;
        
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            currentState = transition(currentState, c);
        }
        
        return currentState == 2;
    }
    
    //Method to define how the DFA moves between states
    public static int transition(int state, char input) {

        // State 0 (q0) - Start
        if (state == 0) {
            if (input == '0') {
                return 1;  
            } else if (input == '1') {
                return 0;  
            }
        }
        
        // State 1 
        else if (state == 1) {
            if (input == '0') {
                return 1; 
            } else if (input == '1') {
                return 2;  
            }
        }
        
        // State 2 (q2) - Accept
        else if (state == 2) {
            if (input == '0') {
                return 1;  
            } else if (input == '1') {
                return 0;  
            }
        }
        
        // Return current state if no valid transition (shouldn't happen with validation)
        return state;
    }

    //Input validation
    public static boolean isValid(String input) {
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);

            if (c != '0' && c != '1') {
                return false;
            }
        }
        // If all characters are valid, return true
        return true;
    }
}