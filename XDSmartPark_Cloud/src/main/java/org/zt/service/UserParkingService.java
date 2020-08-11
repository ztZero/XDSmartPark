package org.zt.service;

import java.util.List;

import org.zt.model.request.GetParkLocationRequest;
import org.zt.model.request.NeighborSearchRequest;
import org.zt.model.request.ParkingLogRequest;
import org.zt.model.request.RecommendRequest;
import org.zt.model.request.SearchRequest;
import org.zt.model.request.UserFavoriteAddRequest;
import org.zt.model.request.UserFavoriteDeleteRequest;
import org.zt.model.request.UserFavoriteListRequest;
import org.zt.model.response.GetParkLocationResponse;
import org.zt.model.response.ParkInfoResponse;
import org.zt.model.response.ParkingLogResponse;
import org.zt.model.response.UserFavoriteResponse;

public interface UserParkingService {

	boolean userFavoriteAdd(UserFavoriteAddRequest userFavoriteAddRequest);

	boolean userFavoriteDelete(UserFavoriteDeleteRequest userFavoriteDeleteRequest);

	List<UserFavoriteResponse> userFavoriteList(UserFavoriteListRequest userFavoriteListRequest);

	List<ParkingLogResponse> parkingLog(ParkingLogRequest parkingLogRequest);

	GetParkLocationResponse getParkLocation(GetParkLocationRequest getParkLocationRequest);

	List<ParkInfoResponse> recommend(RecommendRequest recommendRequest);

	List<ParkInfoResponse> neighborSearch(NeighborSearchRequest neighborSearchRequest);

	List<ParkInfoResponse> search(SearchRequest searchRequest);

}
