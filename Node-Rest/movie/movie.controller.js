const movieModel = require("./movie.model");

function listAction(request, response) {
  const sort = request.query.sort ? request.query.sort : '';
  
  movieModel
    .getAll(sort, "sepp")
    .then((movies) => response.json(movies))
    .catch((error) =>
      response.status(error === "Database error" ? 500 : 400).json(error)
    );
}

function listActionNoLogin(request, response) {
  const sort = request.query.sort ? request.query.sort : "";

  movieModel
    .getAll(sort)
    .then((movies) => response.json(movies))
    .catch((error) =>
      response.status(error === "Database error" ? 500 : 400).json(error)
    );
}

function viewAction(request, response) {
  movieModel
    .get(request.params.id, "sepp")
    .then((movie) => response.json(movie))
    .catch((error) =>
      response.status(error === "Database error" ? 500 : 400).json(error)
    );
}

function removeAction(request, response) {
  movieModel
    .remove(request.params.id, "sepp")
    .then(() => response.status(200).json())
    .catch((error) =>
      response.status(error === "Database error" ? 500 : 400).json(error)
    );
}

function newAction(request, response) {
  console.log(request.body);
  movieModel
    .newMovie(
      request.body.title,
      request.body.year,
      request.body.published,
      "sepp"
    )
    .then((newMovie) => response.status(200).json(newMovie))
    .catch((error) =>
      response.status(error === "Database error" ? 500 : 400).json(error)
    );
}

function clearAllAction(request, response) {
  movieModel
    .removeAll("sepp")
    .then(() => response.status(200).json())
    .catch(error => response.status(error === "Database error" ? 500 : 400).json(error));
}

function updateMovieAction(request, response) {

  movieModel
    .updateMovie(request.params.id, "sepp", request.body.title, request.body.year, request.body.published )
    .then(() => response.status(200).json())
    .catch(error => response.status(error === "Database error" ? 500 : 400).json(error) );
}

module.exports = { listAction, viewAction, removeAction, newAction, clearAllAction, updateMovieAction, listActionNoLogin };
