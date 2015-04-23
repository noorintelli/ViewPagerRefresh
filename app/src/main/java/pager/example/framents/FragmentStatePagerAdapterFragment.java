package pager.example.framents;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

import pager.example.adapters.MyFragmentStatePagerAdapter;
import refresh.example.com.fragmentpagerrefresh.R;
import refresh.example.com.fragmentpagerrefresh.Utils;

/**
 * Created by noor on 10/04/15.
 */
public class FragmentStatePagerAdapterFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "FragmentStatPgrAdapFrag";
    private MyFragmentStatePagerAdapter mPagerAdapter;
    private ArrayList<Utils.DummyItem> mImageItemList;
    private ViewPager mViewPager;

    /* Avoid non-default constructors in fragments: use a default constructor plus Fragment.setArguments(Bundle) instead and use Type value = getArguments().getType("key") to retrieve back the values in the bundle in onCreateView()*/
    public FragmentStatePagerAdapterFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_sample, container, false);
        rootView.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_light));
        Button buttonThumb = (Button) rootView.findViewById(R.id.button_thumb);
        Button buttonFull = (Button) rootView.findViewById(R.id.button_full);
        buttonThumb.setOnClickListener(this);
        buttonFull.setOnClickListener(this);
        mViewPager = (ViewPager) rootView.findViewById(R.id.viewpager);
        rootView.setBackgroundColor(getResources().getColor(android.R.color.holo_purple));
        mImageItemList = new ArrayList<>();
        mImageItemList.addAll(Utils.getThumbImageList());
        mPagerAdapter = new MyFragmentStatePagerAdapter(getChildFragmentManager(), mImageItemList);
        mViewPager = (ViewPager) rootView.findViewById(R.id.viewpager);
        mViewPager.setAdapter(mPagerAdapter);
        return rootView;

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_thumb:
                mImageItemList.clear();
                mImageItemList.addAll(Utils.getThumbImageList());
                mPagerAdapter.notifyDataSetChanged();
                break;
            case R.id.button_full:
                mImageItemList.clear();
                mImageItemList.addAll(Utils.getFullImageList());
                mPagerAdapter.notifyDataSetChanged();
                break;

        }
    }
}
