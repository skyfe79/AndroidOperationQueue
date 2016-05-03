package kr.pe.burt.android.multipleimagedownload;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
