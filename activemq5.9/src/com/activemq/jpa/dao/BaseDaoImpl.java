/**
 * Copyright &copy; 2012-2013 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.activemq.jpa.dao;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.QueryWrapperFilter;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.highlight.Formatter;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleFragmenter;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.util.Version;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.internal.CriteriaImpl;
import org.hibernate.search.FullTextQuery;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.filter.impl.CachingWrapperFilter;
import org.hibernate.search.query.DatabaseRetrievalMethod;
import org.hibernate.search.query.ObjectLookupMethod;
import org.hibernate.transform.ResultTransformer;
import org.hibernate.transform.Transformers;

/**
 * DAO鏀寔绫诲疄鐜�
 * 
 * @author ThinkGem
 * @version 2013-05-15
 * @param <T>
 */
public class BaseDaoImpl<T> implements BaseDao<T> {

    /**
     * 鑾峰彇瀹炰綋宸ュ巶绠＄悊瀵硅薄
     */
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * 瀹炰綋绫荤被鍨�鐢辨瀯閫犳柟娉曡嚜鍔ㄨ祴鍊�
     */
    private Class<?> entityClass;

    /**
     * 鏋勯�鏂规硶锛屾牴鎹疄渚嬬被鑷姩鑾峰彇瀹炰綋绫荤被鍨�
     */
    public BaseDaoImpl() {
//        entityClass = Reflections.getClassGenricType(getClass());
    }

    /**
     * 鑾峰彇瀹炰綋宸ュ巶绠＄悊瀵硅薄
     */
    public EntityManager getEntityManager() {
        return entityManager;
    }

    /**
     * 鑾峰彇 Session
     */
    public Session getSession() {
        return (Session) getEntityManager().getDelegate();
    }

    /**
     * 寮哄埗涓庢暟鎹簱鍚屾
     */
    public void flush() {
        getSession().flush();
    }

    /**
     * 娓呴櫎缂撳瓨鏁版嵁
     */
    public void clear() {
        getSession().clear();
    }

    // -------------- QL Query --------------

    /**
     * QL 鍒嗛〉鏌ヨ
     * 
     * @param page
     * @param qlString
     * @param parameter
     * @return
     */
    @SuppressWarnings("unchecked")
    public <E> Page<E> find(Page<E> page, String qlString, Object... parameter) {
        // get count
        if (!page.isDisabled() && !page.isNotCount()) {
            String countQlString = "select count(*) "
                    + removeSelect(removeOrders(qlString));
            // page.setCount(Long.valueOf(createQuery(countQlString,
            // parameter).uniqueResult().toString()));
            Query query = createQuery(countQlString, parameter);
            List<Object> list = query.list();
            if (list.size() > 0) {
                page.setCount(Long.valueOf(list.get(0).toString()));
            } else {
                page.setCount(list.size());
            }
            if (page.getCount() < 1) {
                return page;
            }
        }
        // order by
        String ql = qlString;
        Query query = createQuery(ql, parameter);
        // set page
        if (!page.isDisabled()) {
            query.setFirstResult(page.getFirstResult());
            query.setMaxResults(page.getMaxResults());
        }
        page.setList(query.list());
        return page;
    }

    /**
     * QL 鏌ヨ
     * 
     * @param qlString
     * @param parameter
     * @return
     */
    @SuppressWarnings("unchecked")
    public <E> List<E> find(String qlString, Object... parameter) {
        Query query = createQuery(qlString, parameter);
        return query.list();
    }

    /**
     * QL 鏇存柊
     * 
     * @param qlString
     * @param parameter
     * @return
     */
    public int update(String qlString, Object... parameter) {
        return createQuery(qlString, parameter).executeUpdate();
    }

    /**
     * 鍒涘缓 QL 鏌ヨ瀵硅薄
     * 
     * @param qlString
     * @param parameter
     * @return
     */
    public Query createQuery(String qlString, Object... parameter) {
        Query query = getSession().createQuery(qlString);
        setParameter(query, parameter);
        return query;
    }

    // -------------- SQL Query --------------

    /**
     * SQL 鍒嗛〉鏌ヨ
     * 
     * @param page
     * @param sqlString
     * @param parameter
     * @return
     */
    public <E> Page<E> findBySql(Page<E> page, String sqlString,
            Object... parameter) {
        return findBySql(page, sqlString, null, parameter);
    }

    /**
     * SQL 鍒嗛〉鏌ヨ
     * 
     * @param page
     * @param sqlString
     * @param resultClass
     * @param parameter
     * @return
     */
    @SuppressWarnings("unchecked")
    public <E> Page<E> findBySql(Page<E> page, String sqlString,
            Class<?> resultClass, Object... parameter) {
        // get count
        if (!page.isDisabled() && !page.isNotCount()) {
            String countSqlString = "select count(*) "
                    + removeSelect(removeOrders(sqlString));
            // page.setCount(Long.valueOf(createSqlQuery(countSqlString,
            // parameter).uniqueResult().toString()));
            Query query = createSqlQuery(countSqlString, parameter);
            List<Object> list = query.list();
            if (list.size() > 0) {
                page.setCount(Long.valueOf(list.get(0).toString()));
            } else {
                page.setCount(list.size());
            }
            if (page.getCount() < 1) {
                return page;
            }
        }
        // order by
        String sql = sqlString;
        SQLQuery query = createSqlQuery(sql, parameter);
        // set page
        if (!page.isDisabled()) {
            query.setFirstResult(page.getFirstResult());
            query.setMaxResults(page.getMaxResults());
        }
        setResultTransformer(query, resultClass);
        page.setList(query.list());
        return page;
    }

    /**
     * SQL 鏌ヨ
     * 
     * @param sqlString
     * @param parameter
     * @return
     */
    public <E> List<E> findBySql(String sqlString, Object... parameter) {
        return findBySql(sqlString, null, parameter);
    }

    /**
     * SQL 鏌ヨ
     * 
     * @param sqlString
     * @param resultClass
     * @param parameter
     * @return
     */
    @SuppressWarnings("unchecked")
    public <E> List<E> findBySql(String sqlString, Class<?> resultClass,
            Object... parameter) {
        SQLQuery query = createSqlQuery(sqlString, parameter);
        setResultTransformer(query, resultClass);
        return query.list();
    }

    /**
     * SQL 鏇存柊
     * 
     * @param sqlString
     * @param parameter
     * @return
     */
    public int updateBySql(String sqlString, Object... parameter) {
        return createSqlQuery(sqlString, parameter).executeUpdate();
    }

    /**
     * 鍒涘缓 SQL 鏌ヨ瀵硅薄
     * 
     * @param sqlString
     * @param parameter
     * @return
     */
    public SQLQuery createSqlQuery(String sqlString, Object... parameter) {
        SQLQuery query = getSession().createSQLQuery(sqlString);
        setParameter(query, parameter);
        return query;
    }

    // -------------- Query Tools --------------

    /**
     * 璁剧疆鏌ヨ缁撴灉绫诲瀷
     * 
     * @param query
     * @param resultClass
     */
    private void setResultTransformer(SQLQuery query, Class<?> resultClass) {
        if (resultClass != null) {
            if (resultClass == Map.class) {
                query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
            } else if (resultClass == List.class) {
                query.setResultTransformer(Transformers.TO_LIST);
            } else {
                query.addEntity(resultClass);
            }
        }
    }

    /**
     * 璁剧疆鏌ヨ鍙傛暟
     * 
     * @param query
     * @param parameter
     */
    private void setParameter(Query query, Object... parameter) {
        if (parameter != null) {
            for (int i = 0; i < parameter.length; i++) {
                query.setParameter(i, parameter[i]);
            }
        }
    }

    /**
     * 鍘婚櫎qlString鐨剆elect瀛愬彞銆�
     * 
     * @param hql
     * @return
     */
    private String removeSelect(String qlString) {
        int beginPos = qlString.toLowerCase().indexOf("from");
        return qlString.substring(beginPos);
    }

    /**
     * 鍘婚櫎hql鐨刼rderBy瀛愬彞銆�
     * 
     * @param hql
     * @return
     */
    private String removeOrders(String qlString) {
        Pattern p = Pattern.compile("order\\s*by[\\w|\\W|\\s|\\S]*",
                Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(qlString);
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            m.appendReplacement(sb, "");
        }
        m.appendTail(sb);
        return sb.toString();
    }

    // -------------- Criteria --------------

    /**
     * 鍒嗛〉鏌ヨ
     * 
     * @param page
     * @return
     */
    public Page<T> find(Page<T> page) {
        return find(page, createDetachedCriteria());
    }

    /**
     * 浣跨敤妫�储鏍囧噯瀵硅薄鍒嗛〉鏌ヨ
     * 
     * @param page
     * @param detachedCriteria
     * @param resultTransformer
     * @return
     */
    public Page<T> find(Page<T> page, DetachedCriteria detachedCriteria) {
        return find(page, detachedCriteria, Criteria.DISTINCT_ROOT_ENTITY);
    }

    /**
     * 浣跨敤妫�储鏍囧噯瀵硅薄鍒嗛〉鏌ヨ
     * 
     * @param page
     * @param detachedCriteria
     * @param resultTransformer
     * @return
     */
    @SuppressWarnings("unchecked")
    public Page<T> find(Page<T> page, DetachedCriteria detachedCriteria,
            ResultTransformer resultTransformer) {
        // get count
        if (!page.isDisabled() && !page.isNotCount()) {
            page.setCount(count(detachedCriteria));
            if (page.getCount() < 1) {
                return page;
            }
        }
        Criteria criteria = detachedCriteria
                .getExecutableCriteria(getSession());
        criteria.setResultTransformer(resultTransformer);
        // set page
        if (!page.isDisabled()) {
            criteria.setFirstResult(page.getFirstResult());
            criteria.setMaxResults(page.getMaxResults());
        }
        // order by
        page.setList(criteria.list());
        return page;
    }

    /**
     * 浣跨敤妫�储鏍囧噯瀵硅薄鏌ヨ
     * 
     * @param detachedCriteria
     * @return
     */
    public List<T> find(DetachedCriteria detachedCriteria) {
        return find(detachedCriteria, Criteria.DISTINCT_ROOT_ENTITY);
    }

    /**
     * 浣跨敤妫�储鏍囧噯瀵硅薄鏌ヨ
     * 
     * @param detachedCriteria
     * @param resultTransformer
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<T> find(DetachedCriteria detachedCriteria,
            ResultTransformer resultTransformer) {
        Criteria criteria = detachedCriteria
                .getExecutableCriteria(getSession());
        criteria.setResultTransformer(resultTransformer);
        return criteria.list();
    }

    /**
     * 浣跨敤妫�储鏍囧噯瀵硅薄鏌ヨ璁板綍鏁�
     * 
     * @param detachedCriteria
     * @return
     */
    @SuppressWarnings("rawtypes")
    public long count(DetachedCriteria detachedCriteria) {
        Criteria criteria = detachedCriteria
                .getExecutableCriteria(getSession());
        long totalCount = 0;
        try {
            // Get orders
            Field field = CriteriaImpl.class.getDeclaredField("orderEntries");
            field.setAccessible(true);
            List orderEntrys = (List) field.get(criteria);
            // Remove orders
            field.set(criteria, new ArrayList());
            // Get count
            criteria.setProjection(Projections.rowCount());
            totalCount = Long.valueOf(criteria.uniqueResult().toString());
            // Clean count
            criteria.setProjection(null);
            // Restore orders
            field.set(criteria, orderEntrys);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return totalCount;
    }

    /**
     * 鍒涘缓涓庝細璇濇棤鍏崇殑妫�储鏍囧噯瀵硅薄
     * 
     * @param criterions
     *            Restrictions.eq("name", value);
     * @return
     */
    public DetachedCriteria createDetachedCriteria(Criterion... criterions) {
        DetachedCriteria dc = DetachedCriteria.forClass(entityClass);
        for (Criterion c : criterions) {
            dc.add(c);
        }
        return dc;
    }

    // -------------- Hibernate search --------------

    /**
     * 鑾峰彇鍏ㄦ枃Session
     */
    public FullTextSession getFullTextSession() {
        return Search.getFullTextSession(getSession());
    }

    /**
     * 寤虹珛绱㈠紩
     */
    public void createIndex() {
        try {
            getFullTextSession().createIndexer(entityClass).startAndWait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

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
    @SuppressWarnings("unchecked")
    public Page<T> search(Page<T> page, BooleanQuery query,
            BooleanQuery queryFilter, Sort sort) {

        // 鎸夊叧閿瓧鏌ヨ
        FullTextQuery fullTextQuery = getFullTextSession().createFullTextQuery(
                query, entityClass);

        // 杩囨护鏃犳晥鐨勫唴瀹�
        if (queryFilter != null) {
            fullTextQuery.setFilter(new CachingWrapperFilter(
                    new QueryWrapperFilter(queryFilter)));
        }

        // 璁剧疆鎺掑簭
        if (sort != null) {
            fullTextQuery.setSort(sort);
        }

        // 瀹氫箟鍒嗛〉
        page.setCount(fullTextQuery.getResultSize());
        fullTextQuery.setFirstResult(page.getFirstResult());
        fullTextQuery.setMaxResults(page.getMaxResults());

        // 鍏堜粠鎸佷箙鍖栦笂涓嬫枃涓煡鎵惧璞★紝濡傛灉娌℃湁鍐嶄粠浜岀骇缂撳瓨涓煡鎵�
        fullTextQuery.initializeObjectsWith(
                ObjectLookupMethod.SECOND_LEVEL_CACHE,
                DatabaseRetrievalMethod.QUERY);

        // 杩斿洖缁撴灉
        page.setList(fullTextQuery.list());

        return page;
    }

    /**
     * 鑾峰彇鍏ㄦ枃鏌ヨ瀵硅薄
     */
    public BooleanQuery getFullTextQuery(BooleanClause... booleanClauses) {
        BooleanQuery booleanQuery = new BooleanQuery();
        for (BooleanClause booleanClause : booleanClauses) {
            booleanQuery.add(booleanClause);
        }
        return booleanQuery;
    }

    /**
     * 鑾峰彇鍏ㄦ枃鏌ヨ瀵硅薄
     * 
     * @param q
     *            鏌ヨ鍏抽敭瀛�
     * @param fields
     *            鏌ヨ瀛楁
     * @return 鍏ㄦ枃鏌ヨ瀵硅薄
     */
    public BooleanQuery getFullTextQuery(String q, String... fields) {
        BooleanQuery query = new BooleanQuery();
        return query;
    }

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
            int subLength, String... fields) {
       
        return list;
    }
}