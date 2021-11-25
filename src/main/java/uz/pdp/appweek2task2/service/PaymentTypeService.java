package uz.pdp.appweek2task2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appweek2task2.entity.Category;
import uz.pdp.appweek2task2.entity.PaymentType;
import uz.pdp.appweek2task2.payloads.ApiResponse;
import uz.pdp.appweek2task2.repository.PaymentTypeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentTypeService {
    @Autowired
    PaymentTypeRepository paymentTypeRepository;

    public List<PaymentType> getAll(){
        return paymentTypeRepository.findAll();
    }

    public PaymentType get(Integer id){
        Optional<PaymentType> optionalCategory = paymentTypeRepository.findById(id);
        if (optionalCategory.isEmpty())
            return null;
        return optionalCategory.get();
    }

    public ApiResponse add(PaymentType paymentType){
        if (paymentTypeRepository.existsByName(paymentType.getName()))
            return new ApiResponse(false, "Already exist") ;
        PaymentType savePaymentType = paymentTypeRepository.save(paymentType);
        return new ApiResponse(true, "Successfully added", savePaymentType.getId());
    }

    public ApiResponse edit(Integer id, PaymentType paymentType){
        if (paymentTypeRepository.existsByNameAndIdNot(paymentType.getName(), id))
            return new ApiResponse(false, "Already exist");
        Optional<PaymentType> optionalPaymentType = paymentTypeRepository.findById(id);
        if (optionalPaymentType.isEmpty())
            return new ApiResponse(false, "No such paymentType with this given id", id);
        PaymentType editingCategory = optionalPaymentType.get();
        editingCategory.setName(paymentType.getName());
        editingCategory.setActive(paymentType.getActive());
        paymentTypeRepository.save(editingCategory);
        return new ApiResponse(true, "Successfully edited", id);
    }

    public ApiResponse delete(Integer id){
        if (!paymentTypeRepository.existsById(id))
            return new ApiResponse(false, "No such category with this given id", id);
        paymentTypeRepository.deleteById(id);
        return new ApiResponse(true, "Successfully deleted", id);
    }

}
