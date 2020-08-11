package org.zt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zt.dao.CarMapper;
import org.zt.model.request.CarAddRequest;
import org.zt.model.request.CarDeleteRequest;
import org.zt.model.request.CarListRequest;
import org.zt.model.request.CarStatusRequest;
import org.zt.model.response.CarListResponse;
import org.zt.model.response.CarStatusResponse;
import org.zt.service.CarService;

@Service
public class CarServiceImpl implements CarService {

	@Autowired CarMapper carMapper;
	@Override
	public boolean carAdd(CarAddRequest carAddRequest) {
		int rows=carMapper.carAdd(carAddRequest);
		System.out.println(rows);
		return rows>0?true:false;
	}
	@Override
	public boolean carDelete(CarDeleteRequest carDeleteRequest) {
		int rows=carMapper.carDelete(carDeleteRequest);
		System.out.println(rows);
		return rows>0?true:false;
	}
	@Override
	public List<CarListResponse> carList(CarListRequest carListRequest) {
		List<CarListResponse> carListResponse=carMapper.carList(carListRequest);
		return carListResponse;

	}
	@Override
	public List<CarStatusResponse> carStatus(CarStatusRequest carStatusRequest) {
		List<CarStatusResponse> carStatusResponse=carMapper.carStatus(carStatusRequest);
		return carStatusResponse;
	}

}
