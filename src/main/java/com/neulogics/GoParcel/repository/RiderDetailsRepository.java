package com.neulogics.GoParcel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.neulogics.GoParcel.model.RiderDetails;

@Repository
public interface RiderDetailsRepository extends JpaRepository<RiderDetails,Long> {

}
