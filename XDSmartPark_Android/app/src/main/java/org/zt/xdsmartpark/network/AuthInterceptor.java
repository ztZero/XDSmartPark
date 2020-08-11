package org.zt.xdsmartpark.network;

import android.content.Context;
import androidx.annotation.NonNull;

import org.zt.xdsmartpark.utils.XDSmartParkSharedPreference;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;


public class AuthInterceptor implements Interceptor {

    private XDSmartParkSharedPreference prefManager;

    public AuthInterceptor(Context context) {
        prefManager = new XDSmartParkSharedPreference(context);
    }

    public Response intercept(@NonNull Chain chain) throws IOException {
        String token = prefManager.getString(XDSmartParkSharedPreference.PREF_TOKEN);
        Request request = chain.request().newBuilder()
                .header("Authorization", "Bearer " + token).build();
        return chain.proceed(request);
    }
}
