checkLogin().then(showInfo);
let changingUser = false;
let changingPass = false;

function showInfo() {
    let heading = document.getElementById('heading');
    heading.innerHTML = loggedUser.username;

    let info = document.getElementById('info');
    info.innerHTML = `
        <a onclick = "changeUsername">Change Username</a>
        <span id="usernameForm" hidden>
            
            <input id="newUser" type="text" />
        </span>
        <br>
        <span id="passwordForm" hidden>
            <a onclick = "changePassword">Change Password</a>
            <label for="newPass">New Password:&nbsp;</label>
            <input name="newPass" id="newPass" type="password />
            <br>
            <label for="newPassConf">Confirm:&nbsp;</label>
            <input name="newPassConf" id="newPassConf" type="password />
        </span>
        <br>
        <button id="submitChanges" onclick="submitChanges" type="button">
            Submit Changes
        </button>
        You have ${loggedUser.cats.length} cats.
    `;
}

function changeUsername() {
    changingUser = !changingUser;
    document.getElementById('usernameForm').toggleAttribute('hidden');
}

function changePassword() {
    changingPass = !changingPass;
    document.getElementById('passwordForm').toggleAttribute('hidden');
}

async function submitChanges() {
    let confirmed = false;
    if (changingUser) {
        loggedUser.username = document.getElementById('newUser').value;
        confirmed = true;
    }
    if (changingPass) {
        let newPass = document.getElementById('newPass');
        let newPassConf = document.getElementById('newPassConf');
        if (newPass === newPassConf) {
            loggedUser.passwd = newPass;
            confirmed = true;
        } else {
            alert('Passwords don\'t match. Try again.');
            confirmed = false;
        }
    }
    if (confirmed) {
        let url = baseUrl + '/users/' + loggedUser.id;
        let response = await fetch(url, {method:'PUT', body:JSON.stringify(loggedUser)});
        if (response === 202) {
            alert('Updated successfully.');
        } else {
            alert('Something went wrong.');
        }
        checkLogin().then(showInfo);
    }
}