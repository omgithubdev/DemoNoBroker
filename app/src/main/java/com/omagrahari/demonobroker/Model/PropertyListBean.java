package com.omagrahari.demonobroker.Model;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;

/**
 * Created by omprakash on 29/06/18.
 */

public class PropertyListBean {
    @Expose
    ArrayList<Data> data = new ArrayList<>();

    public class Data {
        @Expose
        String id;
        @Expose
        String type;
        @Expose
        String title;
        @Expose
        String description;
        @Expose
        String rent;
        @Expose
        String buildingType;
        @Expose
        String propertySize;
        @Expose
        String furnishing;
        @Expose
        String bathroom;
        @Expose
        ArrayList<Photos> photos = new ArrayList<>();

        public class Photos {
            @Expose
            ImageMap imagesMap;
            @Expose
            boolean displayPic;

            public class ImageMap {
                @Expose
                String thumbnail;
                @Expose
                String original;
                @Expose
                String large;
                @Expose
                String medium;

                public String getThumbnail() {
                    return thumbnail;
                }

                public void setThumbnail(String thumbnail) {
                    this.thumbnail = thumbnail;
                }

                public String getOriginal() {
                    return original;
                }

                public void setOriginal(String original) {
                    this.original = original;
                }

                public String getLarge() {
                    return large;
                }

                public void setLarge(String large) {
                    this.large = large;
                }

                public String getMedium() {
                    return medium;
                }

                public void setMedium(String medium) {
                    this.medium = medium;
                }
            }

            public ImageMap getImagesMap() {
                return imagesMap;
            }

            public void setImagesMap(ImageMap imagesMap) {
                this.imagesMap = imagesMap;
            }

            public boolean isDisplayPic() {
                return displayPic;
            }

            public void setDisplayPic(boolean displayPic) {
                this.displayPic = displayPic;
            }
        }

        public ArrayList<Photos> getPhotos() {
            return photos;
        }

        public void setPhotos(ArrayList<Photos> photos) {
            this.photos = photos;
        }

        public String getBathroom() {
            return bathroom;
        }

        public void setBathroom(String bathroom) {
            this.bathroom = bathroom;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getRent() {
            return rent;
        }

        public void setRent(String rent) {
            this.rent = rent;
        }

        public String getBuildingType() {
            return buildingType;
        }

        public void setBuildingType(String buildingType) {
            this.buildingType = buildingType;
        }

        public String getPropertySize() {
            return propertySize;
        }

        public void setPropertySize(String propertySize) {
            this.propertySize = propertySize;
        }

        public String getFurnishing() {
            return furnishing;
        }

        public void setFurnishing(String furnishing) {
            this.furnishing = furnishing;
        }
    }

    public ArrayList<Data> getData() {
        return data;
    }

    public void setData(ArrayList<Data> data) {
        this.data = data;
    }
}
