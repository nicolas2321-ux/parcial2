import React, { useState } from 'react';
import { Form, FormGroup, FormControl, Button } from 'react-bootstrap';
import { register } from '../services/login';
import Swal from 'sweetalert2'
import { useNavigate } from 'react-router-dom';
const LoginRegisterForm = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [name, setName] = useState('');
  const [nombre, setnombre] = useState('');
  const navigate = useNavigate();
  const handleLogin = (e) => {
    e.preventDefault();
    // Aquí puedes implementar la lógica para iniciar sesión
    console.log('Iniciar sesión:', email, password);
  };

  const handleRegister = async(e) => {
    e.preventDefault();
    const object = {
        username: name,
        email: email,
        password: password,
        nombre: nombre
    }
    const res = await register(object)
    const result = (await res.status)
    console.log(res)
    if(result === 200){

          
              Swal.fire(
                'Accion realizada con exito',
                'Usuario registrado con exito',
                'success'
              )
                navigate("/")
    }else{
        Swal.fire(
            'Accion no pudo completarse',
            'Ocurrio un error',
            'error'
          )
    }
    
  };

  return (
    <div style={{ backgroundColor: '#f8f9fa', minHeight: '100vh', display: 'flex', justifyContent: 'center', alignItems: 'center' }}>
      <Form style={{ maxWidth: '400px', padding: '20px', borderRadius: '5px', backgroundColor: '#8453cf' }}>
        <FormGroup controlId="formName">
          <Form.Label>Username</Form.Label>
          <FormControl
            type="text"
            placeholder="Ingresa tu nombre"
            value={name}
            onChange={(e) => setName(e.target.value)}
          />
        </FormGroup>

        <FormGroup controlId="formEmail">
          <Form.Label>Email</Form.Label>
          <FormControl
            type="email"
            placeholder="Ingresa tu email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
          />
        </FormGroup>

        <FormGroup controlId="formPassword">
          <Form.Label>Contraseña</Form.Label>
          <FormControl
            type="password"
            placeholder="Ingresa tu contraseña"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />
        </FormGroup>

        <FormGroup controlId="formPhone">
          <Form.Label>Nombre</Form.Label>
          <FormControl
            type="text"
            placeholder="Ingresa tu teléfono"
            value={nombre}
            onChange={(e) => setnombre(e.target.value)}
          />
        </FormGroup>

        <Button variant="primary" type="submit" className='m-4' onClick={handleRegister}>
          Registrarse
        </Button>
        <Button variant="primary" type="submit" className='m-4' href='/'>
          Iniciar sesión
        </Button>
      </Form>
    </div>
  );
};

export default LoginRegisterForm;
