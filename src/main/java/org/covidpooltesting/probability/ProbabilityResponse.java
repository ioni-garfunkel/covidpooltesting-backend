package org.covidpooltesting.probability;

import java.util.Objects;

import org.covidpooltesting.beans.Symptoms;

public class ProbabilityResponse {

	private final int age;
	private final Symptoms symptoms;
	private final double probability;

	public ProbabilityResponse(int age, Symptoms symptoms, double probability) {
		this.age = age;
		this.symptoms = Objects.requireNonNull(symptoms);
		this.probability = probability;
	}

}
