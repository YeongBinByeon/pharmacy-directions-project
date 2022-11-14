package com.example.pharmacy.repository

import com.example.AbstractIntegrationContainerBaseTest
import com.example.pharmacy.entity.Pharmacy
import org.springframework.beans.factory.annotation.Autowired


// example 패키지 하위에 groovy로 만든 'AbstractIntegrationContainerBaseTest' 파일을 상속받아서 통합테스트 구현
class PharmacyRepositoryTest extends AbstractIntegrationContainerBaseTest {

    @Autowired
    private PharmacyRepository pharmacyRepository

    def setup(){
        pharmacyRepository.deleteAll()
    }

    def "PharmacyRepository save"(){
        given:
        String address = "서울 특별시 성북구 중앙동"
        String name = "은혜 약국"
        double latitude = 36.11
        double longitude = 128.11

        def pharmacy = Pharmacy.builder()
            .pharmacyAddress(address)
            .pharmacyName(name)
            .latitude(latitude)
            .longitude(longitude)
            .build()

        when:
        def result = pharmacyRepository.save(pharmacy)

        then:
        result.getPharmacyAddress() == address
        result.getPharmacyName() == name
        result.getLatitude() == latitude
        result.getLongitude() == longitude
    }

    def "PharmacyRepository saveAll"(){
        given:
        String address = "서울 특별시 성북구 중앙동"
        String name = "은혜 약국"
        double latitude = 36.11
        double longitude = 128.11

        def pharmacy = Pharmacy.builder()
                .pharmacyAddress(address)
                .pharmacyName(name)
                .latitude(latitude)
                .longitude(longitude)
                .build()
        when:
        pharmacyRepository.saveAll(Arrays.asList(pharmacy))
        def result = pharmacyRepository.findAll()

        then:
        result.size() == 1
    }
}
