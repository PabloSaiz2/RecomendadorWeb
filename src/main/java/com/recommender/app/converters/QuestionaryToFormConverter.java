package com.recommender.app.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.recommender.app.model.Questionary;
import com.recommender.app.model.forms.QuestionaryForm;
@Component
public class QuestionaryToFormConverter implements Converter<Questionary,QuestionaryForm>{

	@Override
	public QuestionaryForm convert(Questionary source) {
		QuestionaryForm output = new QuestionaryForm();
		output.setAgeUser(source.getAgeUser());
		output.setHobbies(source.getHobbies());
		output.setLanguage(source.getLanguage());
		output.setLowerBound(output.getLowerBound());
		output.setUpperBound(source.getUpperBound());
		return output;
	}

}
