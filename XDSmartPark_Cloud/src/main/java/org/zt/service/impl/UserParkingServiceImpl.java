package org.zt.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zt.HP2P.IPAddress;
import org.zt.dao.UserParkingMapper;
import org.zt.entity.ParkingLot;
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


@Service
public class UserParkingServiceImpl implements UserParkingService{

	@Autowired UserParkingMapper userParkingMapper;
	@Override
	public boolean userFavoriteAdd(UserFavoriteAddRequest userFavoriteAddRequest) {
		int rows=userParkingMapper.userFavoriteAdd(userFavoriteAddRequest);
		System.out.println(rows);
		return rows>0?true:false;
	}

	@Override
	public boolean userFavoriteDelete(UserFavoriteDeleteRequest userFavoriteDeleteRequest) {
		int rows=userParkingMapper.userFavoriteDelete(userFavoriteDeleteRequest);
		System.out.println(rows);
		return rows>0?true:false;
	}

	@Override
	public List<UserFavoriteResponse> userFavoriteList(UserFavoriteListRequest userFavoriteListRequest) {
		List<UserFavoriteResponse> userFavoriteResponse=userParkingMapper.userFavoriteList(userFavoriteListRequest);
		return userFavoriteResponse;
	}

	@Override
	public List<ParkingLogResponse> parkingLog(ParkingLogRequest parkingLogRequest) {
		List<ParkingLogResponse> parkingLogResponse=userParkingMapper.parkingLog(parkingLogRequest);
		return parkingLogResponse;
	}

	@Override
	public GetParkLocationResponse getParkLocation(GetParkLocationRequest getParkLocationRequest) {
		GetParkLocationResponse getParkLocationResponse=userParkingMapper.getParkLocation(getParkLocationRequest);
		return getParkLocationResponse;
	}

	@Override
	public List<ParkInfoResponse> recommend(RecommendRequest recommendRequest) {
		List<ParkingLot> parkingLots=userParkingMapper.recommend(recommendRequest);
		List<ParkInfoResponse> parkInfoResponses=transform(parkingLots);
		// 推荐算法
		
		return parkInfoResponses;
	}

	@Override
	public List<ParkInfoResponse> neighborSearch(NeighborSearchRequest neighborSearchRequest) {
		List<ParkingLot> parkingLots=userParkingMapper.neighborSearch(neighborSearchRequest);
		List<ParkInfoResponse> parkInfoResponses=transform(parkingLots);
		
		return parkInfoResponses;
	}


	@Override
	public List<ParkInfoResponse> search(SearchRequest searchRequest) {
		List<ParkingLot> parkingLots=userParkingMapper.search(searchRequest);
		List<ParkInfoResponse> parkInfoResponses=transform(parkingLots);
		return parkInfoResponses;
	}
	
	
	//将ParkingLot中的ip和port转换为ParkInfoResponse中的IPAddress
	private List<ParkInfoResponse> transform(List<ParkingLot> parkingLots) {
		List<ParkInfoResponse> responses=new ArrayList<ParkInfoResponse>();
		int len=parkingLots.size();
		for(int i=0;i<len;i++) {
			int parkId=parkingLots.get(i).getParkId();
		    String parkName=parkingLots.get(i).getParkName();
		    double latitude=parkingLots.get(i).getLatitude();
		    double longitude=parkingLots.get(i).getLongitude();
		    double score=parkingLots.get(i).getScore();
		    double parkFee=parkingLots.get(i).getParkFee();
		    String parkAddress=parkingLots.get(i).getParkAddress();
		    int parkSpace=parkingLots.get(i).getParkSpace();
		    String ip=parkingLots.get(i).getIp();
		    int port=parkingLots.get(i).getPort();
		    IPAddress ipAddress=new IPAddress(ip, port);
			ParkInfoResponse temp=new ParkInfoResponse(parkId, parkName, latitude, longitude, score, parkFee, parkAddress, parkSpace, ipAddress);
			responses.add(temp);
		}
		return responses;
	}

}
