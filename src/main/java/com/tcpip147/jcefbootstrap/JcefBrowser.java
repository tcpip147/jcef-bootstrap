package com.tcpip147.jcefbootstrap;

import java.awt.Component;

import org.cef.CefClient;
import org.cef.browser.CefMessageRouter;
import org.cef.browser.CefMessageRouter.CefMessageRouterConfig;
import org.cef.handler.CefMessageRouterHandler;

public class JcefBrowser {

	private CefClient client;
	private Component component;

	public JcefBrowser(CefClient client, Component component) {
		this.component = component;
	}

	public Component getComponent() {
		return component;
	}

	public void setMessageRouterHandler(CefMessageRouterHandler handler) {
		CefMessageRouter msgRouter = CefMessageRouter.create(new CefMessageRouterConfig("cefQuery", "cefQueryCancel"));
		msgRouter.addHandler(handler, false);
		client.addMessageRouter(msgRouter);
	}
}
