package org.covidpooltesting.probability.calculator;

import org.covidpooltesting.beans.Symptoms;

public interface ProbabilityCalculator {

	double getProbability(int age, Symptoms symptoms);
}
