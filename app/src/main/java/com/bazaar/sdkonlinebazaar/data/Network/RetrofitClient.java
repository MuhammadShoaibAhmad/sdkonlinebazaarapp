package com.bazaar.sdkonlinebazaar.data.Network;

import com.bazaar.sdkonlinebazaar.data.responses.PersionResponse;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public class RetrofitClient {
   // private static final String url = "http://"+ Constants.baseurl2 +"/";
  private static final String url = "http://194.163.144.128:5304/";

    public static Api api = null;

    public static Api getInstance() {
        if (api == null) {


            final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .readTimeout(60, TimeUnit.SECONDS)
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .writeTimeout(60, TimeUnit.SECONDS)
                    .build();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(okHttpClient)
                    .build();
            api = retrofit.create(Api.class);
        }
        return api;
    }

    public interface Api {

        @POST("Main/Login")
        Call<PersionResponse> userLogin(
                @Body PersionResponse post
        );


     /*   @FormUrlEncoded
        @POST("Account/CreatePersion")
        Call<PersionResponse> CreatePersion(
                @Field("UserName") String userName,
                @Field("Password") String password
        );*/



        @GET("Main/GetAllPerson")
        Call<List<PersionResponse>> getPersionInfo();

        @POST("Main/CreatePerson")
        Call<PersionResponse> CreatePersion(@Body PersionResponse post);


     /*   @POST("Main/UpdateLatLongPersonInfo")
        Call<Boolean> UpdateLatLong(@Body PersionResponse post);*/

        @GET("Main/UpdateLatLongPersonInfo")
        Call<String> UpdateLatLong(
                @Query("ID") int ID,
                @Query("Latitude") double Latitude,
                @Query("Longitude") double Longitude
        );

/*

    @GET("Notification/GetCriticalAlarms")
        Call<List<NotificationResponse>> getVehicleNotifications(
                @Query("CustomerID") int customerId,
                @Query("Orgid") int organizationId,
                @Query("OrgbrID") int organizationBranchId
        )
        @FormUrlEncoded
        @POST("Account/GetVehicleHistoryByCustomerVehicleId")
        Call<List<VehicleHistoryResponse>> getvehiclesHistory(
                @Field("CustomersVehiclesID") int cvId,
                @Field("startTime") String datefrom,
                @Field("endTime") String dateto
        );

        @GET("Account/GetUserNotifications")
        Call<List<AppNotificationResponse>> GetUserNotifications(
                        @Query("userid") int id
                );


        @GET("Account/CustomerInfo")
        Call<CustomerInfoResponse> getCustomerInfo(
                @Query("customervehicleid") int id
        );


        @GET("Account/GetAppRefreshable")
        Call<RefreshByCVIDResponse> getAppRefreshable(
                @Query("CustomersvehiclesID") int id
        );



        @FormUrlEncoded
        @POST("History/GetMain_Report_VehicleHistory")
        Call<List<VehicleHistoryResponse>> getMain_Report_VehicleHistory(
                @Field("CustomersVehiclesID") int cvId,
                @Field("startTime") String datefrom,
                @Field("endTime") String dateto
        );

        @FormUrlEncoded
        @POST("History/GetMain_Report_TripDetail")
        Call<List<TripDetailResponse>> getMain_Report_TripDetail(
                @Field("CustomersVehiclesID") int cvId,
                @Field("startTime") String datefrom,
                @Field("endTime") String dateto
        );*/

/*        @FormUrlEncoded
        @POST("History/GetMain_Report_IdleIgnitionOn")
        Call<List<VehicleHistoryResponse>> getMain_Report_IdleIgnitionOn(
                @Field("CustomersVehiclesID") int cvId,
                @Field("startTime") String datefrom,
                @Field("endTime") String dateto
        );


        @FormUrlEncoded
        @POST("History/GetMain_Report_Ignition_OnOffVM")
        Call<List<VehicleHistoryResponse>> getMain_Report_Ignition_OnOffVM(
                @Field("CustomersVehiclesID") int cvId,
                @Field("startTime") String datefrom,
                @Field("endTime") String dateto
        );

        @FormUrlEncoded
        @POST("History/GetMain_Reports_Mileage_Result")
        Call<List<VehicleHistoryResponse>> getMain_Reports_Mileage_Result(
                @Field("CustomersVehiclesID") int cvId,
                @Field("startTime") String datefrom,
                @Field("endTime") String dateto
        );


        @FormUrlEncoded
        @POST("History/GetMain_Reports_Mileage_Custom")
        Call<List<VehicleHistoryResponse>> getMain_Reports_Mileage_Custom(
                @Field("CustomersVehiclesID") int cvId,
                @Field("startTime") String datefrom,
                @Field("endTime") String dateto
        );




        @FormUrlEncoded
        @POST("Level/GetQuestions")
        Call<List<QuestionResponse>> GetQuestions(

                @Field("ID") String id,
                @Field("Name") String name,
                @Field("LevelID") int levelid
        );


        @FormUrlEncoded
        @POST("Level/GetQuizQuestions")
        Call<List<QuestionResponse>> GetQuizQuestions(

                *//*   @Field("ID") String id,*//*
                @Field("Name") String name,
                @Field("LevelID") int levelid
        );


        @FormUrlEncoded
        @POST("Subject/UpdateDiviceID")
        Call<String> updateDeviceID(

                @Field("ID") int id,
                @Field("DeviceID") String deviceid,
                @Field("IsFirst") int isfirst
        );


        @FormUrlEncoded
        @POST("Subject/SendFeedBack")
        Call<Void> SendFeedBack(

                @Field("UserName") String name,
                @Field("UserEmail") String email,
                @Field("UserMessage") String message
        );




        @FormUrlEncoded
        @POST("Level/GetSampleQuestions")
        Call<List<QuestionResponse>> GetSampleQuestions(

                *//*   @Field("ID") String id,*//*
                @Field("Name") String name,
                @Field("LevelID") int levelid
        );*/

      /*  @FormUrlEncoded
        @POST("Vehicle/GetLocation")
        Observable<VehicleLocationResponse> getVehicleLocation(
                @Field("VehicleId") int vehicleId
        );

        @FormUrlEncoded
        @POST("Reports/TripSummaryReport")
        Call<List<HistoryResponse>> getHistory(
                @Field("VehicleId") int vehicleId,
                @Field("DateFrom") String dateFrom,
                @Field("DateTo") String dateTo
        );

        @GET("Fence/GetFenceByVehicleId")
        Call<List<GeoFenceResponse>> getGeoFenceList(@Query("id") int id);

        @GET("Command/GetAppControls")
        Call<ControlsResponse> getControlsDetail(@Query("id") int id);

        @GET("Command/Send")
        Call<Boolean> executeCommand(
                @Query("VehicleId") int id,
                @Query("command") String commandId,
                @Query("description") String commandDescription
        );

        @GET("Notification/GetCriticalAlarms")
        Call<List<NotificationResponse>> getVehicleNotifications(
                @Query("CustomerID") int customerId,
                @Query("Orgid") int organizationId,
                @Query("OrgbrID") int organizationBranchId
        );*/


    }

}

