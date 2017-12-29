package cn.edu.gdmec.android.boxuegu.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import cn.edu.gdmec.android.boxuegu.bean.VideoBean;
import cn.edu.gdmec.android.boxuegu.adapter.ExercisesDetailAdapter;

/**
 * Created by student on 17/12/29.
 */

public class VideoListAdapter extends BaseAdapter{
    private Context mContext;
    private List<VideoBean> vbl;//
    private int selectedPosition = -1;//
    private ExercisesDetailAdapter.OnSelectListener onSelectListener;
    public VideoListAdapter(Context context, ExercisesDetailAdapter.OnSelectListener onSelectListener){
        this.mContext = context;
        this.onSelectListener = onSelectListener;
    }
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }
}
