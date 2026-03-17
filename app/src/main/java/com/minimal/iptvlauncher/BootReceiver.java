package com.minimal.iptvlauncher;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class BootReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
            SharedPreferences prefs = context.getSharedPreferences("IPTVLauncherPrefs", Context.MODE_PRIVATE);

            Intent launcherIntent = new Intent(context, MainActivity.class);
            launcherIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(launcherIntent);
        }
    }
}
