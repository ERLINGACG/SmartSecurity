import {computed, ref} from "vue";

class PageUtils{
    static Page(length, pageNumber, _pageSize,pageOriginData){
        const currentPage=ref(pageNumber);
        const total=ref(length);
        const pageSize=ref(_pageSize);
        const pageData=computed(()=>{
            const start=(currentPage.value-1)*pageSize.value;
            return pageOriginData.value.slice(start,start+pageSize.value);
        });
        return {
            currentPage,
            total,
            pageSize,
            pageData,
        }
    }
}