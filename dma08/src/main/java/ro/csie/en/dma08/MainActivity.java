package ro.csie.en.dma08;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.X509TrustManager;

public class MainActivity extends AppCompatActivity {

    private static final String RECIPE_GET_JSON = "https://jsonkeeper.com/b/OCIE";
    private static final String RECIPE_POST_JSON = "https://ptsv2.com/t/MDA2021/post";
    private static final String TAG = MainActivity.class.getName();
    private Button btnGet;
    private Button btnPost;
    private TextView tvPostResult;
    private List<Recipe> recipes= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnGet = findViewById(R.id.btnGet);
        btnPost = findViewById(R.id.btnPost);
        tvPostResult = findViewById(R.id.textView);
        trustEveryone();
    }

    public void getJson(View view)
    {
        Thread thread= new Thread()
        {
            @Override
            public void run() {
                HttpConnectionService httpConnectionService = new HttpConnectionService(RECIPE_GET_JSON);
                String recipeJSONArray = httpConnectionService.getData();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                    }
                });
            }
        };
        thread.start();
    }

    private void trustEveryone() {
        try {
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier(){
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }});
            SSLContext context = SSLContext.getInstance("TLS");
            context.init(null, new X509TrustManager[]{new X509TrustManager(){
                public void checkClientTrusted(X509Certificate[] chain,
                                               String authType) throws CertificateException {}
                public void checkServerTrusted(X509Certificate[] chain,
                                               String authType) throws CertificateException {}
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }}}, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(
                    context.getSocketFactory());
        } catch (Exception e) { // should never happen
            e.printStackTrace();
        }
    }

}