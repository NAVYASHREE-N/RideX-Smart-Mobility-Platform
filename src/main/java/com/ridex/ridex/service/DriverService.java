package com.ridex.ridex.service;

import com.ridex.ridex.entity.Driver;
import com.ridex.ridex.repository.DriverRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class DriverService {

    private final DriverRepository driverRepository;

    public DriverService(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    // Get all drivers
    public List<Driver> getAllDrivers() {
        return driverRepository.findAll();
    }

    // Get driver by ID
    public Optional<Driver> getDriverById(Long id) {
        return driverRepository.findById(id);
    }

    // Add new driver
    public Driver addDriver(Driver driver) {
        return driverRepository.save(driver);
    }

    // Update driver
    public Driver updateDriver(Long id, Driver updatedDriver) {
        Driver existing = driverRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Driver not found!"));
        existing.setFullName(updatedDriver.getFullName());
        existing.setPhone(updatedDriver.getPhone());
        existing.setVehicleModel(updatedDriver.getVehicleModel());
        existing.setVehicleNumber(updatedDriver.getVehicleNumber());
        existing.setStatus(updatedDriver.getStatus());
        return driverRepository.save(existing);
    }

    // Delete driver
    public void deleteDriver(Long id) {
        driverRepository.deleteById(id);
    }

    // Get available drivers
    public List<Driver> getAvailableDrivers() {
        return driverRepository.findByStatus(Driver.DriverStatus.AVAILABLE);
    }

    // Count available drivers
    public long countAvailableDrivers() {
        return driverRepository.countByStatus(Driver.DriverStatus.AVAILABLE);
    }
}
