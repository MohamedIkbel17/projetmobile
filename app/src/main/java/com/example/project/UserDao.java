package com.example.project;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface UserDao {
    @Insert
    void registerUser(UserEntity userEntity);

    /*@Query("SELECT EXISTS (SELECT * from users where email=:email )")
    boolean is_taken(String email);*/

    @Query("SELECT * from users where email=:email AND password=:password AND type=:type")
    UserEntity login(String email,String password,Integer type);
}
