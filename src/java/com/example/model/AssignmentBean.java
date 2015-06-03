/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.model;

import java.util.Date;

/**
 *
 * @author Hazzan
 */
public class AssignmentBean {
    int assign_id;
    String name;
    String instruction;
    String deadline;
    String inputs;
    String output;
    String faculty_number;

    public int getAssign_id() {
        return assign_id;
    }

    public void setAssign_id(int assign_id) {
        this.assign_id = assign_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getInputs() {
        return inputs;
    }

    public void setInputs(String inputs) {
        this.inputs = inputs;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public String getFaculty_number() {
        return faculty_number;
    }

    public void setFaculty_number(String faculty_number) {
        this.faculty_number = faculty_number;
    }
    
}
