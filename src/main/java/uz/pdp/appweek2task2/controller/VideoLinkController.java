package uz.pdp.appweek2task2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appweek2task2.entity.Category;
import uz.pdp.appweek2task2.entity.VideoLink;
import uz.pdp.appweek2task2.payloads.ApiResponse;
import uz.pdp.appweek2task2.service.VideoLinkService;

@RestController
@RequestMapping("/api/video-link")
public class VideoLinkController {
    @Autowired
    VideoLinkService videoLinkService;

    @PreAuthorize(value = "hasAuthority('READ_ALL_PRODUCT')")
    @GetMapping
    public HttpEntity<?> getAll(){
        return ResponseEntity.ok(videoLinkService.getAll());
    }

    @PreAuthorize(value = "hasAuthority('READ_ONE_PRODUCT')")
    @GetMapping("/{id}")
    public HttpEntity<?> get(@PathVariable(value = "id") Integer id){
        VideoLink videoLink = videoLinkService.getById(id);
        return ResponseEntity.status(videoLink == null ? HttpStatus.OK: HttpStatus.NOT_FOUND).body(videoLink);
    }

    @PreAuthorize(value = "hasAuthority('ADD_PRODUCT')")
    @PostMapping
    public HttpEntity<?> add(@RequestBody VideoLink videoLink){
        ApiResponse apiResponse = videoLinkService.add(videoLink);
        return ResponseEntity.status(apiResponse.isSuccess()? HttpStatus.CREATED:HttpStatus.CONFLICT).body(apiResponse);
    }

    @PreAuthorize(value = "hasAuthority('EDIT_PRODUCT')")
    @PutMapping("/{id}")
    public HttpEntity<?> edit(@RequestBody VideoLink videoLink, @PathVariable Integer id){
        ApiResponse apiResponse = videoLinkService.edit(id, videoLink);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(apiResponse);
    }

    @PreAuthorize(value = "hasAuthority('DELETE_PRODUCT')")
    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id){
        ApiResponse apiResponse = videoLinkService.delete(id);
        return  ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.NO_CONTENT : HttpStatus.NOT_FOUND).body(apiResponse);
    }


}
