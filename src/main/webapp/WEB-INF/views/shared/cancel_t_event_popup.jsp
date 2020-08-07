<div class="popup">
    <div class="popup-content">
        <img src="/resources/images/close-icon.png" alt="" class="close" id="close-icon">
        <form:form action="${pageContext.request.contextPath}/nurse/cancel-treatment-event"
              method="post"
              class="form-horizontal"
              role="form">
            <input type="hidden" id="tEvent" name="tEvent">
            Please specify the cancel reason:
            <textarea autofocus rows="4" id="cancelReason"
                      name="cancelReason"
                      class="form-control" style="resize: none"></textarea>
            <div style="padding-left: 40%; padding-top: 2px">
                <input type="submit" class="btn login_btn" value="Specify"
                       style="background-color: orange; opacity: 0.9;"/>
            </div>
        <form:form>
    </div>
</div>



