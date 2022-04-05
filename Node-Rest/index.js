const express = require("express");
const app = express();
const movieRouter = require("./movie/movie.router.js");
const bodyParser = require("body-parser");
app.use(bodyParser.json());

app.use((request, response, next) => {
  response.header("Access-Control-Allow-Origin", "*");
  response.header("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE");
  response.header(
    "Access-Control-Allow-Headers",
    "Origin, X-Requested-With, Content-Type, Accept,Authorization"
  );
  next();
});


app.use("/movie", movieRouter);
app.get("/", (request, response) => response.redirect("/movie"));
app.listen(8080, () => console.log("Web-Service listen on port 8080"));
