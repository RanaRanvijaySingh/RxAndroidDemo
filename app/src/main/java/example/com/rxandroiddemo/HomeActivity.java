package example.com.rxandroiddemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.schedulers.TimeInterval;

public class HomeActivity extends FragmentActivity {


    private static final String TAG = HomeActivity.class.getName();
    private List<Integer> mDrawableList = new ArrayList<>();
    private Subscription subscription;
    private Animation animation;

    {
        mDrawableList.add(R.drawable.ic_android_black_24dp);
        mDrawableList.add(R.drawable.ic_cake_black_24dp);
        mDrawableList.add(R.drawable.ic_email_black_24dp);
        mDrawableList.add(R.drawable.ic_backup_black_24dp);
        mDrawableList.add(R.drawable.ic_notifications_active_black_24dp);
    }

    @BindView(R.id.linearLayoutParent)
    LinearLayout linearLayoutParent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        initializeComponents();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    /**
     * Function to initialize components of the class.
     */
    private void initializeComponents() {
        animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_right);
        Observable<TimeInterval<Integer>> integerOnSubscribe = Observable.interval(500, TimeUnit.MILLISECONDS)
                .from(mDrawableList)
                .timeInterval();
        integerOnSubscribe
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(integerObserver);
    }

    private Observer<TimeInterval<Integer>> integerObserver = new Observer<TimeInterval<Integer>>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            Log.e(TAG, "onError: ", e);
        }

        @Override
        public void onNext(TimeInterval<Integer> integerTimeInterval) {
            Log.d(TAG, "run: " + integerTimeInterval.getValue());
            loadImage(integerTimeInterval.getValue());
        }
    };

    private void loadImage(Integer drawableId) {
        View view = LayoutInflater.from(this).inflate(R.layout.view_image, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        imageView.setBackgroundResource(drawableId);
        linearLayoutParent.addView(view);
        linearLayoutParent.invalidate();
        imageView.startAnimation(animation);
    }
}