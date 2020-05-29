//package com.neulogics.GoParcel.audit;
//
//import javax.persistence.Column;
//import javax.persistence.MappedSuperclass;
//
//import org.springframework.data.annotation.CreatedBy;
//import org.springframework.data.annotation.LastModifiedBy;
//
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import com.neulogic.pollingApp.model.AuditModel;
//
//@MappedSuperclass
//@JsonIgnoreProperties(
//        value = {"createdBy", "updatedBy"},
//        allowGetters = true
//)
//public abstract class UserDateAudit extends AuditModel {
//    /**
//	 * 
//	 */
//	private static final long serialVersionUID = 1L;
//
//	@CreatedBy
//    @Column(updatable = false)
//    private Long createdBy;
//
//    @LastModifiedBy
//    private Long updatedBy;
//
//    public Long getCreatedBy() {
//        return createdBy;
//    }
//
//    public void setCreatedBy(Long createdBy) {
//        this.createdBy = createdBy;
//    }
//
//    public Long getUpdatedBy() {
//        return updatedBy;
//    }
//
//    public void setUpdatedBy(Long updatedBy) {
//        this.updatedBy = updatedBy;
//    }
//}
