package API;

import java.util.List;

import Model.BloodBankModel;
import Model.PharmacyModel;
import Model.ResponseFromAPI;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface BloodBankAPI {

    @GET("mobilehealthcare/v1/bloodbank/getAll")
    Call<List<BloodBankModel>> getAll(@Header("Authorization") String accessToken);


    @POST("mobilehealthcare/v1/bloodbank/add")
    Call<ResponseFromAPI> addBloodBank(@Header("Authorization") String accessToken, @Body BloodBankModel bloodBankModel);
}
