package edu.illinois.cs425_mp1.monitor;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import edu.illinois.cs425_mp1.adapter.Adapter;
import edu.illinois.cs425_mp1.types.Node;
import edu.illinois.cs425_mp1.types.NodeStatus;

public class HeartbeatExaminer implements Runnable {

	//Time intervals in ms
	private final long sleepInterval = 100;
	private final long failInterval = 1000;
	private final long kickoutInterval = 2000;
	
	public void run() {
		while(true) {
			try {
				Thread.sleep(sleepInterval);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			synchronized(HeartbeatAdapter.membershipList) {
				for(Integer index : HeartbeatAdapter.membershipList) {
					DateTime time = new DateTime();
					Node member = HeartbeatAdapter.membershipList.getNode(index);
					if(member.getTimeStamp().getMillis() - time.getMillis() > kickoutInterval) {
						System.out.println(member.getAddress() + " get cleaned out.");
						member.setStatus(NodeStatus.NONE);
					}
					else if(member.getTimeStamp().getMillis() - time.getMillis() > failInterval) {
						System.out.println(member.getAddress() + " seems failed");
						member.setStatus(NodeStatus.FAIL);
					} else {
						member.setStatus(NodeStatus.ACTIVE);
					}
					HeartbeatAdapter.membershipList.updateNeighborInfo(index, member);
				}
			}
		}
	}
	
}