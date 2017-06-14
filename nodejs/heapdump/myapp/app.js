var express = require('express');
var path = require('path');
var favicon = require('serve-favicon');
var logger = require('morgan');
var cookieParser = require('cookie-parser');
var bodyParser = require('body-parser');

var index = require('./routes/index');
var users = require('./routes/users');

var app = express();

// view engine setup
app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'pug');

// uncomment after placing your favicon in /public
//app.use(favicon(path.join(__dirname, 'public', 'favicon.ico')));
app.use(logger('dev'));
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: false }));
app.use(cookieParser());
app.use(express.static(path.join(__dirname, 'public')));

app.use('/', index);
app.use('/users', users);

var heapdump = require('heapdump');
var memoryLeak = [];

function LeakedObject(){};

app.use('/leak', function(req, res, next) {
    for (var i=0; i<1000; i++) {
        memoryLeak.push(new LeakedObject());
    }
    res.send('making memory leak. Current memory usage :'
            +(process.memoryUsage().rss / 1024 / 1024) + 'MB');
});

app.use('/heapdump', function(req, res, next) {
    var filename = '/home/jhkang/heapdump' + Date.now() + '.heapsnapshot';
    heapdump.writeSnapshot(filename);
    res.send('Heapdump has been generated in '+filename);
});

// catch 404 and forward to error handler
app.use(function(req, res, next) {
  var err = new Error('Not Found');
  err.status = 404;
  next(err);
});

// error handler
app.use(function(err, req, res, next) {
  // set locals, only providing error in development
  res.locals.message = err.message;
  res.locals.error = req.app.get('env') === 'development' ? err : {};

  // render the error page
  res.status(err.status || 500);
  res.render('error');
});

module.exports = app;
