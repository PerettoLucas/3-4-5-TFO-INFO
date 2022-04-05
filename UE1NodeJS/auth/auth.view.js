function login(error) {
    return `
        <!DOCTYPE html>
        <html lang="en">
        <head>
            <meta charset="UTF-8">
            <title>Filmliste: Login</title>
        </head>
        <body>
            <p>
                ${error ? 'Benutzername und/oder Passwort falsch' : ''}
            </p>
            <form action="/login" method="POST">
                <div>
                    <label for="username">Benutzername:</label>
                    <input type="text" id="username" name="username" value="sepp" autofocus>
                </div>
                <div>
                    <label for="password">Passwort:</label>
                    <input type="password" id="password" name="password" value="sepp">
                </div>
                <input type="submit" value="Anmelden">
            </form>
        </body>
        </html>`;
}

function head(user) {
    return `
        ${user?.username ?
            `
            <div>

            <span>Sie sind angemeldet als <em>${user?.username}</em>. Ihr name lautet ${user?.firstname} ${user?.secondname} </span>
            <br>
            <a href="/logout">Logout</a>
            <a href="/movie/edit">Neuer Film</a>
            </div>
            `
            :
            
            `
            <div>
                <span >Melden Sie sich bitte an, um Ihre Filme hinzuzufügen</span>
                <br>
                <a href="/login">login</a>
            </div>
            `
        }

        
    `;
}

module.exports = { login, head };