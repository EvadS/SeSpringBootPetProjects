package com.se.sample.mutation;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.se.sample.dao.entity.Vehicle;
import com.se.sample.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * распознаватель мутаций
 */
@Component
public class VehicleMutation implements GraphQLMutationResolver {
    @Autowired
    private VehicleService vehicleService;

    public Vehicle createVehicle(final String type, final String modelCode, final String brandName, final String launchDate) {
        return this.vehicleService.createVehicle(type, modelCode, brandName, launchDate);
    }
}