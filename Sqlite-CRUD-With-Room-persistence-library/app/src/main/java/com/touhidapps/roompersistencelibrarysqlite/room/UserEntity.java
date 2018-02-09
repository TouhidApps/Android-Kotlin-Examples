package com.touhidapps.roompersistencelibrarysqlite.room;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

//@Entity(foreignKeys = @ForeignKey(entity = Address.class, parentColumns = "user_phone", childColumns = "phone")) // Current entity is child entity
@Entity(tableName = DbConstraints.TABLE_USERS)
public class UserEntity {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "user_name")
    public String name;

    @ColumnInfo(name = "user_phone")
    public String phone;

    public UserEntity() {

    }

    // To insert
    @Ignore // as multiple constructor is not allowed
    public UserEntity(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    // To update
    @Ignore  // as multiple constructor is not allowed
    public UserEntity(int id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    @Ignore // To delete
    public UserEntity(int id) {
        this.id = id;
    }

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


}