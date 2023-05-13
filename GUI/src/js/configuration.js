export function getBackendUrl() {
    return "http://localhost:8080"
}

export async function createFetch(url, methode, data= {})
{
    const response = await fetch(url, {
        method: methode, // *GET, POST, PUT, DELETE, etc.
        mode: 'cors', // no-cors, *cors, same-origin
        headers: {
          'Content-Type': 'application/json',
          'Access-Control-Allow-Origin':'*',
          'Access-Control-Allow-Credentials': 'true'
          // 'Content-Type': 'application/x-www-form-urlencoded',
        }
      });
      if(methode == "DELETE")
      {
        return response.text();
      }
      else
      {
      return response.json();
      }
}

export async function forRest(url, methode, data= {})
{
    const response = await fetch(url, {
        method: methode, // *GET, POST, PUT, DELETE, etc.
        mode: 'cors', // no-cors, *cors, same-origin
        cache: 'no-cache', // *default, no-cache, reload, force-cache, only-if-cached
        credentials: 'same-origin', // include, *same-origin, omit
        headers: {
          'Content-Type': 'application/json',
          'Access-Control-Allow-Origin':'*'
          // 'Content-Type': 'application/x-www-form-urlencoded',
        },
        redirect: 'follow', // manual, *follow, error
        referrerPolicy: 'no-referrer', // no-referrer, *no-referrer-when-downgrade, origin, origin-when-cross-origin, same-origin, strict-origin, strict-origin-when-cross-origin, unsafe-url
        body: JSON.stringify({data}) // body data type must match "Content-Type" header
      });
      return response.json();
}
