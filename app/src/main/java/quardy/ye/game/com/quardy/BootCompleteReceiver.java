package quardy.ye.game.com.quardy;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;




/**
 * Created by Yigit on 9.12.2015.
 */

public class BootCompleteReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        Intent service = new Intent(context, alarmService.class);
        context.startService(service);
            /*Calendar cal = Calendar.getInstance();
            int alarmId = 11; //// Dynamically assign alarm ids for multiple alarms
            Intent service = new Intent(context, MyService.class);  //your Intent localIntent = new Intent("com.test.sample");
            intent.putExtra("alarmId", alarmId); // So we can catch the id on BroadcastReceiver
            PendingIntent alarmIntent;
            alarmIntent = PendingIntent.getBroadcast(context,
                    alarmId, service,
                    0);
            AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

            am.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(),
                    10 * 1000, alarmIntent);*/

    }
}
