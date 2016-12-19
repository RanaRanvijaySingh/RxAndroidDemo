package example.com.rxandroiddemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.Subscription;

public class SplashActivity extends AppCompatActivity {

    private Subscription subscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    Observer observer = new Observer() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(Object o) {
            startActivity(new Intent(SplashActivity.this, HomeActivity.class));
        }
    };

    @Override
    protected void onResume() {
        subscription = Observable.timer(3, TimeUnit.SECONDS).subscribe(observer);
        super.onResume();
    }

    @Override
    protected void onPause() {
        subscription.unsubscribe();
        super.onPause();
    }

    @Override
    public void onBackPressed() {
        subscription.unsubscribe();
        super.onBackPressed();
    }
}
