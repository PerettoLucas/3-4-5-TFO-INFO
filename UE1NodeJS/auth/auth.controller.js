const passport = require('passport');
const expressSession = require('express-session');

const LocalStrategy = require('passport-local');
const authView = require('./auth.view');
const authModel = require('./auth.model');
const crypto = require('crypto');

module.exports = function(app) {
    app.use(
        expressSession({
        secret: 'top secret',
        resave: false,
        saveUninitialized: false
    }));

    passport.serializeUser((user, done) => done(null, user.username));

    passport.deserializeUser(async(id, done) => {
        const user = await authModel.get(id);
        if (user) {
            done(null, user);
        } else {
            done(null, false);
        }
    });

    app.use(passport.initialize());
    app.use(passport.session());

    
    passport.use(
        new LocalStrategy(async (username, password, done) => {
            const hash = crypto.createHash('sha256')
                .update(password)
                .digest('hex');

            const user = await authModel.get(username);
            if (user && user.password === hash) {
                user.password = '';
                done(null, user);
            } else {
                done(null, false);
            }
        })
    );
    
    app.get(
        '/login',
        (request, response) => response.send(authView.login(request.query.error))
    );
    
    app.post(
        '/login',
        passport.authenticate('local', { failureRedirect: '/login?error=Notallowed' }),
        (request, response) => {
            
            response.redirect(
                request.session.returnTo ? request.session.returnTo : '/'
            );
        }
    );
    
    app.get(
        '/logout',
        (request, response) => {
            request.logout();
            response.redirect('/');
        }
    );

};
