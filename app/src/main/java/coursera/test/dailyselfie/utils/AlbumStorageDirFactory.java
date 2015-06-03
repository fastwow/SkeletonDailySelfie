package coursera.test.dailyselfie.utils;

import java.io.File;

abstract class AlbumStorageDirFactory {

	protected String mAlbumName;

	public AlbumStorageDirFactory(String albumName) {

		mAlbumName = albumName;
	}

	public File getAlbumStorageDir() {

		return new File(getAlbumStoragePath());
	}

	public abstract String getAlbumStoragePath();
}
