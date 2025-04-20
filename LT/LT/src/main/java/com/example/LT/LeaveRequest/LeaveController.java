package com.example.LT.LeaveRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leaves")
public class LeaveController {

    @Autowired
    private LeaveService leaveService;

    @PostMapping
    public LeaveEntity createLeave(@RequestBody LeaveEntity req){
        return leaveService.createRequest(req);
    }
    @GetMapping
    public List<LeaveEntity> getAll(){
        return leaveService.getAll();
    }
    @GetMapping("/status")
    public List<LeaveEntity> getByStatus(@RequestParam String status){
        return leaveService.filterByStatus(status);
    }
    @PutMapping("/{id}/status")
    public LeaveEntity updateStatus(@PathVariable Long id, @RequestBody StatusUpdate statusUpdate) {
        return leaveService.updateStatus(id, statusUpdate.getStatus());
    }
    @GetMapping("/calendar")
    public List<LeaveCalendar> getCalendarData() {
        return leaveService.getCalendarData();
    }
    @PostMapping("/calendar")
    public LeaveCalendar addLeave(@RequestBody LeaveCalendar leaveCalendar) {

        return leaveService.saveLeaveCalendar(leaveCalendar);
    }
    @DeleteMapping("/delete-expired")
    public String deleteExpiredLeaves() {
        leaveService.deleteExpiredLeaves();
        return "Süresi geçmiş izinler silindi.";
    }
}
