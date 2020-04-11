package org.covidpooltesting.probability;

import javax.inject.Inject;

import org.covidpooltesting.probability.calculator.ProbabilityCalculator;

public class ProbabilityService {

	private final ProbabilityCalculator probabilityCalculator;

	@Inject
	ProbabilityService(ProbabilityCalculator probabilityCalculator) {
		this.probabilityCalculator = probabilityCalculator;
	}

	public ProbabilityResponse calculate(ProbabilityRequest request) {
		double probability = probabilityCalculator.getProbability(request.getAge(), request.getSymptoms());
		return new ProbabilityResponse(request.getAge(), request.getSymptoms(), probability);
	}

}
