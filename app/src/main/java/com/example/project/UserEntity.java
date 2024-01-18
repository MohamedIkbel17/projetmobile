package com.example.project;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "users")
public class UserEntity {

    @PrimaryKey(autoGenerate = true)
    Integer id;

    @ColumnInfo(name = "email")
    String email;

    @ColumnInfo(name = "name")
    String name;

    @ColumnInfo(name = "password")
    String password;

    @ColumnInfo(name = "type")
    Integer type;

    /*public UserEntity(Integer o, String email, String password, String name){
    }*/

    /*public UserEntity(Integer id, String email, String password, String name){
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
    }*/
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
