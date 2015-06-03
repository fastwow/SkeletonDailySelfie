package coursera.test.dailyselfie.utils;

import android.os.Environment;

public class BaseAlbumDirFactory extends AlbumStorageDirFactory {

	// Standard storage location for digital camera files
	private static final String CAMERA_DIR = "/dcim/";

	public BaseAlbumDirFactory(String albumName) {
		super(albumName);

	}

	@Override
	public String getAlbumStoragePath() {

		return Environment.getExternalStorageDirectory() + CAMERA_DIR
				+ mAlbumName;
	}
}
