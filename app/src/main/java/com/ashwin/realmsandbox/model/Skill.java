package com.ashwin.realmsandbox.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Skill implements Parcelable {
    private final String name;
    private int level;

    public Skill(String name, int level) {
        this.name = name;
        this.level = level;
    }

    protected Skill(Parcel in) {
        name = in.readString();
        level = in.readInt();
    }

    public static final Creator<Skill> CREATOR = new Creator<Skill>() {
        @Override
        public Skill createFromParcel(Parcel in) {
            return new Skill(in);
        }

        @Override
        public Skill[] newArray(int size) {
            return new Skill[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(level);
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "Skill{" +
                "name='" + name + '\'' +
                ", level=" + level +
                '}';
    }
}
