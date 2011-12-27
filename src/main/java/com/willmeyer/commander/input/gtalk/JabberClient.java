package com.willmeyer.commander.input.gtalk;

import org.jivesoftware.smack.*;
import org.jivesoftware.smack.packet.*;

import org.slf4j.*;

public abstract class JabberClient {

	protected XMPPConnection conn;
	protected ChatHandler handler = null;
	protected ChatListener listener = null;
	
	protected static Logger logger = LoggerFactory.getLogger(JabberClient.class);
	
	public interface ChatListener {
		
		public void msgReceived(String from, String msg);
		
		public void chatStarted(String user);
		
	}
	
	public abstract String getConnectionDescription();
	
	private class ChatHandler implements ChatManagerListener, MessageListener {
		
		public void processMessage(Chat chat, Message msg) {
			logger.debug("Received message : " + msg.getFrom() + "-> type = " + msg.getType());
			if (msg.getType() == Message.Type.error) {
				logger.debug("Received ERROR msg from " + msg.getFrom() + ": " + "[" + msg.getError().getCode() + "] " + msg.getError().getMessage());
			} else if (msg.getType() == Message.Type.chat) {
				String msgText = msg.getBody();
				logger.debug("Received chat msg from " + msg.getFrom() + ": " + msgText);
				if (listener != null && msgText != null)
					listener.msgReceived(msg.getFrom(), msgText);
			}
		}
	
		public void chatCreated(Chat chat, boolean createdLocally) {
			logger.debug((createdLocally ? "Local" : "External") + " chat created with " + chat.getParticipant());
//			String resp = m_listener.chatStarted(chat.getParticipant());
//			if (resp != null) {
//				try {
//					chat.sendMessage(resp);
//				} catch (Exception e) {}
//			}
			chat.addMessageListener(this);
		}
		
	}

	public void shutdown() {
		
		// Create a new presence
		Presence presence = new Presence(Presence.Type.unavailable);
		presence.setStatus("Offline");
		conn.sendPacket(presence);
		
		// Disconnect
		this.disconnect();
	}

	protected void initSession() {

		// Create a new presence
		Presence presence = new Presence(Presence.Type.available);
		//presence.setStatus("Send \"help\" if you need it");
		conn.sendPacket(presence);
		
		// Listen for chats
		handler = new ChatHandler();
		conn.getChatManager().addChatListener(handler);
		
	}
	
	public void setStatusMessage(String msg) {
		if (conn != null) {
			Presence presence = new Presence(Presence.Type.available);
			presence.setStatus(msg);
			conn.sendPacket(presence);
		}
	}

	public boolean connected() {
		return !(conn == null);
	}
	
	public void disconnect() {
		if (conn != null) {
			this.conn.disconnect();
		}
	}
	
	public void connect() throws Exception {
		connectAndLogIn();
		this.initSession();
	}
	
	protected abstract void connectAndLogIn() throws Exception;
	
	public void setListener (ChatListener listener) {
		assert (listener != null);
		this.listener = listener;
	}

	public void sendMsg(String user, String msg) {
		ChatManager chatmanager = conn.getChatManager();
		Chat newChat = chatmanager.createChat(user, new MessageListener() {
		    public void processMessage(Chat chat, Message message) {
		    }
		});
		try {
		    newChat.sendMessage(msg);
		} catch (XMPPException e) {
		    logger.error(e.getMessage());
		}	
	}
	
	protected JabberClient() {
	}
	
}
