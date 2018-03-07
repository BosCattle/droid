package word.labradors.android.droid.provide;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import retrofit2.HttpException;
import word.labradors.android.droid.BuildConfig;

/**
 * @author Labradors
 * @version 1.0
 * @description class description
 * @date 2017/12/11
 */
public class Message implements Parcelable {
  public Integer status;
  public String message;
  public Integer timestamp;
  public String error;
  public String path;

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Integer getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Integer timestamp) {
    this.timestamp = timestamp;
  }

  public String getError() {
    return error;
  }

  public void setError(String error) {
    this.error = error;
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public static Message handle(Throwable throwable) {
    Message responseError = null;
    if (throwable instanceof HttpException) {
      HttpException exception = (HttpException) throwable;
      try {
        responseError =
            new ObjectMapper().readValue(exception.response().errorBody().string(), Message.class);
      } catch (IOException e) {
        if (e instanceof JsonParseException) {
          responseError = new Message();
          responseError.setMessage("JSON转换错误:" + e.toString());
          responseError.setStatus(250);
        } else {
          responseError = new Message();
          responseError.setMessage("IO错误:" + e.toString());
          responseError.setStatus(250);
        }
      }
    } else {
      responseError = new Message();
      responseError.setMessage("非HTTP异常:" + throwable.toString());
      responseError.setStatus(250);
    }
    if (BuildConfig.DEBUG) throwable.toString();
    return responseError;
  }

  public Message() {
  }

  public Message(Integer status, String message) {
    this.status = status;
    this.message = message;
  }

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeValue(this.status);
    dest.writeString(this.message);
    dest.writeValue(this.timestamp);
    dest.writeString(this.error);
    dest.writeString(this.path);
  }

  protected Message(Parcel in) {
    this.status = (Integer) in.readValue(Integer.class.getClassLoader());
    this.message = in.readString();
    this.timestamp = (Integer) in.readValue(Integer.class.getClassLoader());
    this.error = in.readString();
    this.path = in.readString();
  }

  public static final Creator<Message> CREATOR = new Creator<Message>() {
    @Override public Message createFromParcel(Parcel source) {
      return new Message(source);
    }

    @Override public Message[] newArray(int size) {
      return new Message[size];
    }
  };
}
