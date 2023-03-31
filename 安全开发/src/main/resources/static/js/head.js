/**
 * 菜单
 * */
$(function(){
    layui.use('element', function(){
        var element = layui.element;////lement模块的实例 返回的element变量为该实例的对象，携带一些用于元素操作的基础方法
        // 左侧导航区域（可配合layui已有的垂直导航）
        console.log("执行head.js")
        $.get("/index/type",function(data){
            if(data!=null){

                console.log(data.length);
                getMenus(data);
                element.render('nav');
            }else{
                layer.alert("权限不足，请联系管理员",function () {
                    //退出
                    window.location.href="/logout";
                });
            }
        });
    });
})
var getMenus=function(data){
    //回显选中
    var ul=$("<ul class='layui-nav layui-nav-tree' lay-filter='test'></ul>");
    for(var i=0;i < data.length;i++) {
        var node = data[i];
        if (node.level == 1) {
            console.log(node)
            var li = $("<li class='layui-nav-item' flag='" + node.id + "'></li>");
            var a = $("<a class='' href='javascript:;'>" + node.vulname + "</a>");
            li.append(a);
            //获取子节点

            var childArry = []
            for (var j = 0; j < data.length; j++) {
                if (data[i].id == data[j].fatherid) {

                    childArry.push(data[j])
                }
            }
            console.log("childArr", childArry)
            // var childArry = node.childrens;
            if (childArry.length > 0) {
                a.append("<span class='layui-nav-more'></span>");
                var dl = $("<dl class='layui-nav-child'></dl>");
                for (var y in childArry) {
                    var dd = $("<dd><a href='" + childArry[y].href + "'>" + childArry[y].vulname + "</a></dd>");
                    dl.append(dd);
                }
                li.append(dl);
            }
            ul.append(li);
        }
        $(".layui-side-scroll").append(ul);
    }
}
//根据菜单主键id获取下级菜单
//id：菜单主键id
//arry：菜单数组信息
function getParentArry(id, arry) {
    var newArry = new Array();
    for (var x in arry) {
        if (arry[x].pId == id)
            newArry.push(arry[x]);
    }
    return newArry;
}



// function updateUsePwd(){
//     layer.open({
//         type:1,
//         title: "修改密码",
//         fixed:false,
//         resize :false,
//         shadeClose: true,
//         area: ['450px'],
//         content:$('#pwdDiv')
//     });
// }