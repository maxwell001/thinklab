/**
 * Copyright &copy; 2012-2013 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.activemq.jpa.dao;

import java.util.List;
import javax.persistence.EntityManager;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Sort;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.search.FullTextSession;
import org.hibernate.transform.ResultTransformer;

/**
 * DAO鏀寔鎺ュ彛
 * 
 * @author ThinkGem
 * @version 2013-05-15
 * @param <T>
 */
public interface BaseDao<T> {

    /**
     * 鑾峰彇瀹炰綋宸ュ巶绠＄悊瀵硅薄
     */
    public EntityManager getEntityManager();

    /**
     * 鑾峰彇 Session
     */
    public Session getSession();

    /**
     * 寮哄埗涓庢暟鎹簱鍚屾
     */
    public void flush();

    /**
     * 娓呴櫎缂撳瓨鏁版嵁
     */
    public void clear();

    // -------------- QL Query --------------

    /**
     * QL 鍒嗛〉鏌ヨ
     * 
     * @param page
     * @param qlString
     * @param parameter
     * @return
     */
    public <E> Page<E> find(Page<E> page, String qlString, Object... parameter);

    /**
     * QL 鏌ヨ
     * 
     * @param qlString
     * @param parameter
     * @return
     */
    public <E> List<E> find(String qlString, Object... parameter);

    /**
     * QL 鏇存柊
     * 
     * @param sqlString
     * @param parameter
     * @return
     */
    public int update(String qlString, Object... parameter);

    /**
     * 鍒涘缓 QL 鏌ヨ瀵硅薄
     * 
     * @param qlString
     * @param parameter
     * @return
     */
    public Query createQuery(String qlString, Object... parameter);

    // -------------- SQL Query --------------

    /**
     * SQL 鍒嗛〉鏌ヨ
     * 
     * @param page
     * @param qlString
     * @param parameter
     * @return
     */
    public <E> Page<E> findBySql(Page<E> page, String sqlString,
            Object... parameter);

    /**
     * SQL 鍒嗛〉鏌ヨ
     * 
     * @param page
     * @param qlString
     * @param resultClass
     * @param parameter
     * @return
     */
    public <E> Page<E> findBySql(Page<E> page, String sqlString,
            Class<?> resultClass, Object... parameter);

    /**
     * SQL 鏌ヨ
     * 
     * @param sqlString
     * @param parameter
     * @return
     */
    public <E> List<E> findBySql(String sqlString, Object... parameter);

    /**
     * SQL 鏌ヨ
     * 
     * @param sqlString
     * @param resultClass
     * @param parameter
     * @return
     */
    public <E> List<E> findBySql(String sqlString, Class<?> resultClass,
            Object... parameter);

    /**
     * SQL 鏇存柊
     * 
     * @param sqlString
     * @param parameter
     * @return
     */
    public int updateBySql(String sqlString, Object... parameter);

    /**
     * 鍒涘缓 SQL 鏌ヨ瀵硅薄
     * 
     * @param sqlString
     * @param parameter
     * @return
     */
    public Query createSqlQuery(String sqlString, Object... parameter);

    // -------------- Criteria --------------

    /**
     * 鍒嗛〉鏌ヨ
     * 
     * @param page
     * @return
     */
    public Page<T> find(Page<T> page);

    /**
     * 浣跨敤妫�储鏍囧噯瀵硅薄鍒嗛〉鏌ヨ
     * 
     * @param page
     * @param detachedCriteria
     * @return
     */
    public Page<T> find(Page<T> page, DetachedCriteria detachedCriteria);

    /**
     * 浣跨敤妫�储鏍囧噯瀵硅薄鍒嗛〉鏌ヨ
     * 
     * @param page
     * @param detachedCriteria
     * @param resultTransformer
     * @return
     */
    public Page<T> find(Page<T> page, DetachedCriteria detachedCriteria,
            ResultTransformer resultTransformer);

    /**
     * 浣跨敤妫�储鏍囧噯瀵硅薄鏌ヨ
     * 
     * @param detachedCriteria
     * @return
     */
    public List<T> find(DetachedCriteria detachedCriteria);

    /**
     * 浣跨敤妫�储鏍囧噯瀵硅薄鏌ヨ
     * 
     * @param detachedCriteria
     * @param resultTransformer
     * @return
     */
    public List<T> find(DetachedCriteria detachedCriteria,
            ResultTransformer resultTransformer);

    /**
     * 浣跨敤妫�储鏍囧噯瀵硅薄鏌ヨ璁板綍鏁�
     * 
     * @param detachedCriteria
     * @return
     */
    public long count(DetachedCriteria detachedCriteria);

    /**
     * 鍒涘缓涓庝細璇濇棤鍏崇殑妫�储鏍囧噯瀵硅薄
     * 
     * @param criterions
     *            Restrictions.eq("name", value);
     * @return
     */
    public DetachedCriteria createDetachedCriteria(Criterion... criterions);

    // -------------- Hibernate search --------------

    /**
     * 鑾峰彇鍏ㄦ枃Session
     */
    public FullTextSession getFullTextSession();

    /**
     * 寤虹珛绱㈠紩
     */
    public void createIndex();

    /**
     * 鍏ㄦ枃妫�储
     * 
     * @param page
     *            鍒嗛〉瀵硅薄
     * @param query
     *            鍏抽敭瀛楁煡璇㈠璞�
     * @param queryFilter
     *            鏌ヨ杩囨护瀵硅薄
     * @param sort
     *            鎺掑簭瀵硅薄
     * @return 鍒嗛〉瀵硅薄
     */
    public Page<T> search(Page<T> page, BooleanQuery query,
            BooleanQuery queryFilter, Sort sort);

    /**
     * 鑾峰彇鍏ㄦ枃鏌ヨ瀵硅薄
     */
    public BooleanQuery getFullTextQuery(BooleanClause... booleanClauses);

    /**
     * 鑾峰彇鍏ㄦ枃鏌ヨ瀵硅薄
     * 
     * @param q
     *            鏌ヨ鍏抽敭瀛�
     * @param fields
     *            鏌ヨ瀛楁
     * @return 鍏ㄦ枃鏌ヨ瀵硅薄
     */
    public BooleanQuery getFullTextQuery(String q, String... fields);

    /**
     * 璁剧疆鍏抽敭瀛楅珮浜�
     * 
     * @param query
     *            鏌ヨ瀵硅薄
     * @param list
     *            璁剧疆楂樹寒鐨勫唴瀹瑰垪琛�
     * @param subLength
     *            鎴彇闀垮害
     * @param fields
     *            瀛楁鍚�
     */
    public List<T> keywordsHighlight(BooleanQuery query, List<T> list,
            int subLength, String... fields);
}