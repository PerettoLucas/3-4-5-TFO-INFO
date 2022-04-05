const { v4: uuid } = require('uuid');
const mysql = require('mysql');
const e = require('express');
const { request } = require('express');
let data = [];

const connectionProperties = {
    host: 'localhost',
    user: 'root',
    password: '',
    database: 'movie-db'
};

class Database {
    constructor(connectionProps) {
        this.connection = mysql.createConnection(connectionProps);
    }

    query(sql, params) {
        return new Promise((resolve, reject) => {
            this.connection.query(sql, params, (error, result) => {
                if(error) { reject(error); }
                resolve(result);
            });
        });
    }

    queryClose(sql, params) {
        const ret = this.query(sql, params);
        this.close();
        return ret;
    }

    close() {
        return new Promise((resolve, reject) => {
            this.connection.end(error => {
                if(error) { rejec(error) }
                resolve();
            })
        });
    }
}

async function getAllUsersDB() {
    return new Promise((resolve, reject) => {
        const db = new Database(connectionProperties);
    
        try {
            const sql2 = `
            SELECT * FROM users WHERE 1   
            `;
            
            db.query(sql2).then(result => {
                resolve(result);
            });


        } catch (error) {
            reject(error);
        }

    });
    
}

async function getMovieByID(id) {
    return new Promise((resolve, reject) => {

        const db = new Database(connectionProperties);
    
        try {
            const sql2 = `
                SELECT * FROM \`movies\` WHERE movies.id = '${id}'   
            `;
            
            db.query(sql2).then(result => {
                resolve(result[0]);
            });


        } catch (error) {
            reject(error);
        }


    });
}

async function updateMovieDB(movie) {
    return new Promise((resolve, reject) => {
        const db = new Database(connectionProperties);
        
        try {

            const sql2 = `
                UPDATE \`movies\` SET \`title\` = '${movie.title}', \`year\` = '${movie.year}', \`published\` = '${movie.public}' WHERE \`movies\`.\`id\` = ${movie.id};     
            `;
            
            db.query(sql2).then(result => {
                resolve(result);
            });

        } catch(error) {
            reject(error);
        }      
        
    });
}

async function getAllMoviesDB() {
    return new Promise((resolve, reject) => {
        const db = new Database(connectionProperties);

        try {
            const sql2 = `
                SELECT * FROM movies WHERE 1   
            `;
            
            db.query(sql2).then(result => {
                let tempArr = [];
                result.map(rawData => {
                    tempArr.push({
                        id: rawData.id,
                        title: rawData.title,
                        year: rawData.year,
                        public: rawData.published,
                        user: rawData.owner
                    });
                });
                data = tempArr;
                resolve(data);
               });

        } catch (error) {
            reject(error);
        }

    });
    
}

async function getAllUsernamesDB() {
    return new Promise((resolve, reject) => {

        const db = new Database(connectionProperties);

        try {
            
            const sql2 = `
                SELECT id, username FROM users WHERE 1    
            `;
            
            db.query(sql2).then(result => {
                resolve(result);
            });

        } catch (error) {
            reject(error);
        }
    });
}

async function saveNewMovie(title, date, public, user) {
    return new Promise((resolve, reject) => {

        const db = new Database(connectionProperties);
        
        try {

            const sql2 = `
                INSERT INTO \`movies\` (\`id\`, \`title\`, \`year\`, \`published\`, \`owner\`) VALUES (NULL, '${title}', '${date}', '${public}', '${user.id}');    
            `;
            
            db.query(sql2).then(result => {
                resolve(result);
            });

        } catch(error) {
            reject(error);
        }      
        
    });

}

function save(movie, user) {
    if (movie.id === '-1') {
        saveNewMovie(movie.title, movie.year, movie.public, user);
        data.push(movie);
    } else {
        data = data.map(item => item.id === movie.id ? movie : item);
    }
}

async function deleteMovie(id) {
    return new Promise((resolve, reject) => {
        const db = new Database(connectionProperties);

        const sql2 = `
            DELETE FROM \`movies\` WHERE \`movies\`.\`id\` = '${id}' 
        `;

        db.query(sql2).then(result => {
            resolve(result);
        });

    });
}

async function remove(id) {
    await deleteMovie(id).then().catch();
    data = data.filter(movie => movie.id !== id);
}


module.exports = { remove, save, getAllUsersDB, connectionProperties, getAllMoviesDB, getAllUsernamesDB, getMovieByID, updateMovieDB };