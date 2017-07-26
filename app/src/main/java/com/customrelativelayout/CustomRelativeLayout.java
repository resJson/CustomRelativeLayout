package com.customrelativelayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 自定义View组合
 * Created by Ray on 2017/7/25.
 */

public class CustomRelativeLayout extends RelativeLayout {

    private View mView;
    private RelativeLayout mRootLayout;
    private TextView mLeftTextView;
    private TextView mTipsTextView;
    private TextView mRedDotTextView;
    private View mLine;
    private ImageView mRightIV;
    private ImageView mLeftIV;

    private String mLeftText;
    private String mTipsText;
    private int mLeftTextSize;
    private int mLeftTextColor;
    private int mTipsSize;
    private int mTipsColor;
    private Drawable mLeftDrawable;
    private Drawable mRightDrawable;

    private LayoutInflater layoutInflater;

    private OnSettingItemonClick onSettingItemonClick;

    public CustomRelativeLayout(Context context) {
        super(context);
    }

    public CustomRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
        setAttrs(context, attrs);
    }

    private void setAttrs(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CustomView);

        mLeftText = a.getString(R.styleable.CustomView_leftText);
        mLeftTextSize = a.getDimensionPixelOffset(R.styleable.CustomView_leftTextSize, sp2px(context, 16));
        mLeftTextColor = a.getColor(R.styleable.CustomView_leftTextColor, context.getResources().getColor(R.color.color_primary));
        mLeftTextView.setText(mLeftText);
        mLeftTextView.setTextColor(mLeftTextColor);
        mLeftTextView.setTextSize(px2sp(context, mLeftTextSize));

        mTipsText = a.getString(R.styleable.CustomView_tipsText);
        mTipsSize = a.getDimensionPixelOffset(R.styleable.CustomView_tipsTextSize, sp2px(context, 14));
        mTipsColor = a.getColor(R.styleable.CustomView_tipsTextColor, context.getResources().getColor(R.color.color_hint));
        mTipsTextView.setText(mTipsText);
        mTipsTextView.setTextColor(mTipsColor);
        mTipsTextView.setTextSize(px2sp(context, mTipsSize));

        mLeftDrawable = a.getDrawable(R.styleable.CustomView_leftIcon);
        mLeftIV.setImageDrawable(mLeftDrawable);

        mRightDrawable = a.getDrawable(R.styleable.CustomView_rightIcon);
        mRightIV.setImageDrawable(mRightDrawable);

        mRedDotTextView.setVisibility(a.getBoolean(R.styleable.CustomView_isShowRedDot, false) ? View.VISIBLE : View.GONE);

        mLine.setVisibility(a.getBoolean(R.styleable.CustomView_isShowDownLine, true) ? View.VISIBLE : View.GONE);

        a.recycle();
    }

    private void initView(Context context) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mView = layoutInflater.inflate(R.layout.custom_setting_item, this);
        mRootLayout = (RelativeLayout) mView.findViewById(R.id.rl_contains);
        mLeftTextView = (TextView) mView.findViewById(R.id.tv_left_text);
        mTipsTextView = (TextView) mView.findViewById(R.id.tv_tips);
        mRedDotTextView = (TextView) mView.findViewById(R.id.tv_red_dot);
        mLine = mView.findViewById(R.id.tv_line);
        mRightIV = (ImageView) mView.findViewById(R.id.iv_right);
        mLeftIV = (ImageView) mView.findViewById(R.id.iv_left);
        mRootLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onSettingItemonClick != null){
                    onSettingItemonClick.onClick();
                }
            }
        });
    }

    public float px2sp(Context context, float pxValue) {
        float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int)(pxValue / fontScale + 0.5F);
    }

    public static int sp2px(Context context, float spValue) {
        float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    public void setOnSettingItemonClick(OnSettingItemonClick onSettingItemonClick) {
        this.onSettingItemonClick = onSettingItemonClick;
    }

    public interface OnSettingItemonClick{
        void onClick();
    }

    public void setSettingData(String tips, boolean isShowRedDot){
        mRedDotTextView.setVisibility(isShowRedDot ? View.VISIBLE :View.GONE);
        mTipsTextView.setText(tips);
    }

}


















