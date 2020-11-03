package Passion.Spring.service;

import Passion.Spring.repository.HospitalRepository;
import org.springframework.stereotype.Service;

@Service
public class HospitalService extends AdminHospitalService{

    public HospitalService(HospitalRepository hospitalRepository) {
        super(hospitalRepository);
    }
}
