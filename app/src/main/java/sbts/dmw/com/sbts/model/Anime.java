package sbts.dmw.com.sbts.model;

public class Anime {

    private String name;
    private String description;
    private String rating;
    private int numberOfEpisodes;
    private String categories;
    private String studio;
    private String imageURL;

    public Anime() {
    }

    public Anime(String name, String description, String rating, int numberOfEpisodes, String categories, String studio, String imageURL) {
        this.name = name;
        this.description = description;
        this.rating = rating;
        this.numberOfEpisodes = numberOfEpisodes;
        this.categories = categories;
        this.studio = studio;
        this.imageURL = imageURL;
    }

    //getter

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getRating() {
        return rating;
    }

    public int getNumberOfEpisodes() {
        return numberOfEpisodes;
    }

    public String getCategories() {
        return categories;
    }

    public String getStudio() {
        return studio;
    }

    public String getImageURL() {
        return imageURL;
    }

    //setter

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setNumberOfEpisodes(int numberOfEpisodes) {
        this.numberOfEpisodes = numberOfEpisodes;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

}
