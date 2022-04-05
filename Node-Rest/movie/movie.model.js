const { v4: uuid } = require('uuid');
const mysql = require('mysql');
const e = require('express');
const { request } = require('express');
const { use } = require('./movie.router');
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
                if(error) { reject(error) }
                resolve();
            })
        });
    }
}

async function getAll(sort = null, username = null) {
  //Returns all movies
  const sql = `
    SELECT m.id, title,year,published,CONCAT(u.firstname, ' ', u.secondname) as fullname, owner
    FROM movies m, users u
    WHERE m.owner = u.id
    ${
        username
        ? `AND (u.username = '${username}' OR published = true)`
        : `AND published = true`
    }
    ORDER BY id ${!sort || sort === "asc" ? "ASC" : "DESC"};
  `;
  try {
    const database = new Database(connectionProperties);
    const result = await database.queryClose(sql, [username]);
    return result.length === 0
      ? Promise.reject("No movies found")
      : Promise.resolve(result);
  } catch (error) {
    return Promise.reject("Database error");
  }
}

async function get(id, username) {
  if (!username) {
    return Promise.reject("User not set");
  } else {
    try {
      const database = new Database(connectionProperties);
      const sql = `
        SELECT m.id, title,year,published,CONCAT(u.firstname, ' ', u.secondname) as fullname, owner
        FROM movies m, users u
        WHERE m.owner = u.id 
        AND m.id = ${id}
        ${
          username
            ? `AND (u.username = '${username}' OR published = true)`
            : `AND published = true`
        }
    `;

      const result = await database.queryClose(sql, [id, username]);
      if (result.length === 0) {
        return Promise.reject("Movie not found");
      } else {
        return Promise.resolve(result[0]);
      }
    } catch (error) {
      return Promise.reject("Database error");
    }
  }
}

async function remove(id, username) {
    if (!username) {
      return Promise.reject("User not set");
    } else {
      try {
        const database = new Database(connectionProperties);
        const sql = `
            DELETE movies 
            FROM movies, users 
            WHERE movies.id = ${id} 
            AND users.username = '${username}' 
        `;
        const result = await database.queryClose(sql, [id, username]);
        if (result.affectedRows === 0) {
          return Promise.reject("Movie not found");
        } else if (result.affectedRows === 1) {
          return Promise.resolve("");
        }
      } catch (error) {
        return Promise.reject("Database error");
      }
    }
}

async function removeAll(username) {
  if (!username) {
    return Promise.reject("User not set");
  } else {
    try {
      const database = new Database(connectionProperties);


      const sql = `SELECT users.id FROM users WHERE users.username = '${username}'`;
      const id = await database.query(sql, [username]);

      const sql2 = `
                DELETE movies FROM movies, users WHERE (movies.owner = ${id[0].id} AND users.username = '${username}') 
            `;
      const result2 = await database.queryClose(sql2, [username]);
      
      if(result2.affectedRows > 0) {
        return Promise.resolve("");
      } else {
        return Promise.reject("No Movies Found");
      }
    } catch (error) {
      return Promise.reject("Database error");
    }
  }
}

async function newMovie(title, year, published, username) {
  if (!username) {
    return Promise.reject("User not set");
  } else {
    try {
        
        exists = await checkTitleExists(title);

        if(exists) {
            return Promise.reject("Title Exists");
        }

        const database = new Database(connectionProperties);
        
        const sql2 = `SELECT users.id FROM users WHERE users.username = '${username}'`;
        const owner = await database.query(sql2, [username]);
        
        const sql = `
                INSERT INTO movies (id, title, year, published, owner) 
                VALUES (null, '${title}', ${year}, ${published}, ${owner[0].id}); 
            `;
        const result = await database.queryClose(sql, [title, year, published, owner]);



        return Promise.resolve({
            "id" : result.insertId,
            "title" : title
        });
    } catch (error) {
      return Promise.reject("Database error");
    }
  }
}

async function checkTitleExists(title) {
    try {
      const database = new Database(connectionProperties);
      const sql = `
            SELECT * FROM movies 
            WHERE movies.title = '${title}' 
        `;
      const result = await database.queryClose(sql, [title]);

      if(result[0]?.id === null || result[0]?.id == undefined) {
        return Promise.resolve(false);
      } else {
        return Promise.resolve(true);
      }
    
    } catch (error) {
      return Promise.reject("Database error");
    }
}

async function updateMovie(id, username, title = null, year = null, published = null) {
  if (!username) {
    return Promise.reject("User not set");
  } else { 
  try {
      const database = new Database(connectionProperties);


      const sql = `SELECT users.id FROM users WHERE users.username = '${username}'`;
      const owner = await database.query(sql, [username]);

      const sql2 = `
            UPDATE movies 
              SET ${title ? `title = '${title}',` : '' } ${year ? `year = ${year},` : '' } ${published != null ? `published = ${published},` : '' } owner = ${owner[0].id} 
              WHERE movies.id = ${id};  
        `;

      const result = await database.queryClose(sql2, [id, title, year, published, owner[0].id]);
      
      if (result.affectedRows > 0) {
        return Promise.resolve("");
      } else {
        return Promise.reject("No Movie Found");
      }
    
    } catch (error) {
      return Promise.reject("Database error");
    }
  }
}

module.exports = { connectionProperties, getAll, get, remove, newMovie, removeAll, updateMovie };