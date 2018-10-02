import java.util.*;
public class Kiosk
{
    private Catalogue catalogue;
    private List<Customer> customers = new LinkedList<Customer>();
    
    public static void main(String args[]){
        Kiosk kiosk = new Kiosk();
        kiosk.use();
    }
    
    public Kiosk(){
        catalogue = new Catalogue(this);
        
        customers.add(new Customer(101, "Jaime", 10));
        customers.add(new Customer(102, "Luke", 10));
        customers.add(new Customer(103, "William", 1));
        
    }
    
    public void use(){
        char choice;
  
        while((choice = readChoice()) != 'X'){
            switch (choice){
                case '1': viewCatalogue(); break;
                case '2': viewCustomerRecord(); break;
                case '3': viewFavourites(); break;
                case '4': topUp(); break;
                case '5': adminMenu(); break;
                default: System.out.println("Please enter a number between 1 and 5, or press X to exit."); break;
            }
        }     
    }
    private char readChoice(){
        help();
        System.out.print("Enter a choice: ");
        return In.nextChar();
    }
    private void help(){
        System.out.println("Welcome to the Movie Kiosk! Please make a selection from the menu: ");
        System.out.println("1. Explore the catalogue.");
        System.out.println("2. View your customer record.");
        System.out.println("3. Show you favourite movies.");
        System.out.println("4. Top up account.");
        System.out.println("5. Enter Admin Mode.");
        System.out.println("X. Exit the system.");
    }
    
    // kiosk 1
    private void viewCatalogue(){
        catalogue.use();
    }
    // kiosk 2
    private void viewCustomerRecord(){
        int ID = readID();
        
        Customer customer = customer(ID);
        if(customer != null){
            //System.out.println(customer);
            System.out.println("ID: " + customer.getID());
            System.out.println("Name: " + customer.getName());
            System.out.println("Balance: $" + customer.getBalance());
            System.out.println("Movies currently rented by " + customer.getName() + ": ");
            customer.getCurrentlyRented();
            System.out.println("\n"+ customer.getName() + "'s renting history: ");
            customer.getRentingHistory();
        }
        else{
            System.out.println("That customer does not exist.\n");
        }
    }
    private int readID(){
        System.out.print("\nEnter a customer ID: ");
        return In.nextInt();
    }
    //lookup
    private Customer customer(int ID){
        for(Customer customer : customers){
            if(customer.hasID(ID))
            return customer;
        }
        return null;
    }
    //kiosk 3
    private void viewFavourites(){
        //movies cant have a count field
        //output top 3 most rented
        int ID = readID();
        Customer customer = customer(ID);
        String name = customer.getName();
        
        
        catalogue.viewFavourites(name);
        
        
    }
    //kiosk 4
    private void topUp(){
        int ID = readID();
        int amount = readAmount();
        
        Customer customer = customer(ID);
        if(customer != null){
            String name = customer.getName();
            int currentBalance = customer.getBalance();
            System.out.println("\nTransaction complete.");
            System.out.println(name + "'s balance was: $" + currentBalance);
            int newBalance = currentBalance + amount;
            customer.setBalance(newBalance);
            System.out.println(name + "'s current balance is: $" + customer.getBalance() + "\n");

        }
        else{
            System.out.println("That customer doesn't exist");
        }
    }
    private int readAmount(){
        System.out.print("Enter the top-up amount:");
        return In.nextInt();
    }
    // kiosk 5
    public void adminMenu(){
        char choice;
        
        while((choice = readAdminChoice()) != 'R'){
        
            switch (choice){
                case '1': listAllCustomers(); break;
                case '2': addCustomer(); break;
                case '3': removeCustomer(); break;
                case '4': listAllMovies(); break;
                case '5': addMovie(); break;
                case '6': removeMovie(); break;
                default: adminHelp(); break;
            }
        }
    }
    private char readAdminChoice(){
        adminHelp();
        System.out.print("Enter a choice: ");
        return In.nextChar();
    }
    private void adminHelp(){
        System.out.println("Welcome to the administration menu: ");
        System.out.println("1. List all customers.");
        System.out.println("2. Add a customer.");
        System.out.println("3. Remove a customer.");
        System.out.println("4. List all movies.");
        System.out.println("5. Add a movie to the catalogue.");
        System.out.println("6. Remove a movie from the catalogue.");
        System.out.println("R. Return to the previous menu.");
    }
    //admin 1
    private void listAllCustomers(){
        System.out.println("\nThe Kiosk has the following customers:");
        for(Customer customer : customers){
            System.out.println(customer);
        }
        System.out.println("");
    }
    //admin 2
    private void addCustomer(){
        System.out.println("\nAdding a new customer.");
        int ID = readNewID();
        String name = readName();
        int balance = readBalance();
        
        Customer customer = customer(ID);
        if(customer != null){
            System.out.println("That customer already exists.");
        }
        else{
            System.out.println("Customer added.\n");
            customers.add(new Customer(ID, name, balance));
        }
    }
    private int readNewID(){
        System.out.print("Enter a new ID: ");
        return In.nextInt();
    }
    private String readName(){
        System.out.print("Enter the customer's name: ");
        return In.nextLine();
    }
    private int readBalance(){
        System.out.print("Enter the customer's initial balance: ");
        return In.nextInt();
    }
    //admin 3
    private void removeCustomer(){
        System.out.println("\nRemoving a customer.");
        int ID = readRemoveID();
        
         Customer customer = customer(ID);
        if(customer != null){
            customers.remove(customer);
            System.out.println("Customer removed.\n");
        }
        else{
            System.out.println("That customer does not exist.\n");
        }
    }
    private int readRemoveID(){
        System.out.print("Enter a customer ID: ");
        return In.nextInt();
    }
    //admin 4
    private void listAllMovies(){
        catalogue.viewAllMovies();
    }
    //admin 5
    private void addMovie(){
        catalogue.addMovie();
    }
    // admin 6
    private void removeMovie(){
        catalogue.removeMovie();
    }
    //pushed right
    public void rentMovie(int ID, Movie movie){
        Customer customer = customer(ID);
        int balance = customer.getBalance();
        int moviePrice = movie.getPrice();
        
        customer.setBalance(balance -= moviePrice);

        customer.rentMovie(movie);
      
    }
    public boolean checkCustomerBalance(int ID, Movie movie){
        Customer customer = customer(ID);
        int balance = customer.getBalance();
        int moviePrice = movie.getPrice();
        return balance >= moviePrice;
    }
    public void returnMovie(int ID){
        Customer customer = customer(ID);
        
        customer.returnMovie();
    } 
    public void returnMovieStep2(int ID, String title){
        Customer customer = customer(ID);
        
        customer.returnMovieStep2(title);
    }
}
    

