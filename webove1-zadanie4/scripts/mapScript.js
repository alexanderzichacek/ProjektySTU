fetch('../data.json')
    .then((response) => response.json())
    .then((json) => {
        let data = json.data;
        initMap(data);
        for (let elem in data) {
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
    });

function initMap(data) {
    const initialization = {lat: 48.14816, lng: 17.10674};
    const map = new google.maps.Map(document.getElementById("map"), {
        zoom: 12,
        center: initialization,
    });

    let latitude;
    let longitude;
    let dataMarker;

    for (let elem in data) {
        latitude = data[elem].latitude;
        longitude = data[elem].longitude;
        dataMarker = {lat: Number(latitude), lng: Number(longitude)};

        let marker = new google.maps.Marker({
            id: data[elem].id,
            position: dataMarker,
            map: map,
        });

        marker.addListener("click", () => {
            showModal(marker.id)
        });
    }

    let mode = 'DRIVING';
    let service = [];
    let renderer = [];
    let clicked = false;

    const traceButton = document.getElementById("traceButton");
    traceButton.onclick = function (){
        if (clicked) {
            clicked = false;
            initMap(data);
        }
        else {
            clicked = true;
            for (let i = 0; i < data.length; i++) {
                showTrace(service, renderer, i);
            }
        }
    }

    for (let i = 0; i < data.length; i++) {
        service.push(new google.maps.DirectionsService);
        renderer.push(new google.maps.DirectionsRenderer({
            polylineOptions: {strokeColor: "red"},
            suppressMarkers: true
        }));
    }

    function showTrace(directionsService, rederer, index) {
        directionsService[index].route({
            origin: {lat: Number(data[index].latitude), lng: Number(data[index].longitude)},
            destination: {lat: Number(data[index + 1].latitude), lng: Number(data[index + 1].longitude)},
            travelMode: mode,
        }, function (response, status) {
            if (status === 'OK') {
                rederer[index].setDirections(response);
            } else console.log('Directions request failed due to ' + status);
        });
        rederer[index].setMap(map);
    }
}

function showModal(id) {
    let selectedImage = document.getElementById("carousel-inner");
    for (let i = 0; i < selectedImage.children.length; i++) {
        let child = selectedImage.children[i];
        child.setAttribute("class", "carousel-item");
    }
    let setActive = document.getElementById("modalImage" + id);
    setActive.parentElement.classList.add("active");
    $('#exampleModal').modal('toggle');
}

