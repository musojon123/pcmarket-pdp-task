package uz.pdp.appweek2task2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appweek2task2.entity.Category;
import uz.pdp.appweek2task2.entity.VideoLink;
import uz.pdp.appweek2task2.payloads.ApiResponse;
import uz.pdp.appweek2task2.repository.VideoLinkRepository;

import java.util.List;
import java.util.Optional;

@Service
public class VideoLinkService {
    @Autowired
    VideoLinkRepository videoLinkRepository;

    public List<VideoLink> getAll(){
        return videoLinkRepository.findAll();
    }

    public VideoLink getById(Integer id){
        Optional<VideoLink> videoLinkRepositoryById = videoLinkRepository.findById(id);
        if (videoLinkRepositoryById.isEmpty())
            return null;
        return videoLinkRepositoryById.get();
    }

    public ApiResponse add(VideoLink videoLink){
        if (videoLinkRepository.existsByVideoLink(videoLink.getVideoLink()))
            return new ApiResponse(false, "Already exist") ;
        VideoLink savedVideoLink = videoLinkRepository.save(videoLink);
        return new ApiResponse(true, "Successfully added", videoLink.getId());
    }

    public ApiResponse edit(Integer id, VideoLink videoLink){
        if (videoLinkRepository.existsByVideoLinkAndIdNot(videoLink.getVideoLink(), id))
            return new ApiResponse(false, "Already exist");
        Optional<VideoLink> optionalCategory = videoLinkRepository.findById(id);
        if (optionalCategory.isEmpty())
            return new ApiResponse(false, "No such videoLink with this given id", id);
        VideoLink editingCategory = optionalCategory.get();
        editingCategory.setVideoLink(videoLink.getVideoLink());
        editingCategory.setActive(videoLink.getActive());
        videoLinkRepository.save(editingCategory);
        return new ApiResponse(true, "Successfully edited", id);
    }

    public ApiResponse delete(Integer id){
        if (!videoLinkRepository.existsById(id))
            return new ApiResponse(false, "No such category with this given id", id);
        videoLinkRepository.deleteById(id);
        return new ApiResponse(true, "Successfully deleted", id);
    }

}
