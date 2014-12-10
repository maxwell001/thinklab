/**
 * Copyright &copy; 2012-2013 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.activemq.jpa.dao;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

/**
 * 鍒嗛〉绫�
 * 
 * @author ThinkGem
 * @version 2013-7-2
 * @param <T>
 */
public class Page<T> {

    private int pageNo = 1; // 褰撳墠椤电爜
    private int pageSize = 10; // 椤甸潰澶у皬锛岃缃负鈥�1鈥濊〃绀轰笉杩涜鍒嗛〉锛堝垎椤垫棤鏁堬級

    private long count;// 鎬昏褰曟暟锛岃缃负鈥�1鈥濊〃绀轰笉鏌ヨ鎬绘暟

    private int first;// 棣栭〉绱㈠紩
    private int last;// 灏鹃〉绱㈠紩
    private int prev;// 涓婁竴椤电储寮�
    private int next;// 涓嬩竴椤电储寮�

    private boolean firstPage;// 鏄惁鏄涓�〉
    private boolean lastPage;// 鏄惁鏄渶鍚庝竴椤�

    private int length = 8;// 鏄剧ず椤甸潰闀垮害
    private int slider = 1;// 鍓嶅悗鏄剧ず椤甸潰闀垮害

    private List<T> list = new ArrayList<T>();

    private String orderBy = ""; // 鏍囧噯鏌ヨ鏈夋晥锛�瀹炰緥锛�updatedate desc, name asc

    private String funcName = "page"; // 璁剧疆鐐瑰嚮椤电爜璋冪敤鐨刯s鍑芥暟鍚嶇О锛岄粯璁や负page锛屽湪涓�〉鏈夊涓垎椤靛璞℃椂浣跨敤銆�

    private String message = ""; // 璁剧疆鎻愮ず娑堟伅锛屾樉绀哄湪鈥滃叡n鏉♀�涔嬪悗


    /**
     * 鏋勯�鏂规硶
     * 
     * @param pageNo
     *            褰撳墠椤电爜
     * @param pageSize
     *            鍒嗛〉澶у皬
     */
    public Page(int pageNo, int pageSize) {
        this(pageNo, pageSize, 0);
    }

    /**
     * 鏋勯�鏂规硶
     * 
     * @param pageNo
     *            褰撳墠椤电爜
     * @param pageSize
     *            鍒嗛〉澶у皬
     * @param count
     *            鏁版嵁鏉℃暟
     */
    public Page(int pageNo, int pageSize, long count) {
        this(pageNo, pageSize, count, new ArrayList<T>());
    }

    /**
     * 鏋勯�鏂规硶
     * 
     * @param pageNo
     *            褰撳墠椤电爜
     * @param pageSize
     *            鍒嗛〉澶у皬
     * @param count
     *            鏁版嵁鏉℃暟
     * @param list
     *            鏈〉鏁版嵁瀵硅薄鍒楄〃
     */
    public Page(int pageNo, int pageSize, long count, List<T> list) {
        this.setCount(count);
        this.setPageNo(pageNo);
        this.pageSize = pageSize;
        this.setList(list);
    }

    /**
     * 鍒濆鍖栧弬鏁�
     */
    public void initialize() {

        // 1
        this.first = 1;

        this.last = (int) (count / (this.pageSize < 1 ? 20 : this.pageSize)
                + first - 1);

        if (this.count % this.pageSize != 0 || this.last == 0) {
            this.last++;
        }

        if (this.last < this.first) {
            this.last = this.first;
        }

        if (this.pageNo <= 1) {
            this.pageNo = this.first;
            this.firstPage = true;
        }

        if (this.pageNo >= this.last) {
            this.pageNo = this.last;
            this.lastPage = true;
        }

        if (this.pageNo < this.last - 1) {
            this.next = this.pageNo + 1;
        } else {
            this.next = this.last;
        }

        if (this.pageNo > 1) {
            this.prev = this.pageNo - 1;
        } else {
            this.prev = this.first;
        }

        // 2
        if (this.pageNo < this.first) {// 濡傛灉褰撳墠椤靛皬浜庨椤�
            this.pageNo = this.first;
        }

        if (this.pageNo > this.last) {// 濡傛灉褰撳墠椤靛ぇ浜庡熬椤�
            this.pageNo = this.last;
        }

    }

    /**
     * 榛樿杈撳嚭褰撳墠鍒嗛〉鏍囩 <div class="page">${page}</div>
     */
    @Override
    public String toString() {

        initialize();

        StringBuilder sb = new StringBuilder();

        
        return sb.toString();
    }

    // public static void main(String[] args) {
    // Page<String> p = new Page<String>(3, 3);
    // System.out.println(p);
    // System.out.println("棣栭〉锛�+p.getFirst());
    // System.out.println("灏鹃〉锛�+p.getLast());
    // System.out.println("涓婇〉锛�+p.getPrev());
    // System.out.println("涓嬮〉锛�+p.getNext());
    // }

    /**
     * 鑾峰彇璁剧疆鎬绘暟
     * 
     * @return
     */
    public long getCount() {
        return count;
    }

    /**
     * 璁剧疆鏁版嵁鎬绘暟
     * 
     * @param count
     */
    public void setCount(long count) {
        this.count = count;
        if (pageSize >= count) {
            pageNo = 1;
        }
    }

    /**
     * 鑾峰彇褰撳墠椤电爜
     * 
     * @return
     */
    public int getPageNo() {
        return pageNo;
    }

    /**
     * 璁剧疆褰撳墠椤电爜
     * 
     * @param pageNo
     */
    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    /**
     * 鑾峰彇椤甸潰澶у皬
     * 
     * @return
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * 璁剧疆椤甸潰澶у皬锛堟渶澶�00锛�
     * 
     * @param pageSize
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize <= 0 ? 10 : pageSize > 500 ? 500 : pageSize;
    }

    /**
     * 棣栭〉绱㈠紩
     * 
     * @return
     */
    public int getFirst() {
        return first;
    }

    /**
     * 灏鹃〉绱㈠紩
     * 
     * @return
     */
    public int getLast() {
        return last;
    }

    /**
     * 鑾峰彇椤甸潰鎬绘暟
     * 
     * @return getLast();
     */
    public int getTotalPage() {
        return getLast();
    }

    /**
     * 鏄惁涓虹涓�〉
     * 
     * @return
     */
    public boolean isFirstPage() {
        return firstPage;
    }

    /**
     * 鏄惁涓烘渶鍚庝竴椤�
     * 
     * @return
     */
    public boolean isLastPage() {
        return lastPage;
    }

    /**
     * 涓婁竴椤电储寮曞�
     * 
     * @return
     */
    public int getPrev() {
        if (isFirstPage()) {
            return pageNo;
        } else {
            return pageNo - 1;
        }
    }

    /**
     * 涓嬩竴椤电储寮曞�
     * 
     * @return
     */
    public int getNext() {
        if (isLastPage()) {
            return pageNo;
        } else {
            return pageNo + 1;
        }
    }

    /**
     * 鑾峰彇鏈〉鏁版嵁瀵硅薄鍒楄〃
     * 
     * @return List<T>
     */
    public List<T> getList() {
        return list;
    }

    /**
     * 璁剧疆鏈〉鏁版嵁瀵硅薄鍒楄〃
     * 
     * @param list
     */
    public void setList(List<T> list) {
        this.list = list;
    }

    /**
     * 鑾峰彇鏌ヨ鎺掑簭瀛楃涓�
     * 
     * @return
     */
    public String getOrderBy() {
        return orderBy;
    }

    /**
     * 璁剧疆鏌ヨ鎺掑簭锛屾爣鍑嗘煡璇㈡湁鏁堬紝 瀹炰緥锛�updatedate desc, name asc
     */
    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    /**
     * 鑾峰彇鐐瑰嚮椤电爜璋冪敤鐨刯s鍑芥暟鍚嶇О function ${page.funcName}(pageNo){location=
     * "${ctx}/list-${category.id}${urlSuffix}?pageNo="+i;}
     * 
     * @return
     */
    public String getFuncName() {
        return funcName;
    }

    /**
     * 璁剧疆鐐瑰嚮椤电爜璋冪敤鐨刯s鍑芥暟鍚嶇О锛岄粯璁や负page锛屽湪涓�〉鏈夊涓垎椤靛璞℃椂浣跨敤銆�
     * 
     * @param funcName
     *            榛樿涓簆age
     */
    public void setFuncName(String funcName) {
        this.funcName = funcName;
    }

    /**
     * 璁剧疆鎻愮ず娑堟伅锛屾樉绀哄湪鈥滃叡n鏉♀�涔嬪悗
     * 
     * @param message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 鍒嗛〉鏄惁鏈夋晥
     * 
     * @return this.pageSize==-1
     */
    public boolean isDisabled() {
        return this.pageSize == -1;
    }

    /**
     * 鏄惁杩涜鎬绘暟缁熻
     * 
     * @return this.count==-1
     */
    public boolean isNotCount() {
        return this.count == -1;
    }

    /**
     * 鑾峰彇 Hibernate FirstResult
     */
    public int getFirstResult() {
        int firstResult = (getPageNo() - 1) * getPageSize();
        if (firstResult >= getCount()) {
            firstResult = 0;
        }
        return firstResult;
    }

    /**
     * 鑾峰彇 Hibernate MaxResults
     */
    public int getMaxResults() {
        return getPageSize();
    }

    /**
     * 鑾峰彇 Spring data JPA 鍒嗛〉瀵硅薄
     */
    public Pageable getSpringPage() {
        List<Order> orders = new ArrayList<Order>();
        if (orderBy != null) {
            for (String order : StringUtils.split(orderBy, ",")) {
                String[] o = StringUtils.split(order, " ");
                if (o.length == 1) {
                    orders.add(new Order(Direction.ASC, o[0]));
                } else if (o.length == 2) {
                    if ("DESC".equals(o[1].toUpperCase())) {
                        orders.add(new Order(Direction.DESC, o[0]));
                    } else {
                        orders.add(new Order(Direction.ASC, o[0]));
                    }
                }
            }
        }
        return new PageRequest(this.pageNo - 1, this.pageSize, new Sort(orders));
    }

    /**
     * 璁剧疆 Spring data JPA 鍒嗛〉瀵硅薄锛岃浆鎹负鏈郴缁熷垎椤靛璞�
     */
    public void setSpringPage(org.springframework.data.domain.Page<T> page) {
        this.pageNo = page.getNumber();
        this.pageSize = page.getSize();
        this.count = page.getTotalElements();
        this.list = page.getContent();
    }

}
