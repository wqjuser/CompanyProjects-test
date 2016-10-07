package test.wqj.com.login.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.view.View;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import test.wqj.com.login.bean.WuLiuData;



/**
 * Created by Root on 2016/7/8.
 */
public class WuliuView extends View {


    private int mMaginTop;          //物流信息距离顶部magin
    private int mMaginMsg;          //时间距离物流信息magin
    private int mWidth;             //屏幕宽度
    private int mLineWidth;         //竖直线占条目的宽度
    private TextPaint mTextPaint;   //画物流信息的画笔

    private int mTextHeight;        //物流信息的高度
    private int mMaginBottom;       //时间距离条目底部的宽度
    private int mHeight;                    //条目高度
    private Rect mBound;
    private String mMsg;
    private Paint mTimePaint;        //时间画笔
    private int mTimeHeight;         //时间文字高度

    private ArrayList<WuLiuData> mDatas;
    private ArrayList<StaticLayout> mStaticLayoutList;

    public WuliuView(Context context, ArrayList<WuLiuData> datas) {
        super(context);
        init(datas);
    }


    private void init(ArrayList<WuLiuData> datas) {

        mMaginTop = dp2px(21);
        mMaginBottom = mMaginTop;

        mMaginMsg = dp2px(11);  //时间和信息距离11dp

        mLineWidth = dp2px(40);

        mTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setDither(true);
        mTextPaint.setColor(0xFF999999);
        mTextPaint.setTextSize(dp2px(14));
        mTextPaint.setTextAlign(Paint.Align.LEFT);

        mMsg = "【大天朝】已经签收,签收人是赵日天,感谢使用飞毛腿快递,期待再次为您服务";

        mBound = new Rect();
        mTextPaint.getTextBounds(mMsg, 0, mMsg.length(), mBound);

        mTimePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTimePaint.setDither(true);
        mTimePaint.setColor(0xFFcccccc);
        mTimePaint.setTextSize(dp2px(10));
        mTimePaint.setTextAlign(Paint.Align.LEFT);
        mTimePaint.setTypeface(Typeface.DEFAULT_BOLD);

        Rect boun = new Rect();
        mTimePaint.getTextBounds("7", 0, 1, boun);
        mTimeHeight = boun.height();

        mDatas = datas;          //物流数据

        mStaticLayoutList = new ArrayList<StaticLayout>();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        mWidth = MeasureSpec.getSize(widthMeasureSpec);

        mStaticLayoutList.clear();   //这里会重复测量几次，所以我们每次进来的时候需要清零一下
        mHeight = 0;
        for (int i = mDatas.size() - 1; i >= 0; i--) {  //因为数据要从后面时间的往前面的时间绘制

            mStaticLayoutList.add(new StaticLayout(mDatas.get(i).mMsg, mTextPaint, mWidth - mLineWidth, Layout
                    .Alignment.ALIGN_NORMAL, 1.0f, 1.0f, true));
        }

        for (int i = 0; i < mStaticLayoutList.size(); i++) { //每个条目的高度

            mTextHeight = mStaticLayoutList.get(i).getHeight();
            mHeight += mMaginTop + mTextHeight + mMaginMsg + mTimeHeight + mMaginBottom; //从左到右依次为最顶上margin,描述信息高度,
            // 时间与描述的margin，时间文字高度，与底部的margin
        }

        setMeasuredDimension(mWidth, mHeight);

    }

    @Override
    protected void onDraw(Canvas canvas) {

        mHeight = 0;
        for (int i = 0; i < mStaticLayoutList.size(); i++) {

            StaticLayout staticLayout = mStaticLayoutList.get(i);
            if (i == 0) {      //第一次因为要改变一些颜色，所以这里简单点直接分开处理
                mTimePaint.setColor(0xFFcccccc);
                mTextPaint.setColor(0xFF999999);
                canvas.save();

                canvas.translate(mLineWidth, mMaginTop);

                staticLayout.draw(canvas);  //画描述信息

                canvas.restore();

                mTextHeight = staticLayout.getHeight();
                mHeight += mMaginTop + mTextHeight + mMaginMsg + mTimeHeight + mMaginBottom;


                canvas.drawText(mDatas.get(i).mTime, mLineWidth, mHeight - mMaginBottom, mTimePaint);

                mTimePaint.setColor(Color.GRAY);


                canvas.drawLine(mLineWidth / 2, mMaginTop + mBound.height(), mLineWidth / 2, mHeight, mTimePaint); //竖直线

                mTimePaint.setColor(0x97c8cd);
                canvas.drawCircle(mLineWidth / 2, mMaginTop + mBound.height() / 2, 19, mTimePaint);
                //最近的信息圆，带透明

                mTimePaint.setColor(0xFF97c8cd);
                canvas.drawCircle(mLineWidth / 2, mMaginTop + mBound.height() / 2, 19,
                        mTimePaint);//小一点，不透明
                mTimePaint.setColor(Color.GRAY);
                mTextPaint.setColor(Color.GRAY);
            } else if(i!=mStaticLayoutList.size()-1){ //不是第一个条目,这里不改变颜色了


                canvas.save();

                canvas.translate(mLineWidth, mMaginTop + mHeight);

                staticLayout.draw(canvas);

                canvas.restore();

                mTextHeight = staticLayout.getHeight();

                int addHeight = mMaginTop + mTextHeight + mMaginMsg + mTimeHeight + mMaginBottom;
                mHeight += addHeight;


                canvas.drawText(mDatas.get(i).mTime, mLineWidth, mHeight - mMaginBottom, mTimePaint); //画时间

                canvas.drawCircle(mLineWidth / 2, mHeight - addHeight + mMaginTop + mBound.height() / 2, 15, mTextPaint);

                //              起点横坐标       起点纵坐标         终点横坐标   终点纵坐标
                canvas.drawLine(mLineWidth / 2, mHeight - addHeight, mLineWidth / 2, mHeight, mTimePaint); //竖直线

            }else if (i==mStaticLayoutList.size()-1){
                canvas.save();

                canvas.translate(mLineWidth, mMaginTop + mHeight);

                staticLayout.draw(canvas);

                canvas.restore();

                mTextHeight = staticLayout.getHeight();

                int addHeight = mMaginTop + mTextHeight + mMaginMsg + mTimeHeight + mMaginBottom;
                mHeight += addHeight;


                canvas.drawText(mDatas.get(i).mTime, mLineWidth, mHeight - mMaginBottom, mTimePaint); //画时间

                canvas.drawCircle(mLineWidth / 2, mHeight - addHeight + mMaginTop + mBound.height() / 2, 15, mTextPaint);

                //              起点横坐标       起点纵坐标         终点横坐标   终点纵坐标
                canvas.drawLine(mLineWidth / 2, mHeight - addHeight, mLineWidth / 2, mHeight - addHeight + mMaginTop + mBound.height() / 2, mTimePaint); //竖直线
            }
        }


    }

    public int dp2px(float dp) {

        return (int) (getResources().getDisplayMetrics().density * dp + .5f);
    }

    public String getFormateTime(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

}

