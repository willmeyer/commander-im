package com.willmeyer.commander.input.gtalk;

import org.jivesoftware.smack.*;

public class GTalkClient extends JabberClient {

	@Override
	public String getConnectionDescription() {
		return "User " + this.username + " on GTalk";
	}

	protected String username;
	protected String password;
	
	public GTalkClient(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	
	@Override
	public void connectAndLogIn() throws Exception {
		if (conn != null) {
			return;
		}
		XMPPConnection.DEBUG_ENABLED = false;
		logger.info("Connecting to Google Jabber server...");
	    ConnectionConfiguration connConfig = new ConnectionConfiguration("talk.google.com", 5222, "gmail.com");
		conn = new XMPPConnection(connConfig);
		conn.connect();
		logger.info("Connected...logging in...");
		Thread.sleep(5000);
		conn.login(username, password);
		logger.info("Logged-in.");
	}

}
