let addCatMenuOpen = false;
setup();

function setup() {
    getCats().then(() => {
        checkLogin().then(() => {
            if (loggedUser.role.name === 'Employee') employeeSetup();
        });
    });
}

async function getCats() {
    let url = baseUrl + '/cats';
    let response = await fetch(url);
    if (response.status === 200) {
        let cats = await response.json();
        populateCats(cats);
    }
}

function populateCats(cats) {
    let catSection = document.getElementById('catSection');
    catSection.innerHTML = '';

    if (cats.length > 0) {
        let table = document.createElement('table');
        table.id = 'catTable';

        table.innerHTML = `
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Age</th>
                <th>Breed</th>
                <th>Special Needs</th>
                <th></th>
            </tr>
        `;

        for (let cat of cats) {
            let tr = document.createElement('tr');
            tr.innerHTML = `
                <td>${cat.id}</td>
                <td>${cat.name}</td>
                <td>${cat.age}</td>
                <td>${cat.breed.name}</td>
            `;
            let td = document.createElement('td');
            let ul = document.createElement('ul');
            for (let sn of cat.specialNeeds) {
                let li = document.createElement('li');
                li.innerHTML = sn.name;
                ul.appendChild(li);
            }
            td.appendChild(ul);
            tr.appendChild(td);

            let adoptBtn = document.createElement('button');
            adoptBtn.type = 'button';
            adoptBtn.id = cat.name + '_' + cat.id;
            adoptBtn.textContent = 'Adopt';
            adoptBtn.disabled = !loggedUser;
            // <button type="button" id="Howard_6"
            //  disabled="false">Adopt</button>
            
            let btnTd = document.createElement('td');
            btnTd.appendChild(adoptBtn);
            tr.appendChild(btnTd);
            table.appendChild(tr);
            
            adoptBtn.addEventListener('click', adoptCat);
        }

        catSection.appendChild(table);
    } else {
        catSection.innerHTML = 'No cats are available.';
    }
}

async function adoptCat() {
    let btnId = event.target.id;
    let index = btnId.indexOf('_'); // find underscore (see line 46)
    let id = btnId.slice(index+1); // get text after underscore
    let name = btnId.replace('_', ''); // remove underscore
    if (confirm('You want to adopt ' + name + '?')) {
        let url = baseUrl + '/cats/adopt/' + id;
        let response = await fetch(url, {method:'PUT'});
        switch (response.status) {
            case 200:
                alert('You adopted ' + name + '!');
                break;
            case 409:
                alert('That cat doesn\'t seem to exist...');
                break;
            case 401:
                alert('Hold on, you\'re not logged in!');
                break;
            default:
                alert('Something went wrong.');
                break;
        }
    }
}

function employeeSetup() {
    let employeeSpan = document.getElementById('emp');
    employeeSpan.removeAttribute('hidden');
    // add cat
    if (!(document.getElementById('addCatBtn'))) {
        let addCatBtn = document.createElement('button');
        addCatBtn.type = 'button';
        addCatBtn.textContent = 'Add Cat';
        addCatBtn.id = 'addCatBtn';
        addCatBtn.onclick = addCatMenu;
        employeeSpan.appendChild(addCatBtn);
    }

    // edit cat
    let catsTable = document.getElementById('catTable');
    for (let tr of catsTable.childNodes) {
        if (tr.nodeName === 'TR') {
            let td = document.createElement('td');
            if (tr != catsTable.childNodes.item(0)) {
                let editBtn = document.createElement('button');
                editBtn.id = 'edit_' + tr.childNodes.item(1).textContent;
                editBtn.type = 'button';
                editBtn.textContent = 'Edit';
                editBtn.onclick = editCat;
                td.appendChild(editBtn);
            }
            tr.appendChild(td);
        }
    }
}

function addCatMenu() {
    let employeeSpan = document.getElementById('emp');
    addCatMenuOpen = !addCatMenuOpen;
    console.log('add cat menu open? ' + addCatMenuOpen);

    if (addCatMenuOpen) {
        employeeSpan.innerHTML += `<form id="add-cat-form">
        <label for="name">Name:</label>
        <input type="text" id="name" placeholder="Name" required />
        
        <label for="age">Age:</label>
        <input type="number" id="age" placeholder="age" required />
        
        <label for="breed">Breed:</label>
        <select id="breed" required>

        </select>

        <button type="button" onclick="addCat()" id="submit-add-cat-form" >Submit</button>
        </form>
        `;
        populateBreeds();
        let submitAddBtn = document.getElementById('submit-add-cat-form');
        submitAddBtn.onclick = addCat;
    } else {
        employeeSpan.removeChild(document.getElementById('add-cat-form'));
    }

    let addCatBtn = document.getElementById('addCatBtn');
    addCatBtn.onclick = addCatMenu;
}

function editCat() {
    let editBtn = event.target;
    let editId = event.target.id;
    let editTd = editBtn.parentElement;
    let editTr = editTd.parentElement;

    let nodes = editTr.childNodes;

    editTr.innerHTML = `
        <td>${nodes.item(1).innerHTML}</td>
        <td><input id = "eCName" type = "text" value = ${nodes.item(3).innerHTML}></td>
        <td><input id = "eCAge" type = "text" value = ${nodes.item(5).innerHTML}></td>
        <td>${nodes.item(7).innerHTML}</td>
        <td>${nodes.item(9).innerHTML}</td>
        <td><button disabled = 'true'>Adopt</button>
        <button id = ${editId}>Save</button></td>
        `;
    //<input id = "eCBreed" type = "text" value = ${nodes[3].innerHTML}>
    editBtn = document.getElementById(editId);
    editBtn.addEventListener('click', saveCat);

}

async function saveCat()
{
    let btnId = event.target.id;
    let index = btnId.indexOf('_');
    let id = btnId.slice(index+1); // get text after underscore

    let url = baseUrl + '/cats/' + id;

    let response = await fetch(url);

    let cat = await response.json();

    cat.name = document.getElementById('eCName').value;
    cat.age = document.getElementById('eCAge').value;

    let newResponse = await fetch(url,{method:'PUT',body:JSON.stringify(cat)});
    if (newResponse.status === 200) {
        alert('Updated successfully.');
    } else {
        alert('Something went wrong.');
    }
    
    setup();
}

async function populateBreeds() {
    let breedDropDown = document.getElementById('breed');
    let url = baseUrl + '/breeds';
    let response = await fetch(url);
    if (response.status === 200) {
        let breeds = await response.json();
        for (let breed of breeds) {
            let breedOption = document.createElement('option');
            breedOption.value = breed.id;
            breedOption.textContent = breed.name;
            breedDropDown.appendChild(breedOption);
        }
    } else {
        alert('Something went wrong.');
        addCatMenuOpen = true;
        addCatMenu();
    }
}

async function addCat() {
    let cat = {};
    cat.id = 0;
    cat.name = document.getElementById('name').value;
    cat.age = document.getElementById('age').value;
    cat.status = {};
    cat.status.id = 0;
    cat.status.name = '';
    cat.breed = {};
    cat.breed.id = document.getElementById('breed').value;

    let url = baseUrl + '/cats';
    let response = await fetch(url, {method:'POST', body:JSON.stringify(cat)});
    if (response.status === 201) {
        alert('Added cat successfully.');
    } else {
        alert('Something went wrong.');
    }
    addCatMenu();
    setup();
}