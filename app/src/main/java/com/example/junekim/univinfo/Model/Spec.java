package com.example.junekim.univinfo.Model;

/**
 * Created by JuneKim on 2017. 6. 13..
 */

public class Spec {

    public String spec_name; // 스펙 이름
    public String period;  // 기간
    public String job_desc; // 한 업무 / 내용
    public String difficulty; // 어려웠던 점
    public String learning; // 배운 점

    public Spec() {
        // 데이터 스냅샷을 위해서 필요한 생성자
    }

    public Spec(String spec_name, String period, String job_desc, String difficulty, String learning) {
        this.spec_name = spec_name;
        this.period = period;
        this.job_desc = job_desc;
        this.difficulty = difficulty;
        this.learning = learning;
    }
}
