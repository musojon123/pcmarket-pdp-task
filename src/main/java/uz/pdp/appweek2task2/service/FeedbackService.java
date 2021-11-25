package uz.pdp.appweek2task2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appweek2task2.entity.Category;
import uz.pdp.appweek2task2.entity.Feedback;
import uz.pdp.appweek2task2.payloads.ApiResponse;
import uz.pdp.appweek2task2.payloads.FeedbackT;
import uz.pdp.appweek2task2.repository.FeedbackRepository;
import uz.pdp.appweek2task2.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class FeedbackService {
    @Autowired
    FeedbackRepository feedbackRepository;
    @Autowired
    ProductRepository productRepository;

    public List<Feedback> getAll(){
        return feedbackRepository.findAll();
    }

    public Feedback get(Integer id){
        Optional<Feedback> optionalFeedback = feedbackRepository.findById(id);
        if (optionalFeedback.isEmpty())
            return null;
        return optionalFeedback.get();
    }

    public ApiResponse add(FeedbackT feedbackT){
        Feedback feedback = new Feedback();
        feedback.setFullName(feedbackT.getFullName());
        feedback.setEmail(feedbackT.getEmail());
        feedback.setText(feedbackT.getText());
        feedback.setProduct(productRepository.getById(feedbackT.getProductId()));
        Feedback savedFeedback = feedbackRepository.save(feedback);

        return new ApiResponse(true, "Successfully added", savedFeedback.getId());
    }

    public ApiResponse edit(Integer id, FeedbackT feedbackT){
        Optional<Feedback> optionalFeedback = feedbackRepository.findById(id);
        if (optionalFeedback.isEmpty())
            return new ApiResponse(false, "No such feedback this given id", id);
        Feedback feedback = optionalFeedback.get();
        feedback.setFullName(feedbackT.getFullName());
        feedback.setEmail(feedbackT.getEmail());
        feedback.setText(feedbackT.getText());
        feedback.setProduct(productRepository.getById(feedbackT.getProductId()));
        return new ApiResponse(true, "Successfully edited", id);
    }

    public ApiResponse delete(Integer id){
        if (!feedbackRepository.existsById(id))
            return new ApiResponse(false, "No such category with this given id", id);
        feedbackRepository.deleteById(id);
        return new ApiResponse(true, "Successfully deleted", id);
    }

}
