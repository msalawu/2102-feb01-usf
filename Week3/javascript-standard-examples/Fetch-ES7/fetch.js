/*
    MUST BE FILLED IN TO CUSTOMIZE EXAMPLE
*/
// Endpoint you are sending a GET request to
let apiURL = '';

document.getElementById('getData').onclick = getData;

function getData() {
    // If using input for identifiers, etc.
    // For example, if using PokeAPI, this may be the Pokemon's ID.
    var userInput = document.getElementById('dataInput').value; 

    async function fetchData() {
        let response = await fetch(apiURL + userInput);

        if (response.status === 200) {
            let data = await response.json();
            populateData(data);
        } else {
            /*
                Handle error
            */
        }
    }
}

function populateData(response) {
    var dataSection = document.getElementById('data');
    /*
        Process data from the API to display on the page.
    */

}