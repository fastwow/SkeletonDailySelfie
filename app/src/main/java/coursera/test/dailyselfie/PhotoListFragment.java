package coursera.test.dailyselfie;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.ResourceCursorAdapter;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import coursera.test.dailyselfie.utils.ImageUtil;

public class PhotoListFragment extends ListFragment implements
        AdapterView.OnItemClickListener, LoaderManager.LoaderCallbacks<Cursor> {

    private static final int PHOTOS_LOADER_ID = 1;

    private final String[] photoProjection = {MediaStore.Images.Media._ID,
            MediaStore.Images.Media.DATA,
            MediaStore.Images.Media.DISPLAY_NAME,};
    private final String photoSelection = MediaStore.Images.Media.BUCKET_ID
            + " = ?";
    private String[] photoSelectionArgs;

    private PhotoCursorAdapter mAdapter;

    public static PhotoListFragment newInstance() {

        PhotoListFragment instance = new PhotoListFragment();

        return instance;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        photoSelectionArgs = new String[]{ImageUtil.getInstance(
                getString(R.string.app_name)).getBucketId()};
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mAdapter = new PhotoCursorAdapter(getActivity(),
                R.layout.layout_item_photo, null, 0);

        getListView().setOnItemClickListener(this);

        if (getLoaderManager().getLoader(PHOTOS_LOADER_ID) != null)
            getLoaderManager().restartLoader(PHOTOS_LOADER_ID, null, this);
        else
            getLoaderManager().initLoader(PHOTOS_LOADER_ID, null, this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {

        String photoPath = getPhotoPath((Cursor) mAdapter.getItem(position));
        /**
         *
         * TODO TASK 10: Create and show FullscreenPhotoDialogFragment with selected photo on click
         *
         *  http://developer.android.com/reference/android/app/DialogFragment.html#show(android.app.FragmentTransaction, java.lang.String)
         *
         */
    }

    @Override
    public Loader<Cursor> onCreateLoader(int arg0, Bundle arg1) {

        /**
         * TODO TASK 3: Create new CursorLoader
         *
         * http://developer.android.com/reference/android/content/CursorLoader.html
         *
         * Pass "MediaStore.Images.Media.EXTERNAL_CONTENT_URI" as uri
         * Pass "photoProjection" field as projection
         * Pass "photoSelection" field as selection
         * Pass "photoSelectionArgs" field as selectionArgs
         * Pass "null" as sortOrder
         */

        // return null for the test compiling!!!
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> arg0, Cursor newCursor) {

        /**
         * TODO TASK 4: Swap new cursor into adapter when loader finished
         *
         * http://developer.android.com/reference/android/widget/CursorAdapter.html#swapCursor(android.database.Cursor)
         */

        /**
         *
         * TODO TASK 5: Set adapter for ListFragment
         *
         * http://developer.android.com/reference/android/app/ListFragment.html
         *
         */
    }

    @Override
    public void onLoaderReset(Loader<Cursor> arg0) {

        /**
         *
         * TODO TASK 6: Swap new cursor as null into adapter when loader reset
         */
    }

    private String getPhotoPath(Cursor cursor) {

        return cursor != null ? cursor.getString(cursor
                .getColumnIndex(MediaStore.Images.Media.DATA)) : null;
    }

    private String getPhotoName(Cursor cursor) {

        /**
         *  TODO TASK 7: get photo name from current cursor position
         *
         *  Column's name is MediaStore.Images.Media.DATA
         *  You need implement this like in the above method "getPhotoPath"
         *
         */


        // return null for the test compiling!!!
        return null;
    }

    private class PhotoCursorAdapter extends ResourceCursorAdapter {

        public PhotoCursorAdapter(Context context, int layout, Cursor c,
                                  int flags) {

            super(context, layout, c, flags);
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {

            /**
             * TODO TASK 8: Load data from cursor into view
             *
             * For getting data use our getPhotoPath and getPhotoName methods
             *
             * For getting textview for name and imageview for photo use view.findViewById
             *
             * For loading photo by path need to use "loadThumbImage" method for our ImageUtil class
             *
             * http://stackoverflow.com/questions/9582965/how-to-use-cursoradapter
             *
             */
        }
    }
}
