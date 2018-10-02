public class Movie
{
    private String title;
    private int year;
    private int price;
    private Genre genre;
    
    public Movie(String title, int year, Genre genre, int price){
        this.title = title;
        this.year = year;
        this.genre = genre;
        this.price = price;
    }
    
    //pushed right
   
    public boolean hasTitle(String title){
        return title.equals(this.title);
    }
    public boolean hasYear(int year){
        return year == (this.year);
    }
    public boolean hasGenre(String name){
        return name.equals(genre.toString());  
    }
    public int getPrice(){
        return price;
    }
    @Override
    public String toString(){
        return year + "\t" + title + "\t" + genre + "\t$" + price;
    }
    
}
