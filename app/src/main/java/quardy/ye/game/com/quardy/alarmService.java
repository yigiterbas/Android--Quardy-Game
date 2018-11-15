package quardy.ye.game.com.quardy;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

import java.util.Calendar;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import java.util.Calendar;


public class alarmService extends Service {

    int pr0,pr1,cnt,spd,spd1;
    //long time,time1;
    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void onCreate() {
        //Toast.makeText(this, " alarm Created ", Toast.LENGTH_LONG).show();



    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        startService(new Intent(this, myService.class));
        Intent intent1 = new Intent(this, myService.class);
        PendingIntent pintent= PendingIntent
                .getBroadcast(this, 11, intent1, 0);

        // Toast.makeText(this, " alarm Started", Toast.LENGTH_LONG).show();

        Calendar cal = Calendar.getInstance();


        AlarmManager alarm = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        // Start service every 20 seconds

        alarm.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(),
                2 * 1000, pintent);

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
        // Toast.makeText(this, "alarm Stopped", Toast.LENGTH_SHORT).show();
        //Thread.currentThread().interrupt();

    }







}








