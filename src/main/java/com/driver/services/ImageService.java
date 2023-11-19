package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImageService {

    @Autowired
    BlogRepository blogRepository2;
    @Autowired
    ImageRepository imageRepository2;

    public Image addImage(Integer blogId, String description, String dimensions){
        //add an image to the blog
        Image image = new Image();
        image.setDescription(description);
        image.setDimensions(dimensions);

        Optional<Blog> blogOp = blogRepository2.findById(blogId);
        if(blogOp.isPresent()){
            Blog blog = blogOp.get();
            image.setBlog(blog);
            blog.getImageList().add(image);
            blogRepository2.save(blog);
            return image;
        }
        return null;
    }

    public void deleteImage(Integer id){
        imageRepository2.deleteById(id);
    }

    public int countImagesInScreen(Integer id, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`
        int count = -1;
        Optional<Image> imageOp = imageRepository2.findById(id);
        if(imageOp.isPresent()){
            String imgDimensions = imageOp.get().getDimensions();
            String[] arr = imgDimensions.split("X"); // 2X3
            int inLen = Integer.parseInt(arr[0]);
            int inWid = Integer.parseInt(arr[1]);
//            int inArea = inLen * inWid;

            arr = screenDimensions.split("X");  // 9X12
            int outLen = Integer.parseInt(arr[0]);
            int outWid = Integer.parseInt(arr[1]);
//            int outArea = outLen * outWid;

            int c1 = (outLen/inLen) * (outWid/inWid);

            count = c1;
        }
        return count;
    }
}
