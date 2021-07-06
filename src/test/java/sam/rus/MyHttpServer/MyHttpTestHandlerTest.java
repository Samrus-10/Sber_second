package sam.rus.MyHttpServer;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import sam.rus.MyHttpServer.Util.TokenUtil;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.stream.Collectors;

public class MyHttpTestHandlerTest {
    private URL url;
    private HttpURLConnection httpURLConnection;

    @Before
    public void init() {
        try {
            this.url = new URL("http://127.0.0.1:1025/cards");
            this.httpURLConnection = (HttpURLConnection) this.url.openConnection();
            httpURLConnection.setRequestProperty("Content-Type", "application/json");
            httpURLConnection.setConnectTimeout(10000);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getRequest() {
        httpURLConnection.setRequestProperty("MyToken", "eyJhbGciOiJIUzI1NiIsICJ0eXAiOiJKV1QifQ==.eyJ1c2VySWQiOiIyIiwgImRhdGEiOiIyMS8wNy8wNiAxMjo1NDo1MCIsImV4cCI6IjMwIiwgInJ1bGUiOiJ1c2VyIiwgImxvZ2luIjoic2FteSJ9.YzdmZDdhMjg1NTYxNWEzNGYxYmMwNTRkZjFhZmE1MjdlNzFhYzBjYzUwMTVjYzQzZjhkNWJmMmU2YjgwNjg4NQ==");
        try (
                OutputStreamWriter writer = new OutputStreamWriter(httpURLConnection.getOutputStream());
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(httpURLConnection.getInputStream(), Charset.forName("utf-8")))) {
            writer.write("");
            if (httpURLConnection.getResponseCode() != 200) {
                System.err.println("connection failed");
            }
            String collect = reader.lines().collect(Collectors.joining(System.lineSeparator()));
            System.out.println(collect);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void newAccessToken() {
        String sam = TokenUtil.creatToken("samy", "2222");
        System.out.println(sam);
        String[] split = sam.split("---");

        for (int i = 0; i < split.length; i++) {
            System.out.println(split[i]);
        }
    }

}
