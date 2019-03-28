
package ru.droidwelt.bluetoothdisplay.server;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import top.defaults.colorpicker.ColorObserver;
import top.defaults.colorpicker.ColorPickerView;

//  https://android-arsenal.com/details/1/7068

public class MainActivity extends FragmentActivity {

    public static final String TAG = "MainActivity";
    final int REQUEST_ACCESS_COARSE_LOCATION = 1;
    public TextView textView;
    public ColorPickerView colorPickerView;
    BluetoothChatFragment fragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        colorPickerView = findViewById(R.id.colorPickerView);

        int color = PrefUtils.getPrefColor();
        colorPickerView.setInitialColor(color);
        textView.setBackgroundColor(color);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            switch (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)) {
                case PackageManager.PERMISSION_DENIED:
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                            REQUEST_ACCESS_COARSE_LOCATION);
                    break;
              /*  case PackageManager.PERMISSION_GRANTED:
                    break;*/
            }
        }

        if (savedInstanceState == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            fragment = new BluetoothChatFragment();
            transaction.replace(R.id.sample_content_fragment, fragment);
            transaction.commit();
        }


        colorPickerView.subscribe(new ColorObserver() {
            @Override
            public void onColor(int color, boolean fromUser, boolean shouldPropagate) {
                if (fromUser) {
                    textView.setBackgroundColor(color);
                    PrefUtils.setPrefColor(color);
                    fragment.sendMessage ( Appl.TRANSFER_CODE+ Integer.toString(color),true);
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_ACCESS_COARSE_LOCATION: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // ok
                } else {
                    finish();
                }
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }


}
