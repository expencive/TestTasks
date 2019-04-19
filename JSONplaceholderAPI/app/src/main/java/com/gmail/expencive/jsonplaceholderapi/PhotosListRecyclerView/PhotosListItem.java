package com.gmail.expencive.jsonplaceholderapi.PhotosListRecyclerView;

public class PhotosListItem {

    private String mImageUrl;
    private String mTitle;
    private int mPhotoId;

    public PhotosListItem(String imageUrl, String title, int photoId) {
        mImageUrl = imageUrl;
        mTitle = title;
        mPhotoId = photoId;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public String getTitle() {
        return mTitle;
    }

    public int getPhotoId() {
        return mPhotoId;
    }
}
