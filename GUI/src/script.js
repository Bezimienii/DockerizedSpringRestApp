import {clearElementChildren, createLinkCell, createTextCell} from 'js/dom_utils.js';
import {createFetch} from 'js/configuration.js';

window.addEventListener('load', () => {
    fetchAndDisplayFiles();
});

function fetchAndDisplayFiles()
{
   createFetch("http://localhost:8080/api/files", "GET").then((data)=>displayFiles(data)) ;
}

function displayFiles(files)
{
    let tableBody = document.getElementById('tableBody');
    console.log(files);
    clearElementChildren(tableBody);
    files.files.forEach(file => {
        tableBody.appendChild(createTableRow(file));
    })
}

function createTableRow(file) {
    let tr = document.createElement('tr');
    tr.appendChild(createTextCell(file.name));
    tr.appendChild(createLinkCell('view', '../files/' + file.name));
    return tr;
}

function postFile()
{
    let file = document.getElementById('upload').files[0];
    const postData = new FormData();
    postData.append('file_upload', file, file.name);

    fetch('http://localhost:8080/api/files/'+file.name, {
        method: "POST",
        body: postData,
    })
    .then(res => res.json())
    .then(data => console.log(data))
    .catch(err => console.log(err));
}
