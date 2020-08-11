package org.zt.controller;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zt.HP2P.Node;
import org.zt.model.request.ApplyJoinHP2PRequest;
import org.zt.service.HP2PService;

@Controller
public class HP2PController {
	
	@Autowired HP2PService hService;
	
	@RequestMapping(value = "/applyJoinHP2P", method = POST)
	@ResponseBody
	public List<Node> applyJoinHP2P(@RequestBody ApplyJoinHP2PRequest applyJoinRequest) {
		
		List<Node> superNodeList=hService.applyJoinHP2P(applyJoinRequest);
		
		return superNodeList;
	}
	
}
