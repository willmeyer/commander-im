package com.willmeyer.commander.input.gtalk;

import org.jivesoftware.smack.*;

public class OpenJabberClient extends JabberClient {

	protected String server;
	protected String username;
	protected String password;
	
	@Override
	public String getConnectionDescription() {
		return "User " + this.username + " on " + server;
	}

	public OpenJabberClient(String server, String username, String password) {
		super();
		this.server = server;
		this.username = username;
		this.password = password;
	}
	
	@Override
	public void connectAndLogIn() throws Exception {
		if (conn != null) {
			return;
		}
		XMPPConnection.DEBUG_ENABLED = false;
		logger.info("Connecting to Jabber server...");
		conn = new XMPPConnection(server);
		conn.connect();
		logger.info("Connected...logging in...");
		Thread.sleep(5000);
		conn.login(username, password);
		logger.info("Logged-in.");
	}

}
