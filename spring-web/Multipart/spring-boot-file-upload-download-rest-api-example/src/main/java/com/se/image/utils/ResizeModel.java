package com.se.image.utils;

public class ResizeModel {
    private int dx;
    private int dy;
    private int previewWidth;
    private int previewHeight;
    private int croppedWidth;
    private int croppedHeight;

    public ResizeModel() {

    }

    public ResizeModel(int dx, int dy, int previewWidth, int previewHeight, int coppedWidth, int croppedHeight) {
        this.dx = dx;
        this.dy = dy;
        this.previewWidth = previewWidth;
        this.previewHeight = previewHeight;
        this.croppedWidth = coppedWidth;
        this.croppedHeight = croppedHeight;
    }


    //    public ResizeModel(int dx, int dy, int preview_width, int preview_height, int width, int recomendedHeight) {
//    }

    public int getDx() {
        return dx;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public int getDy() {
        return dy;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }

    public int getPreviewWidth() {
        return previewWidth;
    }

    public void setPreviewWidth(int previewWidth) {
        this.previewWidth = previewWidth;
    }

    public int getPreviewHeight() {
        return previewHeight;
    }

    public void setPreviewHeight(int previewHeight) {
        this.previewHeight = previewHeight;
    }

    public int getCroppedWidth() {
        return croppedWidth;
    }

    public void setCroppedWidth(int croppedWidth) {
        this.croppedWidth = croppedWidth;
    }

    public int getCroppedHeight() {
        return croppedHeight;
    }

    public void setCroppedHeight(int croppedHeight) {
        this.croppedHeight = croppedHeight;
    }
}
