package app;

import app.client.BatchService;
import app.client.StatesService;
import app.client.StatusService;
import app.database.Database;

import java.io.File;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

public class App {

    public static void main(final String[] args) {
        boolean ready = Database.clearTables();
        if (ready) {
            startServer();
        }
    }

    public static void startServer() {
        Tomcat tomcat = new Tomcat();
        tomcat.setBaseDir(new File("target/tomcat/").getAbsolutePath());
        tomcat.setPort(8080);
        tomcat.getConnector();
        tomcat.addWebapp("/project-1-sutter", new File("src/main/resources/").getAbsolutePath());
        tomcat.addServlet("/project-1-sutter", "StatusService", new StatusService()).addMapping("/status");
        tomcat.addServlet("/project-1-sutter", "BatchService", new BatchService()).addMapping("/batch");
        tomcat.addServlet("/project-1-sutter", "StatesService", new StatesService()).addMapping("/leases");
        try {
            tomcat.start();
        } catch (LifecycleException e) {
            e.printStackTrace();
        }
        ensureGracefulShutdown(tomcat);
    }

    private static void ensureGracefulShutdown(Tomcat server) {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                try {
                    System.out.println("\nShutting down Tomcat server...\n");
                    server.stop();
                } catch (LifecycleException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}