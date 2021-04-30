package com.example.requestvacation.domain.entitiy;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import javax.persistence.*;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "vac_master")
public class Vacation extends Time{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(length = 4, nullable = false)
    private Long vacSeq;
    
    @Column(length = 8, nullable = false)
	private String vacDt;
    
    @Column(length = 3, nullable = false)
	private Double vacDs;
    
    @Column(length = 20, nullable = false)
	private String usrId;
        
    @Column(length = 1000)
	private String vacRmk;

    @Builder
    public Vacation(Long vacSeq, String vacDt, Double vacDs, String usrId, String vacRmk) {
    	this.vacSeq = vacSeq;
    	this.vacDt = vacDt;
    	this.vacDs = vacDs;
    	this.usrId = usrId;
		this.vacRmk = vacRmk;
    }
    
}