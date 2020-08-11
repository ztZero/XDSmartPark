package org.zt.HP2P;

import java.util.List;
import java.util.Map;

public class SuperNode extends Node{
	
	private int chordKey;
	private boolean isOnline;
	private List<IPAddress> neighborNodeList;
	private List<IPAddress> successor;
	private List<IPAddress> predecessor;
	private Map<Integer, List<IPAddress>> fingerTable;
	
	public int getChordKey() {
		return chordKey;
	}
	public void setChordKey(int chordKey) {
		this.chordKey = chordKey;
	}
	public boolean isOnline() {
		return isOnline;
	}
	public void setOnline(boolean isOnline) {
		this.isOnline = isOnline;
	}
	public List<IPAddress> getNeighborNodeList() {
		return neighborNodeList;
	}
	public void setNeighborNodeList(List<IPAddress> neighborNodeList) {
		this.neighborNodeList = neighborNodeList;
	}
	public List<IPAddress> getSuccessor() {
		return successor;
	}
	public void setSuccessor(List<IPAddress> successor) {
		this.successor = successor;
	}
	public List<IPAddress> getPredecessor() {
		return predecessor;
	}
	public void setPredecessor(List<IPAddress> predecessor) {
		this.predecessor = predecessor;
	}
	public Map<Integer, List<IPAddress>> getFingerTable() {
		return fingerTable;
	}
	public void setFingerTable(Map<Integer, List<IPAddress>> fingerTable) {
		this.fingerTable = fingerTable;
	}
	public SuperNode() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SuperNode(String nodeId, IPAddress ipAddress) {
		super(nodeId, ipAddress);
		// TODO Auto-generated constructor stub
	}
	public SuperNode(int chordKey, boolean isOnline, List<IPAddress> neighborNodeList, List<IPAddress> successor,
			List<IPAddress> predecessor, Map<Integer, List<IPAddress>> fingerTable) {
		super();
		this.chordKey = chordKey;
		this.isOnline = isOnline;
		this.neighborNodeList = neighborNodeList;
		this.successor = successor;
		this.predecessor = predecessor;
		this.fingerTable = fingerTable;
	}
	

}
