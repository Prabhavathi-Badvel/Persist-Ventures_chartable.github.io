/* app.js */
document.addEventListener("DOMContentLoaded", () => {
    const musicList = document.getElementById("music-list");
    const searchInput = document.getElementById("search");
    
    const songs = [
        { title: "Sunflower", artist: "Post Malone", img: "sunflower.jpg" },
        { title: "Blinding Lights", artist: "The Weeknd", img: "blinding_lights.jpg" },
        { title: "Levitating", artist: "Dua Lipa", img: "levitating.jpg" },
        { title: "Stay", artist: "The Kid LAROI", img: "stay.jpg" }
    ];
    
    function displaySongs(filter = "") {
        musicList.innerHTML = "";
        songs.filter(song => song.title.toLowerCase().includes(filter.toLowerCase()))
            .forEach(song => {
                const songElement = document.createElement("div");
                songElement.classList.add("song");
                songElement.innerHTML = `
                    <img src="${song.img}" alt="${song.title}">
                    <h3>${song.title}</h3>
                    <p>${song.artist}</p>
                    <button class="play-btn">Play</button>
                `;
                musicList.appendChild(songElement);
            });
    }
    
    searchInput.addEventListener("input", (e) => {
        displaySongs(e.target.value);
    });
    
    displaySongs();
});
