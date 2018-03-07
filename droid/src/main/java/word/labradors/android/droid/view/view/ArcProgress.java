package word.labradors.android.droid.view.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import java.text.DecimalFormat;
import word.labradors.android.droid.R;
import word.labradors.android.droid.support.ScreenUtils;

/**
 * @author Labradors
 * @version 1.0
 * @description draw a arc progress
 * @date 2017/12/15
 */

public class ArcProgress extends View {

  private Paint paint;
  protected Paint textPaint;

  private RectF rectF = new RectF();

  private float strokeWidth;
  private float suffixTextSize;
  private float centerTextSize;
  private String centerText;
  private String arc_data;
  private float textSize;
  private int textColor;
  private float progress = 0;
  private int max;
  private int finishedStrokeColor;
  private int unfinishedStrokeColor;
  private String default_arc_data = "10000Kg";
  private float arcAngle;
  private float suffixTextPadding;
  private float arcBottomHeight;
  private final int default_finished_color = Color.WHITE;
  private final int default_unfinished_color = Color.rgb(72, 106, 176);
  private final int default_text_color = Color.rgb(66, 145, 241);
  private final float default_suffix_text_size;
  private final float default_suffix_padding;
  private final float default_bottom_text_size;
  private final float default_stroke_width;
  private final int default_max = 100;
  private final float default_arc_angle = 360 * 0.6f;
  private float default_text_size;
  private final int min_size;

  private static final String INSTANCE_STATE = "saved_instance";
  private static final String INSTANCE_STROKE_WIDTH = "stroke_width";
  private static final String INSTANCE_SUFFIX_TEXT_SIZE = "suffix_text_size";
  private static final String INSTANCE_SUFFIX_TEXT_PADDING = "suffix_text_padding";
  private static final String INSTANCE_BOTTOM_TEXT_SIZE = "bottom_text_size";
  private static final String INSTANCE_BOTTOM_TEXT = "bottom_text";
  private static final String INSTANCE_TEXT_SIZE = "text_size";
  private static final String INSTANCE_TEXT_COLOR = "text_color";
  private static final String INSTANCE_PROGRESS = "progress";
  private static final String INSTANCE_MAX = "max";
  private static final String INSTANCE_FINISHED_STROKE_COLOR = "finished_stroke_color";
  private static final String INSTANCE_UNFINISHED_STROKE_COLOR = "unfinished_stroke_color";
  private static final String INSTANCE_ARC_ANGLE = "arc_angle";
  private static final String INSTANCE_SUFFIX = "suffix";
  private static final String INSTANCE_DATA = "arc_data";

  public ArcProgress(Context context) {
    this(context, null);
  }

  public ArcProgress(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public ArcProgress(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);

    default_text_size = ScreenUtils.sp2px(14);
    min_size = ScreenUtils.dp2px(100);
    default_text_size = ScreenUtils.sp2px(22);
    default_suffix_text_size = ScreenUtils.sp2px(15);
    default_suffix_padding = ScreenUtils.dp2px(4);
    default_bottom_text_size = ScreenUtils.sp2px(10);
    default_stroke_width = ScreenUtils.dp2px(4);
    default_arc_data = "60000Kg";
    TypedArray attributes =
        context.getTheme().obtainStyledAttributes(attrs, R.styleable.ArcProgress, defStyleAttr, 0);
    initByAttributes(attributes);
    attributes.recycle();
    initPainters();
  }

  protected void initByAttributes(TypedArray attributes) {
    finishedStrokeColor =
        attributes.getColor(R.styleable.ArcProgress_arc_finished_color, default_finished_color);
    unfinishedStrokeColor =
        attributes.getColor(R.styleable.ArcProgress_arc_unfinished_color, default_unfinished_color);
    textColor = attributes.getColor(R.styleable.ArcProgress_arc_text_color, default_text_color);
    textSize = attributes.getDimension(R.styleable.ArcProgress_arc_text_size, default_text_size);
    arcAngle = attributes.getFloat(R.styleable.ArcProgress_arc_angle, default_arc_angle);
    setMax(attributes.getInt(R.styleable.ArcProgress_arc_max, default_max));
    setProgress(attributes.getFloat(R.styleable.ArcProgress_arc_progress, 0));
    strokeWidth =
        attributes.getDimension(R.styleable.ArcProgress_arc_stroke_width, default_stroke_width);
    suffixTextSize = attributes.getDimension(R.styleable.ArcProgress_arc_suffix_text_size,
        default_suffix_text_size);
    suffixTextPadding = attributes.getDimension(R.styleable.ArcProgress_arc_suffix_text_padding,
        default_suffix_padding);
    centerTextSize = attributes.getDimension(R.styleable.ArcProgress_arc_center_text_size,
        default_bottom_text_size);
    centerText = attributes.getString(R.styleable.ArcProgress_arc_center_text);
    arc_data = attributes.getString(R.styleable.ArcProgress_arc_data);
  }

  protected void initPainters() {
    textPaint = new TextPaint();
    textPaint.setColor(textColor);
    textPaint.setTextSize(textSize);
    textPaint.setAntiAlias(true);

    paint = new Paint();
    paint.setColor(default_unfinished_color);
    paint.setAntiAlias(true);
    paint.setStrokeWidth(strokeWidth);
    paint.setStyle(Paint.Style.STROKE);
    paint.setStrokeCap(Paint.Cap.ROUND);
  }

  @Override public void invalidate() {
    initPainters();
    super.invalidate();
  }

  public float getStrokeWidth() {
    return strokeWidth;
  }

  public void setStrokeWidth(float strokeWidth) {
    this.strokeWidth = strokeWidth;
    this.invalidate();
  }

  public float getSuffixTextSize() {
    return suffixTextSize;
  }

  public void setSuffixTextSize(float suffixTextSize) {
    this.suffixTextSize = suffixTextSize;
    this.invalidate();
  }

  public float getCenterTextSize() {
    return centerTextSize;
  }

  public void setCenterTextSize(float centerTextSize) {
    this.centerTextSize = centerTextSize;
    this.invalidate();
  }

  public String getArc_data() {
    return arc_data;
  }

  public void setArc_data(String arc_data) {
    this.arc_data = arc_data;
    this.invalidate();
  }

  public String getCenterText() {
    return centerText;
  }

  public void setCenterText(String centerText) {
    this.centerText = centerText;
  }

  public float getProgress() {
    return progress;
  }

  public void setProgress(float progress) {
    this.progress = Float.valueOf(new DecimalFormat("#.##").format(progress));

    if (this.progress > getMax()) {
      this.progress %= getMax();
    }
    invalidate();
  }

  public int getMax() {
    return max;
  }

  public void setMax(int max) {
    if (max > 0) {
      this.max = max;
      invalidate();
    }
  }

  public float getTextSize() {
    return textSize;
  }

  public void setTextSize(float textSize) {
    this.textSize = textSize;
    this.invalidate();
  }

  public int getTextColor() {
    return textColor;
  }

  public void setTextColor(int textColor) {
    this.textColor = textColor;
    this.invalidate();
  }

  public int getFinishedStrokeColor() {
    return finishedStrokeColor;
  }

  public void setFinishedStrokeColor(int finishedStrokeColor) {
    this.finishedStrokeColor = finishedStrokeColor;
    this.invalidate();
  }

  public int getUnfinishedStrokeColor() {
    return unfinishedStrokeColor;
  }

  public void setUnfinishedStrokeColor(int unfinishedStrokeColor) {
    this.unfinishedStrokeColor = unfinishedStrokeColor;
    this.invalidate();
  }

  public float getArcAngle() {
    return arcAngle;
  }

  public void setArcAngle(float arcAngle) {
    this.arcAngle = arcAngle;
    this.invalidate();
  }

  public void setSuffixText(String suffixText) {
    this.invalidate();
  }

  public float getSuffixTextPadding() {
    return suffixTextPadding;
  }

  public void setSuffixTextPadding(float suffixTextPadding) {
    this.suffixTextPadding = suffixTextPadding;
    this.invalidate();
  }

  @Override protected int getSuggestedMinimumHeight() {
    return min_size;
  }

  @Override protected int getSuggestedMinimumWidth() {
    return min_size;
  }

  @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
    int width = MeasureSpec.getSize(widthMeasureSpec);
    rectF.set(strokeWidth / 2f, strokeWidth / 2f, width - strokeWidth / 2f,
        MeasureSpec.getSize(heightMeasureSpec) - strokeWidth / 2f);
    float radius = width / 2f;
    float angle = (360 - arcAngle) / 2f;
    arcBottomHeight = radius * (float) (1 - Math.cos(angle / 180 * Math.PI));
  }

  @Override protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    float startAngle = 270 - arcAngle / 2f;
    float finishedSweepAngle = progress / (float) getMax() * arcAngle;
    float finishedStartAngle = startAngle;
    if (progress == 0) finishedStartAngle = 0.01f;
    paint.setColor(unfinishedStrokeColor);
    canvas.drawArc(rectF, startAngle, arcAngle, false, paint);
    paint.setColor(finishedStrokeColor);
    canvas.drawArc(rectF, finishedStartAngle, finishedSweepAngle, false, paint);

    String text = String.valueOf(default_arc_data);
    if (!TextUtils.isEmpty(text)) {
      textPaint.setColor(textColor);
      textPaint.setTextSize(textSize);
      float textHeight = textPaint.descent() + textPaint.ascent();
      float textBaseline = (getHeight() - textHeight) / 1.6f;
      canvas.drawText(arc_data, (getWidth() - textPaint.measureText(text)) / 2.0f,
          textBaseline, textPaint);
    }

    // draw the percent
    textPaint.setTextSize(suffixTextSize);
    float suffixHeight = textPaint.descent() + textPaint.ascent();
    String arcText = progress + "%";
    float textHeight = textPaint.descent() + textPaint.ascent();
    float arcBaseline = (getHeight() - textHeight) / 4f;
    canvas.drawText(arcText, (getWidth() - textPaint.measureText(arcText)) / 2.0f, arcBaseline,
        textPaint);

    if (arcBottomHeight == 0) {
      float radius = getWidth() / 2f;
      float angle = (360 - arcAngle) / 2f;
      arcBottomHeight = radius * (float) (1 - Math.cos(angle / 180 * Math.PI));
    }

    // draw the center text
    if (!TextUtils.isEmpty(getCenterText())) {
      textPaint.setTextSize(centerTextSize);
      float bottomTextBaseline = getMeasuredHeight() / 3;
      canvas.drawText(getCenterText(), (getWidth() - textPaint.measureText(getCenterText())) / 2.0f,
          (float) (bottomTextBaseline * (1.2)), textPaint);
    }
    // draw the line
  }

  @Override protected Parcelable onSaveInstanceState() {
    final Bundle bundle = new Bundle();
    bundle.putParcelable(INSTANCE_STATE, super.onSaveInstanceState());
    bundle.putFloat(INSTANCE_STROKE_WIDTH, getStrokeWidth());
    bundle.putFloat(INSTANCE_SUFFIX_TEXT_SIZE, getSuffixTextSize());
    bundle.putFloat(INSTANCE_SUFFIX_TEXT_PADDING, getSuffixTextPadding());
    bundle.putFloat(INSTANCE_BOTTOM_TEXT_SIZE, getCenterTextSize());
    bundle.putString(INSTANCE_BOTTOM_TEXT, getCenterText());
    bundle.putFloat(INSTANCE_TEXT_SIZE, getTextSize());
    bundle.putInt(INSTANCE_TEXT_COLOR, getTextColor());
    bundle.putFloat(INSTANCE_PROGRESS, getProgress());
    bundle.putInt(INSTANCE_MAX, getMax());
    bundle.putInt(INSTANCE_FINISHED_STROKE_COLOR, getFinishedStrokeColor());
    bundle.putInt(INSTANCE_UNFINISHED_STROKE_COLOR, getUnfinishedStrokeColor());
    bundle.putFloat(INSTANCE_ARC_ANGLE, getArcAngle());
    return bundle;
  }

  @Override protected void onRestoreInstanceState(Parcelable state) {
    if (state instanceof Bundle) {
      final Bundle bundle = (Bundle) state;
      strokeWidth = bundle.getFloat(INSTANCE_STROKE_WIDTH);
      suffixTextSize = bundle.getFloat(INSTANCE_SUFFIX_TEXT_SIZE);
      suffixTextPadding = bundle.getFloat(INSTANCE_SUFFIX_TEXT_PADDING);
      centerTextSize = bundle.getFloat(INSTANCE_BOTTOM_TEXT_SIZE);
      centerText = bundle.getString(INSTANCE_BOTTOM_TEXT);
      textSize = bundle.getFloat(INSTANCE_TEXT_SIZE);
      textColor = bundle.getInt(INSTANCE_TEXT_COLOR);
      setMax(bundle.getInt(INSTANCE_MAX));
      setProgress(bundle.getFloat(INSTANCE_PROGRESS));
      finishedStrokeColor = bundle.getInt(INSTANCE_FINISHED_STROKE_COLOR);
      unfinishedStrokeColor = bundle.getInt(INSTANCE_UNFINISHED_STROKE_COLOR);
      initPainters();
      super.onRestoreInstanceState(bundle.getParcelable(INSTANCE_STATE));
      return;
    }
    super.onRestoreInstanceState(state);
  }
}
