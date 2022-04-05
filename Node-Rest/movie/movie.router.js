const express = require("express");
const router = express.Router();

const { listAction, viewAction, removeAction, newAction, clearAllAction, updateMovieAction, listActionNoLogin  } = require("./movie.controller");

router.get("/published", listActionNoLogin);
router.get("/", listAction);
router.get("/:id", viewAction);
router.delete("/clear", clearAllAction);
router.delete("/:id", removeAction);
router.post("/", newAction);
router.put("/:id", updateMovieAction);
module.exports = router;


