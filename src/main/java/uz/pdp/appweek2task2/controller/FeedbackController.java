package uz.pdp.appweek2task2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appweek2task2.entity.Category;
import uz.pdp.appweek2task2.entity.Feedback;
import uz.pdp.appweek2task2.payloads.ApiResponse;
import uz.pdp.appweek2task2.payloads.FeedbackT;
import uz.pdp.appweek2task2.service.FeedbackService;

@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {
    @Autowired
    FeedbackService feedbackService;

    @PreAuthorize(value = "hasAuthority('READ_ALL_PRODUCT')")
    @GetMapping
    public HttpEntity<?> getAll(){
        return ResponseEntity.ok(feedbackService.getAll());
    }

    @PreAuthorize(value = "hasAuthority('READ_ONE_PRODUCT')")
    @GetMapping("/{id}")
    public HttpEntity<?> get(@PathVariable(value = "id") Integer id){
        Feedback feedback = feedbackService.get(id);
        return ResponseEntity.status(feedback == null ? HttpStatus.OK: HttpStatus.NOT_FOUND).body(feedback);
    }

    @PreAuthorize(value = "hasAuthority('ADD_PRODUCT')")
    @PostMapping
    public HttpEntity<?> add(@RequestBody FeedbackT feedbackT){
        ApiResponse apiResponse = feedbackService.add(feedbackT);
        return ResponseEntity.status(apiResponse.isSuccess()? HttpStatus.CREATED:HttpStatus.CONFLICT).body(apiResponse);
    }

    @PreAuthorize(value = "hasAuthority('EDIT_PRODUCT')")
    @PutMapping("/{id}")
    public HttpEntity<?> edit(@RequestBody FeedbackT feedbackT, @PathVariable Integer id){
        ApiResponse apiResponse = feedbackService.edit(id, feedbackT);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(apiResponse);
    }

    @PreAuthorize(value = "hasAuthority('DELETE_PRODUCT')")
    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id){
        ApiResponse apiResponse = feedbackService.delete(id);
        return  ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.NO_CONTENT : HttpStatus.NOT_FOUND).body(apiResponse);
    }


}
