package coursera.test.dailyselfie.utils;

import android.os.Environment;

public final class FroyoAlbumDirFactory extends AlbumStorageDirFactory {

	public FroyoAlbumDirFactory(String albumName) {
		super(albumName);
	}

	@Override
	public String getAlbumStoragePath() {

		return Environment
				.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
				+ "/" + mAlbumName;
	}

}
