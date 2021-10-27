package com.example.filedemo.payload;

import com.example.filedemo.payload.UploadFileResponse;

import java.util.ArrayList;
import java.util.List;

public class ImageUriResponse {

    private List<UploadFileResponse> imageslink;

    public ImageUriResponse() {
        imageslink = new ArrayList<>();
    }

    public ImageUriResponse(List downloadUriList) {
        imageslink = downloadUriList;
    }

    public  void addLink(UploadFileResponse link){
        imageslink.add(link);
    }

    public List<UploadFileResponse> getImageslink() {
        return imageslink;
    }

    public void setImageslink(List<UploadFileResponse> imageslink) {
        this.imageslink = imageslink;
    }
}
