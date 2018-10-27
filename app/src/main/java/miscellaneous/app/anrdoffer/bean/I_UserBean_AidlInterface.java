package miscellaneous.app.anrdoffer.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 *
 */
public class I_UserBean_AidlInterface implements Parcelable {

    int aInt;

    public int getaInt() {
        return aInt;
    }

    public void setaInt(int aInt) {
        this.aInt = aInt;
    }

    public I_UserBean_AidlInterface() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.aInt);
    }

    protected I_UserBean_AidlInterface(Parcel in) {
        this.aInt = in.readInt();
    }

    public static final Creator<I_UserBean_AidlInterface> CREATOR = new Creator<I_UserBean_AidlInterface>() {
        @Override
        public I_UserBean_AidlInterface createFromParcel(Parcel source) {
            return new I_UserBean_AidlInterface(source);
        }

        @Override
        public I_UserBean_AidlInterface[] newArray(int size) {
            return new I_UserBean_AidlInterface[size];
        }
    };
}
