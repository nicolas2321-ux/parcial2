import React, { useState } from 'react';
import { Form, FormGroup, FormControl, Button } from 'react-bootstrap';
import { login } from '../services/login';
import { useNavigate } from 'react-router-dom';
import Swal from 'sweetalert2'
export function Login() {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const navigate = useNavigate();
  const handleLogin = async(e) => {
    e.preventDefault();
    const object = {
        identifier: email,
        password: password
    }
    const res = await login(object)
   const response = (await res)
   const token = await response.json()
   
   if(response.status === 200){
    Swal.fire(
      'Inicio de sesion correcto',
      '',
      'success'
    )
    localStorage.setItem('item', token.content)
    navigate("/home")
   }else{
    Swal.fire(
      'Credenciales invalidas',
      '',
      'error'
    )
   }
    
  };

  const handleRegister = (e) => {
    e.preventDefault();
    // Aquí puedes implementar la lógica para registrar un nuevo usuario
    console.log('Registrarse:', email, password);
  };

  return (
    <div style={{ backgroundColor: '#f8f9fa', minHeight: '100vh', display: 'flex', justifyContent: 'center', alignItems: 'center' }}>
      <Form style={{ maxWidth: '400px', padding: '20px', borderRadius: '5px', backgroundColor: '#8453cf' }}>
        <FormGroup controlId="formEmail">
          <Form.Label>Email/Usuario</Form.Label>
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

        <Button variant="primary" type="submit" className='m-4' onClick={handleLogin}>
          Iniciar sesión
        </Button>

        <Button variant="primary" type="submit" href='/register'>
          Registrarse
        </Button>
      </Form>
    </div>
  );
};


