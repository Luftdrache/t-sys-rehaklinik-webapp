function showInfo() {
    document.querySelector(".popup-about").style.display = "flex";
}

document.getElementById('about-close-icon').addEventListener('click', function () {
    document.querySelector(".popup-about").style.display = "none";
});