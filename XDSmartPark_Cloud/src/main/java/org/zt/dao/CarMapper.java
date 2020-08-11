package org.zt.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.zt.model.request.CarAddRequest;
import org.zt.model.request.CarDeleteRequest;
import org.zt.model.request.CarListRequest;
import org.zt.model.request.CarStatusRequest;
import org.zt.model.response.CarListResponse;
import org.zt.model.response.CarStatusResponse;

@Mapper
public interface CarMapper {

	@Insert("insert into car(userid,carplate) values(#{userId},#{carPlate})")
	int carAdd(CarAddRequest carAddRequest);

	@Delete("delete from car where userid=#{userId} and carid=#{carId}")
	int carDelete(CarDeleteRequest carDeleteRequest);

	@Select("select * from car where userid=#{userId}")
	List<CarListResponse> carList(CarListRequest carListRequest);

	@Select("select logid,parkname,carplate,entertime,parkfee from carstatus where userid=#{userId}")
	List<CarStatusResponse> carStatus(CarStatusRequest carStatusRequest);

}
