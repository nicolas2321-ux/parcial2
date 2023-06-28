const BASE_URL = 'http://localhost:8080/'



export const getSongs = async(data) => {
    
    const response = await fetch(`${BASE_URL}song?titleFragment=${data.cancion}`,{
        "method": "GET",
        headers: {
            "Authorization": `Bearer ${data.token}`,
            "Content-Type": "application/json"
          },
         
    })
    const respuesta = await response;
    return respuesta
}
export const setSongs = async(data) => {
    
    const response = await fetch(`${BASE_URL}api/newSongPlaylist?playlistCode=${data.idPlaylist}`,{
        "method": "POST",
        headers: {
            "Authorization": `Bearer ${data.token}`,
            "Content-Type": "application/json"
          }, 
          body: JSON.stringify({
            code: data.code,
            fecha: data.fecha
            
 
         })
         
    })
    const respuesta = await response;
    return respuesta
}
export const songsInPlaylist = async(data) => {
    
    const response = await fetch(`${BASE_URL}api/songsplay/${data.idPlaylist}`,{
        "method": "GET",
        headers: {
            "Authorization": `Bearer ${data.token}`,
            "Content-Type": "application/json"
          }
         
    })
    const respuesta = await response;
    return respuesta
}

export const inserPlaylist = async(data) => {
    console.log(data)
    const response = await fetch(`${BASE_URL}playlist/`,{
        "method": "POST",
        headers: {
            "Authorization": `Bearer ${data.token}`,
            "Content-Type": "application/json"
          },
          body: JSON.stringify({
           title: data.title,
           description: data.descripcion
           

        })
          
         
    })
    const respuesta = await response;
    return respuesta
}

export const buscarPlaylist = async(data) => {
    console.log(data)
    const response = await fetch(`${BASE_URL}auth/playlist?title=${data.title}`,{
        "method": "POST",
        headers: {
            "Authorization": `Bearer ${data.token}`,
            "Content-Type": "application/json"
          }
    })
    const respuesta = await response;
    return respuesta
}

