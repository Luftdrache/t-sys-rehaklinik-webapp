<div class="header">
    <div class="header-menu">
        <div class="title">Med<span>Helper</span></div>
        <div class="sidebar-btn">
            <i class="fas fa-bars"></i>
        </div>
        <ul>
            <li>
                <form:form action="${pageContext.request.contextPath}/logout" method="post">
                <input type="submit" value="Sign out" class="btn login_btn"
                       style="background-color: orange; padding: 5px; margin-top: 15px">
                </form:form>
            </li>
        </ul>
    </div>
</div>