package uz.pdp.appweek2task2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appweek2task2.entity.Category;
import uz.pdp.appweek2task2.entity.Product;
import uz.pdp.appweek2task2.payloads.ApiResponse;
import uz.pdp.appweek2task2.payloads.ProductT;
import uz.pdp.appweek2task2.service.ProductService;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @PreAuthorize(value = "hasAuthority('READ_ALL_PRODUCT')")
    @GetMapping
    public HttpEntity<?> getAll(){
        return ResponseEntity.ok(productService.getAll());
    }

    @PreAuthorize(value = "hasAuthority('READ_ONE_PRODUCT')")
    @GetMapping("/{id}")
    public HttpEntity<?> get(@PathVariable(value = "id") Integer id){
        Product product = productService.get(id);
        return ResponseEntity.status(product == null ? HttpStatus.OK: HttpStatus.NOT_FOUND).body(product);
    }

    @PreAuthorize(value = "hasAuthority('ADD_PRODUCT')")
    @PostMapping
    public HttpEntity<?> add(@RequestBody ProductT productT){
        ApiResponse apiResponse = productService.add(productT);
        return ResponseEntity.status(apiResponse.isSuccess()? HttpStatus.CREATED:HttpStatus.CONFLICT).body(apiResponse);
    }

    @PreAuthorize(value = "hasAuthority('EDIT_PRODUCT')")
    @PutMapping("/{id}")
    public HttpEntity<?> edit(@RequestBody ProductT productT, @PathVariable Integer id){
        ApiResponse apiResponse = productService.edit(id, productT);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(apiResponse);
    }

    @PreAuthorize(value = "hasAuthority('DELETE_PRODUCT')")
    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id){
        ApiResponse apiResponse = productService.delete(id);
        return  ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.NO_CONTENT : HttpStatus.NOT_FOUND).body(apiResponse);
    }


}
