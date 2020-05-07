package in.myinnos.inappupdate;

import android.app.Activity;
import android.content.IntentSender;
import android.util.Log;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.play.core.appupdate.AppUpdateManager;
import com.google.android.play.core.install.InstallStateUpdatedListener;
import com.google.android.play.core.install.model.AppUpdateType;
import com.google.android.play.core.install.model.InstallStatus;
import com.google.android.play.core.install.model.UpdateAvailability;

public class InAppUpdate {

    public static int REQUEST_APP_UPDATE = 302;

    public static void setImmediateUpdate(AppUpdateManager appUpdateManager, Activity activity) {

        appUpdateManager
                .getAppUpdateInfo()
                .addOnSuccessListener(
                        appUpdateInfo -> {

                            // Checks that the platform will allow the specified type of update.
                            if ((appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE)
                                    && appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE)) {
                                // Request the update.
                                try {
                                    appUpdateManager.startUpdateFlowForResult(
                                            appUpdateInfo,
                                            AppUpdateType.IMMEDIATE,
                                            activity,
                                            REQUEST_APP_UPDATE);
                                } catch (IntentSender.SendIntentException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
    }

    public static void setImmediateUpdateOnResume(AppUpdateManager appUpdateManager, Activity activity) {

        appUpdateManager
                .getAppUpdateInfo()
                .addOnSuccessListener(
                        appUpdateInfo -> {

                            if (appUpdateInfo.updateAvailability()
                                    == UpdateAvailability.DEVELOPER_TRIGGERED_UPDATE_IN_PROGRESS) {
                                // If an in-app update is already running, resume the update.
                                try {
                                    appUpdateManager.startUpdateFlowForResult(
                                            appUpdateInfo,
                                            AppUpdateType.IMMEDIATE,
                                            activity,
                                            REQUEST_APP_UPDATE);
                                } catch (IntentSender.SendIntentException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
    }

    public static void setFlexibleUpdate(AppUpdateManager appUpdateManager, String note, Activity activity) {

        InstallStateUpdatedListener installStateUpdatedListener = installState -> {
            if (installState.installStatus() == InstallStatus.DOWNLOADED) {
                Snackbar snackbar =
                        Snackbar.make(
                                activity.findViewById(android.R.id.content),
                                activity.getString(R.string.in_app_snack_bar_message),
                                Snackbar.LENGTH_INDEFINITE);
                //lambda operation used for below action
                snackbar.setAction(activity.getString(R.string.in_app_snack_bar_action_title), view ->
                        appUpdateManager.completeUpdate());
                snackbar.setActionTextColor(activity.getResources().getColor(R.color.in_app_snack_bar_text_color))
                ;
                snackbar.show();
            } else
                Log.e("UPDATE", "Not downloaded yet");
        };

        appUpdateManager
                .getAppUpdateInfo()
                .addOnSuccessListener(
                        appUpdateInfo -> {

                            // Checks that the platform will allow the specified type of update.
                            if ((appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE)
                                    && appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.FLEXIBLE)) {
                                // Request the update.
                                try {
                                    appUpdateManager.startUpdateFlowForResult(
                                            appUpdateInfo,
                                            AppUpdateType.FLEXIBLE,
                                            activity,
                                            REQUEST_APP_UPDATE);
                                } catch (IntentSender.SendIntentException e) {
                                    e.printStackTrace();
                                }
                            }
                        });

        appUpdateManager.registerListener(installStateUpdatedListener);
    }

    public static void setFlexibleUpdateOnResume(AppUpdateManager appUpdateManager, Activity activity) {

        appUpdateManager
                .getAppUpdateInfo()
                .addOnSuccessListener(
                        appUpdateInfo -> {

                            if (appUpdateInfo.updateAvailability()
                                    == UpdateAvailability.DEVELOPER_TRIGGERED_UPDATE_IN_PROGRESS) {
                                // If an in-app update is already running, resume the update.
                                try {
                                    appUpdateManager.startUpdateFlowForResult(
                                            appUpdateInfo,
                                            AppUpdateType.FLEXIBLE,
                                            activity,
                                            REQUEST_APP_UPDATE);
                                } catch (IntentSender.SendIntentException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
    }
}
