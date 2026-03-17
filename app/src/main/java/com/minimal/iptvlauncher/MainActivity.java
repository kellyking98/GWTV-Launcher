package com.minimal.iptvlauncher;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private static final String PREFS_NAME = "IPTVLauncherPrefs";
    private static final String PREF_IPTV_PACKAGE = "iptv_package_name";
    private static final String DEFAULT_IPTV_PACKAGE = "com.haxapps.smart405";

    private String iptvPackageName;
    private TextView statusText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        iptvPackageName = prefs.getString(PREF_IPTV_PACKAGE, DEFAULT_IPTV_PACKAGE);

        statusText = findViewById(R.id.statusText);
        Button launchIPTVButton = findViewById(R.id.launchIPTVButton);
        Button wifiSettingsButton = findViewById(R.id.wifiSettingsButton);

        updateStatusText();

        launchIPTVButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchIPTV();
            }
        });

        wifiSettingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWiFiSettings();
            }
        });

        if (prefs.getBoolean("auto_launch", true)) {
            launchIPTVButton.postDelayed(new Runnable() {
                @Override
                public void run() {
                    launchIPTV();
                }
            }, 1000);
        }
    }

    private void updateStatusText() {
        if (isAppInstalled(iptvPackageName)) {
            statusText.setText("GWTV App: Ready");
            statusText.setTextColor(0xFF4CAF50);
        } else {
            statusText.setText("GWTV App: Not Installed");
            statusText.setTextColor(0xFFF44336);
        }
    }

    private boolean isAppInstalled(String packageName) {
    try {
        getPackageManager().getPackageInfo(packageName, 0);
        return true;
    } catch (PackageManager.NameNotFoundException e) {
        return false;
    }
}

    private void launchIPTV() {
    try {
        // Use explicit activity name for GWTV
        Intent launchIntent = new Intent();
        launchIntent.setClassName("com.haxapps.smart405", "com.haxapps.smart405.view.activity.ScreenTypeActivity");
        launchIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(launchIntent);
    } catch (Exception e) {
        Toast.makeText(this, "Error launching GWTV: " + e.getMessage(), Toast.LENGTH_LONG).show();
    }
}

    private void openWiFiSettings() {
        try {
            Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(this, "Error opening WiFi settings", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        // Disable back button
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateStatusText();
    }
}
