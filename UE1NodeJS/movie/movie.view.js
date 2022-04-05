
function renderList(movies, user, userNameList) {
    return `
        <table>
            <tr>
                <th>Titel</th>
                <th>Jahr</th>
                <th>Public</th>
                <th>Owner</th>
            </tr>
            ${movies.map(movie => 
                movie.public || movie.user == user?.id ?
                    `<tr> 
                        <td>${movie.title}</td>
                        <td>${movie.year}</td>
                        <td>${movie.public == 1 ? `true` : `false`}</td>
                        <td>${userNameList.map(element => movie.user === element.id ? element.username : `` ).join('')}</td>
                        ${movie.user != user?.id ? `<td><a href="/movie/view/${movie.id}">Ansehen</a></td>` : ``}
                        ${movie?.user != null && movie?.user == user?.id ? 
                        `<td><a href="/movie/remove/${movie.id}">Löschen</a></td>
                         <td><a href="/movie/edit/${movie.id}">Ändern</a></td>`
                        : ``
                        }
                    </tr>`
                : ``
            )
            .join('')}
        </table>
        `;
}

function renderMovie(movie, user) {
    return `
        <form action="/movie/${movie.id == -1 ? `save` : `update`}" method="post">
            <input type="hidden" name="id" value="${movie?.id}">
            <div>
                <label for="title">Titel:</label>
                <input type="text" id="title" name="title" value="${movie?.title}">
                <br>
                <label for="year">Year:</label>
                <input type="number" id="year" name="year" value="${movie?.year ? `${movie?.year}` : 2000}">
                <br>
                <label for="user">User:</label>
                <input type="text" id="user" name="user" value="${user?.username}" readonly>
                <br>
                <label for="public">Public:</label>
                <input type="checkbox" id="public" name="public" value="1" ${movie?.published == 1 ? `checked` : ``}>
            </div>  
            <input type="submit" value="Speichern"> <a href="/">Back</a>
        </form>
    `;
}


function viewMovie(movie, userNameList) {
    return `

        <div>
            <hr>
            Titel: ${movie?.title}
            <hr>
            Jahr: ${movie?.year}
            <hr>
            Öffentlich: ${movie?.public ? `Ja` : `Nein`}
            <hr>
            Besitzer: ${userNameList.map(element => movie.user === element.id ? element.username : `` ).join('')}
        </div>
        <a href="/">Back</a>
    
    `
}

function viewError(error) {
    return `
        <div>Leider ist der Server momentan überlastet. <a href="/">Wiederholen</a> ${error}</div>
    
    `
}

module.exports = { renderList, renderMovie, viewMovie, viewError };