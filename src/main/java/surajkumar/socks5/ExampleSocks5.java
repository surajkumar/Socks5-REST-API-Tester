package surajkumar.socks5;

import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;
import java.util.Base64;
import java.util.concurrent.ThreadLocalRandom;

public class ExampleSocks5 extends Socks5Request {

    @Override
    public void run(Proxy proxy) {
        String auth = Base64
                .getEncoder()
                .encodeToString((String.valueOf(Math.random()).substring(0, 8) + ":xxxxxx").getBytes());
        try {
            URL url = new URL("https://domain.com/register?username=" + ThreadLocalRandom.current().nextInt());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection(proxy);
            connection.setRequestProperty("Authorization", "Basic " + auth);
            int response = connection.getResponseCode();
            if (response != 200) {
                System.err.println("Error: " + response);
            } else {
                System.out.println("OK");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
