package org.zt.xdsmartpark.network;

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
import org.zt.xdsmartpark.network.HP2P.entity.Node;
import org.zt.xdsmartpark.network.HP2P.protocol.ApplyJoinHP2PRequest;
import org.zt.xdsmartpark.network.HP2P.protocol.JoinClusterRequest;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface NetworkService {


    @Headers("Accept: application/json")
    @POST("/userRegister")
    Call<ResponseBody> userRegister(@Body RegisterRequest request);

    @Headers("Accept: application/json")
    @POST("/userLogin")
    Call<LoginResponse> userLogin(@Body LoginRequest request);

    @Headers("Accept: application/json")
    @POST("/carList")
    Call<List<CarListResponse>> carList(@Body CarListRequest request);

    @Headers("Accept: application/json")
    @POST("/carDelete")
    Call<ResponseBody> carDelete(@Body CarDeleteRequest request);

    @Headers("Accept: application/json")
    @POST("/carAdd")
    Call<ResponseBody> carAdd(@Body CarAddRequest request);

    @Headers("Accept: application/json")
    @POST("/carStatus")
    Call<List<CarStatusResponse>> carStatus(@Body CarStatusRequest request);

    @Headers("Accept: application/json")
    @POST("/userFavoriteList")
    Call<List<UserFavoriteResponse>> userFavoriteList(@Body UserFavoriteListRequest request);

    @Headers("Accept: application/json")
    @POST("/userFavoriteDelete")
    Call<ResponseBody> userFavoriteDelete(@Body UserFavoriteDeleteRequest request);

    @Headers("Accept: application/json")
    @POST("/userFavoriteAdd")
    Call<ResponseBody> userFavoriteAdd(@Body UserFavoriteAddRequest request);

    @Headers("Accept: application/json")
    @POST("/getParkLocation")
    Call<GetParkLocationResponse> getParkLocation(@Body GetParkLocationRequest request);

    @Headers("Accept: application/json")
    @POST("/parkingLog")
    Call<List<ParkingLogResponse>> parkingLog(@Body ParkingLogRequest request);


    @Headers("Accept: application/json")
    @POST("/recommend")
    Call<List<ParkInfoResponse>> recommend(@Body RecommendRequest request);

    //停车场端实现预定服务
    @Headers("Accept: application/json")
    @POST("/book")
    Call<ResponseBody> book(@Body BookRequest request);

    @Headers("Accept: application/json")
    @POST("/search")
    Call<List<ParkInfoResponse>> search(@Body SearchRequest request);

    @Headers("Accept: application/json")
    @POST("/neighborSearch")
    Call<List<ParkInfoResponse>> neighborSearch(@Body NeighborSearchRequest request);

    @Headers("Accept: application/json")
    @POST("/applyJoinHP2P")
    Call<List<Node>> applyJoinHP2P(@Body ApplyJoinHP2PRequest request);

    @Headers("Accept: application/json")
    @POST("/joinCluster")
    Call<List<Node>> joinCluster(@Body JoinClusterRequest request);
}
