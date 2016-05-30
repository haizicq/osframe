package com.os.osframe.frame.common;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页信息
 * Created by wangdc on 14-4-21.
 */
public class PageInfo {

    public PageInfo()
    {
        list = new ArrayList();
        pagingList = new ArrayList();
    }

    public static int DEFAULT_PAGESIZE = 15;
    public static int DEFAULT_PAGING_LIST_SIZE = 10;
    private List list;
    private int pageno;
    private int rowsize;
    private int total;
    private int totalrows;
    private int start;
    private int end;
    private int allsize;
    private String orderby;
    private boolean orderReserve;
    private ArrayList pagingList;
    private int pagingListSize;
    private int startPagingNo;
    private int endPagingNo;
    private boolean ascending;
    private String simpleDomain;//简单域模型
    public static PageInfo getEmptyPage()
    {
        PageInfo page = new PageInfo();
        page.setRowsize(DEFAULT_PAGESIZE);
        page.setList(new ArrayList());
        page.setPagingList(new ArrayList());
        page.setTotal(1);
        page.setPageno(1);
        page.setAllsize(0);
        return page;
    }

    public void excecute()
    {
        if(pageno <= 0)
            pageno = 1;
        if(rowsize <= 0)
            rowsize = DEFAULT_PAGESIZE;
        int start = 1;
        if(totalrows <= (pageno - 1) * rowsize)
            if(totalrows % rowsize == 0)
                pageno = totalrows / rowsize;
            else
                pageno = totalrows / rowsize + 1;
        if(pageno <= 0)
            pageno = 1;
        start = rowsize * (pageno - 1);
        int totalpage = 1;
        if(totalrows % rowsize == 0)
            totalpage = totalrows / rowsize;
        else
            totalpage = totalrows / rowsize + 1;
        total = totalpage;
        this.start = start;
        end = (start + rowsize) - 1;
        if(end >= totalrows)
            end = totalrows - 1;
        calPagingList();
    }

    public void calPagingList()
    {
        if(pagingListSize == 0)
        {
            if(total < 10)
                endPagingNo = total;
            else
                endPagingNo = 10;
        } else
        {
            int pagenoSize = pageno / pagingListSize;
            if(pageno % pagingListSize > 0)
                startPagingNo = pagenoSize * pagingListSize;
            else
                startPagingNo = (pagenoSize - 1) * pagingListSize;
            startPagingNo++;
            endPagingNo = (startPagingNo + pagingListSize) - 1;
            if(endPagingNo > total)
                endPagingNo = total;
        }
        pagingList.clear();
        for(int i = startPagingNo; i <= endPagingNo; i++)
            pagingList.add(new Integer(i));

    }

    public boolean isHasNextPage()
    {
        return pageno < total;
    }

    public boolean isHasPrePage()
    {
        return pageno > 1;
    }

    public boolean isHasNextPagingList()
    {
        return endPagingNo < getTotal();
    }

    public boolean isHasPrePagingList()
    {
        return pagingListSize > 0 && pageno > pagingListSize;
    }

    public int getTotalrows()
    {
        return totalrows;
    }

    public void setTotalrows(int totalrows)
    {
        this.totalrows = totalrows;
    }

    public List getList()
    {
        return list;
    }

    public int getPageno()
    {
        return pageno;
    }

    public int getRowsize()
    {
        return rowsize;
    }

    public int getTotal()
    {
        return total;
    }

    public void setList(List list)
    {
        this.list = list;
    }

    public void setPageno(int i)
    {
        pageno = i;
    }

    public void setRowsize(int i)
    {
        rowsize = i;
    }

    public void setTotal(int i)
    {
        total = i;
    }

    public int getStart()
    {
        return start;
    }

    public void setStart(int start)
    {
        this.start = start;
    }

    public String getOrderby()
    {
        return orderby;
    }

    public void setOrderby(String orderby)
    {
        this.orderby = orderby;
    }

    public boolean isOrderReserve()
    {
        return orderReserve;
    }

    public void setOrderReserve(boolean orderReserve)
    {
        this.orderReserve = orderReserve;
    }

    public ArrayList getPagingList()
    {
        return pagingList;
    }

    public void setPagingList(ArrayList pagingList)
    {
        this.pagingList = pagingList;
    }

    public int getPagingListSize()
    {
        return pagingListSize;
    }

    public void setPagingListSize(int pagingListSize)
    {
        this.pagingListSize = pagingListSize;
        calPagingList();
    }

    public int getEnd()
    {
        return end;
    }

    public void setEnd(int end)
    {
        this.end = end;
    }

    public int getEndPagingNo()
    {
        return endPagingNo;
    }

    public int getStartPagingNo()
    {
        return startPagingNo;
    }

    public boolean isAscending()
    {
        return ascending;
    }

    public void setAscending(boolean ascending)
    {
        this.ascending = ascending;
    }

    public String getSimpleDomain() {
        return simpleDomain;
    }

    public void setSimpleDomain(String simpleDomain) {
        this.simpleDomain = simpleDomain;
    }

    public int getAllsize() {
        if(this.list!=null && !this.list.isEmpty()){
            this.allsize=this.list.size();
        }else{
            this.allsize=0;
        }
        return allsize;
    }

    public void setAllsize(int allsize) {
        this.allsize = allsize;
    }
}
