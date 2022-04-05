const { getAllUsersDB } = require('../movie/movie.model');

async function get(username) {
    return new Promise(async (resolve) => {
        await getAllUsersDB().then(result => {
            let tempArr = [];
            result.map(rawData => {
                tempArr.push({
                    id: rawData.id,
                    username: rawData.username,
                    password: rawData.passwordhash,
                    firstname: rawData.firstname,
                    secondname: rawData.secondname
                });
            });
            
            const user = tempArr.find(user => user.username === username);
            resolve(user ? Object.assign({}, user) : null);
        });
        
    });
    
}
module.exports = { get };