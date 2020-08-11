package org.zt.xdsmartpark.network.HP2P.protocol;

import android.widget.Toast;

import org.zt.xdsmartpark.activity.MainActivity;
import org.zt.xdsmartpark.network.HP2P.cluster.MobileNode;
import org.zt.xdsmartpark.network.HP2P.entity.IPAddress;
import org.zt.xdsmartpark.network.HP2P.entity.Node;
import org.zt.xdsmartpark.network.Network;

import java.util.List;

public class Protocol {

    private Network network;


    //申请加群算法,云端返回超级节点列表
    public List<Node> applyJoinHP2P(MainActivity context,MobileNode mobileNode){

        int nodeId=mobileNode.getNodeId();
        IPAddress ipAddress=mobileNode.getIpAddress();
        double latitude=mobileNode.getLatitude();
        double longitude=mobileNode.getLongitude();
        network = new Network(context);
        ApplyJoinHP2PRequest applyJoinRequest=new ApplyJoinHP2PRequest(nodeId,ipAddress,latitude,longitude);
        network.ApplyJoinHP2P(applyJoinRequest, new Network.MyCallback<List<Node>>() {
            @Override
            public void onSuccess(List<Node> response) {
                mobileNode.setSuperNodeList(response);
            }

            @Override
            public void onError(String error) {
                Toast.makeText(context, "申请加入HP2P网络失败", Toast.LENGTH_SHORT).show();
            }
        });
        return mobileNode.getSuperNodeList();
    }

    //向超级节点发送加群请求，得到邻居节点列表
    public void joinCluster(MainActivity context,MobileNode mobileNode){

        List<Node> NodeList=applyJoinHP2P(context,mobileNode);
        if(null!=NodeList){
            int nodeId=mobileNode.getNodeId();
            IPAddress ipAddress=mobileNode.getIpAddress();
            network = new Network(context,ipAddress);
            JoinClusterRequest joinHP2PRequest=new JoinClusterRequest(nodeId,ipAddress);
            network.JoinCluster(joinHP2PRequest, new Network.MyCallback<List<Node>>() {
                @Override
                public void onSuccess(List<Node> response) {

                    mobileNode.setNeighborNodeList(response);

                    mobileNode.setOnline(true);
                }

                @Override
                public void onError(String error) {
                    Toast.makeText(context, "申请加入HP2P网络失败", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
