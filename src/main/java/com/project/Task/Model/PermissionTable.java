//package com.project.Task.Model;
//
//import javax.persistence.*;
//
//@Entity
//public class PermissionTable {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    @Column(unique = true)
//    private String code;
//    private String description;
//    @Column(columnDefinition = "boolean default false")
//    private boolean isDeleted;
//
//    public PermissionTable() {
//    }
//
//    public PermissionTable(Long id, String code, String description, boolean isDeleted) {
//        this.id = id;
//        this.code = code;
//        this.description = description;
//        this.isDeleted = isDeleted;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getCode() {
//        return code;
//    }
//
//    public void setCode(String code) {
//        this.code = code;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public boolean isDeleted() {
//        return isDeleted;
//    }
//
//    public void setDeleted(boolean deleted) {
//        isDeleted = deleted;
//    }
//
//    @Override
//    public String toString() {
//        return "PermissionTable{" +
//                "id=" + id +
//                ", code='" + code + '\'' +
//                ", description='" + description + '\'' +
//                ", isDeleted=" + isDeleted +
//                '}';
//    }
//}
