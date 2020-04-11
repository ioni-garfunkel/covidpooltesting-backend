package org.covidpooltesting.probability;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("1/probability")
public class ProbabilityResource {

	private final ProbabilityService service;

	@Inject
	ProbabilityResource(ProbabilityService service) {
		this.service = service;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ProbabilityResponse test(ProbabilityRequest request) {
		return service.calculate(request);
	}
}
