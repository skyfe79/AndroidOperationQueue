package kr.pe.burt.android.lib.androidoperationqueue.app;

import android.support.annotation.BoolRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import kr.pe.burt.android.lib.androidoperationqueue.AndroidOperation;
import kr.pe.burt.android.lib.androidoperationqueue.AndroidOperationQueue;
import kr.pe.burt.android.lib.androidoperationqueue.Operation;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    AndroidOperationQueue queue = new AndroidOperationQueue("MyQueue");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.textview);

        for(int i = 1; i<=10; i++) {
            final int count = i;
            queue.addOperation(new Operation() {
                @Override
                public void run() {
                    AndroidOperation.runOnUiThread(new Operation() {
                        @Override
                        public void run() {
                            textView.setText(String.valueOf(count));
                        }
                    });

                    AndroidOperation.sleep(1000);
                }
            });
        }
        queue.start();

        queue.addOperation(new Operation() {
            @Override
            public void run() {
                Bundle bundle = queue.getBundle();
                bundle.putInt("sum", 1);
            }
        });
        queue.addOperation(new Operation() {
            @Override
            public void run() {
                Bundle bundle = queue.getBundle();
                int sum = bundle.getInt("sum");
                bundle.putInt("sum", sum + 2);
            }
        });
        queue.addOperation(new Operation() {
            @Override
            public void run() {
                Bundle bundle = queue.getBundle();
                int sum = bundle.getInt("sum");
                bundle.putInt("sum", sum + 3);
            }
        });
        queue.addOperation(new Operation() {
            @Override
            public void run() {
                Bundle bundle = queue.getBundle();
                int sum = bundle.getInt("sum");
                Log.v("SUM", String.format("1+2+3 = %d", sum));
            }
        });
    }
}
