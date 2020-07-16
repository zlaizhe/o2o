<%@ page import="java.util.Enumeration" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>首页</title>
</head>
<body>
<a href="./shopadmin/shopoperation">访问店铺注册/更新页面</a><br>
<a href="./shopadmin/shoplist">访问店铺列表</a><br>
<a href="./shopadmin/shopmanagement">访问店铺管理页面</a><br>
<a href="./shopadmin/productcategorymanagement">访问商品分类管理页面</a><br>
<a href="./shopadmin/productoperation">访问商品添加/更新页面</a><br>
<a href="./shopadmin/productmanagement">访问商品管理页面</a><br>
<hr>
<a href="./frontend/index">访问前端首页</a>
<hr>
<h3>session中的user</h3>
${user}<br>
<h3>session中的currentShop</h3>
 ${currentShop}<br>
<hr>
<h3>session中的所有信息</h3>
<%
    Enumeration<String> attributeNames = session.getAttributeNames();
    while (attributeNames.hasMoreElements()) {
        String name = attributeNames.nextElement();
        Object attribute = session.getAttribute(name);
        out.println("<strong>key</strong>: " + name + "<br>  <strong>value</strong>: " + attribute + "<br><br>");
    }
%>
</body>
</html>
