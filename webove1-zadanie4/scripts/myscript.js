let active = -1;

fetch('../data.json')
    .then((response) => response.json())
    .then((json) => {
        let data = json.data;
        let picDiv = document.getElementById("pictures");
        for (let elem in data) {
            let image = document.createElement("img");
            image.setAttribute("class", "column");
            image.setAttribute("id", data[elem].id);
            image.setAttribute("src", data[elem].path);

            image.setAttribute("data-bs-target", "#carouselExample");
            image.setAttribute("data-slide-to", elem);

            image.addEventListener("click", function () {
                active = this.id;
            });

            picDiv.appendChild(image);

            const imageDesc = document.createElement("p");
            const imageTitle = document.createElement("h1");
            const imageDate = document.createElement("p");
            const div = document.createElement("div");
            const imageModal = document.createElement("img");

            div.setAttribute("class", "carousel-item");

            imageModal.setAttribute("class", "d-block w-100");
            imageModal.setAttribute("id", "modalImage" + data[elem].id);
            imageModal.setAttribute("src", data[elem].path);

            imageDesc.innerText = data[elem].description;
            imageTitle.innerText = data[elem].title;
            imageDate.innerText = data[elem].dateAndTime;

            div.appendChild(imageTitle);
            div.appendChild(imageModal);
            div.appendChild(imageDesc);
            div.appendChild(imageDate);
            document.getElementById("carousel-inner").appendChild(div);
        }

        let searchInput = document.getElementById('searchInput');
        searchInput.addEventListener('keyup', filterPictures);

        function filterPictures() {
            let filterValue = document.getElementById('searchInput').value.toLowerCase();

            for (let elem in data) {
                let titleMatch = data[elem].title;
                let descMatch = data[elem].description;
                if (titleMatch.toLowerCase().includes(filterValue) || descMatch.toLowerCase().includes(filterValue)) {
                    document.getElementById("pictures").children.item(elem).style.display = '';
                } else {
                    document.getElementById("pictures").children.item(elem).style.display = 'none';
                }
            }
        }
    });

function showModal() {
    let selectedImage = document.getElementById("carousel-inner");
    for (let i = 0; i < selectedImage.children.length; i++) {
        let child = selectedImage.children[i];
        child.setAttribute("class", "carousel-item");
    }

    let setActive = document.getElementById("modalImage" + active);
    setActive.parentElement.classList.add("active");
}

const playButton = document.getElementById("playButton");
const pauseButton = document.getElementById("pauseButton");
const element = document.getElementById("carouselExample");
const myCarousel = new bootstrap.Carousel(element);

playButton.addEventListener("click", function () {
    myCarousel.cycle();
});

pauseButton.addEventListener("click", function () {
    myCarousel.pause();
});