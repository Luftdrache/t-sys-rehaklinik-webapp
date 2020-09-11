$("#endTreatment").change(function () {
    var startDate = document.getElementById("startTreatment").value;
    var endDate = document.getElementById("endTreatment").value;

    if ((Date.parse(startDate) >= Date.parse(endDate))) {
        alert("End date should be greater than Start date");
    }
});