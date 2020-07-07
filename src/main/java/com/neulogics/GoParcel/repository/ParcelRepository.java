
package com.neulogics.GoParcel.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.neulogics.GoParcel.model.Parcel;

@Repository
public interface ParcelRepository extends JpaRepository<Parcel,Long> {

}
