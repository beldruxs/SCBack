// ServiceService.java
package com.jedos.jedosbackend.service;

import com.jedos.jedosbackend.dto.ServiceDTO;
import com.jedos.jedosbackend.model.ServiceEntity;
import com.jedos.jedosbackend.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ServiceService {

    @Autowired
    private ServiceRepository serviceRepository;

    public List<ServiceDTO> getAllServices() {
        List<ServiceEntity> services = serviceRepository.findAll();
        return services.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private ServiceDTO convertToDTO(ServiceEntity serviceEntity) {
        ServiceDTO serviceDTO = new ServiceDTO();
        serviceDTO.setId(serviceEntity.getId());
        serviceDTO.setTitle(serviceEntity.getTitle());
        serviceDTO.setTitulo(serviceEntity.getTitulo());
        serviceDTO.setDescription(serviceEntity.getDescription());
        serviceDTO.setDescripcion(serviceEntity.getDescripcion());
        serviceDTO.setPrice(serviceEntity.getPrice());
        serviceDTO.setReccurente(serviceEntity.getReccurente());
        return serviceDTO;
    }

    public Optional<ServiceDTO> getServiceById(Integer id) {
        Optional<ServiceEntity> serviceEntity = serviceRepository.findById(id);
        return serviceEntity.map(this::convertToDTO);
    }
}