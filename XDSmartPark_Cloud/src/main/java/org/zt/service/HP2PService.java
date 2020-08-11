package org.zt.service;

import java.util.List;

import org.zt.HP2P.Node;
import org.zt.model.request.ApplyJoinHP2PRequest;

public interface HP2PService {

	List<Node> applyJoinHP2P(ApplyJoinHP2PRequest applyJoinRequest);

}
