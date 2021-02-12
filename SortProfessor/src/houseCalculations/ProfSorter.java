package houseCalculations;

import java.util.ArrayList;

public class ProfSorter {

	public static String determineHouse(ArrayList<Integer> surveyResults) {
		//0 is gryffindor
		//1 is slytherin
		//2 is ravenclaw
		//3 is hufflepuff
		int highestHouse = 0;
		int highestHousePoints = 0;
		int gryffindor = 0;
		int slytherin = 0;
		int ravenclaw = 0;
		int hufflepuff = 0;
		for (int i = 0; i < surveyResults.size(); i++) {
			int questionSelection = surveyResults.get(i);
			switch(questionSelection) {
			case 0:
				gryffindor++;
				if (gryffindor > highestHousePoints) {
					highestHouse = 0;
					highestHousePoints = gryffindor;
				}
				break;
			case 1:
				slytherin++;
				if (slytherin > highestHousePoints) {
					highestHouse = 1;
					highestHousePoints = slytherin;
				}
				break;
			case 2:
				ravenclaw++;
				if (ravenclaw > highestHousePoints) {
					highestHouse = 2;
					highestHousePoints = ravenclaw;
				}
				break;
			case 3:
				hufflepuff++;
				if (hufflepuff > highestHousePoints) {
					highestHouse = 3;
					highestHousePoints = hufflepuff;
				}
				break;
			}
		}
		
		switch(highestHouse) {
		case 0:
			return "Gryffindor";
		case 1:
			return "Slytherin";
		case 2:
			return "Ravenclaw";
		case 3:
			return "Hufflepuff";
		}
		System.out.println("something went wrong in deterimenHouse()");
		Exception e = new Exception();
		e.printStackTrace();
		return "whoops";
		
	}
	
	
	

}
