package API;

import java.util.List;

import Fragments.Hospital_Services_Fragment;
import Model.Doctor_HospitalModel;
import Model.HospitalModel;
import Model.PatientModel;
import Model.ResponseFromAPI;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface HospitalAPI {

    @GET("mobilehealthcare/v1/hospital/getAll")
    Call<List<HospitalModel>> getAll(@Header("Authorization") String accessToken);

    @GET("mobilehealthcare/v1/hospital/{id}")
    Call<HospitalModel> getHospitalById(@Header("Authorization") String accessToken, @Path("id") int id);

    @POST("mobilehealthcare/v1/hospital/add")
    Call<ResponseFromAPI> addHospital(@Header("Authorization") String accessToken, @Body HospitalModel hospitalModel);


    @GET("mobilehealthcare/v1/hospital/getAllDoctors/{id}")
    Call<List<Doctor_HospitalModel>> getDoctorById(@Header("Authorization") String accessToken, @Path("id") int id);

    @GET("mobilehealthcare/v1/hospital/getAllDoctorsWithDepartment/{id}")
    Call<List<Doctor_HospitalModel>> getDoctorByHospitalId(@Header("Authorization") String accessToken, @Header("Department") String department, @Path("id") int id);


}
