package API;

        import java.util.List;

        import Model.AmbulanceModel;
        import Model.HospitalModel;
        import Model.PatientModel;
        import Model.ResponseFromAPI;
        import retrofit2.Call;
        import retrofit2.http.Body;
        import retrofit2.http.GET;
        import retrofit2.http.Header;
        import retrofit2.http.POST;

public interface AmbulanceAPI {

    @GET("mobilehealthcare/v1/ambulance/getAll")
    Call<List<AmbulanceModel>> getAll(@Header("Authorization") String accessToken);


    @POST("mobilehealthcare/v1/ambulance/add")
    Call<ResponseFromAPI> addAmbulance(@Header("Authorization") String accessToken,@Body AmbulanceModel ambulanceModel);

}
