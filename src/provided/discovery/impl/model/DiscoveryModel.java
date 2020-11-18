package provided.discovery.impl.model;

import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.function.Consumer;


import provided.discovery.IEndPointData;
import provided.rmiUtils.IRMIUtils;

/**
 * Full featured ADiscoverModel that supports BOTH publishing an endpoint to a discovery server and connecting to endpoints on the discovery server.
 * USAGE: This class should be used when the app is BOTH connecting to remote services AND providing any services of its own to remote entities. 
 * Note: This class can be started with watchOnly = true which not publish an endpoint but allow connections to endpoints.
 * @author swong
 *
 * @param <TRemoteStub> The type of stub to which the endpoints are referencing
 */
public class DiscoveryModel<TRemoteStub extends Remote> extends DiscoveryModelPubOnly<TRemoteStub> {

	/**
	 * The adapter to this class's "view".
	 */
	private IDiscoveryModelToViewAdapter<TRemoteStub> viewAdpt;

	
	/**
	 * Constructor for the class.
	 * @param viewAdpt The adapter to this class's "view".
	 */
	public DiscoveryModel(IDiscoveryModelToViewAdapter<TRemoteStub> viewAdpt) {
		super();
		this.viewAdpt = viewAdpt;
	}
	
	
	/**
	 * Start the discovery model, configured to publish an endpoint with the given info 
	 * if connectToDiscoveryServer() is called with watchOnly = false.
	 * @param rmiUtils The ALREADY BE STARTED IRMIUtils to use.
	 * @param discoveryName The friendly name to display in the discovery server for the published endpoint
	 * @param boundName The bound name of the stub in the local Registry
	 */
	public void start(IRMIUtils rmiUtils, String discoveryName, String boundName)  {
		System.out.println("[DiscoveryModel.start() With Published End Point] Starting...");
		startInit(rmiUtils);
		
		try {
			this.discConn = new DiscoveryConnector(rmiUtils, discoveryName, boundName);
			System.err.println("[DiscoveryModel.start()  With Published End Point] DiscoveryConnector instantiated.");
		} catch (RemoteException e) {
			System.err.println("[DiscoveryModel.start()  With Published End Point] Exception while instantiating the DiscoveryConnector: "+e);
			e.printStackTrace();
		}
	}

	/**
	 * Connect to the discovery server with a given category, optionally publishing an endpoint to the given category
	 * @param category The category in the discovery server to connect to.
	 * @param watchOnly If true then do not publish an end point to the discovery server
	 * @param endPtsUpdateFn A function to update the DiscoveryPanel, typically supplied by the panel itself.
	 */
	public void connectToDiscoveryServer(String category, boolean watchOnly,
			Consumer<Iterable<IEndPointData>> endPtsUpdateFn) {
		System.out.println("[DiscoveryModel.connectToDiscoveryServer()] Connecting to the discovery server.");
		try {
			discConn.connectToDiscoveryServer(category, watchOnly, endPtsUpdateFn);
		} catch (RemoteException e) {
			System.err.println("[DiscoveryModel.connectToDiscoveryServer()] Exception while connecting to discovery server: "+e);
			e.printStackTrace();
		}
		
	}

	/**
	 * Connect to the given endpoint, calling the Consumer<TRemoteStub> supplied to the constructor with the resultant stub.
	 * @param endPt  The end point containt 
	 */
	public void connectToEndPoint(IEndPointData endPt) {
		System.out.println("[DiscoveryModel.connectToEndPoint()] Connecting to endpoint: "+endPt);
		try {
			TRemoteStub stub = this.remAPIStubFac.get(endPt);
			viewAdpt.addStub(stub);
		} catch (RemoteException | NotBoundException e) {
			System.err.println("[DiscoveryModel.connectToEndPoint()] Exception while connecting to remote endpoint: "+e);
			e.printStackTrace();
		} 
	}

}
