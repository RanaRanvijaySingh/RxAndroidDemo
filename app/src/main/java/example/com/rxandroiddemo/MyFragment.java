package example.com.rxandroiddemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyFragment extends Fragment {

    private static Integer mDrawableId;

    @BindView(R.id.imageView)
    ImageView imageView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, null);
        ButterKnife.bind(this, view);
        return view;
    }

    public static Fragment getInstance(Integer drawableId) {
        mDrawableId = drawableId;
        return new MyFragment();
    }

    @Override
    public void onResume() {
        super.onResume();
        imageView.setBackgroundResource(mDrawableId);
    }
}