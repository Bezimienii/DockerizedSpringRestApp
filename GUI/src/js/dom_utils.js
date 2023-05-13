/*Author: Michał Wójcik, Gdańsk University of Technology Lecturer*/
/**
 * Clears all children of the provided element
 *
 * @param {HTMLElement} element parent element
 */
export function clearElementChildren(element) {
    console.log(element)
    while (element.firstChild) {
        element.removeChild(element.firstChild);
    }
}

/**
 * Create new table cell with button with assigned action.
 *
 * @param {string} text text to be displayed on button
 * @param {function} action function to be executed on button click
 * @returns {HTMLTableDataCellElement} table cell with action button
 */
export function createButtonCell(text, action) {
    const td = document.createElement('td');
    const button = document.createElement('button');
    button.appendChild(document.createTextNode(text));
    button.classList.add('ui-control', 'ui-button');
    td.appendChild(button);
    button.addEventListener('click', action);
    return td;
}

/**
 * Create new table cell with hyperlink.
 *
 * @param {string} text text to be displayed on link
 * @param {string} url link url
 */
export function createLinkCell(text, url) {
    const td = document.createElement('td');
    const a = document.createElement('a');
    a.appendChild(document.createTextNode(text));
    a.href = url;
    td.appendChild(a);
    return td;
}

/**
 * Create new table cell with image.
 *
 * @param {string} url link url
 */

/**
 * Create new table cell with text.
 *
 * @param {string} text text to be displayed
 */
export function createTextCell(text) {
    const td = document.createElement('td');
    td.appendChild(document.createTextNode(text));
    return td;
}

/**
 * Returns value for query param.
 *
 * @param {string} name name of the query param
 * @returns {string} query param value
 */
export function getParameterByName(name) {
    return new URLSearchParams(window.location.search).get(name);
}

/**
 * Removes all children for selected element and adds new text node.
 *
 * @param id element id
 * @param {string} text text used to create text node
 */
export function setTextNode(id, text) {
    let element = document.getElementById(id);
    clearElementChildren(element);
    element.appendChild(document.createTextNode(text));
}
