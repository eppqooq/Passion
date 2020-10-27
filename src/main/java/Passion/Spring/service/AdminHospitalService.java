package Passion.Spring.service;

import Passion.Spring.controller.HospitalForm;
import Passion.Spring.domain.Hospital;
import Passion.Spring.repository.HospitalRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
@Transactional
public class AdminHospitalService
{
    private final HospitalRepository hospitalRepository;

    public AdminHospitalService(HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }


    public List<Hospital> findHospitals()
    {
        return hospitalRepository.findAll();
    }
    public Optional<Hospital> findByNo(Long no)
    {
        return hospitalRepository.findByNo(no);
    }

    public Hospital editFormHospitalObject(Hospital hospital, HospitalForm hospitalForm)
    {
        String tel = hospitalForm.getTel1() + hospitalForm.getTel2() + hospitalForm.getTel3();

        hospital.setNo(hospitalForm.getNo());
        hospital.setName(hospitalForm.getName());
        hospital.setLocation(hospitalForm.getLocation());
        hospital.setInformation1(hospitalForm.getInformation1());
        hospital.setInformation2(hospitalForm.getInformation2());
        hospital.setKind_no(hospitalForm.getKind_no());
        hospital.setTel(tel);
        return hospital;
    }
    public void updateHospital(Hospital hospital)
    {
        hospitalRepository.save(hospital);
    }

    public void deleteByNo(Long no)
    {
        hospitalRepository.deleteByNo(no);
    }
}
