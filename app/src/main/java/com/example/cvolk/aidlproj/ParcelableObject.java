package com.example.cvolk.aidlproj;

import android.os.Parcel;
import android.os.Parcelable;

public class ParcelableObject implements Parcelable {

    final public static DataContract CONTRACT = new DataContract(
            "ParcelableObject",
            "id",
            "INTEGER",
            new String[] { "Name" },
            new String[] { "TEXT" },
            1
    );

    int id;
    String name;

    public ParcelableObject(int id, String name) {
        this.id = id;
        this.name = name;
    }

    protected ParcelableObject(Parcel input) {
        this.id = input.readInt();
        this.name = input.readString();
    }

    public static final Creator<ParcelableObject> CREATOR = new Creator<ParcelableObject>() {
        @Override
        public ParcelableObject createFromParcel(Parcel source) {
            return new ParcelableObject(source);
        }

        @Override
        public ParcelableObject[] newArray(int size) {
            return new ParcelableObject[size];
        }
    };

    public static DataContract getContract() { return CONTRACT; }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
    }
}
