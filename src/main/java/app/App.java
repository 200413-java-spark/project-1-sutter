package app;

import app.client.ServletMain;

import java.io.File;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.Wrapper;
import org.apache.catalina.startup.Tomcat;

public class App {

    public static void main(final String[] args) {

        System.out.println("\n\n\nHello World!\n\n\n");

        startServer();

    }

    public static void startServer() {
        Tomcat tomcat = new Tomcat();
        tomcat.setBaseDir(new File("target/tomcat/").getAbsolutePath());
        tomcat.setPort(8080);
        tomcat.getConnector();
        tomcat.addWebapp("/project-1-sutter", new File("src/main/resources/").getAbsolutePath());
        Wrapper helloServlet = tomcat.addServlet("/project-1-sutter", "ServletMain", new ServletMain());
        helloServlet.addMapping("/main");
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