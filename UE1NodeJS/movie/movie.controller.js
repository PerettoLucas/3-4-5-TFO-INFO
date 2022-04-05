const movieModel = require('./movie.model');
const movieView = require('./movie.view');
const indexView = require('../index.view');
const authView = require('../auth/auth.view');


function listAction(request, response) {
    movieModel.getAllMoviesDB().then(result => {
        movieModel.getAllUsernamesDB().then(usernames => {
            response.send(indexView.render(
                authView.head(request?.user), 
                movieView.renderList(result, request?.user, usernames)
            ));
        }).catch(error => response.send(movieView.viewError(error)));
    }).catch(error => response.send(movieView.viewError(error)));

}

function viewMovieAction(request, response) {
    movieModel.getAllMoviesDB().then(movies => {
        movieModel.getAllUsernamesDB().then(usernameList => {
            movies.map(movie => {
                if(movie.id == request.params.id) {
                    response.send(indexView.render(
                        authView.head(request?.user),
                        movieView.viewMovie(movie, usernameList)
                    ));
                }
            });
        });
    }).catch();

}

function removeAction(request, response) {
    movieModel.remove(request.params.id);
    response.redirect(request.baseUrl); 
}

async function editAction(request, response) {
    let movie = { id: '-1', title: '', year: '', public: undefined, user: '' };
    if (request.params.id) {
        await movieModel.getMovieByID(request.params.id).then(result => {
            movie = result;
        }).catch();
    }

    response.send(
        indexView.render(
            authView.head(request?.user),
            movieView.renderMovie(movie, request?.user)
        )
    );
}

async function updateAction(request, response) {
    const movie = {
        id: request.body.id,
        title: request.body.title,
        year: request.body.year,
        public: request.body.published == undefined ? 0 : 1
    }

    await movieModel.updateMovieDB(movie)
        .then(result => console.log('updated movie : ' + result))
        .catch();

    response.redirect(request.baseUrl);

}

function saveAction(request, response) {
    const movie = {
        id: request.body.id,
        title: request.body.title,
        year: request.body.year,
        public: request.body.public == undefined ? 0 : 1,
        user: request.body.user
    };


    movieModel.save(movie, request?.user);
    response.redirect(request.baseUrl);
}

module.exports = { listAction, removeAction, editAction, saveAction, viewMovieAction, updateAction };