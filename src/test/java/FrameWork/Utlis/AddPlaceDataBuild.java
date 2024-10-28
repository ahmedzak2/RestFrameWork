package FrameWork.Utlis;


import Pages.java.pojoAddPlaceAPI.AddPlace;
import Pages.java.pojoAddPlaceAPI.Location;

import java.util.Arrays;

public class AddPlaceDataBuild {
    public static AddPlace addPlace(String language, String address, String name, String phoneNumber){
        AddPlace place = new AddPlace();

        place.setAccuracy(50);
        place.setLanguage(language);
        place.setPhone_number(phoneNumber);
        place.setWebsite("https://rahulshettyacademy.com");
        place.setAddress(address);
        place.setName(name);
        Location location = new Location();
        location.setLat(22);
        location.setLng(50);
        place.setLocation(location);
        place.setTypes(Arrays.asList("male", "female", "alien"));

return place;
    }

}
