import java.util.*;
public class Catalogue
{
    private Kiosk kiosk;
    private List<Movie> moviesAvailable = new LinkedList<Movie>();
    private List<Movie> moviesRented = new LinkedList<Movie>();
    private List<Genre> genres = new ArrayList<Genre>();
    
    public Catalogue(Kiosk kiosk){
        this.kiosk = kiosk;
        
        genres.add(new Genre("SciFi")); //0
        genres.add(new Genre("Drama")); //1 
        genres.add(new Genre("Crime")); //2
        moviesAvailable.add(new Movie("Matrix", 1999, genres.get(0), 3));
        moviesAvailable.add(new Movie("Jurassic Park", 1993, genres.get(0), 4));
        moviesAvailable.add(new Movie("Terminator 2", 1991, genres.get(0), 3));
        moviesAvailable.add(new Movie("Titanic", 1997, genres.get(1), 4));
        moviesAvailable.add(new Movie("The Silence of the Lambs", 1991, genres.get(2), 3));
 
    }
    
    public void use(){
        char choice;

        while((choice = readChoice()) != 'R'){
        
            switch (choice){
                case '1': viewAllMovies(); break;
                case '2': viewAvailableMovies(); break;
                case '3': viewGenres(); break;
                case '4': viewByGenre(); break;
                case '5': viewByYear(); break;
                case '6': rentMovie(); break;
                case '7': returnMovie(); break;
                default: System.out.println("Please enter a number between 1 and 7 or press R to return to the previous menu."); break;
            }
        }
        
    }
    private char readChoice(){
        help();
        System.out.print("Enter a choice: ");
        return In.nextChar();
    }
    public void help(){
        System.out.println("Welcome to the Catalogue! Please make a selection from the menu: ");
        System.out.println("1. Display all movies.");
        System.out.println("2. Display all available movies.");
        System.out.println("3. Display all genres.");
        System.out.println("4. Display movies in a genre.");
        System.out.println("5. Display all movies by year.");
        System.out.println("6. Rent a movie.");
        System.out.println("7. Return a movie.");
        System.out.println("R. Return to previous menu.");
    }
    // Catalogue 1
    public void viewAllMovies(){
        System.out.println("\nThe Kiosk has the following movies: ");
        for(Movie movie : moviesAvailable){
            System.out.println(movie);
        }
        System.out.println("");
    }
    // Catalogue 2
    public void viewAvailableMovies(){
        System.out.println("\nThe following movies are available: ");
        for(Movie movie : moviesAvailable){
            System.out.println(movie);
        }
        System.out.println("");
    }
    // Catalogue 3
    private void viewGenres(){
        System.out.println("\nThe Kiosk has movies in the following genres: ");
        for(Genre genre : genres){
            System.out.println(genre);
        }
        System.out.println("");
    }
    // Catalogue 4
    private void viewByGenre(){
        String genreName = readSortingGenre();
        System.out.println("The kiosk has the following movies in that genre: ");
        for(Movie movie : moviesAvailable){
            if(movie.hasGenre(genreName))
                System.out.println(movie);
        }
        System.out.println("");
        
    }
    private String readSortingGenre(){
        System.out.print("\nEnter a genre: ");
        return In.nextLine();
    }
    // Catalogue 5
    private void viewByYear(){
        int year = readSortingYear();
        System.out.println("The kiosk has the following movies by that year: ");
        for(Movie movie : moviesAvailable){
            if(movie.hasYear(year))
                System.out.println(movie);
        }
        System.out.println("");
    }
    private int readSortingYear(){
        System.out.print("\nEnter the year: ");
        return In.nextInt();
    }
    private void rentMovie(){
        int ID = readValidID(); // CustomerID
        String title = readRentMovie();
        Movie movie = movie(title);
        
        if(movie != null){
            if(kiosk.checkCustomerBalance(ID, movie)){
                moviesAvailable.remove(movie);
                // add movie to rented movies list
                moviesRented.add(movie);
                // Pass on customer ID and title to Kiosk
                kiosk.rentMovie(ID, movie);
                System.out.println("Movie rented.\n");
     
            }
            else{
                System.out.println("You don't have sufficient funds to rent this movie.\n");
            }
        }
        else{
            System.out.println("That movie is not available or doesn't exist.\n");
        }
    }

    private int readValidID(){
        System.out.print("\nEnter a valid customer ID: ");
        return In.nextInt();
    }
    private String readRentMovie(){
        System.out.print("Enter the title of the movie you wish to rent: ");
        return In.nextLine();
    }
    // Catalogue 7
    private void returnMovie(){
        int ID = readValidID();
        kiosk.returnMovie(ID);
        returnMovieStep2(ID);

        
    }
    private void returnMovieStep2(int ID){
        String title = readReturnMovie();
        kiosk.returnMovieStep2(ID, title);
        
        
        Movie movie = rentedMovie(title);
        //add movie back to movies available
        moviesAvailable.add(movie);
        //remove movie from movies rented - lookup movie title
        moviesRented.remove(movie);
        
    }
    private String readReturnMovie(){
        System.out.print("Enter the title of the movie you wish to return: ");
        return In.nextLine();
    }
    //lookup
    private Movie rentedMovie(String title){
        for(Movie movie : moviesRented){
            if(movie.hasTitle(title))
                return movie;
        }
        return null;
    }
    // pushed right
    public void viewFavourites(String name){
        //movies cant have a count field
        //output top 3 most rented
        
        //declare new favourites list
        List<Movie> favourites = new LinkedList<Movie>();
        //add rented movies to list??
         
        //Refer back to the lecture notes counter array example in week 2. 
        //Use indexOf() method from LinkedList to track the idex of the favourite movies. 
        //Then create a method that get the top 3 frequencies
        
        System.out.println(name + "'s favourite movies are: ");
        //loop
        for(Movie movie : favourites){
            System.out.println(movie);
        }
        System.out.println("\n");
        
    }
    public void addMovie(){
        System.out.println("\nAdding a new movie.");
        String title = readTitle();
        int year = readYear();
        Genre genre = new Genre(readGenre());
        int price = readPrice();
        
        Movie movie = movie(title);
        if(movie != null){
            System.out.println("The movie is already in the catalogue.");
        }
        else{
            
            moviesAvailable.add(new Movie(title, year, genre, price));
        }
    }
    //lookup
    private Movie movie(String title){
        for(Movie movie : moviesAvailable){
            if(movie.hasTitle(title))
                return movie;
        }
        return null;
    }
    public void removeMovie(){
        System.out.println("\nRemoving a movie.");
        String title = readTitle();
        int year = readYear();
        
        Movie movie = movie (title);
        
        if(movie != null){
            if(movie.hasTitle(title) && movie.hasYear(year)){
                
                System.out.println(movie.toString() + " removed from catalogue.\n"); 
            }
        }
        else{
            System.out.println("No such movie found.\n");
        }

    }
    private String readTitle(){
        System.out.print("Enter the title of the Movie: ");
        return In.nextLine();
    }
    private int readYear(){
        System.out.print("Enter the year: ");
        return In.nextInt();
    }
    private String readGenre(){
        System.out.print("Enter the genre: ");
        return In.nextLine();
    }
    private int readPrice(){
        System.out.print("Enter price: ");
        return In.nextInt();
    }
       
}

