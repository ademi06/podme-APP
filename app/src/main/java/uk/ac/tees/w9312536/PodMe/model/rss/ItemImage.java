package uk.ac.tees.w9312536.PodMe.model.rss;

import android.os.Parcel;
import android.os.Parcelable;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root(name = "image", strict = false)
public class ItemImage implements Parcelable {

    @Attribute(name = "href", required = false)
    private String mHref;

    public ItemImage() {
    }

    public ItemImage(String href) {
        mHref = href;
    }

    protected ItemImage(Parcel in) {
        mHref = in.readString();
    }

    public static final Creator<ItemImage> CREATOR = new Creator<ItemImage>() {
        @Override
        public ItemImage createFromParcel(Parcel in) {
            return new ItemImage(in);
        }

        @Override
        public ItemImage[] newArray(int size) {
            return new ItemImage[size];
        }
    };

    public String getItemImageHref() {
        return mHref;
    }

    public void setItemImageHref(String href) {
        mHref = href;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mHref);
    }}
