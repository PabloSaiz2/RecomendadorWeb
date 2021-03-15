package com.recommender.app.model.forms;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class QuestionaryForm {

	private static Set<String> HOBBIES;

	private static Set<String> LANGUAGES;

	private static Map<String, String> HOBBIES_CATEGORIES_CONVERTER;
	private static boolean loaded = false;
	private int ageUser;
	private String hobbies;
	private String language;
	private int lowerBound;
	private int upperBound;

	public QuestionaryForm() {
		ageUser = 18;
		hobbies = "Videogames,Electronics,Movies";
		language = "English";
		lowerBound = 30;
		upperBound = 60;
	}

	private static void initializeLanguages() {
		QuestionaryForm.LANGUAGES = new HashSet<String>();
		try (FileReader input = new FileReader(new File("src/main/resources/static/languages.txt"));
				BufferedReader reader = new BufferedReader(input)) {
			int numLanguages = Integer.parseInt(reader.readLine());
			for (int i = 0; i < numLanguages; ++i)
				QuestionaryForm.LANGUAGES.add(reader.readLine());
		} catch (IOException | NumberFormatException e) {
			e.printStackTrace();
		}

	}

	private static void initializeConverter() {
		// TODO Auto-generated method stub
		QuestionaryForm.HOBBIES_CATEGORIES_CONVERTER = new HashMap<String, String>();
		try (FileReader input = new FileReader(new File("src/main/resources/static/converterHobbies.txt"));
				BufferedReader reader = new BufferedReader(input)) {
			int numCategories = Integer.parseInt(reader.readLine());
			for (int i = 0; i < numCategories; ++i) {
				String hobbieCategory = reader.readLine();
				QuestionaryForm.HOBBIES_CATEGORIES_CONVERTER.put(hobbieCategory, hobbieCategory);
				int hobbiesInCategory = Integer.parseInt(reader.readLine());
				for (int j = 0; j < hobbiesInCategory; ++j) {
					QuestionaryForm.HOBBIES_CATEGORIES_CONVERTER.put(reader.readLine(), hobbieCategory);
				}
			}
		} catch (IOException | NumberFormatException e) {
			e.printStackTrace();
		}
	}

	private static void initializeHobbies()  {
		QuestionaryForm.HOBBIES = new HashSet<String>();
		try (FileReader input = new FileReader(new File("src/main/resources/static/hobbies.txt"));
				BufferedReader reader = new BufferedReader(input)) {
			int numHobbies = Integer.parseInt(reader.readLine());
			for (int i = 0; i < numHobbies; ++i)
				QuestionaryForm.HOBBIES.add(reader.readLine());
		} catch (IOException | NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void initializeQuestionaries() {
		if (!loaded) {
			initializeLanguages();
			initializeHobbies();
			initializeConverter();
			loaded = true;
		}
	}

	public int getAgeUser() {
		return ageUser;
	}

	public void setAgeUser(int ageUser) {
		this.ageUser = ageUser;
	}

	public List<String> getHobbiesCategories() {
		List<String> output = new ArrayList<String>();
		for (String hobbie : hobbies.trim().split(",")) {
			output.add(QuestionaryForm.HOBBIES_CATEGORIES_CONVERTER.get(hobbie.trim()));
		}
		return output;
	}
	public static List<String> getCategories(String hobbies) {
		List<String> output = new ArrayList<String>();
		for (String hobbie : hobbies.trim().split(",")) {
			output.add(QuestionaryForm.HOBBIES_CATEGORIES_CONVERTER.get(hobbie.trim()));
		}
		return output;
	}
	public String getHobbies() {
		return hobbies;
	}

	public void setHobbies(String hobbies) {

		this.hobbies = hobbies;

	}

	private boolean validHobbies() {
		boolean succesfull = true;
		if (hobbies != null) {
			String[] formattedHobbies = hobbies.trim().split(",");
			int i = 0;
			while (succesfull && i < formattedHobbies.length) {
				succesfull = QuestionaryForm.HOBBIES.contains(formattedHobbies[i].trim());
				++i;
			}
		}
		else
			succesfull = false;
		return succesfull;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	private boolean validLanguage() {
		// TODO Auto-generated method stub
		if (language != null)
			return QuestionaryForm.LANGUAGES.contains(language.trim());
		else
			return false;
	}

	public int getLowerBound() {
		return lowerBound;
	}

	public void setLowerBound(int lowerBound) {

		this.lowerBound = lowerBound;
	}

	public int getUpperBound() {
		return upperBound;
	}

	public void setUpperBound(int upperBound) {

		this.upperBound = upperBound;

	}

	public boolean isValid() {
		return lowerBound<=upperBound&&upperBound>0&&lowerBound > 0 && validLanguage() && validHobbies() && ageUser > 0 && ageUser < 150;
	}

	public String toString() {
		String output = "";
		output+=lowerBound+" ";
		output+=upperBound+" ";
		output+=hobbies+" ";
		output+=language+" ";
		output+=ageUser;
		return output;
	}
}
