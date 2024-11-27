import java.io.File;
import java.io.IOException;
import java.io.FileWriter;


public class Ticket {
    private String row;
    private int seat;
    private double price;
    private Person person;

    public Ticket(int seat, int price, Person person, String row) {
        this.row = row;
        this.seat = seat;
        this.price = price;
        this.person = person;
    }

    // Get Row as String
    public String getRow() {
        return row;
    }

    // Set Row as String
    public void setRow(String row) {
        this.row = row;
    }

    // Get Seat as Integer
    public int getSeat() {
        return seat;
    }

    // Set Seat as Integer
    public void setSeat(int seat) {
        this.seat = seat;
    }

    // Get Price as Float
    public double getPrice() {
        return price;
    }

    // Set Price as Float
    public void setPrice(double price) {
        this.price = price;
    }

    // Get Person information as Person
    public Person getPerson() {
        return person;
    }

    // Set Person information as Person
    public void setPerson(Person person) {
        this.person = person;
    }

    // Print Ticket information
    public void printTicketInfo() {
        System.out.println("Ticket Information:");
        System.out.println("Row: " + row);
        System.out.println("Seat: " + seat);
        System.out.println("Price: £ " + price);
        System.out.println("Person Information:");
        person.printPersonInfo(); // Assuming Person class has a method to print person info
    }

    public void saveToFile() {
        try {
            FileWriter writer = new FileWriter(row + seat + ".txt");
            // Format the ticket information
            writer.write("Row:" + row);
            writer.write("\nSeat number :" + seat);
            writer.write("\nPrice :"+price);
            writer.write("\nName :" + person.getName());
            writer.write("\nSurname :" + person.getSurname());
            writer.write("\nEmail :" + person.getEmail());

            writer.close();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    public void delete() {
        File file = new File(row + seat + ".txt");
        if (file.exists()) {
            file.delete();
        }
    }
}
