package org.covidpooltesting.probability;

import java.util.Objects;

import org.covidpooltesting.beans.Symptoms;

public class ProbabilityRequest {

	private int age;
	private Symptoms symptoms;

	public ProbabilityRequest(int age, Symptoms symptoms) {
		this.age = age;
		this.symptoms = Objects.requireNonNull(symptoms);
	}

	public int getAge() {
		return age;
	}

	public Symptoms getSymptoms() {
		return symptoms;
	}

}
