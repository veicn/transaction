package com.sinochem.crude.trade.blockchain.domain;

public class TBrokerDocuments {
    private Long id;

    private String declarationuuid;

    private String accompanyingDocumentscode;

    private String accompanyingDocumentsno;

    private String documentscode;

    private String documentsno;

    private String relevancydeclaration;

    private String relevancyrecords;

    private String supervisesite;

    private String sitecode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDeclarationuuid() {
        return declarationuuid;
    }

    public void setDeclarationuuid(String declarationuuid) {
        this.declarationuuid = declarationuuid == null ? null : declarationuuid.trim();
    }

    public String getAccompanyingDocumentscode() {
        return accompanyingDocumentscode;
    }

    public void setAccompanyingDocumentscode(String accompanyingDocumentscode) {
        this.accompanyingDocumentscode = accompanyingDocumentscode == null ? null : accompanyingDocumentscode.trim();
    }

    public String getAccompanyingDocumentsno() {
        return accompanyingDocumentsno;
    }

    public void setAccompanyingDocumentsno(String accompanyingDocumentsno) {
        this.accompanyingDocumentsno = accompanyingDocumentsno == null ? null : accompanyingDocumentsno.trim();
    }

    public String getDocumentscode() {
        return documentscode;
    }

    public void setDocumentscode(String documentscode) {
        this.documentscode = documentscode == null ? null : documentscode.trim();
    }

    public String getDocumentsno() {
        return documentsno;
    }

    public void setDocumentsno(String documentsno) {
        this.documentsno = documentsno == null ? null : documentsno.trim();
    }

    public String getRelevancydeclaration() {
        return relevancydeclaration;
    }

    public void setRelevancydeclaration(String relevancydeclaration) {
        this.relevancydeclaration = relevancydeclaration == null ? null : relevancydeclaration.trim();
    }

    public String getRelevancyrecords() {
        return relevancyrecords;
    }

    public void setRelevancyrecords(String relevancyrecords) {
        this.relevancyrecords = relevancyrecords == null ? null : relevancyrecords.trim();
    }

    public String getSupervisesite() {
        return supervisesite;
    }

    public void setSupervisesite(String supervisesite) {
        this.supervisesite = supervisesite == null ? null : supervisesite.trim();
    }

    public String getSitecode() {
        return sitecode;
    }

    public void setSitecode(String sitecode) {
        this.sitecode = sitecode == null ? null : sitecode.trim();
    }
}