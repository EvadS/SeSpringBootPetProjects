'use strict';

var singleUploadForm = document.querySelector('#singleUploadForm');
var singleFileUploadInput = document.querySelector('#singleFileUploadInput');
var singleFileUploadError = document.querySelector('#singleFileUploadError');
var singleFileUploadSuccess = document.querySelector('#singleFileUploadSuccess');

var singleUploadForm2 = document.querySelector('#singleUploadForm2');
var singleFileUploadInput2 = document.querySelector('#singleFileUploadInput2');
var singleFileUploadError2 = document.querySelector('#singleFileUploadError2');
var singleFileUploadSuccess2 = document.querySelector('#singleFileUploadSuccess2');
//------------------------------------------------------------------------------------------
var singleUploadForm3 = document.querySelector('#singleUploadForm3');
var singleFileUploadInput3 = document.querySelector('#singleFileUploadInput3');
var singleFileUploadError3 = document.querySelector('#singleFileUploadError3');
var singleFileUploadSuccess3 = document.querySelector('#singleFileUploadSuccess3');

///---------------------------------------------------------------------------------------
var multipleUploadForm = document.querySelector('#multipleUploadForm');
var multipleFileUploadInput = document.querySelector('#multipleFileUploadInput');
var multipleFileUploadError = document.querySelector('#multipleFileUploadError');
var multipleFileUploadSuccess = document.querySelector('#multipleFileUploadSuccess');

function uploadSingleFile(file) {
    var formData = new FormData();
    formData.append("file", file);

    var xhr = new XMLHttpRequest();
    xhr.open("POST", "/uploadFile");

    xhr.onload = function() {
        console.log(xhr.responseText);
        var response = JSON.parse(xhr.responseText);
        if(xhr.status == 200) {
            singleFileUploadError.style.display = "none";
            singleFileUploadSuccess.innerHTML = "<p>File Uploaded Successfully.</p><p>DownloadUrl : <a href='" + response.fileDownloadUri + "' target='_blank'>" + response.fileDownloadUri + "</a></p>";
            singleFileUploadSuccess.style.display = "block";
        } else {
            singleFileUploadSuccess.style.display = "none";
            singleFileUploadError.innerHTML = (response && response.message) || "Some Error Occurred";
        }
    }

    xhr.send(formData);
}
//------------------------------------------------------

function uploadSingleFile2(file) {
    var formData = new FormData();
    formData.append("file", file);

    var xhr = new XMLHttpRequest();
    xhr.open("POST", "/upload-valid-file");

    xhr.onload = function() {
        console.log(xhr.responseText);
        var response = JSON.parse(xhr.responseText);
        if(xhr.status == 200) {
            singleFileUploadError2.style.display = "none";
            singleFileUploadSuccess2.innerHTML = "<p>File Uploaded Successfully.</p><p>DownloadUrl : <a href='" + response.fileDownloadUri + "' target='_blank'>" + response.fileDownloadUri + "</a></p>";
            singleFileUploadSuccess2.style.display = "block";
        } else {
            singleFileUploadSuccess2.style.display = "none";
            singleFileUploadError2.innerHTML = (response && response.message) || "Some Error Occurred";
        }
    }

    xhr.send(formData);
}
//--------------------------------

//------------------------------------------------------

function uploadSingleFile3(file) {
    var formData = new FormData();
    formData.append("file", file);

    var xhr = new XMLHttpRequest();
    xhr.open("POST", "/upload-valid-file-model");

    xhr.onload = function() {
        console.log(xhr.responseText);
        var response = JSON.parse(xhr.responseText);
        if(xhr.status == 200) {
            singleFileUploadError3.style.display = "none";
            singleFileUploadSuccess3.innerHTML = "<p>File Uploaded Successfully.</p><p>DownloadUrl : <a href='" + response.fileDownloadUri + "' target='_blank'>" + response.fileDownloadUri + "</a></p>";
            singleFileUploadSuccess3.style.display = "block";
        } else {
            singleFileUploadSuccess3.style.display = "none";
            singleFileUploadError3.innerHTML = (response && response.message) || "Some Error Occurred";
        }
    }

    xhr.send(formData);
}

function uploadMultipleFiles(files) {
    var formData = new FormData();
    for(var index = 0; index < files.length; index++) {
        formData.append("files", files[index]);
    }

    var xhr = new XMLHttpRequest();
    xhr.open("POST", "/uploadMultipleFiles");

    xhr.onload = function() {
        console.log(xhr.responseText);
        var response = JSON.parse(xhr.responseText);
        if(xhr.status == 200) {
            multipleFileUploadError.style.display = "none";
            var content = "<p>All Files Uploaded Successfully</p>";
            for(var i = 0; i < response.length; i++) {
                content += "<p>DownloadUrl : <a href='" + response[i].fileDownloadUri + "' target='_blank'>" + response[i].fileDownloadUri + "</a></p>";
            }
            multipleFileUploadSuccess.innerHTML = content;
            multipleFileUploadSuccess.style.display = "block";
        } else {
            multipleFileUploadSuccess.style.display = "none";
            multipleFileUploadError.innerHTML = (response && response.message) || "Some Error Occurred";
        }
    }

    xhr.send(formData);
}

singleUploadForm.addEventListener('submit', function(event){
    var files = singleFileUploadInput.files;
    if(files.length === 0) {
        singleFileUploadError.innerHTML = "Please select a file";
        singleFileUploadError.style.display = "block";
    }
    uploadSingleFile(files[0]);
    event.preventDefault();
}, true);

singleUploadForm2.addEventListener('submit', function(event){
    var files = singleFileUploadInput2.files;
    if(files.length === 0) {
        singleFileUploadError2.innerHTML = "Please select a file";
        singleFileUploadError2.style.display = "block";
    }
    uploadSingleFile2(files[0]);
    event.preventDefault();
}, true);

singleUploadForm3.addEventListener('submit', function(event){
    var files = singleFileUploadInput3.files;
    if(files.length === 0) {
        singleFileUploadError3.innerHTML = "Please select a file";
        singleFileUploadError3.style.display = "block";
    }
    uploadSingleFile2(files[0]);
    event.preventDefault();
}, true);


multipleUploadForm.addEventListener('submit', function(event){
    var files = multipleFileUploadInput.files;
    if(files.length === 0) {
        multipleFileUploadError.innerHTML = "Please select at least one file";
        multipleFileUploadError.style.display = "block";
    }
    uploadMultipleFiles(files);
    event.preventDefault();
}, true);

