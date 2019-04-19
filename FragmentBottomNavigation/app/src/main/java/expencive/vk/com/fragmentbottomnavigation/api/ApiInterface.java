package expencive.vk.com.fragmentbottomnavigation.api;

import expencive.vk.com.fragmentbottomnavigation.models.Animals;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("api.php?query=cat")
    Call<Animals> getCats();



    @GET("api.php?query=dog")
    Call<Animals> getDogs();
}
