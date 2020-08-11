package org.zt.controller;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zt.model.request.CarAddRequest;
import org.zt.model.request.CarDeleteRequest;
import org.zt.model.request.CarListRequest;
import org.zt.model.request.CarStatusRequest;
import org.zt.model.response.CarListResponse;
import org.zt.model.response.CarStatusResponse;
import org.zt.service.CarService;

@Controller
public class CarController {
	
	@Autowired CarService carService;
	
	@RequestMapping(value = "/carAdd", method = POST)
	@ResponseBody
	public boolean carAdd(@RequestBody CarAddRequest carAddRequest) {
		
		System.out.println("userName"+carAddRequest.getUserId());
		boolean carAddResponse=carService.carAdd(carAddRequest);
		
		//UserRegisterResponse userRegisterResponse=new UserRegisterResponse();
		
		return carAddResponse;
	}
	
	@RequestMapping(value = "/carDelete", method = POST)
	@ResponseBody
	public boolean carDelete(@RequestBody CarDeleteRequest carDeleteRequest) {
		
		System.out.println("userName"+carDeleteRequest.getUserId());
		boolean carDeleteResponse=carService.carDelete(carDeleteRequest);
		
		//UserRegisterResponse userRegisterResponse=new UserRegisterResponse();
		
		return carDeleteResponse;
	}
	
	@RequestMapping(value = "/carList", method = POST)
	@ResponseBody
	public List<CarListResponse> carList(@RequestBody CarListRequest carListRequest) {
		
		System.out.println("userName"+carListRequest.getUserId());
		List<CarListResponse> carListResponse=carService.carList(carListRequest);
		System.out.println("carList"+carListResponse);
		
		return carListResponse;
	}
	
	@RequestMapping(value = "/carStatus", method = POST)
	@ResponseBody
	public List<CarStatusResponse> carStatus(@RequestBody CarStatusRequest carStatusRequest) {
		
		System.out.println("userId"+carStatusRequest.getUserId());
		List<CarStatusResponse> carStatusResponse=carService.carStatus(carStatusRequest);
		System.out.println("carStatus"+carStatusResponse);
		
		return carStatusResponse;
	}

}
