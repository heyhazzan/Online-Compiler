/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.model;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


public class ProjectBean {
    String file = "";
    String language = "";
    String date;
    double score;
    String stud_number = "";
    int assign_id;

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getStud_number() {
        return stud_number;
    }

    public void setStud_number(String stud_number) {
        this.stud_number = stud_number;
    }

    public int getAssign_id() {
        return assign_id;
    }

    public void setAssign_id(int assign_id) {
        this.assign_id = assign_id;
    }
}
