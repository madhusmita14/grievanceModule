package com.madhusmita.final__ois.EntityClasses;

public class ModelTimeTable {
    private String subject,teacher,timing,room;

    public ModelTimeTable(String subject, String teacher, String timing, String room) {
        this.subject = subject;
        this.teacher = teacher;
        this.timing = timing;
        this.room = room;
    }

    public ModelTimeTable() {
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getTiming() {
        return timing;
    }

    public void setTiming(String timing) {
        this.timing = timing;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }
}
