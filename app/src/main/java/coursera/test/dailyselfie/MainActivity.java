package coursera.test.dailyselfie;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.io.File;
import java.io.IOException;

import coursera.test.dailyselfie.utils.HelperUtil;
import coursera.test.dailyselfie.utils.ImageUtil;

public class MainActivity extends ActionBarActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private static final int TAKE_PHOTO_REQUEST_CODE = 1;
    private static final String CURRENT_PHOTO_PATH_SAVED_STATE = "current_path";

    private static final long TWO_MIN_IN_MILLIS = 2 * 60 * 1000;

    private String mCurrentPhotoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {

            /**
             * TODO Add PhotoListFragment into R.id.container
             *
             * http://developer.android.com/reference/android/app/FragmentTransaction.html
             *
             */
        }

        if (!HelperUtil.isReceiverAddedToAlarmManager(getApplicationContext())) {

            addOrUpdateReceiverToAlarmManager();
            HelperUtil.setReceiverAddedToAlarmManager(getApplicationContext(), true);
        }

    }

    private void addOrUpdateReceiverToAlarmManager() {

        /**
         * TODO Add repeating alarm for our BroadcastReceiver "DailySelfieNotificationReceiver"
         *
         * Selfie Notification must showing every two minutes. Need to use TWO_MIN_IN_MILLIS as repeating interval
         * For more detail about repeating interval please read "Requirement #5" in "Lab-DailySelfie.pdf" file
         *
         * https://developer.android.com/training/scheduling/alarms.html
         */
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        mCurrentPhotoPath = savedInstanceState
                .getString(CURRENT_PHOTO_PATH_SAVED_STATE);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString(CURRENT_PHOTO_PATH_SAVED_STATE, mCurrentPhotoPath);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    private void capturePhoto() {

        if (ImageUtil.isPhotoCaptureIntentAvailable(getApplicationContext())) {

            Intent takePictureIntent = new Intent(
                    MediaStore.ACTION_IMAGE_CAPTURE);

            File outputPhotoFile;
            try {

                outputPhotoFile = ImageUtil.getInstance(
                        getString(R.string.app_name)).createPhotoFile();
                if (outputPhotoFile != null) {

                    mCurrentPhotoPath = outputPhotoFile.getAbsolutePath();
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                            Uri.fromFile(outputPhotoFile));
                    startActivityForResult(takePictureIntent, TAKE_PHOTO_REQUEST_CODE);
                }
            } catch (IOException e) {

                Log.e(TAG, "Failed to capturePhoto!", e);
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.action_camera) {

            capturePhoto();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == TAKE_PHOTO_REQUEST_CODE && resultCode == RESULT_OK) {

            ImageUtil.getInstance(getString(R.string.app_name)).AddToGallery(
                    this, mCurrentPhotoPath);
            addOrUpdateReceiverToAlarmManager();
        }
    }

}
