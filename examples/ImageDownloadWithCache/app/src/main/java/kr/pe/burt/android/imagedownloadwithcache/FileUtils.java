package kr.pe.burt.android.imagedownloadwithcache;

/**
 * Created by burt on 2015. 12. 13..
 */
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Utility for temp file creation and etc.
 * Created by burt on 15. 8. 19..
 */
public class FileUtils {
    /**
     * create temp file at external storage
     * @param folder folder name
     * @param prefix the prefix of the temp file
     * @param ext file extension like ".jpg"
     * @return file path of the crated temp file.
     */
    public static String generateTempFileAtExternalStorage(String folder, String prefix, String ext) {
        File file = generateFileOnExternalStorage(folder, prefix, ext);
        if(file == null)
            return null;
        return Uri.fromFile(file).getPath();
    }

    /**
     * create temp file at internal storage
     * @param prefix the prefix of the temp file
     * @param ext file extension ".jpg"
     * @return file path of the crated temp file.
     */
    public static String getnerageTempFileAtInternalStorage(Context context, String prefix, String ext) {
        File file = generageFileOnInternalStorage(context, prefix, ext);
        if(file == null)
            return null;
        return Uri.fromFile(file).getPath();
    }


    private static File generateFileOnExternalStorage(String folder, String prefix, String ext) {

        File mediaStoreDirectory = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), folder);

        if(!mediaStoreDirectory.exists()) {
            if(!mediaStoreDirectory.mkdirs()) {
                Log.d("FileUtils", "Could not create file directory");
                return null;
            }
        }

        String timeStamp = new SimpleDateFormat("yyyyMMDd_HHmmss").format(new Date());
        File mediaFile = new File(mediaStoreDirectory.getPath() + File.separator + prefix + "_" + timeStamp + ext);
        return mediaFile;
    }


    private static File generageFileOnInternalStorage(Context context, String prefix, String ext) {

        File dataDirectory = context.getFilesDir();

        String timeStamp = new SimpleDateFormat("yyyyMMDd_HHmmss").format(new Date());
        File mediaFile = new File(dataDirectory.getPath() + File.separator + prefix + timeStamp + ext);
        return mediaFile;

    }

    public static boolean delete(String filePath) {
        return new File(filePath).delete();
    }

    public static boolean isExist(String filePath) {
        return new File(filePath).exists();
    }

    public static boolean isExternalStorageRemovable() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            return Environment.isExternalStorageRemovable();
        }
        return true;
    }

    public static File getExternalCacheDir(Context context) {
        if (hasExternalCacheDir()) {
            return context.getExternalCacheDir();
        }

        // Before Froyo we need to construct the external cache dir ourselves
        final String cacheDir = "/Android/data/" + context.getPackageName() + "/cache/";
        return new File(Environment.getExternalStorageDirectory().getPath() + cacheDir);
    }

    public static boolean hasExternalCacheDir() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO;
    }
}