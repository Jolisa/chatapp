package provided.discovery.impl.model;

import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.function.Consumer;


import provided.discovery.IEndPointData;
import provided.rmiUtils.IRMIUtils;

/**
 * An ADiscoveryModel that only supports watching the discovery server and connecting to end points.
 * This implementation does NOT support publishing and endpoint to the discovery server!
 * USAGE: This class should be used when the app is connecting to remote services but is NOT providing any services of its own to remote entities. 
 * @author swong
 *
 * @param <TRemoteStub>  The type of stub to which the endpoints are referencing
 */
public class DiscoveryModelWatchOnly<TRemoteStub extends Remote> extends ADiscoveryModel<TRemoteStub> {

	
	/**
	 * The adapter to this class's "view".
	 */
	private IDiscoveryModelToViewAdapter<TRemoteStub> viewAdpt;

	/**
	 * Constructor for the class.
	 * @param viewAdpt The adapter to this class's "view".
	 */
	public DiscoveryModelWatchOnly(IDiscoveryModelToViewAdapter<TRemoteStub> viewAdpt) {
		super();
		this.viewAdpt = viewAdpt;
	}
	
	
	/**
	 * Start the discovery model, configure to not publish an endpoint
	 * @param rmiUtils The ALREADY BE STARTED IRMIUtils to use.
	 */
	public void start(IRMIUtils rmiUtils)  {
		System.out.println("[DiscoveryModelWatchOnly.start() Watch Only] Starting...");
		startInit(rmiUtils);
		
		try {
			this.discConn = new DiscoveryConnector(rmiUtils); // DiscoveryConnector will only ignore watchOnly = false requests.
			System.err.println("[DiscoveryModelWatchOnly.start()] DiscoveryConnector instantiated.");
		} catch (RemoteException e) {
			System.err.println("[DiscoveryModelWatchOnly.start()] Exception while instantiating the DiscoveryConnector: "+e);
			e.printStackTrace();
		}
	}
	
	

	/**
	 * Connect to the discovery server, watching the given category but without publishing an endpoint
	 * @param category The category in the discovery server to connect to.
	 * @param endPtsUpdateFn A function to update the DiscoveryPanel, typically supplied by the panel itself.
	 */
	public void connectToDiscoveryServer(String category, Consumer<Iterable<IEndPointData>> endPtsUpdateFn) {
		System.out.println("[DiscoveryModelWatchOnly.connectToDiscoveryServer()] Connecting to the discovery server.");
		try {
			discConn.connectToDiscoveryServer(category, true, endPtsUpdateFn); // watchOnly must be true here.
		} catch (RemoteException e) {
			System.err.println("[DiscoveryModelWatchOnly.connectToDiscoveryServer()] Exception while connecting to discovery server: "+e);
			e.printStackTrace();
		}
		
	}

	/**
	 * Connect to the given endpoint, calling the Consumer<TRemoteStub> supplied to the constructor with the resultant stub.
	 * @param endPt  The end point containt 
	 */
	public void connectToEndPoint(IEndPointData endPt) {
		System.out.println("[DiscoveryModelWatchOnly.connectToEndPoint()] Connecting to endpoint: "+endPt);
		try {
			TRemoteStub stub = this.remAPIStubFac.get(endPt);
			viewAdpt.addStub(stub);
		} catch (RemoteException | NotBoundException e) {
			System.err.println("[DiscoveryModelWatchOnly.connectToEndPoint()] Exception while connecting to remote endpoint: "+e);
			e.printStackTrace();
		} 
	}

}
