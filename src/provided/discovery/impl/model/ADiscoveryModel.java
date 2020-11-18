package provided.discovery.impl.model;

import java.rmi.Remote;
import provided.rmiUtils.IRMIUtils;

/**
 * Abstract Discovery Model that provides common services to its implementations
 * @author swong
 *
 * @param <TRemoteStub> The type of stub to which the endpoints are referencing
 */
public abstract class ADiscoveryModel<TRemoteStub extends Remote> {

	/**
	 * The IRMIUtils to use
	 */
	protected IRMIUtils rmiUtils;

	/**
	 * The DiscoveryConnector to use
	 */
	protected DiscoveryConnector discConn;
	
	/**
	 * The stub factory to use
	 */
	protected RemoteAPIStubFactory<TRemoteStub> remAPIStubFac;

	/**
	 * Constructor for the class
	 */
	public ADiscoveryModel() {
	}

	/**
	 * Internal service to perform some of the common processes to start the discovery model
	 * @param rmiUtils The ALREADY STARTED IRMIUtils to use
	 */
	protected void startInit(IRMIUtils rmiUtils) {
		this.rmiUtils = rmiUtils;
		this.remAPIStubFac = new RemoteAPIStubFactory<TRemoteStub>(rmiUtils);
	}

	/**
	 * Disconnect from the discovery server
	 */
	public void disconnectFromDiscoveryServer() {
		System.out.println("[DiscoveryModel.disconnectFromDiscoveryServer()] Disconnecting from the discovery server.");
		discConn.disconnect();
	}

	/**
	 * Stop this discovery model
	 */
	public void stop() {
		System.out.println("[DiscoveryModel.stop()] Stopping...");
		this.disconnectFromDiscoveryServer();
	}

}