package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {

    @Autowired
    BlogRepository blogRepository2;
    @Autowired
    ImageRepository imageRepository2;

    public Image addImage(Integer blogId, String description, String dimensions){
        //add an image to the blog
        Blog blog=blogRepository2.findById(blogId).get();
        Image image=new Image();
        image.setBlog(blog);
        image.setDescription(description);
        image.setDimensions(dimensions);
        imageRepository2.save(image);
        return image;
    }

    public void deleteImage(Integer id){
        imageRepository2.deleteById(id);
    }

    public int countImagesInScreen(Integer id, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`
        Image image=imageRepository2.findById(id).get();
        String[] dim=image.getDimensions().split("X");
        int inLen=Integer.parseInt(dim[0]);
        int inWid=Integer.parseInt(dim[1]);

        String[] dim2=screenDimensions.split("X");
        int outLen=Integer.parseInt(dim2[0]);
        int outWid=Integer.parseInt(dim2[1]);
        return (outLen/inLen)*(outWid/inWid);
    }
}
