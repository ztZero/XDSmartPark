package org.zt.HP2P;

public class Node {
	
	private String nodeId;
	private IPAddress ipAddress;

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public IPAddress getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(IPAddress ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Node() {
    }

    public Node(String nodeId, IPAddress ipAddress) {
        this.nodeId = nodeId;
        this.ipAddress = ipAddress;
    }

}
