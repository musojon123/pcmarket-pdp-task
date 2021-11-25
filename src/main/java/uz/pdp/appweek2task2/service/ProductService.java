package uz.pdp.appweek2task2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import uz.pdp.appweek2task2.entity.Category;
import uz.pdp.appweek2task2.entity.PaymentType;
import uz.pdp.appweek2task2.entity.Product;
import uz.pdp.appweek2task2.payloads.ApiResponse;
import uz.pdp.appweek2task2.payloads.ProductT;
import uz.pdp.appweek2task2.repository.AttachmentRepository;
import uz.pdp.appweek2task2.repository.CategoryRepository;
import uz.pdp.appweek2task2.repository.PaymentTypeRepository;
import uz.pdp.appweek2task2.repository.ProductRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    PaymentTypeRepository paymentTypeRepository;
    @Autowired
    AttachmentRepository attachmentRepository;
    @Autowired
    CategoryRepository categoryRepository;


    public List<Product> getAll(){
        return productRepository.findAll();
    }

    public Product get(Integer id){
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isEmpty())
            return null;
        return optionalProduct.get();
    }

    public ApiResponse add(ProductT productT){
        Product product = new Product();
        product.setName(product.getBrandName());
        product.setCategory(categoryRepository.getById(product.getId()));
        product.setBrandName(productT.getBrandName());
        product.setSpecifications(productT.getSpecifications());
        product.setDescription(productT.getDescription());
        product.setAttachment(attachmentRepository.getById(productT.getAttachmentId()));
        product.setMadeOn(productT.getMadeOn());
        product.setPaymentType(paymentTypeRepository.getById(productT.getAttachmentId()));
        product.setActive(productT.getActive());

        Product savedProduct = productRepository.save(product);
        return new ApiResponse(true, "Successfully added", savedProduct.getId());
    }

    public ApiResponse edit(Integer id, ProductT productT){
        Optional<Product> optionalProduct = productRepository.findById(productT.getId());
        if (optionalProduct.isEmpty())
            return new ApiResponse(false, "No such Product with this given id", id);
        Product editingProduct = optionalProduct.get();
        editingProduct.setName(productT.getName());
        editingProduct.setCategory(categoryRepository.getById(productT.getAttachmentId()));
        editingProduct.setBrandName(productT.getBrandName());
        editingProduct.setSpecifications(productT.getSpecifications());
        editingProduct.setDescription(productT.getDescription());
        editingProduct.setAttachment(attachmentRepository.getById(productT.getAttachmentId()));
        editingProduct.setMadeOn(productT.getMadeOn());
        editingProduct.setPaymentType(paymentTypeRepository.getById(productT.getPaymentTypeId()));
        editingProduct.setActive(productT.getActive());

        Product editedProduct = productRepository.save(editingProduct);

        return new ApiResponse(true, "Successfully edited", id);
    }

    public ApiResponse delete(Integer id){
        if (!productRepository.existsById(id))
            return new ApiResponse(false, "No such product with this given id", id);
        productRepository.deleteById(id);
        return new ApiResponse(true, "Successfully deleted", id);
    }

}
