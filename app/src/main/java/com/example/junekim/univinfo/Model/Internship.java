package com.example.junekim.univinfo.Model;

/**
 * Created by JuneKim on 2017. 6. 13..
 */

public class Internship {

    public String endDate; // 종료날짜
    public String title;  // 인턴십 제목
    public int type; // 0 : 채용 연계형, 1: 여름인턴, 2: 겨울인턴
    public String companyName; // 회사 이름
    public String internshipDesc; // 인턴십 설명
    public String more_url; // 지원 url

    public Internship() {
        // 데이터 스냅샷을 위해서 필요한 생성자
    }

    public Internship(String endDate, String title,int type,String companyName,String internshipDesc ) {
        this.endDate = endDate;
        this.title = title;
        this.type = type;
        this.companyName = companyName;
        this.internshipDesc = internshipDesc;
    }
}
