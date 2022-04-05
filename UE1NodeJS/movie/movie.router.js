const express = require('express');
const router = express.Router();

const { listAction, removeAction, editAction, saveAction, viewMovieAction, updateAction } = require('./movie.controller');
const { ensureLoggedIn } = require('connect-ensure-login');

router.get('/',  listAction);
router.get('/remove/:id', ensureLoggedIn('/login'), removeAction);
router.get('/view/:id', viewMovieAction)
router.get('/edit/:id?',ensureLoggedIn('/login'), editAction);
router.post('/update',ensureLoggedIn('/login'), updateAction);
router.post('/save',ensureLoggedIn('/login'), saveAction);

module.exports = router;
