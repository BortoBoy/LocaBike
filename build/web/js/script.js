function cidadeescolhida(cidade, path)
{
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.onreadystatechange = function() { 
        if (xmlHttp.readyState === 4 && xmlHttp.status === 200){
            callback(xmlHttp.responseText);
        }
    }
    xmlHttp.open( "GET", path+ "?option=" +cidade, false ); // false for synchronous request
    xmlHttp.send( null );
    }