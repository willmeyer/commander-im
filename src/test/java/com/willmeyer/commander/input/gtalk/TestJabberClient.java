package com.willmeyer.commander.input.gtalk;

import org.junit.*;

public class TestJabberClient 
{
	public static String GTALK_TEST_USERNAME = "refactr@gmail.com";
	public static String GTALK_TEST_PW = "thepwassord";
	public static String GTALK_RECV_USERNAME = "willmeyer@gmail.com";
	
	JabberClient jabber = null;
		
	@Before
    public void beforeTest() throws Exception {
		//jabber = new JabberClient();
    }

	@After
    public void afterTest() throws Exception {
		jabber.disconnect();
    }

	@Test
	public void testSimple() throws Exception 
    {
		//jabber.connectGTalk(GTALK_TEST_USERNAME, GTALK_TEST_PW);
		jabber.disconnect();
    }

	@Test
	public void testSendMessage() throws Exception 
    {
		//jabber.connectGTalk(GTALK_TEST_USERNAME, GTALK_TEST_PW);
		jabber.sendMsg(GTALK_RECV_USERNAME, "a test!");
		jabber.disconnect();
    }
}
