package com.willmeyer.commander.input.gtalk;

import com.willmeyer.commander.*;

import java.io.*;

import org.slf4j.*;

public final class JabberShell extends CmdInputInterface {

	private HumanShellProcessor processor = null;
	private JabberClient jabber = null;
	private final Logger logger = LoggerFactory.getLogger(JabberShell.class);
	
	@Override
	public String getInterfaceAccessInfo() {
		return "IM access: " + jabber.getConnectionDescription();
	}

	@Override
	public boolean isForHumans() {
		return true;
	}

	@Override
	public boolean isForMachines() {
		return false;
	}

	@Override
	public void spawnInterface() throws Exception {
		logger.info("Spawning interface...");
		assert(jabber != null);
		if (!jabber.connected()) {
			jabber.connect();
		}
		jabber.setListener(new JabberHandler());
		jabber.setStatusMessage(statusMsg);
	}

	public class JabberHandler implements JabberClient.ChatListener {

		public void chatStarted(String user) {
			logger.info("Chat started with {}", user);
		}

		public void msgReceived(String from, String msg) {
			logger.info("Message received: {}", msg);
			ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
			PrintStream print = new PrintStream(byteOut);
			boolean keepGoing = processor.processInputLine("IM user " + from, print, msg);
			String resp = byteOut.toString();
			if (resp.length() > 0) {
				jabber.sendMsg(from, resp);
			}
			
			// More?
			if (!keepGoing) {
				jabber.sendMsg(from, "ttyl");
			}
		}
	}

	protected String statusMsg;
	
	/**
	 * @param jabber A client, connected or not 
	 * @param statusMsg
	 * @param processor
	 */
	public JabberShell(JabberClient jabber, String statusMsg, HumanShellProcessor processor) {
		super("Jabber console");
		this.jabber = jabber;
		this.processor = processor;
		this.statusMsg = statusMsg;
	}

	@Override
	public void stopHandler() {
	}
	
}