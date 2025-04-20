package com.example.LT.LeaveRequest;

import java.time.LocalDate;
import java.time.LocalDate;

public class LeaveCalendar {
    private String title;
    private LocalDate start;
    private LocalDate end;
    private String status;

    public LeaveCalendar(String title, LocalDate start, String status, LocalDate end) {
        this.title = title;
        this.start = start;
        this.status = status;
        this.end = end;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }

    // LeaveCalendar'dan LeaveEntity'ye dönüşüm metodu
    public LeaveEntity toLeaveEntity() {
        LeaveEntity leaveEntity = new LeaveEntity();
        leaveEntity.setEmployeeName(this.title);
        leaveEntity.setStartDate(this.start);
        leaveEntity.setEndDate(this.end);
        leaveEntity.setStatus(this.status);

        return leaveEntity;
    }
}