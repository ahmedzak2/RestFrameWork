package FrameWork.Utlis;


import Pages.java.pojoAddPlaceAPI.AddPlace;
import Pages.java.pojoAddPlaceAPI.Location;

import java.util.Arrays;

public class AddPlaceDataBuild {
    public static AddPlace addPlace(String name,String language, String address , String phoneNumber){
        AddPlace place = new AddPlace();

        place.setAccuracy(50);
        place.setAddress(address);
        place.setLanguage(language);
        place.setPhone_number(phoneNumber);
        place.setWebsite("https://rahulshettyacademy.com");
        place.setName(name);
        Location location = new Location();
        location.setLat(22);
        location.setLng(50);
        place.setLocation(location);
        place.setTypes(Arrays.asList("male", "female", "alien"));

return place;
    }

}
