package fm.fish.engine.rest;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.concurrent.TimeUnit;

public class ServiceFactory {

    private static final Logger LOG = LoggerFactory.getLogger(ServiceFactory.class);
    private static final ThreadLocal<JavaNetCookieJar> COOKIE_STORE = ThreadLocal.withInitial(() -> {
        CookieManager cookieManager = new CookieManager();
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
        return new JavaNetCookieJar(cookieManager);
    });
    public static Gson G = new Gson();
    private static String[] headerNamesToIgnore = {"Access-Control", "Cache-Control", "Content-Length", "Connection",
            "Date", "Set-Cookie", "ETag", "Content-Type", "Keep-Alive", "Transfer-Encoding", "Pragma", "Server",
            "Vary", "X-Powered-By", "X-Content-Type", "X-Frame-Options", "X-Request-Id", "X-Runtime",
            "X-XSS-Protection", "CF-RAY", "Accept:", "x-amzn-RequestId", "Expect-CT:", "Location:", "X-RingDeviceType:",
            "AWS Request ID:"};

    private ServiceFactory() {
    }

    public static <T> T createService(Class<T> clazz, String baseUrl) {

        HttpLoggingInterceptor.Logger excludeHeaders = (String msg) -> {
            for (String header : headerNamesToIgnore) {
                if (msg.startsWith(header) || msg.isEmpty()) {
                    return;
                }
            }
            if (isJSONValid(msg)) {
                msg = G.toJson(new JsonParser().parse(msg));
            }
            LOG.info(msg);
        };

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(excludeHeaders)
                .setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = getUnsafeOkHttpClient()
                .connectTimeout(5, TimeUnit.MINUTES)
                .writeTimeout(5, TimeUnit.MINUTES)
                .readTimeout(5, TimeUnit.MINUTES)
                .addInterceptor(interceptor).build();

        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(G))
                .build()
                .create(clazz);
    }

    private static boolean isJSONValid(String jsonInString) {
        try {
            G.fromJson(jsonInString, Object.class);
            return true;
        } catch (com.google.gson.JsonSyntaxException ex) {
            return false;
        }
    }

    private static OkHttpClient.Builder getUnsafeOkHttpClient() {
        try {
            final TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                @Override
                public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                }

                @Override
                public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                }

                @Override
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return new java.security.cert.X509Certificate[]{};
                }
            }
            };

            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0]);
            builder.hostnameVerifier((hostName, session) -> true);
            builder.cookieJar(COOKIE_STORE.get());

            return builder;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
