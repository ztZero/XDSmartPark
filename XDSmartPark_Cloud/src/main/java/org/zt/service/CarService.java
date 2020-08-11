package org.zt.service;

import java.util.List;

import org.zt.model.request.CarAddRequest;
import org.zt.model.request.CarDeleteRequest;
import org.zt.model.request.CarListRequest;
import org.zt.model.request.CarStatusRequest;
import org.zt.model.response.CarListResponse;
import org.zt.model.response.CarStatusResponse;

public interface CarService {

	boolean carAdd(CarAddRequest carAddRequest);

	boolean carDelete(CarDeleteRequest carDeleteRequest);

	List<CarListResponse> carList(CarListRequest carListRequest);

	List<CarStatusResponse> carStatus(CarStatusRequest carStatusRequest);

}
