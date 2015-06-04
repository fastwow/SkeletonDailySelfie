package coursera.test.dailyselfie.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.ImageView;

import coursera.test.dailyselfie.R;

public class ImageUtil {

	public static String TAG = ImageUtil.class.getSimpleName();

	private static ImageUtil mInstance;

	private String mAlbumName;

	private static final String JPEG_FILE_PREFIX = "IMG_";
	private static final String JPEG_FILE_SUFFIX = ".jpg";

	private AlbumStorageDirFactory mAlbumStorageDirFactory = null;

	private ImageUtil(String albumName) {

		mAlbumName = albumName;
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO) {

			mAlbumStorageDirFactory = new FroyoAlbumDirFactory(mAlbumName);
		} else {

			mAlbumStorageDirFactory = new BaseAlbumDirFactory(mAlbumName);
		}
	}

	public static ImageUtil getInstance(String albumName) {

		if (mInstance == null)
			mInstance = new ImageUtil(albumName);

		return mInstance;
	}

	public String getBucketId() {

		return mAlbumStorageDirFactory != null ? String
				.valueOf(mAlbumStorageDirFactory.getAlbumStoragePath()
						.toLowerCase().hashCode()) : null;
	}

	private File getAlbumDir() {

		File storage = null;
		if (Environment.MEDIA_MOUNTED.equals(Environment
				.getExternalStorageState())) {

			storage = mAlbumStorageDirFactory.getAlbumStorageDir();

			if (storage != null) {
				if (!storage.mkdirs()) {
					if (!storage.exists()) {
						Log.d("CameraSample", "failed to create directory");
						return null;
					}
				}
			}

		} else {
			Log.v(TAG, "External storage is not mounted READ/WRITE.");
		}

		return storage;
	}

	public File createPhotoFile() throws IOException {

		// Create an image file name
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",
				Locale.getDefault()).format(new Date());
		String imageFileName = JPEG_FILE_PREFIX + timeStamp + "_";
		File albumFile = getAlbumDir();
		File imageFile = File.createTempFile(imageFileName, JPEG_FILE_SUFFIX,
				albumFile);

		return imageFile;
	}

	private int getThumbImageWidth(Context context) {

		return context.getResources().getDimensionPixelSize(R.dimen.thumb_image_w);
	}

	private int getThumbImageHeight(Context context) {

		return context.getResources().getDimensionPixelSize(R.dimen.thumb_image_h);
	}

	public void loadThumbImage(Context context, String photoPath, ImageView imageView) {

		loadImage(photoPath, imageView,
				getThumbImageWidth(context), getThumbImageHeight(context));
	}

	public void loadImage(String photoPath, ImageView imageView, int requestWidth, int requestHeight) {

		/**
		 * TODO TASK 9: Load and resize big image without OutOfMemory error
		 *
		 * http://developer.sonymobile.com/2011/06/27/how-to-scale-images-for-your-android-application/
		 */
	}

	public void AddToGallery(Context context, String photoPath) {

		/**
		 * TODO TASK 1: Add photoPath to gallery
		 * http://developer.android.com/training/camera/photobasics.html
		 */
	}

	/**
	 * Indicates whether the specified action can be used as an intent. This
	 * method queries the package manager for installed packages that can
	 * respond to an intent with the specified action. If no suitable package is
	 * found, this method returns false.
	 * http://android-developers.blogspot.com/2009/01/can-i-use-this-intent.html
	 *
	 */
	public static boolean isPhotoCaptureIntentAvailable(Context context) {

		final PackageManager packageManager = context.getPackageManager();
		final Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		List<ResolveInfo> list = packageManager.queryIntentActivities(intent,
				PackageManager.MATCH_DEFAULT_ONLY);
		return list.size() > 0;
	}
}
