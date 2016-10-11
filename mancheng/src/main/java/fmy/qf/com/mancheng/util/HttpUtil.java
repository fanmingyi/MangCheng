package fmy.qf.com.mancheng.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by Administrator on 2016/10/9.
 */
public class HttpUtil {

    private static OkHttpClient client;

    /**
     * 判断是否用连网
     * @param context
     * @return true可以用  flase网络不可用
     */
    public static boolean isNetconn(Context context){

        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (manager != null) {
            NetworkInfo info = manager.getActiveNetworkInfo();
            if (info!=null){
                return  info.isConnected();
            }
        }
        return  false;
    }
    static {
        client = new OkHttpClient.Builder().connectTimeout(5, TimeUnit.SECONDS).readTimeout(5, TimeUnit.SECONDS).build();

    }
    public static byte[] getByteArray(String path) throws IOException {
        Request request = new Request.Builder().url(path).build();


            Response response = client.newCall(request).execute();
            ResponseBody body = response.body();
            return  body.bytes();


    }

    public static String getString(String path) throws IOException {
        byte[] byteArray = getByteArray( path);
        if (byteArray!=null){
            try {
                return new String(byteArray,0,byteArray.length,"UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return  null;
    }

}
