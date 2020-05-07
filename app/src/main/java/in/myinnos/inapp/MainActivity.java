package in.myinnos.inapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.play.core.appupdate.AppUpdateManager;
import com.google.android.play.core.appupdate.AppUpdateManagerFactory;

import in.myinnos.inappupdate.InAppUpdate;

public class MainActivity extends AppCompatActivity {

    private AppUpdateManager appUpdateManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        appUpdateManager = AppUpdateManagerFactory.create(this);
        InAppUpdate.setImmediateUpdate(appUpdateManager, this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        InAppUpdate.setImmediateUpdateOnResume(appUpdateManager, this);
    }
}
