package word.labradors.android.droid.view.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import word.labradors.android.droid.support.ScreenUtils;

/**
 * @author Administrator
 * @version 1.0
 * @description 绘制圆环和文字
 * @date 2018/1/3
 */

public class DrawImageView extends AppCompatImageView {
  private final Paint paint;
  private final Context context;

  public DrawImageView(Context context, AttributeSet attrs) {
    super(context, attrs);
    // TODO Auto-generated constructor stub
    this.context = context;
    this.paint = new Paint();
    this.paint.setAntiAlias(true);
    this.paint.setStyle(Paint.Style.STROKE);
  }

  @Override protected void onDraw(Canvas canvas) {
    // TODO Auto-generated method stub
    int center = getWidth() / 2;
    //内圆半径
    int innerCircle = ScreenUtils.dp2px(83);
    //圆环宽度
    int ringWidth = ScreenUtils.dp2px(10);

    // 第一种方法绘制圆环
    //绘制内圆
    this.paint.setARGB(255, 138, 43, 226);
    this.paint.setStrokeWidth(2);
    canvas.drawCircle(center, center, innerCircle, this.paint);

    //绘制圆环
    this.paint.setARGB(255, 138, 43, 226);
    this.paint.setStrokeWidth(ringWidth);
    canvas.drawCircle(center, center, innerCircle + 1 + ringWidth / 2, this.paint);

    //绘制外圆
    this.paint.setARGB(255, 138, 43, 226);
    this.paint.setStrokeWidth(2);
    canvas.drawCircle(center, center, innerCircle + ringWidth, this.paint);
    super.onDraw(canvas);
  }
}
