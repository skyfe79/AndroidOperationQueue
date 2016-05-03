package kr.pe.burt.android.imagedownloadwithcache;

import android.Manifest;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import com.github.kayvannj.permission_utils.Func;
import com.github.kayvannj.permission_utils.PermissionUtil;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_STORAGE = 1;
    private PermissionUtil.PermissionRequestObject storagePermissionRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        storagePermissionRequest = PermissionUtil.with(this)
                .request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .onAllGranted(new Func() {
                    @Override
                    protected void call() {
                        run();
                    }
                })
                .onAnyDenied(new Func() {
                    @Override
                    protected void call() {

                    }
                })
                .ask(REQUEST_CODE_STORAGE);

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(storagePermissionRequest!=null) storagePermissionRequest.onRequestPermissionsResult(requestCode, permissions, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void run() {
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        layoutManager.setSmoothScrollbarEnabled(true);
        recyclerView.setLayoutManager(layoutManager);

        List<CardItem> items = new ArrayList<>();
        for(String url : urls) {
            items.add(new CardItem(url));
        }
        recyclerView.setAdapter(new RecyclerAdapter(items));
    }



    String urls[] = new String[] {
            "https://raw.githubusercontent.com/skyfe79/AndroidOperationQueue/master/examples/art/marvel01.jpeg",
            "https://raw.githubusercontent.com/skyfe79/AndroidOperationQueue/master/examples/art/marvel02.jpeg",
            "https://raw.githubusercontent.com/skyfe79/AndroidOperationQueue/master/examples/art/marvel03.jpeg",
            "https://raw.githubusercontent.com/skyfe79/AndroidOperationQueue/master/examples/art/marvel04.jpeg",
            "https://raw.githubusercontent.com/skyfe79/AndroidOperationQueue/master/examples/art/marvel05.jpeg",

            "https://raw.githubusercontent.com/skyfe79/AndroidOperationQueue/master/examples/art/marvel06.jpeg",
            "https://raw.githubusercontent.com/skyfe79/AndroidOperationQueue/master/examples/art/marvel07.jpeg",
            "https://raw.githubusercontent.com/skyfe79/AndroidOperationQueue/master/examples/art/marvel08.jpeg",
            "https://raw.githubusercontent.com/skyfe79/AndroidOperationQueue/master/examples/art/marvel09.jpeg",
            "https://raw.githubusercontent.com/skyfe79/AndroidOperationQueue/master/examples/art/marvel10.jpeg",

            "https://raw.githubusercontent.com/skyfe79/AndroidOperationQueue/master/examples/art/marvel11.jpeg",
            "https://raw.githubusercontent.com/skyfe79/AndroidOperationQueue/master/examples/art/marvel12.jpeg",
            "https://raw.githubusercontent.com/skyfe79/AndroidOperationQueue/master/examples/art/marvel13.jpeg",
            "https://raw.githubusercontent.com/skyfe79/AndroidOperationQueue/master/examples/art/marvel14.jpeg",
            "https://raw.githubusercontent.com/skyfe79/AndroidOperationQueue/master/examples/art/marvel15.jpeg",

            "https://raw.githubusercontent.com/skyfe79/AndroidOperationQueue/master/examples/art/marvel16.jpeg",
            "https://raw.githubusercontent.com/skyfe79/AndroidOperationQueue/master/examples/art/marvel17.jpeg",
            "https://raw.githubusercontent.com/skyfe79/AndroidOperationQueue/master/examples/art/marvel18.jpeg",
            "https://raw.githubusercontent.com/skyfe79/AndroidOperationQueue/master/examples/art/marvel19.jpeg",
            "https://raw.githubusercontent.com/skyfe79/AndroidOperationQueue/master/examples/art/marvel20.jpeg",

            "https://raw.githubusercontent.com/skyfe79/AndroidOperationQueue/master/examples/art/marvel01.jpeg",
            "https://raw.githubusercontent.com/skyfe79/AndroidOperationQueue/master/examples/art/marvel02.jpeg",
            "https://raw.githubusercontent.com/skyfe79/AndroidOperationQueue/master/examples/art/marvel03.jpeg",
            "https://raw.githubusercontent.com/skyfe79/AndroidOperationQueue/master/examples/art/marvel04.jpeg",
            "https://raw.githubusercontent.com/skyfe79/AndroidOperationQueue/master/examples/art/marvel05.jpeg",

            "https://raw.githubusercontent.com/skyfe79/AndroidOperationQueue/master/examples/art/marvel06.jpeg",
            "https://raw.githubusercontent.com/skyfe79/AndroidOperationQueue/master/examples/art/marvel07.jpeg",
            "https://raw.githubusercontent.com/skyfe79/AndroidOperationQueue/master/examples/art/marvel08.jpeg",
            "https://raw.githubusercontent.com/skyfe79/AndroidOperationQueue/master/examples/art/marvel09.jpeg",
            "https://raw.githubusercontent.com/skyfe79/AndroidOperationQueue/master/examples/art/marvel10.jpeg",

            "https://raw.githubusercontent.com/skyfe79/AndroidOperationQueue/master/examples/art/marvel11.jpeg",
            "https://raw.githubusercontent.com/skyfe79/AndroidOperationQueue/master/examples/art/marvel12.jpeg",
            "https://raw.githubusercontent.com/skyfe79/AndroidOperationQueue/master/examples/art/marvel13.jpeg",
            "https://raw.githubusercontent.com/skyfe79/AndroidOperationQueue/master/examples/art/marvel14.jpeg",
            "https://raw.githubusercontent.com/skyfe79/AndroidOperationQueue/master/examples/art/marvel15.jpeg",

            "https://raw.githubusercontent.com/skyfe79/AndroidOperationQueue/master/examples/art/marvel16.jpeg",
            "https://raw.githubusercontent.com/skyfe79/AndroidOperationQueue/master/examples/art/marvel17.jpeg",
            "https://raw.githubusercontent.com/skyfe79/AndroidOperationQueue/master/examples/art/marvel18.jpeg",
            "https://raw.githubusercontent.com/skyfe79/AndroidOperationQueue/master/examples/art/marvel19.jpeg",
            "https://raw.githubusercontent.com/skyfe79/AndroidOperationQueue/master/examples/art/marvel20.jpeg",
    };
}
