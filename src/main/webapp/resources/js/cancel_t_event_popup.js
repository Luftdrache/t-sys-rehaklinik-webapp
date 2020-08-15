function setVariable(tEventId) {
    document.querySelector(".popup").style.display = "flex";
    document.getElementById("tEvent").value = tEventId;
}

document.getElementById('close-icon').addEventListener('click', function () {
    document.querySelector(".popup").style.display = "none";
});