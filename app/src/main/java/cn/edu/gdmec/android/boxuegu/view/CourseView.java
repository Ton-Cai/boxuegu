package cn.edu.gdmec.android.boxuegu.view;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import cn.edu.gdmec.android.boxuegu.R;
import cn.edu.gdmec.android.boxuegu.adapter.AdBannerAdapter;
import cn.edu.gdmec.android.boxuegu.adapter.CourseAdapter;
import cn.edu.gdmec.android.boxuegu.bean.CourseBean;
import cn.edu.gdmec.android.boxuegu.utils.AnalysisUtils;

/**
 * Created by student on 17/12/29.
 */

public class CourseView {
    public static final int MSG_AD_SLID = 002;  //广告自动播放
    private LayoutInflater mInflater;
    private FragmentActivity mContext;
    private ArrayList<CourseBean> cadl;
    private List<List<CourseBean>> cbl;
    private View mCurrentView;
    private ListView lv_list;
    private CourseAdapter adapter;
    private ViewPager adPager;
    private Handler mHandler;
    private AdBannerAdapter ada;

    public CourseView(FragmentActivity context){
        mContext = context;
        mInflater = LayoutInflater.from(context);

    }

    private void createView(){
        mHandler = new MHandler();
        initAdData();
        getCourseData();
        initView();
        new AdAutoSlidThread().start();
    }

    /**
     * 获取课程信息
     */
    private void getCourseData() {
        try {
            InputStream is = mContext.getResources().getAssets().open("chaptertitle.xml");
            cbl = AnalysisUtils.getCourseInfos(is);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 初始化广告中的数据
     */
    private void initAdData() {
        cadl = new ArrayList<CourseBean>();
        for (int i = 0; i < 3; i++){
            CourseBean bean  = new CourseBean();
            bean.id = (i + 2);
            switch (i){
                case 0:
                    bean.icon = "banner_1";
                    break;
                case 1:
                    bean.icon = "banner_2";
                    break;
                case 2:
                    bean.icon = "banner_3";
                    break;
                default:
                    break;
            }
            cadl.add(bean);
        }
    }

    private void initView() {
        mCurrentView = mInflater.inflate(R.layout.main_view_course, null);
        lv_list = mCurrentView.findViewById(R.id.lv_list);
        adapter = new CourseAdapter(mContext);
        adapter.setData(cbl);
        lv_list.setAdapter(adapter);

        adPager = mCurrentView.findViewById(R.id.vp_advertBanner);
        adPager.setLongClickable(false);
        ada = new AdBannerAdapter(mContext.getSupportFragmentManager(), mHandler);

    }

    private class AdAutoSlidThread extends Thread{
        @Override
        public void run() {
            super.run();
        }
    }

    private class MHandler extends Handler{
        @Override
        public void dispatchMessage(Message msg) {
            super.dispatchMessage(msg);
            switch (msg.what){
                case MSG_AD_SLID:
                    if (ada.getCount() > 0){
                        adPager.setCurrentItem(adPager.getCurrentItem() + 1);
                    }
                    break;
            }
        }
    }
}
