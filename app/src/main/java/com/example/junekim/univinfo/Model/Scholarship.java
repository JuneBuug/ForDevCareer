package com.example.junekim.univinfo.Model;

/**
 * Created by JuneKim on 2017. 6. 13..
 */

public class Scholarship {

    public String endDate ; // 종료날짜
    public String title ;  // 장학금 제목


    public Scholarship(){
        // 데이터 스냅샷을 위해서 필요한 생성자
    }

    public Scholarship(String endDate, String title) {
        this.endDate = endDate;
        this.title = title;
    }
}
