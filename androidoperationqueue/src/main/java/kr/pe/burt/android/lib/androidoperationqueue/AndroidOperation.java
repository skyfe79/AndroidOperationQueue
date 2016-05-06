package kr.pe.burt.android.lib.androidoperationqueue;


import android.graphics.Path;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;

import java.lang.ref.WeakReference;

/**
 * Created by burt on 2016. 5. 1..
 */
public class AndroidOperation extends Thread {

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // Thread Helper Functions
    private static final Handler handler = new Handler(Looper.getMainLooper());

    public static void runOnUiThread(Runnable runnable) {
        handler.post(runnable);
    }

    public static void runOnUiThreadAfterDelay(Runnable runnable, long delayTimeMillis) {
        handler.postDelayed(runnable, delayTimeMillis);
    }

    public static void removeOperationOnUiThread(Runnable runnable) {
        handler.removeCallbacks(runnable);
    }

    public static void sleep(long sleepTimeMillis) {
        try { Thread.sleep(sleepTimeMillis); } catch(Exception e) {}
    }

    protected enum Type {

        NORMAL,
        ATFIRST,
        ATTIME,
        ATTIME_WITH_TOKEN,
        DELAY,
    }

    private Operation operation = null;
    private Type type = Type.NORMAL;
    private Object token = null;
    private long time = 0;
    private WeakReference<AndroidOperationQueue> owner;
    ////////////////////////////////////////////////////////////////////////////////////////////////
    // AndroidOperation
    protected AndroidOperation(AndroidOperationQueue queue, Operation operation) {
        this(queue, operation, Type.NORMAL, null, 0);
    }

    protected AndroidOperation(AndroidOperationQueue queue, Operation operation, Type type, Object token, long time) {
        this.owner = new WeakReference<AndroidOperationQueue>(queue);
        this.operation = operation;
        this.type = type;
        this.token = token;
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AndroidOperation that = (AndroidOperation) o;

        if (!operation.equals(that.operation)) return false;
        return token != null ? token.equals(that.token) : that.token == null;

    }

    @Override
    public int hashCode() {
        int result = operation.hashCode();
        result = 31 * result + (token != null ? token.hashCode() : 0);
        return result;
    }

    @Override
    public void run() {

        AndroidOperationQueue queue = owner.get();
        if(queue != null && queue.isActivated()) {
            operation.run(queue, queue.getBundle());
        }

    }

    protected void queueing(Handler handler) {
        switch (type) {
            case NORMAL:
                handler.post(this);
                break;
            case ATFIRST:
                handler.postAtFrontOfQueue(this);
                break;
            case ATTIME:
                handler.postAtTime(this, time);
                break;
            case ATTIME_WITH_TOKEN:
                handler.postAtTime(this, token, time);
                break;
            case DELAY:
                handler.postDelayed(this, time);
        }
    }
}
