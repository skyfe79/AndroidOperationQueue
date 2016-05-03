package kr.pe.burt.android.imagedownloadwithcache;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * Created by burt on 2016. 5. 3..
 */
public class Cache {
    private static Cache instance = null;

    private LruCache<String, Bitmap> memoryLruCache;
    private static final int MEMORY_CACHE_SIZE = 1024 * 1024 * 5; //5MB

    private LruCache<String, String> fileLruCache;
    private static final int FILE_CACHE_SIZE = 1024 * 1024 * 5; //5MB

    public static Cache sharedInstance() {
        if(instance == null) {
            instance = new Cache();
        }
        return instance;
    }

    private Cache() {
        memoryLruCache = new LruCache<>(MEMORY_CACHE_SIZE);
        fileLruCache = new LruCache<>(FILE_CACHE_SIZE);
    }

    public boolean hasURLInMemoryCache(String url) {
        return memoryLruCache.get(url) != null;
    }

    public void putBitmapInMemoryCache(String url, Bitmap bitmap) {
        synchronized (memoryLruCache) {
            if(memoryLruCache.get(url) == null) {
                memoryLruCache.put(url, bitmap);
            }
        }
    }

    public Bitmap getBitmapFromMemoryCache(String url) {
        return memoryLruCache.get(url);
    }

    public boolean hasURLInFileCache(String url) {
        return fileLruCache.get(url) != null;
    }

    public void putPathInFileCache(String url, String path) {
        synchronized (fileLruCache) {
            if(fileLruCache.get(url) == null) {
                fileLruCache.put(url, path);
            }
        }
    }

    public String getFilePathFromFileCache(String url) {
        return fileLruCache.get(url);
    }
}
