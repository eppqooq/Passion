package Passion.Spring.tech;

import java.util.ArrayList;
import java.util.List;



public class Pagination<T> {
    List<T> paginationObject; // pagination 당할 녀석. member냐 board냐 그것이 궁금하다.
    private int currentPageNum; // 현재 페이지 번호.
    private int totalDataCount;// 총 자료의 개수
    private int dataDisplayPerPage;// 한 페이지에 몇 개의 자료를 보여줄 것인가
    private int maxPageValue;// 최대 페이지 값 = 페이지의 개수 ( 총 자료수 / 한 페이지 보여줄 자료 수 )
    private int minPageValue;// 가장 작은 페이지는 1이겠죠?
    private int pageDisplayPerPage;// 한 페이지에 페이지 개수를 몇 개 보여줄 것 인가

    public Pagination(List<T> paginationObject,int currentPageNum,
                      int totalDataCount, int dataDisplayPerPage, int pageDisplayPerPage) // 가득찬 생성자
    {
        this.paginationObject = paginationObject;
        this.currentPageNum=currentPageNum;
        this.totalDataCount = totalDataCount;
        this.dataDisplayPerPage = dataDisplayPerPage;
        this.pageDisplayPerPage = pageDisplayPerPage;
        if(totalDataCount%dataDisplayPerPage == 0)
            this.maxPageValue = totalDataCount/dataDisplayPerPage;
        else
            this.maxPageValue = (totalDataCount/dataDisplayPerPage) + 1; // 나눗셈 결과에 나머지가 있으면, (몫 + 1)개의 페이지가 필요

        this.minPageValue=1; // 가장 작은 페이지 번호는 1이겠지 뭐
    }
    public Pagination(List<T> paginationObject, int currentPageNum) // Object와 현재 페이지만 가진 생성자
    {
        this.paginationObject = paginationObject;   // 어떤 객체세요
        this.currentPageNum=currentPageNum;         // 현재 몇 쪽 보고 계세요
        this.totalDataCount = paginationObject.size();  // 넘긴 배열 개수나 좀 압시다
        this.dataDisplayPerPage = 5;                    // 한 페이지당 5개의 자료 보여줄겁니다
        this.pageDisplayPerPage = 5;                    // 한 페이지당 5개의 페이지 번호가 있을 겁니다. 아.. 페이지 개수 모자르면 어쩌지...
        if(totalDataCount%dataDisplayPerPage == 0)
            this.maxPageValue = totalDataCount/dataDisplayPerPage;
        else
            this.maxPageValue = (totalDataCount/dataDisplayPerPage) + 1; // 나눗셈 결과에 나머지가 있으면, (몫 + 1)개의 페이지가 필요
        this.minPageValue=1; // 가장 작은 페이지 번호는 1이겠지 뭐
    }
    public List<T> paginationObject()
            //
    {
        List<T> helpers = new ArrayList<>();// pagination 된 배열.
        int start=0, end=0;
        start = (currentPageNum-1) * dataDisplayPerPage; // 배열 중 첫 번째 표시할 요소의 첨자
        end = start + dataDisplayPerPage - 1;            // 배열 중 마지막 표시할 요소의 첨자
        int i=0; //첨자 처리를 도와줄 친구

        for(T helper : paginationObject)     // 입력된 배열 객체에서
        {
            if(start<=i && i<=end)
                helpers.add(helper);            // 내가 원하는 첨자의 요소만 helpers 객체에 담기.
            i++;
            if(i>end) break;
        }
        return helpers;
    }
    public List<Integer> paginationPage()
    {
        List<Integer> helpers = new ArrayList<>();

        Integer helper = pageDisplayPerPage / 2;
        System.out.println("pageDisplayPerPage = " + pageDisplayPerPage);
        System.out.println("pageDisplayPerPage/2 = " + pageDisplayPerPage/2);
        System.out.println("currentPageNum = " + currentPageNum);
        int ii;

        if (currentPageNum <= helper)
        {
            for (int i=1; i<=pageDisplayPerPage; i++)
            {
                if (i<=maxPageValue)
                helpers.add(i);
            }
        }
        else
        {
            for (int i = 0; i <= pageDisplayPerPage - 1; i++) {
                ii = currentPageNum - helper;
                System.out.println("ii = " + ii);
                System.out.println("ii+i = " + (ii + i));
                if (0 <= ii+i && ii+i <= maxPageValue)
                    helpers.add(ii + i);
            }
        }
        System.out.println("helpers = " + helpers);
        return helpers;
    }


    public List<T> getPaginationObject() {
        return paginationObject;
    }

    public void setPaginationObject(List<T> paginationObject) {
        this.paginationObject = paginationObject;
    }

    public int getCurrentPageNum() {
        return currentPageNum;
    }

    public void setCurrentPageNum(int currentPageNum) {
        this.currentPageNum = currentPageNum;
    }

    public int getTotalDataCount() {
        return totalDataCount;
    }

    public void setTotalDataCount(int totalDataCount) {
        this.totalDataCount = totalDataCount;
    }

    public int getDataDisplayPerPage() {
        return dataDisplayPerPage;
    }

    public void setDataDisplayPerPage(int dataDisplayPerPage) {
        this.dataDisplayPerPage = dataDisplayPerPage;
    }

    public int getMaxPageValue() {
        return maxPageValue;
    }

    public void setMaxPageValue(int maxPageValue) {
        this.maxPageValue = maxPageValue;
    }

    public int getMinPageValue() {
        return minPageValue;
    }

    public void setMinPageValue(int minPageValue) {
        this.minPageValue = minPageValue;
    }

    public int getPageDisplayPerPage() {
        return pageDisplayPerPage;
    }

    public void setPageDisplayPerPage(int pageDisplayPerPage) {
        this.pageDisplayPerPage = pageDisplayPerPage;
    }
}
