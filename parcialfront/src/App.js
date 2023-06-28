import logo from './logo.svg';
import 'bootstrap/dist/css/bootstrap.min.css';
import './App.css';
import { Route, Routes } from 'react-router-dom';
import { Login } from './pages/login';
import LoginRegisterForm from './pages/register';
import Home from './pages/home';

function App() {
  return (
   <Routes>
    <Route path='/' element={<Login></Login>}/>
    <Route path='/register' element={<LoginRegisterForm></LoginRegisterForm>}/>
    <Route path='/home' element={<Home></Home>}/>
   </Routes>
  );
}

export default App;
