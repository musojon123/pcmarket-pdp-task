package uz.pdp.appweek2task2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appweek2task2.entity.Category;
import uz.pdp.appweek2task2.entity.PaymentType;
import uz.pdp.appweek2task2.payloads.ApiResponse;
import uz.pdp.appweek2task2.service.PaymentTypeService;

@RestController
@RequestMapping("/api/payment-type")
public class PaymentTypeController {
    @Autowired
    PaymentTypeService paymentTypeService;

    @PreAuthorize(value = "hasAuthority('READ_ALL_PRODUCT')")
    @GetMapping
    public HttpEntity<?> getAll(){
        return ResponseEntity.ok(paymentTypeService.getAll());
    }

    @PreAuthorize(value = "hasAuthority('READ_ONE_PRODUCT')")
    @GetMapping("/{id}")
    public HttpEntity<?> get(@PathVariable(value = "id") Integer id){
        PaymentType category = paymentTypeService.get(id);
        return ResponseEntity.status(category == null ? HttpStatus.OK: HttpStatus.NOT_FOUND).body(category);
    }

    @PreAuthorize(value = "hasAuthority('ADD_PRODUCT')")
    @PostMapping
    public HttpEntity<?> add(@RequestBody PaymentType paymentType){
        ApiResponse apiResponse = paymentTypeService.add(paymentType);
        return ResponseEntity.status(apiResponse.isSuccess()? HttpStatus.CREATED:HttpStatus.CONFLICT).body(apiResponse);
    }

    @PreAuthorize(value = "hasAuthority('EDIT_PRODUCT')")
    @PutMapping("/{id}")
    public HttpEntity<?> edit(@RequestBody PaymentType paymentType, @PathVariable Integer id){
        ApiResponse apiResponse = paymentTypeService.edit(id, paymentType);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(apiResponse);
    }

    @PreAuthorize(value = "hasAuthority('DELETE_PRODUCT')")
    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id){
        ApiResponse apiResponse = paymentTypeService.delete(id);
        return  ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.NO_CONTENT : HttpStatus.NOT_FOUND).body(apiResponse);
    }


}
