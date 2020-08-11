package org.zt.xdsmartpark.network.HP2P.protocol;

import com.google.gson.annotations.SerializedName;

import org.zt.xdsmartpark.network.HP2P.entity.IPAddress;

public class JoinClusterRequest {

    @SerializedName("nodeId")
    private int nodeId;
    @SerializedName("ipAddress")
    private IPAddress ipAddress;

    public JoinClusterRequest(int nodeId, IPAddress ipAddress) {
        this.nodeId = nodeId;
        this.ipAddress=ipAddress;
    }

    public int getNodeId() {
        return nodeId;
    }

    public void setNodeId(int nodeId) {
        this.nodeId = nodeId;
    }

    public IPAddress getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(IPAddress ipAddress) {
        this.ipAddress = ipAddress;
    }

    public JoinClusterRequest() {
    }
}
