/**
 * 
 */
/**
 * @author jolisabrown
 *
 */
module HW08 {
	requires transitive java.desktop;
	requires transitive java.rmi;
	exports provided.discovery;
	exports provided.rmiUtils;
	exports provided.datapacket;
	exports common;
	exports common.message;
	exports common.message.type;
	exports common.message.type.cmd;
}