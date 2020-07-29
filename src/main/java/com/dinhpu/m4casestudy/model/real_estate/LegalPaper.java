package com.dinhpu.m4casestudy.model.real_estate;

import javax.persistence.*;

@Entity
@Table(name="legal_paper")
public class LegalPaper {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="legal_paper_name")
    private String legalPaperName;

    public LegalPaper() {
    }

    public LegalPaper(String legalPaperName) {
        this.legalPaperName = legalPaperName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLegalPaperName() {
        return legalPaperName;
    }

    public void setLegalPaperName(String legalPaperName) {
        this.legalPaperName = legalPaperName;
    }


}
