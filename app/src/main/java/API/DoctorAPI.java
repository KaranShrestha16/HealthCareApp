package API;

import java.util.List;

import Model.BloodBankModel;
import Model.DoctorModel;
import Model.Doctor_HospitalModel;
import Model.Hospital_DoctorModel;
import Model.ResponseFromAPI;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface DoctorAPI {

    @GET("mobilehealthcare/v1/doctor/getAll")
    Call<List<DoctorModel>> getAll(@Header("Authorization") String accessToken);


    @POST("mobilehealthcare/v1/doctor/register")
    Call<DoctorModel> addDoctor(@Header("Authorization") String accessToken, @Body DoctorModel doctorModel);


    @POST("mobilehealthcare/v1/doctor/addDoctor_Hospital")
    Call<ResponseFromAPI> addDoctorHospital(@Header("Authorization") String accessToken, @Body Hospital_DoctorModel doctor_hospitalModel);


    @GET("mobilehealthcare/v1/doctor/getAllDoctorsWithHospital/{id}")
    Call<List<Doctor_HospitalModel>> getDoctorByHospitalId(@Header("Authorization") String accessToken, @Path("id") int id);

    @GET("mobilehealthcare/v1/doctor/{id}")
    Call<DoctorModel> getById(@Header("Authorization") String accessToken, @Path("id") int id);

}
