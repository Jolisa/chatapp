package provided.discovery.impl.model;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.function.Consumer;


import provided.discovery.IEndPointData;
import provided.rmiUtils.IRMIUtils;

/**
 * An ADiscoveryModel that only supports publishing an endpoint to the discovery server but NOT connecting to end points.
 * This implementation does NOT support connecting to endpoints in the discovery server!
 * USAGE: This class should be used when the app is only providing a service to remote clients and not connecting to other remote entities. 
 * @author swong
 *
 * @param <TRemoteStub>  The type of stub to which the endpoints are referencing
 */
public class DiscoveryModelPubOnly<TRemoteStub extends Remote> extends ADiscoveryModel<TRemoteStub> {

	
	/**
	 *
	 */
	public DiscoveryModelPubOnly() {
		super();
	}
	
	
	
	/**
	 * Start the discovery model, configured to publish an endpoint with the given info 
	 * @param rmiUtils The ALREADY BE STARTED IRMIUtils to use.
	 * @param discoveryName The friendly name to display in the discovery server for the published endpoint
	 * @param boundName The bound name of the stub in the local Registry
	 */
	public void start(IRMIUtils rmiUtils, String discoveryName, String boundName)  {
		System.out.println("[DiscoveryModelPubOnly.start()] Starting...");
		startInit(rmiUtils);
		
		try {
			this.discConn = new DiscoveryConnector(rmiUtils, discoveryName, boundName);
			System.err.println("[DiscoveryModelPubOnly.start() DiscoveryConnector instantiated.");
		} catch (RemoteException e) {
			System.err.println("[DiscoveryModelPubOnly.start()] Exception while instantiating the DiscoveryConnector: "+e);
			e.printStackTrace();
		}
	}

	/**
	 * Connect to the discovery server, publishing an endpoint to the given category
	 * @param category The category in the discovery server to connect to.
	 * @param endPtsUpdateFn A function to update the DiscoveryPanel, typically supplied by the panel itself.
	 */
	public void connectToDiscoveryServer(String category, Consumer<Iterable<IEndPointData>> endPtsUpdateFn) {
		System.out.println("[DiscoveryModelPubOnly.connectToDiscoveryServer()] Connecting to the discovery server.");
		try {
			discConn.connectToDiscoveryServer(category, false, endPtsUpdateFn);  // Makes no sense if watchOnlyh = true
		} catch (RemoteException e) {
			System.err.println("[DiscoveryModelPubOnly.connectToDiscoveryServer()] Exception while connecting to discovery server: "+e);
			e.printStackTrace();
		}
		
	}



}
