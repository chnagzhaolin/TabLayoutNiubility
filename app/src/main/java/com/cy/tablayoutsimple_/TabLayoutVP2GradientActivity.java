package com.cy.tablayoutsimple_;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.graphics.Typeface;
import android.os.Bundle;
import android.util.DisplayMetrics;

import com.cy.tablayoutniubility.TabMediatorVp2;
import com.cy.tablayoutniubility.TabViewHolder;
import com.cy.tablayoutniubility.FragPageAdapterVp2;
import com.cy.tablayoutniubility.TabAdapter;
import com.cy.tablayoutniubility.TabLayoutScroll;
import com.cy.tablayoutniubility.TabGradientTextView;

import java.util.ArrayList;
import java.util.List;

public class TabLayoutVP2GradientActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout_r_v_gradient);
        ViewPager2 viewPager2= findViewById(R.id.view_pager);
        TabLayoutScroll tabLayoutLine= findViewById(R.id.tablayout);

//        tabLayoutLine.setSpace_horizontal(dpAdapt(20)).setSpace_vertical(dpAdapt(8));
        FragPageAdapterVp2<String> fragmentPageAdapter = new FragPageAdapterVp2<String>(this) {

            @Override
            public Fragment createFragment(String bean, int position) {
                return FragmentTab2.newInstance(FragmentTab2.TAB_NAME2, getList_bean().get(position));
            }

            @Override
            public void bindDataToTab(TabViewHolder holder, int position, String bean, boolean isSelected) {
                TabGradientTextView textView = holder.getView(R.id.tv);
                if (isSelected) {
                    textView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                    //因为            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                    //positionOffset没有为1的时候
                    //必须
                    textView.setProgress(1);
                } else {
                    textView.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                    //因为快速滑动时，            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                    //positionOffset不会出现0
                    //必须
                    textView.setProgress(0);
                }
                textView.setText(bean);
            }

            @Override
            public int getTabLayoutID(int position, String bean) {
                return R.layout.item_tab_gradient;
            }

            @Override
            public void onTabScrolled(TabViewHolder holderCurrent, int positionCurrent, boolean fromLeft2RightCurrent, float positionOffsetCurrent, TabViewHolder holder2, int position2, boolean fromLeft2Right2, float positionOffset2) {
                super.onTabScrolled(holderCurrent, positionCurrent, fromLeft2RightCurrent, positionOffsetCurrent, holder2, position2, fromLeft2Right2, positionOffset2);
                TabGradientTextView textViewCurrent = holderCurrent.getView(R.id.tv);
                TabGradientTextView textView2= holder2.getView(R.id.tv);
                LogUtils.log("onTabScrolled");
                textViewCurrent.setDirection(fromLeft2RightCurrent?TabGradientTextView.DIRECTION_FROM_LEFT:TabGradientTextView.DIRECTION_FROM_RIGHT)
                        .setProgress(positionOffsetCurrent);
                textView2.setDirection(fromLeft2Right2?TabGradientTextView.DIRECTION_FROM_LEFT:TabGradientTextView.DIRECTION_FROM_RIGHT)
                        .setProgress(positionOffset2);


            }
        };

        TabAdapter<String> tabAdapter = new TabMediatorVp2<String>(tabLayoutLine, viewPager2).setAdapter(fragmentPageAdapter);

        List<String> list = new ArrayList<>();
        list.add("关注");
        list.add("推荐");
        list.add("视频");
        list.add("抗疫");
        list.add("深圳");
        list.add("热榜");
        list.add("小视频");
        list.add("软件");
        list.add("探索");
        list.add("在家上课");
        list.add("手机");
        list.add("动漫");
        list.add("通信");
        list.add("影视");
        list.add("互联网");
        list.add("设计");
        list.add("家电");
        list.add("平板");
        list.add("网球");
        list.add("军事");
        list.add("羽毛球");
        list.add("奢侈品");
        list.add("美食");
        list.add("瘦身");
        list.add("幸福里");
        list.add("棋牌");
        list.add("奇闻");
        list.add("艺术");
        list.add("减肥");
        list.add("电玩");
        list.add("台球");
        list.add("八卦");
        list.add("酷玩");
        list.add("彩票");
        list.add("漫画");
        fragmentPageAdapter.add(list);
        tabAdapter.add(list);
    }

    /**
     * --------------------------------------------------------------------------------
     */
    public int dpAdapt(float dp) {
        return dpAdapt(dp, 360);
    }

    public int dpAdapt(float dp, float widthDpBase) {
        DisplayMetrics dm = getResources().getDisplayMetrics();
        int heightPixels = dm.heightPixels;//高的像素
        int widthPixels = dm.widthPixels;//宽的像素
        float density = dm.density;//density=dpi/160,密度比
        float heightDP = heightPixels / density;//高度的dp
        float widthDP = widthPixels / density;//宽度的dp
        float w = widthDP > heightDP ? heightDP : widthDP;
        return (int) (dp * w / widthDpBase * density + 0.5f);
    }
}
