package app;

import app.batch.BatchJob;
import app.client.ServletMain;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

public class App {

    public static void main(final String[] args) {

        //startServer();

        //pause();

        BatchJob.learnRDD();

    }

    public static void startServer() {
        Tomcat tomcat = new Tomcat();
        tomcat.setBaseDir(new File("target/tomcat/").getAbsolutePath());
        tomcat.setPort(8080);
        tomcat.getConnector();
        tomcat.addWebapp("/project-1-sutter", new File("src/main/resources/").getAbsolutePath());
        tomcat.addServlet("/project-1-sutter", "ServletMain", new ServletMain()).addMapping("/main");
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

    private static void pause() {
        try {
            TimeUnit.SECONDS.wait(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}