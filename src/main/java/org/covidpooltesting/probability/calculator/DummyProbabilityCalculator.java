package org.covidpooltesting.probability.calculator;

import org.covidpooltesting.beans.Symptoms;

public class DummyProbabilityCalculator implements ProbabilityCalculator {

	public double getProbability(int age, Symptoms symptoms) {
		return Math.random();
	}

}
