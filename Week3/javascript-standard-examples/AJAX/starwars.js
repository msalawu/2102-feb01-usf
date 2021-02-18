/*
    FILLED IN FOR USE WITH STAR WARS API (SWAPI)
*/
// Endpoint you are sending a GET request to
var apiURL = 'https://swapi.dev/api/starships/';

document.getElementById('getData').onclick = getData;

function getData() {
    // If using input for identifiers, etc.
    // For example, if using PokeAPI, this may be the Pokemon's ID.
    var userInput = document.getElementById('dataInput').value;

    // 4 steps to making an AJAX call
    // STEP 1: Create an XML Http Request object
    var xhttp = new XMLHttpRequest();

    // STEP 2: Set a callback function for the readystatechange event
    xhttp.onreadystatechange = receiveData;

    // STEP 3: Open the request
    xhttp.open('GET', apiURL + '' + userInput);

    // STEP 4: Send the request
    xhttp.send();

    // This needs to be an inner function so that it has closure to xhttp.
    function receiveData() {
        /*
            Different ready states of an XMLHttpRequest object
            0: UNSENT
            1: OPENED
            2: HEADERS RECEIVED
            3: LOADING
            4: DONE
        */
        // Emptying out the div before inserting new data.
        var dataSection = document.getElementById('data');
        dataSection.innerHTML = '';
        if (xhttp.readyState === 4) {
            if (xhttp.status === 200) {
                // Ready state is DONE, HTTP status code is "OK"
                var response = xhttp.responseText;
                response = JSON.parse(response);
                populateData(response);
            } else {
                // Ready state is DONE but status code is not "OK"
                dataSection.innerHTML = "The Force was not with you!"
            }
        } else {
            // Ready state is not DONE
            /*
                Can have some sort of "loading" action
            */
        }
    }
}

function populateData(response) {
    var dataSection = document.getElementById('data');
    
    var nameTag = document.createElement('h3');
    nameTag.innerHTML = response.name;

    dataSection.appendChild(nameTag);
    dataSection.innerHTML += 'Model: ' + response.model + '<br>';
    dataSection.innerHTML += 'Manufacturer: ' + response.manufacturer + '<br>';
    dataSection.innerHTML += 'Starship Class: ' + response.starship_class + '<br>';
}