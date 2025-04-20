package com.example.LT.LeaveRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LeaveService {

    @Autowired
    private LeaveRepo leaveRepo;

    public LeaveEntity createRequest(LeaveEntity request) {
        // Çakışan izin talepleri kontrolü
        if (!leaveRepo.findOverlappingLeaves(
                request.getEmployeeName(),
                request.getStartDate(),
                request.getEndDate()
        ).isEmpty()) {
            throw new IllegalArgumentException("Tarihler çakışıyor! Bu çalışan bu tarih aralığında zaten izinli.");
        }

        return leaveRepo.save(request);
    }

    public List<LeaveEntity> getAll() {
        return leaveRepo.findAll();
    }

    public List<LeaveEntity> filterByStatus(String status) {
        return leaveRepo.findByStatus(status);
    }

    public LeaveEntity updateStatus(Long id, String status) {
        LeaveEntity req = leaveRepo.findById(id).orElseThrow();
        req.setStatus(status);
        return leaveRepo.save(req);
    }


    public List<LeaveCalendar> getCalendarData() {
        List<LeaveEntity> leaveRequests = leaveRepo.findAll();
        List<LeaveCalendar> leaveCalendarList = new ArrayList<>();


        for (LeaveEntity leave : leaveRequests) {
            LeaveCalendar calendar = new LeaveCalendar(
                    leave.getEmployeeName(),
                    leave.getStartDate(),
                    leave.getStatus(),
                    leave.getEndDate()
            );
            leaveCalendarList.add(calendar);
        }

        return leaveCalendarList;
    }

    // LeaveCalendar verisini veritabanına kaydetme işlemi
    public LeaveCalendar saveLeaveCalendar(LeaveCalendar leaveCalendar) {
        // LeaveCalendar nesnesini LeaveEntity'ye dönüştür
        LeaveEntity leaveEntity = new LeaveEntity();
        leaveEntity.setEmployeeName(leaveCalendar.getTitle());
        leaveEntity.setStartDate(leaveCalendar.getStart());
        leaveEntity.setEndDate(leaveCalendar.getEnd());
        leaveEntity.setStatus(leaveCalendar.getStatus());

        // Veritabanına kaydet
        leaveRepo.save(leaveEntity);

        // Kaydedilen LeaveEntity'yi tekrar LeaveCalendar objesine dönüştürüp döndür
        return new LeaveCalendar(
                leaveEntity.getEmployeeName(),
                leaveEntity.getStartDate(),
                leaveEntity.getStatus(),
                leaveEntity.getEndDate()
        );
    }
    public void deleteExpiredLeaves() {
  }
}