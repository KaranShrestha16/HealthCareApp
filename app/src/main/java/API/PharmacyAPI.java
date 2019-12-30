package API;

import java.util.List;

import Model.PharmacyModel;
import Model.ReportModel;
import Model.ResponseFromAPI;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface PharmacyAPI {

    @GET("mobilehealthcare/v1/pharmacy/getAll")
    Call<List<PharmacyModel>> getAll(@Header("Authorization") String accessToken);


    @POST("mobilehealthcare/v1/pharmacy/addPharmacy")
    Call<ResponseFromAPI> addPharamcy(@Header("Authorization") String accessToken, @Body PharmacyModel pharmacyModel);


    @GET("mobilehealthcare/v1/report/getReportByid/{id}")
    Call<List<ReportModel>> getReportById(@Header("Authorization") String accessToken, @Path("id") int id);


    @POST("mobilehealthcare/v1/report/addReport")
    Call<ResponseFromAPI> addReport(@Header("Authorization") String accessToken, @Body ReportModel reportModel);

    @Multipart
    @POST("mobilehealthcare/v1/imageUpload")
    Call<ResponseFromAPI> uploadImage(@Part MultipartBody.Part myImage);

}
