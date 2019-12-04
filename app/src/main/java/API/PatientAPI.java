package API;

import Model.PatientModel;
import Model.ResponseFromAPI;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface PatientAPI {
    @FormUrlEncoded
    @POST("mobilehealthcare/v1/patient/login")
    Call<ResponseFromAPI> CheckUser(@Field("email") String email, @Field("password")String password);

    @FormUrlEncoded
    @POST("mobilehealthcare/v1/admin/login")
    Call<ResponseFromAPI> CheckUserAdmin(@Field("email") String email, @Field("password")String password);

    @POST("mobilehealthcare/v1/patient/signup")
    Call<ResponseFromAPI> patientRegistration(@Body PatientModel patient);

    @GET("mobilehealthcare/v1/patient/{id}")
    Call<PatientModel> getPatientById( @Header("Authorization") String accessToken,@Path("id") int id);

    @Multipart
    @POST("/api/v1/uploadResume")
    Call<ResponseFromAPI> uploadResume(@Part MultipartBody.Part myResume);



}
