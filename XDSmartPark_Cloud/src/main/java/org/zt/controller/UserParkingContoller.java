package org.zt.controller;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
import org.zt.service.UserParkingService;

@Controller
public class UserParkingContoller {
	
	@Autowired UserParkingService userParkingService;
	
	@RequestMapping(value = "/userFavoriteAdd", method = POST)
	@ResponseBody
	public boolean userFavoriteAdd(@RequestBody UserFavoriteAddRequest userFavoriteAddRequest) {
		
		boolean userFavoriteAddResponse=userParkingService.userFavoriteAdd(userFavoriteAddRequest);
		
		
		return userFavoriteAddResponse;
	}
	
	@RequestMapping(value = "/userFavoriteDelete", method = POST)
	@ResponseBody
	public boolean userFavoriteDelete(@RequestBody UserFavoriteDeleteRequest userFavoriteDeleteRequest) {
		
		boolean userFavoriteDeleteResponse=userParkingService.userFavoriteDelete(userFavoriteDeleteRequest);
		
		
		return userFavoriteDeleteResponse;
	}
	
	@RequestMapping(value = "/userFavoriteList", method = POST)
	@ResponseBody
	public List<UserFavoriteResponse> userFavoriteList(@RequestBody UserFavoriteListRequest userFavoriteListRequest) {
		
		List<UserFavoriteResponse> userFavoriteListResponse=userParkingService.userFavoriteList(userFavoriteListRequest);
		
		return userFavoriteListResponse;
	}
	
	@RequestMapping(value = "/parkingLog", method = POST)
	@ResponseBody
	public List<ParkingLogResponse> parkingLog(@RequestBody ParkingLogRequest parkingLogRequest) {
		
		List<ParkingLogResponse> parkingLogResponse=userParkingService.parkingLog(parkingLogRequest);
		
		return parkingLogResponse;
	}
	
	@RequestMapping(value = "/getParkLocation", method = POST)
	@ResponseBody
	public GetParkLocationResponse getParkLocation(@RequestBody GetParkLocationRequest getParkLocationRequest) {
		
		GetParkLocationResponse getParkLocationResponse=userParkingService.getParkLocation(getParkLocationRequest);
		
		return getParkLocationResponse;
	}
	
	@RequestMapping(value = "/recommend", method = POST)
	@ResponseBody
	public List<ParkInfoResponse> recommend(@RequestBody RecommendRequest recommendRequest) {
		
		List<ParkInfoResponse> parkInfoResponse=userParkingService.recommend(recommendRequest);
		
		return parkInfoResponse;
	}
	
	@RequestMapping(value = "/neighborSearch", method = POST)
	@ResponseBody
	public List<ParkInfoResponse> neighborSearch(@RequestBody NeighborSearchRequest neighborSearchRequest) {
		
		List<ParkInfoResponse> parkInfoResponse=userParkingService.neighborSearch(neighborSearchRequest);
		
		System.out.println(neighborSearchRequest.getLatitude());
		
		return parkInfoResponse;
	}
	
	@RequestMapping(value = "/search", method = POST)
	@ResponseBody
	public List<ParkInfoResponse> search(@RequestBody SearchRequest searchRequest) {
		
		List<ParkInfoResponse> parkInfoResponse=userParkingService.search(searchRequest);
		
		return parkInfoResponse;
	}
	
	

}
