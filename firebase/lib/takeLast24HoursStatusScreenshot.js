'use strict';

const fs = require('fs');
const chrome = require('selenium-webdriver/chrome');
const webdriver = require('selenium-webdriver');

const By = webdriver.By;
const until = webdriver.until;

const emailInput = By.id('identifierId');
const emailNext = By.id('identifierNext');
const passwordInput = By.name('password');
const passwordNext = By.id('passwordNext');
const loadGraph = By.css('gviz-linechart')

const delay = millis=>new Promise(resolve=>setTimeout(resolve,millis));

function takeLast24HoursStatusScreenshot (projectId, email, password, filename) {
    if (!projectId || !email || !password || !filename) {
        throw new TypeError(`Null argument(s). projectId:${!!projectId} email:${!!email} password:${!!password} filename:${!!filename}`);
    }

    const url = `https://console.firebase.google.com/project/${projectId}/database/usage/last-24h/load`;

    const options = new chrome.Options();
    options.addArguments('headless', 'disable-gpu');

    const driver = new webdriver.Builder().forBrowser('chrome').setChromeOptions(options).build();
    driver.manage().window().setSize(1280, 720);
    driver.get(url);

    googleAuth(driver, email, password)
    // wait for dashboard page loading
    .then(()=>driver.wait(until.elementLocated(loadGraph)))
    .then(()=>delay(1000))
    // take screenshot
    .then(()=>{
        console.log('Take screenshot!');
        return driver.takeScreenshot();
    })
    .then(v=>{
        return new Promise(resolve=>{
            console.log('Write load graph to', filename);
            fs.writeFile(filename,
                    v.replace(/^data:image\/png;base64,/, ""),
                    'base64',
                    _=>resolve(filename));
        });
    })
    .catch(console.error)
    // quit
    .then(()=>{
        console.log('Quit WebDriver');
        driver.quit();
    });
}

function googleAuth(driver, email, password) {
    driver.findElement(emailInput).sendKeys(email);
    driver.findElement(emailNext).click();

    return driver.wait(until.elementLocated(passwordInput))
    .then(()=>driver.wait(until.elementIsVisible(driver.findElement(passwordInput))))
    .then(el=>el.sendKeys(password))
    .then(()=>driver.wait(until.elementLocated(passwordNext)))
    .then(()=>driver.wait(until.elementIsVisible(driver.findElement(passwordNext))))
    .then(()=>delay(500))
    .then(()=>driver.actions().click(driver.findElement(passwordNext)).perform())
}

module.exports = takeLast24HoursStatusScreenshot;

