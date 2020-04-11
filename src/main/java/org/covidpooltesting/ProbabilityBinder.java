package org.covidpooltesting;

import javax.inject.Singleton;
import javax.ws.rs.ext.Provider;

import org.covidpooltesting.probability.ProbabilityService;
import org.covidpooltesting.probability.calculator.DummyProbabilityCalculator;
import org.covidpooltesting.probability.calculator.ProbabilityCalculator;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

@Provider
public class ProbabilityBinder extends AbstractBinder {

	@Override
	protected void configure() {
		bind(ProbabilityService.class).to(ProbabilityService.class).in(Singleton.class);
		bind(DummyProbabilityCalculator.class).to(ProbabilityCalculator.class);
	}

}
