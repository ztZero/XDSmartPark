package org.zt.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
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
import org.zt.model.response.ParkingLogResponse;
import org.zt.model.response.UserFavoriteResponse;

@Mapper
public interface UserParkingMapper {

	@Insert("insert into userfavorite(userid,parkid) values(#{userId},#{parkId})")
	int userFavoriteAdd(UserFavoriteAddRequest userFavoriteAddRequest);
	
	@Delete("delete from userfavorite where userid=#{userId} and favoriteid=#{favoriteId}")
	int userFavoriteDelete(UserFavoriteDeleteRequest userFavoriteDeleteRequest);
	
	@Select("select favoriteid,parkname,userfavorite.parkid,parkfee from userfavorite,parkinglot where userid=#{userId}")
	List<UserFavoriteResponse> userFavoriteList(UserFavoriteListRequest userFavoriteListRequest);
	
	@Select("select logid,entertime,leavetime,carplate,parkname from parkinglog where userid=#{userId}")
	List<ParkingLogResponse> parkingLog(ParkingLogRequest parkingLogRequest);

	@Select("select parkid,latitude,longitude from parkinglot where parkid=#{parkId}")
	GetParkLocationResponse getParkLocation(GetParkLocationRequest getParkLocationRequest);

	@Select("select * from parkinglot")
	List<ParkingLot> neighborSearch(NeighborSearchRequest neighborSearchRequest);

	@Select("select * from parkinglot")
	List<ParkingLot> recommend(RecommendRequest recommendRequest);

	@Select("select * from parkinglot")
	List<ParkingLot> search(SearchRequest searchRequest);
	
	

}
