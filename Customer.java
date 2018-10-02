import java.util.*;
public class Customer
{
    private int ID;
    private String name;
    private int balance;
    private List<Movie> currentlyRented = new LinkedList<Movie>();
    private List<Movie> rentingHistory = new ArrayList<Movie>();
    
    public Customer(){
    
        }
    
    public Customer(int ID, String name, int balance){
        this.ID = ID;
        this.name = name;
        this.balance = balance;
    }
    //pushed right
    public void rentMovie(Movie movie){
        currentlyRented.add(movie);
        rentingHistory.add(movie);
    }
    //lookup
    private Movie currentlyRented(String title){
        for(Movie movie : currentlyRented){
            if(movie.hasTitle(title))
                return movie;
        }
        return null;
    }
    //display all
    private void viewRentingHistory(){
        for(Movie movie : rentingHistory){
            System.out.println(movie + "\n");
        }
    }
    
    public void returnMovie(){
        System.out.println(name + " has the following movies: ");
        System.out.println("Movies currently rented by " + name + ": ");
        for(Movie movie : currentlyRented){
            System.out.println(movie);
        }

    }
    public void returnMovieStep2(String title){
        for(Movie movie : currentlyRented){
            if(movie.hasTitle(title)){
                currentlyRented.remove(movie);
                System.out.println(title + " has been returned.\n");
            }
        }
    }
    
    public int getID(){
        return ID;
    }
    public String getName(){
        return name;
    }
    
    public void setBalance(int amount){
        balance = amount;
    }
    
    public int getBalance(){
        return balance;
    }
    public void getCurrentlyRented(){
        for(Movie movie : currentlyRented){
            System.out.print(movie);
        }
    }
    public void getRentingHistory(){
        for(Movie movie : rentingHistory){
            System.out.print(movie);
        }
    }
    
    public boolean hasID(int ID){
        return ID == (this.ID);
    }
    
    @Override
    public String toString(){
        return ID + "\t" + name + "\t$ " + balance; 
    }
}
