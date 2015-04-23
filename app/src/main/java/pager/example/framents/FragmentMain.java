package pager.example.framents;

/**
 * Created by noor on 31/03/15.
 */

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;
import android.widget.TextView;

import refresh.example.com.fragmentpagerrefresh.R;
import refresh.example.com.fragmentpagerrefresh.Utils;

/**
 * A placeholder fragment containing a simple view.
 */
public class FragmentMain extends Fragment implements FragmentTabHost.OnTabChangeListener{
    private FragmentTabHost mTabHost;
    private static final String TAG = "FragmentMain";
    /* Avoid non-default constructors in fragments: use a default constructor plus Fragment.setArguments(Bundle) instead and use Type value = getArguments().getType("key") to retrieve back the values in the bundle in onCreateView()*/
    public FragmentMain() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        //mTabHost = new FragmentTabHost(getActivity());
        mTabHost = (FragmentTabHost)rootView.findViewById(R.id.tabhost);
        mTabHost.setup(getActivity(), getChildFragmentManager(), android.R.id.tabcontent);
        Bundle  bundle = new Bundle();
        bundle.putString(Utils.EXTRA_TITLE, Utils.TAB_PAGER_ADAPTER);

        //The third parameter is a Bundle object, that will be received inside the fragment.
        mTabHost.addTab(mTabHost.newTabSpec(Utils.TAB_PAGER_ADAPTER).setIndicator(Utils.TAB_PAGER_ADAPTER), PagerAdapterFragment.class, bundle);
        bundle = new Bundle();
        bundle.putString(Utils.EXTRA_TITLE, Utils.TAB_FRAGMENT_PAGER_ADAPTER);
        mTabHost.addTab(mTabHost.newTabSpec(Utils.TAB_FRAGMENT_PAGER_ADAPTER).setIndicator(Utils.TAB_FRAGMENT_PAGER_ADAPTER), FragmentPagerAdapterFragment.class, bundle);
        bundle = new Bundle();
        bundle.putString(Utils.EXTRA_TITLE, Utils.TAB_FRAGMENT_STATE_PAGER_ADAPTER);
        mTabHost.addTab(mTabHost.newTabSpec(Utils.TAB_FRAGMENT_STATE_PAGER_ADAPTER).setIndicator(Utils.TAB_FRAGMENT_STATE_PAGER_ADAPTER), FragmentStatePagerAdapterFragment.class, bundle);
        mTabHost.setOnTabChangedListener(this);

        //By Default the title TextView of Tab shows all caps characters, we are making it setAllCaps(false).
        TextView tv =  (TextView) mTabHost.getTabWidget().getChildAt(0).findViewById(android.R.id.title);
        tv.setAllCaps(false);
        tv =  (TextView) mTabHost.getTabWidget().getChildAt(1).findViewById(android.R.id.title);
        tv.setAllCaps(false);
        tv =  (TextView) mTabHost.getTabWidget().getChildAt(2).findViewById(android.R.id.title);
        tv.setAllCaps(false);

        return mTabHost;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mTabHost = null;
    }


    @Override
    public void onTabChanged(String tabId) {
        Log.i(TAG, "onTabChanged*** tabId:"+tabId);
        switch (tabId){
            case Utils.TAB_PAGER_ADAPTER:
                break;
            case Utils.TAB_FRAGMENT_PAGER_ADAPTER:
                break;
            case Utils.TAB_FRAGMENT_STATE_PAGER_ADAPTER:
                break;
        }
    }
}