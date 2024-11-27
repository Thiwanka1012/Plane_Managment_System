public class Person {
    private String name;
    private String surname;
    private String email;

    public Person(String name, String surname, String email) {
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    // Get name
    public String getName() {
        return name;
    }

    // Set name
    public void setName(String name) {
        this.name = name;
    }

    // Get Surname
    public String getSurname() {
        return surname;
    }

    // Set Surname
    public void setSurname(String surname) {
        this.surname = surname;
    }

    // Get Email
    public String getEmail() {
        return email;
    }

    // Set Email
    public void setEmail(String email) {
        this.email = email;
    }

    // Print Person information
    public void printPersonInfo() {
        System.out.println("Name: " + name);
        System.out.println("Surname: " + surname);
        System.out.println("Email: " +email);

    }
}