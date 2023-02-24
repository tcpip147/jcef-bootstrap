package com.tcpip147.jcefbootstrap;

import java.io.File;
import java.io.IOException;

import org.cef.CefApp;
import org.cef.CefApp.CefAppState;
import org.cef.CefClient;
import org.cef.browser.CefBrowser;
import org.springframework.stereotype.Component;

import me.friwi.jcefmaven.CefAppBuilder;
import me.friwi.jcefmaven.CefInitializationException;
import me.friwi.jcefmaven.MavenCefAppHandlerAdapter;
import me.friwi.jcefmaven.UnsupportedPlatformException;

@Component
public class JcefBrowserProvider {

	private final CefApp cefApp;

	public JcefBrowserProvider() {
		CefAppBuilder builder = new CefAppBuilder();
		builder.getCefSettings().windowless_rendering_enabled = false;
		
		String remote_debugging_port = System.getProperty("remote_debugging_port");
		if (remote_debugging_port != null) {
			builder.getCefSettings().remote_debugging_port = Integer.parseInt(remote_debugging_port);
		}

		builder.setAppHandler(new MavenCefAppHandlerAdapter() {
			@Override
			public void stateHasChanged(org.cef.CefApp.CefAppState state) {
				if (state == CefAppState.TERMINATED) {
					System.exit(0);
				}
			}
		});

		try {
			cefApp = builder.build();
		} catch (IOException | UnsupportedPlatformException | InterruptedException | CefInitializationException e) {
			throw new RuntimeException(e);
		}
	}

	public JcefBrowser newComponent(String url) {
		CefClient client = cefApp.createClient();
		CefBrowser browser = client.createBrowser(new File(url).getAbsolutePath(), false, false);
		return new JcefBrowser(client, browser.getUIComponent());
	}
}
