package com.example.junekim.univinfo.Model;

/**
 * Created by JuneKim on 2017. 6. 13..
 */

public class Internship {

    public String endDate; // 종료날짜
    public String title;  // 인턴십 제목


    public Internship() {
        // 데이터 스냅샷을 위해서 필요한 생성자
    }

    public Internship(String endDate, String title) {
        this.endDate = endDate;
        this.title = title;
    }
}
