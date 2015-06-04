package coursera.test.dailyselfie;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class FullscreenPhotoDialogFragment extends DialogFragment {

    public static final String PHOTO_PATH_ARG = "photo_path";

    private View mRootView;

    private ImageView mPhotoImageView;
    private String mPhotoPath;

    public static FullscreenPhotoDialogFragment newInstance(String photoPath) {

        FullscreenPhotoDialogFragment instance = new FullscreenPhotoDialogFragment();

        /*

        TODO TASK 11: Save photo path as argument of Fragment

        http://developer.android.com/reference/android/support/v4/app/Fragment.html#setArguments(android.os.Bundle)

         */

        return instance;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*

          TODO TASK 14: Set fullscreen style for dialog
         */
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        mRootView = inflater.inflate(R.layout.fragment_dialog_fullscreen_photo,
                null);

        /**
         * TODO TASK 12: Get photo path from arguments bundle
         *
         * /

         /*
         * TODO TASK 13: load image into our fullscreen imageView "mPhotoImageView"
         *
         */

        return mRootView;
    }
}
