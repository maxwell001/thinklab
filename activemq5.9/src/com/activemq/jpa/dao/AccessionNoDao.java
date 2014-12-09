/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.activemq.jpa.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.activemq.jpa.model.AccessionNo;

/**
 * 鐥呯悊鍙稤AO鎺ュ彛
 * 
 * @author 鐜嬩繆
 * @version 2013-11-09
 */
public interface AccessionNoDao extends AccessionNoDaoCustom,
        CrudRepository<AccessionNo, Long> {

    @Modifying
    @Query("update AccessionNo set delFlag='" + 1
            + "' where id = ?1")
    public int deleteById(Long id);

    @Modifying
    @Query("update AccessionNo set accessionNo=?1 where id = ?2")
    public int updateAccessionById(String accessionNo, Long id);

    @Query("select accessionNo from AccessionNo where id=(select max(id) from AccessionNo)")
    public String findMaxNo();

    @Query("from AccessionNo where id=(select max(id) from AccessionNo where accessionNo like ?1) and accessionNo like ?2 and type='"
            + AccessionNo.TYPE_AUTO + "'")
    public AccessionNo findLatestAccessionNo(String typeName, String typeName2);

    @Query("select count(*) from AccessionNo where accessionNo like ?1")
    public Long countByType(String typeName);

}

interface AccessionNoDaoCustom extends BaseDao<AccessionNo> {

}

@Component
class AccessionNoDaoImpl extends BaseDaoImpl<AccessionNo> implements
        AccessionNoDaoCustom {

}
