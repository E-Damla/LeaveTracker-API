package com.example.LT.LeaveRequest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface LeaveRepo extends JpaRepository<LeaveEntity,Long> {
    List<LeaveEntity>findByStatus(String status);
    List<LeaveEntity> findByEmployeeNameContainingIgnoreCase(String keyword);
    @Query("SELECT l FROM LeaveEntity l WHERE l.employeeName = :name AND l.startDate <= :endDate AND l.endDate >= :startDate")
    List<LeaveEntity> findOverlappingLeaves(String name, LocalDate startDate, LocalDate endDate);
}
