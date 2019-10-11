package expencive.vk.com.fragmentbottomnavigation.models;

public class AnimalModel {

    private String imageUrl;
    private String title;
    private String author;
    private  int quantity;


    public static class Builder{
        private final  String title;
        private final  int quantity;


        private  String imageUrl;
        private  String author;

        public Builder(String title, int quantity) {
            this.title = title;
            this.quantity = quantity;
        }

        public Builder imageUrl(String val){
            imageUrl = val;
            return this;
        }

        public Builder author(String val){
            author = val;
            return this;
        }

        public AnimalModel build(){
            return new AnimalModel(this);
        }
    }

    private AnimalModel(Builder builder){
        title = builder.title;
        quantity = builder.quantity;
        imageUrl = builder.imageUrl;
        author = builder.author;

    }


    public String getImageUrl() {
        return imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getQuantity() {
        return quantity;
    }
}
