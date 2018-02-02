package com.godfkc.center.repository;

import com.godfkc.center.entity.AddressDict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @version 1.0
 * @auther Administrator
 * @date 2018/1/29
 * @description
 */
public interface AddressDictRepository extends JpaRepository<AddressDict, Long> {
   @Query("select adc  from AddressDict adc where adc.parent.id =?1")
   /*@Query(value = "select *  from cs_address_dict  where parent_id =?1",nativeQuery = true)*/
 List<AddressDict> selectAddressDictList(Long parentId);

 List<AddressDict> findByParent_Id(Long parentId);

 List<AddressDict> findByParent_IdIsNull();

    @Query("select adc from AddressDict adc where adc.parent.id is null")
    List<AddressDict> selectAddressDictIsNull();


}
