package kr.pe.burt.android.lib.androidoperationqueue;

import android.os.Bundle;

/**
 * Created by burt on 2016. 5. 1..
 */
public interface Operation {
    void run(AndroidOperationQueue queue, Bundle bundle);
}
