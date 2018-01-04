package cn.edu.gdmec.android.boxuegu.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.edu.gdmec.android.boxuegu.R;
import cn.edu.gdmec.android.boxuegu.activity.ExercisesDetailActivity;
import cn.edu.gdmec.android.boxuegu.activity.LoginActivity;
import cn.edu.gdmec.android.boxuegu.activity.PlayHistoryActivity;
import cn.edu.gdmec.android.boxuegu.activity.UserInfoActivity;
import cn.edu.gdmec.android.boxuegu.bean.ExercisesBean;
import cn.edu.gdmec.android.boxuegu.utils.AnalysisUtils;

/**
 * Created by student on 17/12/26.
 */

public class ExercisesAdapter extends BaseAdapter{
    private Context mContext;
    private List<ExercisesBean> ebl;

    public ExercisesAdapter(Context context){
        this.mContext = context;
    }

    /**
     * 设置数据更新界面
     */
    public void setData(List<ExercisesBean> ebl){
        this.ebl = ebl;
        notifyDataSetChanged();
    }

    /**
     * 获取Item的总数
     */
    @Override
    public int getCount() {
        return ebl == null ? 0 : ebl.size();
    }

    /**
     * 根据position得到对应的Item的对象
     */
    @Override
    public ExercisesBean getItem(int position){
        return ebl == null ? null : ebl.get(position);
    }

    /**
     * 根据position得到对应Item的对象
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * 得到相应position对应的Item视图，position是当前Item的位置
     * convertView参数就是滚出屏幕的Item的View
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder vh;
        //复用convertView
        if (convertView == null){
            vh = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.exercises_list_item, null);
            vh.title = (TextView) convertView.findViewById(R.id.tv_title);
            vh.content = (TextView) convertView.findViewById(R.id.tv_content);
            vh.order = (TextView) convertView.findViewById(R.id.tv_order);
            convertView.setTag(vh);
        }else {
            vh = (ViewHolder) convertView.getTag();
        }
        //获取position对应的Item的数据对象
        final ExercisesBean bean = getItem(position);
        if (bean != null){
            vh.order.setText(position + 1 + "");
            vh.title.setText(bean.title);
            vh.content.setText(bean.content);
            vh.order.setBackgroundResource(bean.background);
        }

        //每个Item的点击事件
        convertView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //判断是否登录
                if (readLoginStatus()) {
                    //跳转到习题详细页面
                    Intent intent = new Intent(mContext, ExercisesDetailActivity.class);
                    //把章节Id传递到习题详情页面
                    intent.putExtra("id", bean.id);
                    //把标题传递到习题详细页面
                    intent.putExtra("title", bean.title);
                    mContext.startActivity(intent);
                } else {
                    Toast.makeText(mContext, "您还未登录,请先登录", Toast.LENGTH_LONG).show();
                }

            }
        });
        return convertView;
    }
    private boolean readLoginStatus(){
        SharedPreferences sp = mContext.getSharedPreferences("loginInfo", Context.MODE_PRIVATE);
        boolean isLogin = sp.getBoolean("isLogin", false);
        return isLogin;
    }

    class ViewHolder{
        public TextView title, content;
        public TextView order;
    }

}
