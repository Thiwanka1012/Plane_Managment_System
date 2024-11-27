import java.util.InputMismatchException;
import java.util.Scanner;

class w2053207_PlaneManagement {
    static Ticket[][] User_info = new Ticket[4][];
    static {

        User_info[0] = new Ticket[14];
        User_info[1] = new Ticket[12];
        User_info[2] = new Ticket[12];
        User_info[3] = new Ticket[14];}


    public static void main(String[] args) {
        // Define the Seating arrangement in Plane
        int[][] seats = new int[4][];
        seats[0] = new int[14];
        seats[1] = new int[12];
        seats[2] = new int[12];
        seats[3] = new int[14];



        Scanner scanner = new Scanner(System.in);

        System.out.println("\nWelcome to the Plane Management Application");

        while (true) {
            System.out.println("\n***********************************************************");
            System.out.println("*                    MENU OPTION                 *");
            System.out.println("***********************************************************");
            System.out.println("      1. Buy a Seat");
            System.out.println("      2. Cancel a seat");
            System.out.println("      3. Find first available seat ");
            System.out.println("      4. Show seating plan ");
            System.out.println("      5. Print tickets information and local sales ");
            System.out.println("      6. Search ticket");
            System.out.println("      0. Quit");
            System.out.println("************************************************************");
            System.out.print("Please select an option: ");

            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                //Using a switch case because there are only few choices for the user
                switch (choice) {
                    case 1:
                        buy_seat(scanner, seats);
                        break;
                    case 2:
                        cancel_seat(scanner, seats);
                        break;
                    case 3:
                        find_first_available(seats);
                        break;
                    case 4:
                        show_seating_plan(seats);
                        break;
                    case 5:
                        print_ticket_info(seats);
                        break;
                    case 6:
                        search_ticket(scanner, seats);
                        break;
                    case 0:
                        System.out.println("Exiting the Program.Good Bye!");
                        System.exit(0);
                        //if user enter a number have no method to call
                    default:
                        System.out.println("\nInvalid choice. Please try again.");
                }
            } else {
                System.out.println("\nInvalid input. Please enter a Menu Option.");
                scanner.next(); // Consume the invalid input
            }
        }
    }

    public static void buy_seat(Scanner scanner, int[][] seats) {
        System.out.print("\nEnter row Letter: ");
        String rowString = scanner.next().toUpperCase();

        // Checking Input validation for row

        if (rowString.length() != 1 || !(rowString.charAt(0) >= 'A' && rowString.charAt(0) <= 'D')) {
            System.out.println("\nInvalid row Letter. Please enter A, B, C, or D (According to the plane seat plan).");
            return;
        }

        int row = rowString.charAt(0) - 'A'; // Convert seat letter to index

        System.out.print("Enter seat number: ");

        // Checking Input validation for seat number
        if (!scanner.hasNextInt()) {
            System.out.println("\nInvalid input. Please enter a valid integer for the seat number.");
            scanner.next(); // Clear the invalid input
            return;
        }

        int seat = scanner.nextInt() - 1; // Adjust for 0-based indexing

        if (seat < 0 ||
                (rowString.charAt(0) == 'A' || rowString.charAt(0) == 'D') && seat >= seats[0].length || // Rows A and D (full length)
                (rowString.charAt(0) == 'B' || rowString.charAt(0) == 'C') && seat >= 12) {
            System.out.println("\nInvalid seat number. Please enter the number according to the plane seat plan.");
            return;
        } else if (seat >= 0 &&
                (rowString.charAt(0) == 'A' || rowString.charAt(0) == 'D') && seat < seats[0].length || // Rows A and D (full length)
                (rowString.charAt(0) == 'B' || rowString.charAt(0) == 'C') && seat < 12) {

            if (seats[row][seat] == 1) {
                System.out.println("\nThis seat is already booked. Please try another seat.");
            } else {
                seats[row][seat] = 1; // Mark the seat as booked


                //Personal Information
                System.out.print("\nEnter your name:");
                String name = scanner.next();
                System.out.print("Enter your surname:");
                String surname = scanner.next();
                System.out.print("Enter your email:");
                String email = scanner.next();
                Person info=new Person(name,surname,email);
                int Price = seatPrice(seat);
                Ticket userinfo=new Ticket((seat+1),Price,info,rowString);
                User_info[row][seat]=userinfo;
                userinfo.saveToFile();
                System.out.println("\nSeat booked successfully! Your seat is " + rowString + (seat + 1));




            }
        }
    }

    public static void cancel_seat(Scanner scanner, int[][] seats) {
        System.out.print("\nEnter row Letter: ");
        String rowString = scanner.next().toUpperCase();
        // Checking Input validation for row
        if (rowString.length() != 1 || !(rowString.charAt(0) >= 'A' && rowString.charAt(0) <= 'D')) {
            System.out.println("\nInvalid row Letter. Please enter A, B, C, or D (According to the plane seat plan).");
            return;
        }

        int row = rowString.charAt(0) - 'A'; // Convert seat letter to index

        System.out.print("Enter seat number according to the plane seat plan: ");

        // Checking Input validation for seat number
        if (!scanner.hasNextInt()) {
            System.out.println("\nInvalid input. Please enter a valid integer for the seat number.");
            scanner.next(); // Clear the invalid input
            return;
        }

        int seat = scanner.nextInt() - 1; // Adjust for 0-based indexing

        if (seat < 0 ||
                (rowString.charAt(0) == 'A' || rowString.charAt(0) == 'D') && seat >= seats[0].length || // Rows A and D (full length)
                (rowString.charAt(0) == 'B' || rowString.charAt(0) == 'C') && seat >= 12) {
            System.out.println("\nInvalid seat number. Please enter the number according to the plane seat plan.");
            return;
        } else if (seat >= 0 &&
                (rowString.charAt(0) == 'A' || rowString.charAt(0) == 'D') && seat < seats[0].length || // Rows A and D (full length)
                (rowString.charAt(0) == 'B' || rowString.charAt(0) == 'C') && seat < 12) {

            if (seats[row][seat] == 0) {
                System.out.println("\nThis seat is not booked. No cancellation required.");
            } else {
                seats[row][seat] = 0; // Mark the seat as available
                User_info[row][seat].delete();
                User_info[row][seat]=null;


                System.out.println("\nSeat cancellation successful! The seat " + rowString + (seat + 1) + " is now available.");
            }
        }
    }

    public static void find_first_available(int[][]seats) {
        System.out.println("\nAvailable Seats:");
        for (int i = 0; i < seats.length; i++) {
            char rowLetter = (char) ('A' + i);
            for (int j = 0; j < seats[i].length; j++) {
                if (seats[i][j] == 0) {
                    // Print available seat in desired format
                    System.out.println("\nThe first available seat is: " + rowLetter + (j + 1));
                    return; // Exit the method after finding the first available seat
                }
            }
        }
        System.out.println("No available seats found.");
    }

    public static void show_seating_plan(int[][] seats) {
        System.out.println("\nSeating Plan:");
        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[i].length; j++) {
                if (seats[i][j] == 0) {
                    System.out.print(" O "); // Available seat - circle
                } else {
                    System.out.print(" X "); // Sold seat - X
                }
            }
            System.out.println();
        }
    }

    public static int seatPrice(int class_row) {
        if (class_row <= 4) {
            return 200;
        } else if (class_row < 9) {
            return 150;
        } else if (class_row < 14) {
            return 180;
        }

        return class_row;
    }

    public static void search_ticket(Scanner scanner, int[][] seats) {

        System.out.print("\nEnter row Letter: ");
        String rowString = scanner.next().toUpperCase();
        // Input validation for row
        if (rowString.length() != 1 || !(rowString.charAt(0) >= 'A' && rowString.charAt(0) <= 'D')) {
            System.out.println("\nInvalid row Letter. Please enter A, B, C, or D.(Acording to the plane seat plan)");
            return;
        }

        int row = rowString.charAt(0) - 'A'; // Convert seat letter to index
        System.out.print("Enter seat number: ");

        if (!scanner.hasNextInt()) {
            System.out.println("\nInvalid input.Please enter the number acording to the plane seat plan.");
            scanner.next(); // Clear the invalid input
            return;
        }

        int seat = scanner.nextInt() - 1; // Adjust for 0-based indexing

        if (seat < 0 || seat >= seats[0].length ||
                (rowString.charAt(0) == 'B' || rowString.charAt(0) == 'C') && seat >= 12) {
            System.out.println("\nInvalid seat number. Please enter the number acording to the plane seat plan.");
            return;
        }

        // Check if the seat is already booked
        if (seats[row][seat] == 1) {
            System.out.println("\nThis seat is already booked.Try Another one.");
            User_info[row][seat].printTicketInfo();

        } else {
            System.out.println("\nThis seat is available.");
        }
    }

    public static void print_ticket_info(int[][]seats) {
        int Price = 0 ;
        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[i].length; j++) {
                if (seats[i][j] == 1) {
                    // Print available seat in desired format
                    int Seat_price=seatPrice(j+1);
                    Price =Price+Seat_price ;
                    User_info[i][j].printTicketInfo();
                    System.out.println("\ntotal payment =£ "+Price);
                    // Exit the method after finding the first available seat

                }
            }
        }

    }

}

