package org.covidpooltesting;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

public class MainServer extends ResourceConfig {

	public MainServer() {
		register(new ProbabilityBinder());
		register(GsonJerseyProvider.class);
		packages("org.covidpooltesting", "org.covidpooltesting.probability");
	}

	public static void main(String[] args) throws Exception {
		ServletHolder jerseyServlet = new ServletHolder(new ServletContainer(new MainServer()));
		Server jettyServer = new Server(8080);
		ServletContextHandler context = new ServletContextHandler(jettyServer, "/");
		context.addServlet(jerseyServlet, "/*");
		try {
			jettyServer.start();
			jettyServer.join();
		} finally {
			jettyServer.destroy();
		}
	}
}
