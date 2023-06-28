const BASE_URL = 'http://localhost:8080/'



export const login = async(data) => {
    const response = await fetch(`${BASE_URL}auth/login`,{
        "method": "POST",
        headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify({
            identifier: data.identifier,
            password: data.password
           

        })
    })
    const respuesta = await response;
    return respuesta
}

export const register = async(data) => {
    const response = await fetch(`${BASE_URL}auth/signup`,{
        "method": "POST",
        headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify({
            username: data.username,
            email: data.email,
            password: data.password,
            nombre: data.nombre
           

        })
    })
    const respuesta = await response;
    return respuesta
}