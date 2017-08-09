var exec = require('cordova/exec');

var readCard = function( success, error) {
    exec(success, error, "idcard", "read", []);
};
module.exports = {readCard:readCard};