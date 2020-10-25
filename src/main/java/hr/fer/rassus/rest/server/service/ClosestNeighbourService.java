package hr.fer.rassus.rest.server.service;

import hr.fer.rassus.rest.server.model.UserAddress;

public interface ClosestNeighbourService {
   UserAddress searchNeighbour(String username);
}
