const base = {
    get() {
        return {
            url : "http://localhost:8080/xiyanghong/",
            name: "xiyanghong",
            // 退出到首页链接
            indexUrl: 'http://localhost:8080/xiyanghong/front/index.html'
        };
    },
    getProjectName(){
        return {
            projectName: "夕阳红公寓"
        } 
    }
}
export default base
