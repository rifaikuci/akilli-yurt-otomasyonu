package com.rifaikuci.myapplication.firebase;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.rifaikuci.myapplication.DuyurularDetay;
import com.rifaikuci.myapplication.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Hashtable;
import java.util.Map;
import java.util.Random;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        if(remoteMessage.getData() != null) { enviarNotificacion(remoteMessage); }

        if(remoteMessage.getNotification() != null) { enviarNotificacion(remoteMessage); }
    }

    private void enviarNotificacion(RemoteMessage remoteMessage) {
        Map<String, String> data = remoteMessage.getData();
        String baslik = data.get("duyuruBaslik");
        String detay = data.get("duyuruDetay");
        String resim  = data.get("duyuruResim");
        String video  = data.get("duyuruVideo");


        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        String NOTIFICATION_CHANNEL_ID = "com.rifaikuci.fereli.kyk";



        // Versiyon O serisinden daha yüksekse
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            @SuppressLint("WrongConstant")
            NotificationChannel channel = new NotificationChannel(NOTIFICATION_CHANNEL_ID,
                    "Mi notificacion",
                    NotificationManager.IMPORTANCE_MAX
            );

            // Configuracion del canal de notificacion
            channel.setDescription("xcheko51x channel para app");
            channel.enableLights(true);
            channel.setLightColor(Color.BLUE);
            channel.setVibrationPattern(new long[]{0, 1000, 500, 1000});
            channel.enableVibration(true);

            manager.createNotificationChannel(channel);

        }

        Intent intent = new Intent(this, DuyurularDetay.class);

        intent.putExtra("duyuruBaslik", baslik);
        intent.putExtra("duyuruDetay", detay);
        intent.putExtra("duyuruResim", resim);
        intent.putExtra("duyuruVideo", video);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK    );

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent,    PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID);

        Bitmap bitmap = getBitmapFromURL(resim);


        builder.setAutoCancel(true)
                .setWhen(System.currentTimeMillis())
                .setTicker("Hearty365")
                .setContentTitle(baslik)
                .setContentText(detay)
                .setLights(Color.BLUE,1000,1000)
                .setVibrate(new long[]{0, 1000, 500, 1000})
                .setSmallIcon(R.drawable.logo)
                .setLargeIcon(bitmap)
                .setContentIntent(pendingIntent)
                .setContentInfo("Detay");

        Random random = new Random();
        int m = random.nextInt(9999 - 1000) + 1000;
        manager.notify(m, builder.build());
    }

    //yeni bir Token Oluşturma
    @Override
    public void onNewToken(String token) {

        FirebaseMessaging.getInstance().subscribeToTopic("dispositivos");
        enviarTokenToServer(token);

    }

    private void enviarTokenToServer(final String token) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                "https://rifaikuci.com/akilliYurt/registerToken.php",
                new Response.Listener<String>() {
            
            @Override public void onResponse(String response) { }}, new Response.ErrorListener() {
            @Override public void onErrorResponse(VolleyError error) { }}){
            @Override protected Map<String, String> getParams()
                    throws AuthFailureError { Map<String, String> parametres = new Hashtable<String, String>();
                    parametres.put("Token", token);

                return parametres; }};
        
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        
        requestQueue.add(stringRequest);
    }

    
    // Resmi jpg'den bitmape dönüştürüp notification kısmında gösterme
    public Bitmap getBitmapFromURL(String strURL) {
        try {
            URL url = new URL(strURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } 
        catch (IOException e) { e.printStackTrace();return null; }
    } }
