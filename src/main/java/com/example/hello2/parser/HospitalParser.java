package com.example.hello2.parser;

import com.example.hello2.domain.Hospital;

import java.time.LocalDateTime;

public class HospitalParser implements Parser<Hospital>{

    @Override
    public Hospital parse(String str) {
        String[] row = str.split("\",\"");

        Hospital hospital = new Hospital();

        // 숫자도 split을 해서 row에 넣게 되면 spring타입으로 저장되기 때문에 int 타입으로 바꿔줘야됨
        // 제일 첫번째 row 와 마지막 row는 "," 이걸로 split 했기 때문에 ", < 이렇게 되어있는건 replace를 사용하여 삭제
        hospital.setId(Integer.parseInt(row[0].replace("\"","")));
        hospital.setOpenServiceName(row[1]);
        hospital.setOpenLocalGovernmentCode(Integer.parseInt(row[3]));
        hospital.setManagementNumber(row[4]);

        // LicenseDate substring 으로 나누기 . < 가 있어서 다른것과 다르게
        // 1 9 9 6 0 2 1 8
        int year = Integer.parseInt(row[5].substring(0, 4));
        int month = Integer.parseInt(row[5].substring(4, 6));
        int day = Integer.parseInt(row[5].substring(6, 8));
        //System.out.printf("%d %d %d \n", year, month, day);
        hospital.setLicenseDate(LocalDateTime.of(year, month, day, 0, 0, 0));

        hospital.setBusinessStatus(Integer.parseInt(row[7]));
        hospital.setBusinessStatusCode(Integer.parseInt(row[9]));
        hospital.setPhone(row[15]);
        hospital.setFullAddress(row[18]);
        hospital.setRoadNameAddress(row[19]);
        hospital.setHospitalName(row[21]);
        hospital.setBusinessTypeName(row[25]);
        hospital.setHealthcareProviderCount(Integer.parseInt(row[29]));
        hospital.setPatientRoomCount(Integer.parseInt(row[30]));
        hospital.setTotalNumberOfBeds(Integer.parseInt(row[31]));
        hospital.setTotalAreaSize(Float.parseFloat(row[32].replace("\"","")));

        return hospital;
    }
}
