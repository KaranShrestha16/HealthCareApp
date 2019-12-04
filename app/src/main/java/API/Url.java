package API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Url {
    public static final String BASE_URL="http://192.168.74.1:8001/";
    public static String accessToken="";
    public static int id;
    public static Retrofit getInstance(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Url.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
}
