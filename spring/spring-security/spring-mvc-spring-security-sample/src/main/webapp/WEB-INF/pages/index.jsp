<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <%@include file="common/head_script.jsp" %>
    <title>首页</title>
</head>
<body>
<div>
    <label id="username" username="<sec:authentication property='name'/>" hidden="hidden"></label>
    <h5 id="welcome"></h5>
</div>
<hr>

<div>
    <h2>简介</h2>
    <p>本网站是集成了基本的spring-security权限控制功能的一个demo。</p>
    <p>本网站的权限控制基于以下简单的场景：</p>
    <ol>
        <li>学生信息和学生的详细信息仅可以被学生自己查看；</li>
        <li>教师的信息仅可被教师自己查看；</li>
        <li>班级信息可以同时被学生和教师查看；</li>
        <li>教工消息由消息通知管理员发出，教师可以查看教工消息；</li>
    </ol>
</div>

<hr>

<div>
    <h3>你可以使用以下账号：</h3>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>账号</th>
            <th>密码</th>
            <th>角色</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>teacher</td>
            <td>teacher</td>
            <td>ROLE_TEACHER</td>
        </tr>
        <tr>
            <td>student</td>
            <td>student</td>
            <td>ROLE_STUDENT</td>
        </tr>
        <tr>
            <td>notice</td>
            <td>notice</td>
            <td>ROLE_NOTICE</td>
        </tr>
        </tbody>
    </table>
</div>

<hr>

<div>
    <h3>页面介绍</h3>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>页面</th>
            <th>所需要的权限</th>
            <th>介绍</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td><a href="${pageContext.request.contextPath}/index.html">/index.html</a></td>
            <td>无</td>
            <td>首页，即当前页面</td>
        </tr>
        <tr>
            <td><a href="${pageContext.request.contextPath}/login.html">/login.html</a></td>
            <td>无</td>
            <td>登录页面</td>
        </tr>
        <tr>
            <td><a href="${pageContext.request.contextPath}/student.html">/student.html</a></td>
            <td>ROLE_STUDENT</td>
            <td>学生页面</td>
        </tr>
        <tr>
            <td><a href="${pageContext.request.contextPath}/student/detail.html">/student/detail.html</a></td>
            <td>ROLE_STUDENT</td>
            <td>学生详情页面</td>
        </tr>
        <tr>
            <td><a href="${pageContext.request.contextPath}/teacher.html">/teacher.html</a></td>
            <td>ROLE_TEACHER</td>
            <td>教师页面</td>
        </tr>
        <tr>
            <td><a href="${pageContext.request.contextPath}/class.html">/class.html</a></td>
            <td>ROLE_STUDENT, ROLE_TEACHER</td>
            <td>班级页面</td>
        </tr>
        <tr>
            <td><a href="${pageContext.request.contextPath}/notice.html">/notice.html</a></td>
            <td>ROLE_NOTICE, ROLE_TEACHER</td>
            <td>教工信息通知页面</td>
        </tr>
        </tbody>
    </table>
</div>

<hr>

<div>
    <h3>角色介绍</h3>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>角色</th>
            <th>介绍</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>ROLE_STUDENT</td>
            <td>学生角色</td>
        </tr>
        <tr>
            <td>ROLE_TEACHER</td>
            <td>教师角色</td>
        </tr>
        <tr>
            <td>ROLE_NOTICE</td>
            <td>通知角色</td>
        </tr>
        </tbody>
    </table>
</div>

</body>
<%@include file="common/footer.jsp" %>
</html>
