function getDefaultDate() {
    var now = new Date();
    var day = ("0" + now.getDate()).slice(-2);
    var month = ("0" + (now.getMonth() + 1)).slice(-2);
    var today = now.getFullYear() + "-" + (month) + "-" + (day);
    return today;
}


$(document).ready(function () {
    $("#startTreatment").val(getDefaultDate());
    $("#startTreatment").attr({"min": getDefaultDate()});
});

$(document).ready(function () {
    $("#endTreatment").val(getDefaultDate());
    $("#endTreatment").attr({"min": getDefaultDate()});
});


