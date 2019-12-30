package API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Url {
//    public static final String BASE_URL="http://192.168.10.103:8001/"; //Mobile device home
    public static final String BASE_URL="http://172.26.0.27:8001/"; //Mobile device cloz
//    public static final String BASE_URL="http://192.168.74.1:8001/"; //emulator
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
