package org.zt.xdsmartpark.network;

import android.content.Context;

import com.google.gson.Gson;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.zt.xdsmartpark.model.request.BookRequest;
import org.zt.xdsmartpark.model.request.CarAddRequest;
import org.zt.xdsmartpark.model.request.CarDeleteRequest;
import org.zt.xdsmartpark.model.request.CarListRequest;
import org.zt.xdsmartpark.model.request.GetParkLocationRequest;
import org.zt.xdsmartpark.model.request.LoginRequest;
import org.zt.xdsmartpark.model.request.NeighborSearchRequest;
import org.zt.xdsmartpark.model.request.ParkingLogRequest;
import org.zt.xdsmartpark.model.request.RecommendRequest;
import org.zt.xdsmartpark.model.request.RegisterRequest;
import org.zt.xdsmartpark.model.request.CarStatusRequest;
import org.zt.xdsmartpark.model.request.SearchRequest;
import org.zt.xdsmartpark.model.request.UserFavoriteAddRequest;
import org.zt.xdsmartpark.model.request.UserFavoriteDeleteRequest;
import org.zt.xdsmartpark.model.request.UserFavoriteListRequest;
import org.zt.xdsmartpark.model.response.CarListResponse;
import org.zt.xdsmartpark.model.response.GetParkLocationResponse;
import org.zt.xdsmartpark.model.response.LoginResponse;
import org.zt.xdsmartpark.model.response.CarStatusResponse;
import org.zt.xdsmartpark.model.response.ParkInfoResponse;
import org.zt.xdsmartpark.model.response.ParkingLogResponse;
import org.zt.xdsmartpark.model.response.UserFavoriteResponse;
import org.zt.xdsmartpark.network.HP2P.entity.IPAddress;
import org.zt.xdsmartpark.network.HP2P.entity.Node;
import org.zt.xdsmartpark.network.HP2P.protocol.ApplyJoinHP2PRequest;
import org.zt.xdsmartpark.network.HP2P.protocol.JoinClusterRequest;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Network {
    public static String BASE_URL_API = "http://192.168.123.1:8080";
    private NetworkService service;

    public Network(Context context) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        AuthInterceptor authInterceptor = new AuthInterceptor(context);
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(interceptor);
        builder.addInterceptor(authInterceptor);
        OkHttpClient client = builder
                .connectTimeout(90, TimeUnit.SECONDS)
                .writeTimeout(90, TimeUnit.SECONDS)
                .readTimeout(90, TimeUnit.SECONDS)
                .build();

        Gson gson = new Gson();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL_API)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();

        service = retrofit.create(NetworkService.class);
    }

    public Network(Context context, IPAddress ipAddress) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        AuthInterceptor authInterceptor = new AuthInterceptor(context);
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(interceptor);
        builder.addInterceptor(authInterceptor);
        OkHttpClient client = builder
                .connectTimeout(90, TimeUnit.SECONDS)
                .writeTimeout(90, TimeUnit.SECONDS)
                .readTimeout(90, TimeUnit.SECONDS)
                .build();

        Gson gson = new Gson();

        BASE_URL_API="http://"+ipAddress.getIp()+":"+ipAddress.getPort();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL_API)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();

        service = retrofit.create(NetworkService.class);
    }

    public NetworkService getService() {
        return service;
    }

    public interface MyCallback<T> {
        void onSuccess(T response);

        void onError(String error);

    }

    public void Register (final RegisterRequest request, final MyCallback<String> callback) {
        service.userRegister(request).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()) {
                    callback.onSuccess("注册成功");
                }
                else {
                    callback.onError("注册失败");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
                callback.onError(t.getLocalizedMessage());
            }
        });
    }

    public void Login (final LoginRequest request, final MyCallback<LoginResponse> callback) {
        service.userLogin(request).enqueue(new Callback<LoginResponse>() {

            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if(response.isSuccessful()) {
                    callback.onSuccess(response.body());
                }
                else {
                    if (response.code() == 422) {
                        callback.onError("请检查您的用户名");
                    }
                    callback.onError("登录失败");
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                t.printStackTrace();
                callback.onError(t.getLocalizedMessage());
            }

        });
    }

    public void CarList (final CarListRequest request, final MyCallback<List<CarListResponse>> callback) {
        service.carList(request).enqueue(new Callback<List<CarListResponse>>() {

            @Override
            public void onResponse(Call<List<CarListResponse>> call, Response<List<CarListResponse>> response) {
                if(response.isSuccessful()) {
                    callback.onSuccess(response.body());
                }
                else {
                    callback.onError("获取车辆信息失败");
                }
            }

            @Override
            public void onFailure(Call<List<CarListResponse>> call, Throwable t) {
                t.printStackTrace();
                callback.onError(t.getLocalizedMessage());
            }

        });
    }

    public void CarDelete (final CarDeleteRequest request, final MyCallback<String> callback) {
        service.carDelete(request).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()) {
                    callback.onSuccess("删除成功");
                }
                else {
                    callback.onError("删除失败");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
                callback.onError(t.getLocalizedMessage());
            }
        });
    }

    public void CarAdd (final CarAddRequest request, final MyCallback<String> callback) {
        service.carAdd(request).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()) {
                    callback.onSuccess("添加车辆成功");
                }
                else {
                    callback.onError("添加车辆失败");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
                callback.onError(t.getLocalizedMessage());
            }
        });
    }

    public void CarStatus (final CarStatusRequest request, final MyCallback<List<CarStatusResponse>> callback) {
        service.carStatus(request).enqueue(new Callback<List<CarStatusResponse>>() {

            @Override
            public void onResponse(Call<List<CarStatusResponse>> call, Response<List<CarStatusResponse>> response) {
                if(response.isSuccessful()) {
                    callback.onSuccess(response.body());
                }
                else {
                    callback.onError("获取车辆状态信息失败");
                }
            }

            @Override
            public void onFailure(Call<List<CarStatusResponse>> call, Throwable t) {
                t.printStackTrace();
                callback.onError(t.getLocalizedMessage());
            }

        });
    }

    public void UserFavoriteList (final UserFavoriteListRequest request, final MyCallback<List<UserFavoriteResponse>> callback) {
        service.userFavoriteList(request).enqueue(new Callback<List<UserFavoriteResponse>>() {

            @Override
            public void onResponse(Call<List<UserFavoriteResponse>> call, Response<List<UserFavoriteResponse>> response) {
                if(response.isSuccessful()) {
                    callback.onSuccess(response.body());
                }
                else {
                    callback.onError("获取收藏停车场信息失败");
                }
            }

            @Override
            public void onFailure(Call<List<UserFavoriteResponse>> call, Throwable t) {
                t.printStackTrace();
                callback.onError(t.getLocalizedMessage());
            }

        });
    }

    public void UserFavoriteDelete (final UserFavoriteDeleteRequest request, final MyCallback<String> callback) {
        service.userFavoriteDelete(request).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()) {
                    callback.onSuccess("删除成功");
                }
                else {
                    callback.onError("删除失败");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
                callback.onError(t.getLocalizedMessage());
            }
        });
    }

    public void UserFavoriteAdd (final UserFavoriteAddRequest request, final MyCallback<String> callback) {
        service.userFavoriteAdd(request).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()) {
                    callback.onSuccess("收藏停车场成功");
                }
                else {
                    callback.onError("收藏停车场失败");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
                callback.onError(t.getLocalizedMessage());
            }
        });
    }

    public void GetParkLocation (final GetParkLocationRequest request, final MyCallback<GetParkLocationResponse> callback) {
        service.getParkLocation(request).enqueue(new Callback<GetParkLocationResponse>() {

            @Override
            public void onResponse(Call<GetParkLocationResponse> call, Response<GetParkLocationResponse> response) {
                if(response.isSuccessful()) {
                    callback.onSuccess(response.body());
                }
                else {
                    callback.onError("获取停车场经纬度失败");
                }
            }

            @Override
            public void onFailure(Call<GetParkLocationResponse> call, Throwable t) {
                t.printStackTrace();
                callback.onError(t.getLocalizedMessage());
            }

        });
    }

    public void ParkingLog (final ParkingLogRequest request, final MyCallback<List<ParkingLogResponse>> callback) {
        service.parkingLog(request).enqueue(new Callback<List<ParkingLogResponse>>() {

            @Override
            public void onResponse(Call<List<ParkingLogResponse>> call, Response<List<ParkingLogResponse>> response) {
                if(response.isSuccessful()) {
                    callback.onSuccess(response.body());
                }
                else {
                    callback.onError("获取停车记录失败");
                }
            }

            @Override
            public void onFailure(Call<List<ParkingLogResponse>> call, Throwable t) {
                t.printStackTrace();
                callback.onError(t.getLocalizedMessage());
            }

        });
    }

    public void Recommend (final RecommendRequest request, final MyCallback<List<ParkInfoResponse>> callback) {
        service.recommend(request).enqueue(new Callback<List<ParkInfoResponse>>() {

            @Override
            public void onResponse(Call<List<ParkInfoResponse>> call, Response<List<ParkInfoResponse>> response) {
                if(response.isSuccessful()) {
                    callback.onSuccess(response.body());
                }
                else {
                    callback.onError("获取收藏停车场信息失败");
                }
            }

            @Override
            public void onFailure(Call<List<ParkInfoResponse>> call, Throwable t) {
                t.printStackTrace();
                callback.onError(t.getLocalizedMessage());
            }

        });
    }

    public void ApplyJoinHP2P (final ApplyJoinHP2PRequest request, final MyCallback<List<Node>> callback) {
        service.applyJoinHP2P(request).enqueue(new Callback<List<Node>>() {

            @Override
            public void onResponse(Call<List<Node>> call, Response<List<Node>> response) {
                if(response.isSuccessful()) {
                    callback.onSuccess(response.body());
                }
                else {
                    callback.onError("申请加入HP2P网络失败");
                }
            }

            @Override
            public void onFailure(Call<List<Node>> call, Throwable t) {
                t.printStackTrace();
                callback.onError(t.getLocalizedMessage());
            }

        });
    }

    public void JoinCluster (final JoinClusterRequest request, final MyCallback<List<Node>> callback) {
        service.joinCluster(request).enqueue(new Callback<List<Node>>() {

            @Override
            public void onResponse(Call<List<Node>> call, Response<List<Node>> response) {
                if(response.isSuccessful()) {
                    callback.onSuccess(response.body());
                }
                else {
                    callback.onError("加入HP2P网络失败");
                }
            }

            @Override
            public void onFailure(Call<List<Node>> call, Throwable t) {
                t.printStackTrace();
                callback.onError(t.getLocalizedMessage());
            }

        });
    }


    public void Book (final BookRequest request, final MyCallback<String> callback) {
        service.book(request).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()) {
                    callback.onSuccess("预约成功");
                }
                else {
                    callback.onError("预约失败");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
                callback.onError(t.getLocalizedMessage());
            }
        });
    }

    public void Search (final SearchRequest request, final MyCallback<List<ParkInfoResponse>> callback) {
        service.search(request).enqueue(new Callback<List<ParkInfoResponse>>() {

            @Override
            public void onResponse(Call<List<ParkInfoResponse>> call, Response<List<ParkInfoResponse>> response) {
                if(response.isSuccessful()) {
                    callback.onSuccess(response.body());
                }
                else {
                    callback.onError("获取停车场信息失败");
                }
            }

            @Override
            public void onFailure(Call<List<ParkInfoResponse>> call, Throwable t) {
                t.printStackTrace();
                callback.onError(t.getLocalizedMessage());
            }

        });
    }

    public void NeighborSearch (final NeighborSearchRequest request, final MyCallback<List<ParkInfoResponse>> callback) {
        service.neighborSearch(request).enqueue(new Callback<List<ParkInfoResponse>>() {

            @Override
            public void onResponse(Call<List<ParkInfoResponse>> call, Response<List<ParkInfoResponse>> response) {
                if(response.isSuccessful()) {
                    callback.onSuccess(response.body());
                }
                else {
                    callback.onError("获取附近停车场信息失败");
                }
            }

            @Override
            public void onFailure(Call<List<ParkInfoResponse>> call, Throwable t) {
                t.printStackTrace();
                callback.onError(t.getLocalizedMessage());
            }

        });
    }
}
