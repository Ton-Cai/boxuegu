package cn.edu.gdmec.android.boxuegu.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;

import cn.edu.gdmec.android.boxuegu.R;

/**
 * Created by student on 17/12/28.
 */

public class ViewPagerIndicator extends LinearLayout {

    private int mCount;  //小圆点的个数
    private int mIndex;  //当前小圆点位置
    private Context context;

    public ViewPagerIndicator(Context context) {
        super(context, null);
    }

    public ViewPagerIndicator(Context context, AttributeSet attrs){
        super(context, attrs);
        this.context = context;
        setGravity(Gravity.CENTER); //设置此布局居中

    }

    /**
     * 设置滑动到当前小圆点时其它圆点的位置
     */
    public void serCurrentPosition(int currentIndex){
        mIndex = currentIndex;   //当前小圆点
        removeAllViews();  //移除界面上存在的view
        int pex = 5;      //内边距
        for(int i = 0; i < mCount; i++){
            //创建一个ImageView控件来放置小圆点
            ImageView imageView = new ImageView(context);
            if (mIndex == i){
                //蓝色为选中圆点
                imageView.setImageResource(R.drawable.indicator_on);
            }else {
                //灰色图片
                imageView.setImageResource(R.drawable.indicator_off);
            }
            imageView.setPadding(pex, 0, pex, 0);   //设置小圆点图片的上下左右的padding
            addView(imageView);   //把此小圆点添加到自定义的ViewPagerIndicator控件上
        }
    }

    /**
     * 设置小圆点的数目
     */
    public void setCount(int count){
        this.mCount = count;
    }
}
