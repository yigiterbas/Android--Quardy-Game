package quardy.ye.game.com.quardy;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.IBinder;
import android.os.PowerManager;
import android.support.v7.app.NotificationCompat;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;



import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;


public class myService extends Service {

    //long time,time1;
    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void onCreate() {
        //  Toast.makeText(this, " IT Created ", Toast.LENGTH_LONG).show();



    }
    int countcounter = 0;
    Boolean gamestop = false;
    int originalcounter = 0;
    Boolean fatal = false;
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        SharedPreferences pref;
        pref = getSharedPreferences("info", MODE_PRIVATE);

        final SharedPreferences.Editor editor = pref.edit();
        //  Toast.makeText(this, " IT Started", Toast.LENGTH_LONG).show();
        final Calendar cl = Calendar.getInstance();




        new CountDownTimer(1000000000, 1000) {
            public void onTick(long milsec) {

                if (countcounter < 1)
                {



                }

                countcounter++;
                SharedPreferences shared = getSharedPreferences("info", MODE_PRIVATE);
                //Using getXXX- with XX is type date you wrote to file "name_file"


                originalcounter = shared.getInt("gamestarcounter", originalcounter);
                gamestop = shared.getBoolean("gamestop", gamestop);
                final boolean sleep = shared.getBoolean("sleep", true);


                final long time = shared.getLong("time", 0);
                final long lastplaytime = shared.getLong("lastplaytime", 0);
                final long[] time1 = {10};


                Long timenow = System.currentTimeMillis() / 1000;



                int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
                int minutes = Calendar.getInstance().get(Calendar.MINUTE);
                int sec = Calendar.getInstance().get(Calendar.SECOND);

                int seconds= hour*10000+minutes*100+sec;

               // if (timenow - lastplaytime > 60 * 60 * 120) fatal = true;
                if (timenow - lastplaytime > 60*60*24 ) fatal = true;

               else if (timenow - lastplaytime > 60*60*48 ) fatal = true;

                else if (timenow - lastplaytime > 60*60*72 ) fatal = true;

                else if (timenow - lastplaytime > 60*60*96 ) fatal = true;

                else fatal=false;

                if (fatal && gamestop && (seconds == 130000 || seconds == 200000)) {

                    fatal = false;
                    showNotificatiowarning();

                }

            }

            public void onFinish() {

            }
        }.start();




        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
       /* SharedPreferences pref;
        pref = getSharedPreferences("info", MODE_PRIVATE);
//Using putXXX - with XXX is type data you want to write like: putString, putInt...   from      Editor object
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("process0", pr0);
        editor.putInt("process1", pr1);
        editor.putInt("speed1", spd);
        editor.putInt("counter", cnt);
        editor.commit();*/

        //Thread.currentThread().interrupt();
        stopForeground(true);
    }


    public void showNotificatiowarning() {

        SharedPreferences pref;
        pref = getSharedPreferences("info", MODE_PRIVATE);
//Using putXXX - with XXX is type data you want to write like: putString, putInt...   from      Editor object
        final SharedPreferences.Editor editor = pref.edit();

        PendingIntent pi = PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class), 0);
        Resources r = getResources();
        Notification notification = new NotificationCompat.Builder(this)
                .setTicker(getResources().getString(R.string.notificationheader))
                .setSmallIcon(R.mipmap.logo)
                .setDefaults(Notification.DEFAULT_SOUND)
                .setContentTitle("Quardy")
                .setContentText(getResources().getString(R.string.notificationbody))
                .setVibrate(new long[]{1000, 1000})
                // .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.largenotifiaction))
                .setAutoCancel(true)
                .setContentIntent(pi)
                //.setSmallIcon(0)
                .build();
        notification.contentView.setImageViewResource(android.R.id.icon, R.mipmap.logo);
        NotificationManager notificationManager=(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(0, notification);
    }
}