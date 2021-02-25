let baseUrl = '/CatApp2';
let nav = document.getElementById('navBar');
let loggedUser = null;
checkLogin();
setNav();
function setNav() {
    nav.innerHTML = `
            <a href="index.html"><strong>Cat App</strong></a>
            <a href="viewCats.html">View Cats</a>`;
    if (!loggedUser) {
        nav.innerHTML += `
            <form>
                <label for="user">Username: </label>
                <input id="user" name="user" type="text" />
                <label for="pass"> Password: </label>
                <input id="pass" name="pass" type="password" />
                <button type="button" id="loginBtn">Log In</button>
            </form>
        `;
    } else {
        nav.innerHTML += `
            <a href="myCats.html">My Cats</a>
            <span>
                <a href="profile.html">${loggedUser.username}&nbsp;</a>
                <button type="button" id="loginBtn">Log Out</button>
            </span>
        `;
    }

    let loginBtn = document.getElementById('loginBtn');
    if (loggedUser) loginBtn.onclick = logout;
    else loginBtn.onclick = login;
}

async function login() {
    // http://localhost:8080/users?user=sierra&pass=pass
    let url = baseUrl + '/user/login?';
    url += 'user=' + document.getElementById('user').value + '&';
    url += 'pass=' + document.getElementById('pass').value;
    let response = await fetch(url, {method: 'POST'});
    
    switch (response.status) {
        case 200: // successful
            loggedUser = await response.json();
            setNav();
            break;
        case 400: // incorrect password
            alert('Incorrect password, try again.');
            document.getElementById('pass').value = '';
            break;
        case 404: // user not found
            alert('That user does not exist.');
            document.getElementById('user').value = '';
            document.getElementById('pass').value = '';
            break;
        default: // other error
            alert('Something went wrong.');
            break;
    }
}

async function logout() {
    let url = baseUrl + '/user';
    let response = await fetch(url, {method:'DELETE'});

    if (response.status != 200) alert('Something went wrong.');
    loggedUser = null;
    setNav();
}

async function checkLogin() {
    let url = baseUrl + '/user';
    let response = await fetch(url);
    if (response.status === 200) loggedUser = await response.json();
    setNav();
}