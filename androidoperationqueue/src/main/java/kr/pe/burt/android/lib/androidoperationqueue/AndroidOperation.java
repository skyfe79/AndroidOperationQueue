package kr.pe.burt.android.lib.androidoperationqueue;


import android.graphics.Path;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;

/**
 * Created by burt on 2016. 5. 1..
 */
public class AndroidOperation extends Thread {

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // Thread Helper Functions
    private static final Handler handler = new Handler(Looper.getMainLooper());

    public static void runOnUiThread(Operation operation) {
        handler.post(operation);
    }

    public static void runOnUiThreadAfterDelay(Operation operation, long delayTimeMillis) {
        handler.postDelayed(operation, delayTimeMillis);
    }

    public static void removeOperationOnUiThread(Operation operation) {
        handler.removeCallbacks(operation);
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
    ////////////////////////////////////////////////////////////////////////////////////////////////
    // AndroidOperation
    protected AndroidOperation(Operation operation) {
        this(operation, Type.NORMAL, null, 0);
    }

    protected AndroidOperation(Operation operation, Type type, Object token, long time) {
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

    protected void queueing(Handler handler) {
        switch (type) {
            case NORMAL:
                handler.post(operation);
                break;
            case ATFIRST:
                handler.postAtFrontOfQueue(operation);
                break;
            case ATTIME:
                handler.postAtTime(operation, time);
                break;
            case ATTIME_WITH_TOKEN:
                handler.postAtTime(operation, token, time);
                break;
            case DELAY:
                handler.postDelayed(operation, time);
        }
    }
}
