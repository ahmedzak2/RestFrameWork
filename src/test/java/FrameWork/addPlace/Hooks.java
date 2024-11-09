package FrameWork.addPlace;

import FrameWork.addPlace.validatePlace.BaseTest;
import FrameWork.addPlace.validatePlace.ValidatePlaceStepDef;
import io.cucumber.java.Before;

import java.io.IOException;

public class Hooks extends BaseTest {

	@Before("@DeletePlace")
	public void beforeScenario() throws IOException
	{		//execute this code only when place id is null
		//write a code that will give you place id

		ValidatePlaceStepDef m =new ValidatePlaceStepDef();
		if(place_Id==null)
		{
		m.addPlacePayLoadWithAndAndAnd("Shetty", "French", "Asia","(+91) 983 893 3937");
		m.userCallsWithHttpRequest("/maps/api/place/add/json", "POST");
		m.verify_place_Id_created_maps_to_using("Shetty", "getPlaceAPI");
		}
		
		

	}
}
