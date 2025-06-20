const base = {
    get() {
        return {
            url : "http://localhost:8080/shixishijianxitong/",
            name: "shixishijianxitong",
            // 退出到首页链接
            indexUrl: 'http://localhost:8080/shixishijianxitong/front/index.html'
        };
    },
    getProjectName(){
        return {
            projectName: "面向智慧教育的实习实践系统"
        } 
    }
}
export default base
