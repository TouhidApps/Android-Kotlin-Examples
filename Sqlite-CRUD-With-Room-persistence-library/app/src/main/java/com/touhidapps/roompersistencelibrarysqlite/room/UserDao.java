package com.touhidapps.roompersistencelibrarysqlite.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM " + DbConstraints.TABLE_USERS)
    List<UserEntity> loadAllUsers();

    @Query("SELECT * FROM " + DbConstraints.TABLE_USERS + " WHERE user_name LIKE :name")
    List<UserEntity> loadAllUsersByLikeName(String name);

    @Insert
    void insertUser(UserEntity user);

    @Update //(onConflict = OnConflictStrategy.REPLACE)
    void updateUser(UserEntity user);

    @Delete
    void deleteUser(UserEntity user);


//    // insert any amount of user
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    void insertUsers(UserEntity... users);
//
//    // insert 2 users
//    @Insert
//    void insertBothUsers(UserEntity user1, UserEntity user2);
//
//    // insert any amount of user
//    @Insert
//    void insertUsersAndFriends(UserEntity user, List<UserEntity> friends);
//
//    // Update any amount of user
//    @Update
//    void updateUsers(UserEntity... users);
//
//    // Delete any amount of user
//    @Delete
//    void deleteUsers(UserEntity... users);


}
