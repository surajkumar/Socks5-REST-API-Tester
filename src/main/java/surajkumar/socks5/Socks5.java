package surajkumar.socks5;

import javax.net.ssl.*;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Socks5 {
    private final ExecutorService executor = Executors.newCachedThreadPool();
    private List<String> proxies = new ArrayList<>();

    public Socks5() {}

    public void run(int connectionPerHost, Socks5Request request) {
        for(int i = 0; i < connectionPerHost; i++) {
            for(String p : proxies) {
                String[] split = p.split(":");
                String host = split[0];
                int port = Integer.parseInt(split[1]);
                Runnable task = () -> {
                    Proxy proxy = new Proxy(Proxy.Type.SOCKS, new InetSocketAddress(host, port));
                    request.run(proxy);
                };
                executor.submit(task);
            }
        }
    }

    public static void trustAllSSL() {
        TrustManager[] trustAllCerts = new TrustManager[] {
                new X509TrustManager() {
                    public X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }

                    @Override
                    public void checkClientTrusted(X509Certificate[] arg0, String arg1) { }

                    @Override
                    public void checkServerTrusted(X509Certificate[] arg0, String arg1) { }
                }
        };

        SSLContext sc;
        try {
            sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            HostnameVerifier validHosts = (arg0, arg1) -> true;
            HttpsURLConnection.setDefaultHostnameVerifier(validHosts);
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            /* Print the stack trace to allow users to debug why SSL is not present on their system */
            e.printStackTrace();
        }
    }

    public void loadProxyFile(String filePath) throws IOException {
        proxies = Files.readAllLines(Path.of(filePath));
    }

    public List<String> getProxies() {
        return Collections.unmodifiableList(proxies);
    }

    public void add(String address) {
        proxies.add(address);
    }

    public void remove(String address) {
        proxies.remove(address);
    }
}
